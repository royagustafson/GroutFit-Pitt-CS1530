package com.GroutFit;

import com.GroutFit.Helper.JsonTransformer;
import com.GroutFit.Model.ClothingItem;
import com.GroutFit.Model.ClothingType;
import com.GroutFit.Model.Outfit;
import com.GroutFit.Model.Profile;
import com.google.gson.JsonObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.service.spi.ServiceException;

import javax.persistence.criteria.CriteriaQuery;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class GroutFitApp {

    public static void main(String[] args) {

        // Set up hibernate
        SessionFactory sf = setUp();
        assert sf != null;
        AtomicReference<Session> session = new AtomicReference<>();
        session.set(sf.openSession());

        // Load static DHTML, create basic login table
        staticFiles.location("public");
        HashSet<String> loginTable = new HashSet<>();

        // Set the content type to json, open a new database session
        before("/api/*", (req, res) -> {
            res.type("application/json");
        });

        // Verify login
        // NOTE: security is TERRIBLE, but allows for proper security later on
        before("/api/auth/*", (req, res) -> {
            if (!loginTable.contains(toMap(req.body()).get("username")))
                halt(401, "\"User not logged in\"");
        });

        /* API calls */
        path("/api", () -> {
            /* SEARCH FUNCTIONS */
            post("/search", (req, res) -> {
                HashMap<String, String> map = toMap(req.body());
                String searchTerm = map.get("query");

                FullTextSession fullTextSession = Search.getFullTextSession(session.get());
                QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(ClothingType.class).get();
                org.apache.lucene.search.Query query = qb.keyword().onFields("name", "category", "description").matching(searchTerm).createQuery();

                javax.persistence.Query jpaQuery = fullTextSession.createFullTextQuery(query, ClothingType.class);

                List<JsonObject> json = new ArrayList<>();
                List results = jpaQuery.getResultList();
                for (Object result : results) {
                    json.add(((ClothingType) result).toJson());
                }
                return json;
            }, new JsonTransformer());

            /* USER FUNCTIONS */
            post("/register", (req, res) -> {
                HashMap<String, String> params = toMap(req.body());

                // Unique email
                if (session.get().get(Profile.class, params.get("username")) != null)
                    halt("\"Invalid username\"");

                Profile pro = Profile.register(params);

                // Save the new user
                save(session, pro);
                session.set(sf.openSession());

                return "\"Success\"";
            });

            // Login the user for auth privileges
            post("/login", (req, res) -> {
                HashMap<String, String> params = toMap(req.body());

                if (loginTable.contains(params.get("username")))
                    return "\"User is already logged in\"";

                Profile user = session.get().get(Profile.class, params.get("username"));

                if (user == null)
                    halt(401, "\"Invalid username\"");
                if (!user.login(params.get("password")))
                    halt(401, "\"Invalid password\"");

                // Save the login
                loginTable.add(user.getEmail());

                return "\"Success\"";
            });

            // For all paths that require the user to be logged in
            path("/auth", () -> {

                /* WISHLIST FUNCTIONS */
                // Get wishlist as json list
                post("/wishlist", (req, res) -> {
                    ArrayList<JsonObject> jsonObjects = new ArrayList<>();
                    for (ClothingItem clothingItem : session.get().get(Profile.class, toMap(req.body()).get("username"))
                            .getWishlist()) {
                        JsonObject toJson = clothingItem.toJson();
                        jsonObjects.add(toJson);
                    }
                    return jsonObjects;              // collect in list
                }, new JsonTransformer());

                // Add, delete, clear
                post("/wishlist/:function", (req, res) -> {
                    HashMap<String, String> params = toMap(req.body());
                    Profile pro = session.get().get(Profile.class, params.get("username"));

                    // Determine function to apply
                    Consumer<ClothingItem> applyFunction = null;
                    switch (req.params("function")) {
                        case "add":
                            applyFunction = pro.getWishlist()::add;
                            break;
                        case "delete":
                            applyFunction = pro.getWishlist()::remove;
                            break;
                        case "clear":
                            pro.getWishlist().clear();
                            return "\"Success\"";
                        default:
                            halt(404, "\"Invalid function\"");
                    }

                    // Stream all ID's that correspond to non null ClothingItem, apply function
                    parseIDs(params.get("wishlist_id")).stream()
                            .map(item_id -> session.get().get(ClothingItem.class, item_id))
                            .filter(Objects::nonNull)
                            .forEach(applyFunction);

                    // Save updated profile
                    update(session, pro);
                    session.set(sf.openSession());
                    return "\"Success\"";
                });

                /* OUTFIT FUNCTIONS */
                // Render outfits
                post("/outfits", (req, res) -> {
                    HashMap<String, String> params = toMap(req.body());
                    Profile pro = session.get().get(Profile.class, params.get("username"));
                    List<JsonObject> json = new ArrayList<>();

                    // Assemble outfits
                    if (params.containsKey("outfit_id")) {
                        // For requested outfits
                        json = parseIDs(params.get("outfit_id")).stream()
                                .map(outfit_id -> session.get().get(Outfit.class, outfit_id))
                                .filter(Objects::nonNull)
                                .map(Outfit::toJson)
                                .collect(Collectors.toList());
                    } else {
                        // For all outfits
                        for (Outfit outfit : pro.getOutfits())
                            json.add(outfit.toJson());
                    }

                    // For all outfits

                    return json;
                }, new JsonTransformer());

                // Add a single outfit
                post("/outfits/add", (req, res) -> {
                    HashMap<String, String> params = toMap(req.body());

                    // Params are all fields of "outfit" except outfit_id (will be generated)
                    // username, full_body, top_id, bottom_id, jacket_id
                    Outfit out = Outfit.build(session.get(), params);

                    // Save the new outfit
                    save(session, out);
                    session.set(sf.openSession());

                    // Return new ID
                    return "\"" + out.getOutfit_id() + "\"";
                });

                // Remove outfits in batch
                post("/outfits/delete", (req, res) -> {
                    HashMap<String, String> params = toMap(req.body());
                    Profile pro = session.get().get(Profile.class, params.get("username"));

                    // Stream all ID's that correspond to non null Outfit, apply function
                    parseIDs(params.get("outfit_id")).stream()
                            .map(item_id -> session.get().get(Outfit.class, item_id))
                            .filter(Objects::nonNull)
                            .filter(outfit -> !outfit.getProfile().equals(pro))
                            .forEach(outfit -> pro.getOutfits().remove(outfit));

                    update(session, pro);
                    session.set(sf.openSession());
                    return "\"Success\"";
                });

                // Remove all outfits by hand, then clear profile
                post("/outfits/clear", (req, res) -> {
                    HashMap<String, String> params = toMap(req.body());
                    Profile pro = session.get().get(Profile.class, params.get("username"));

                    // Clear all outfits
                    pro.getOutfits().clear();
                    update(session, pro);

                    // Open new session
                    session.set(sf.openSession());
                    return "\"Success\"";
                });

                // Logs user out
                post("/logout", (req, res) -> {
                    loginTable.remove(toMap(req.body()).get("username"));
                    return "\"Success\"";
                });
            });

            /* CLOTHING */
            // Types
            path("/type", () -> {

                // Return all
                get("", (req, res) -> {
                    // Create a query to select all from table represented by ClothingType.class
                    CriteriaQuery<ClothingType> criteriaQuery = session.get().getCriteriaBuilder()
                            .createQuery(ClothingType.class);
                    criteriaQuery.select(criteriaQuery.from(ClothingType.class));

                    // Query these entities and collect them as a json list
                    return session.get().createQuery(criteriaQuery).getResultList()
                            .stream()
                            .map(ClothingType::toJson)
                            .collect(Collectors.toList());
                }, new JsonTransformer());

                // Stream through type_ids of valid ClothingTypes, collect as list of JsonObjects
                get("/:type_id", (req, res) -> {
                    return parseIDs(req.params("type_id")).stream()
                            .map(type_id -> session.get().get(ClothingType.class, type_id))
                            .filter(Objects::nonNull)
                            .map(ClothingType::toJson)
                            .collect(Collectors.toList()
                            );
                }, new JsonTransformer());

                // Same thing, but returns items lists mapped by types
                get("/:type_id/items", (req, res) -> {
                    return parseIDs(req.params("type_id")).stream()
                            .map(type_id -> session.get().get(ClothingType.class, type_id))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toMap(ClothingType::getType_id, ClothingType::itemsToJson, (a, b) -> b));
                }, new JsonTransformer());
            });

            // Items
            path("/item", () -> {

                // Get all items
                get("", (req, res) -> {

                    // Create a query to select all from table represented by ClothingItem.class
                    CriteriaQuery<ClothingItem> criteriaQuery = session.get().getCriteriaBuilder()
                            .createQuery(ClothingItem.class);
                    criteriaQuery.select(criteriaQuery.from(ClothingItem.class));

                    // Query these entities and collect them as a json list
                    return session.get().createQuery(criteriaQuery)
                            .getResultList().stream()
                            .map(ClothingItem::toJsonWithTypeID)
                            .collect(Collectors.toList());
                }, new JsonTransformer());

                // Stream through item_ids of valid ClothingItems, collect as list of JsonObjects
                get("/:item_id", (req, res) -> {
                    return parseIDs(req.params("item_id")).stream()
                            .map(item_id -> session.get().get(ClothingItem.class, item_id))
                            .filter(Objects::nonNull)
                            .map(ClothingItem::toJson)
                            .collect(Collectors.toList());
                }, new JsonTransformer());

                get("/:item_id/types", (req, res) -> {
                    return parseIDs(req.params("item_id")).stream()
                            .map(type_id -> session.get().get(ClothingItem.class, type_id))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toMap(ClothingItem::getItem_id, ClothingItem::typeToJson, (a, b) -> b));
                }, new JsonTransformer());
            });
        });

        /* Error handing */
        internalServerError((req, res) -> "\"Unknown error\"");
        exception(Exception.class, (exception, request, response) -> exception.printStackTrace());
        notFound((req, res) -> "\"Invalid request\"");

        System.out.println("\n\n~~ Server is Running ~~\n\n");
    }

    // Performs boiler plate hibernate setup
    private static SessionFactory setUp() {

        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            return new MetadataSources(standardRegistry)
                    .addAnnotatedClass(Profile.class)
                    .addAnnotatedClass(ClothingItem.class)
                    .addAnnotatedClass(ClothingType.class)
                    .addAnnotatedClass(Outfit.class)
                    .getMetadataBuilder()
                    .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                    .build()
                    .getSessionFactoryBuilder()
                    .build();
        } catch (ServiceException e) {
            System.err.println("Could not connect to database");
            System.err.println(e.getMessage());
        }
        return null;
    }

    // Persists an entity to the database
    private static void save(AtomicReference<Session> session, Object object) {
        session.get().beginTransaction();
        session.get().save(object);
        session.get().getTransaction().commit();
        session.get().close();
    }

    // Persists any changes made to an entity
    private static void update(AtomicReference<Session> session, Object object) {
        session.get().beginTransaction();
        session.get().update(object);
        session.get().getTransaction().commit();
        session.get().close();
    }

    // Deletes an entity
    private static void delete(AtomicReference<Session> session, Object object) {
        session.get().beginTransaction();
        session.get().delete(object);
        session.get().getTransaction().commit();
        session.get().close();
    }

    // Parses out the HTTP Request body as map of variables to values
    private static HashMap<String, String> toMap(String body) {
        ArrayList<NameValuePair> pairs = (ArrayList<NameValuePair>) URLEncodedUtils.parse(body, Charset.defaultCharset());
        HashMap<String, String> map = new HashMap<>();
        for (NameValuePair pair : pairs) map.put(pair.getName(), pair.getValue());
        return map;
    }

    // Produces an iterable list of IDs
    private static ArrayList<Integer> parseIDs(String body) {
        ArrayList<Integer> ids = new ArrayList<>();
        if (!Pattern.matches("\\A([\\d]*,)*\\d+$", body)) return ids;
        for (String str : body.split(",", 100)) ids.add(Integer.parseInt(str));
        return ids;
    }

    // Run this method to index the db for search
    private static void indexDB() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory(); // Hibernate
        Session session = sf.openSession();

        try {
            FullTextSession fullTextSession = Search.getFullTextSession(session);
            fullTextSession.createIndexer().startAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}