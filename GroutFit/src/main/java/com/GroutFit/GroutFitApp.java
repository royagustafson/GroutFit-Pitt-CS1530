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
import org.hibernate.service.spi.ServiceException;

import javax.persistence.criteria.CriteriaQuery;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class GroutFitApp {

    public static void main(String[] args) {

        SessionFactory sf = setUp();
        assert sf != null;
        Session session = sf.openSession();

        staticFiles.location("public");

        HashSet<String> loginTable = new HashSet<>();

        before("/api/*", (req, res) -> res.type("application/json"));

        // Verify login
        // NOTE: security is TERRIBLE
        // But this allows for proper OAuth to be implemented, or other security
        before("/api/auth/*", (req, res) -> {
            if (!loginTable.contains(toMap(req.body()).get("username")))
                halt(401, "\"User not logged in\"");
        });

        /* API calls */
        path("/api", () -> {

            /* USER FUNCTIONS */
            post("/register", (req, res) -> {
                HashMap<String, String> params = toMap(req.body());

                // Unique email
                if (session.get(Profile.class, params.get("username")) != null)
                    halt("\"Invalid username\"");

                Profile pro = Profile.register(params);
                session.save(pro);

                return "\"Success\"";
            });

            post("/login", (req, res) -> {
                HashMap<String, String> params = toMap(req.body());

                if (loginTable.contains(params.get("username")))
                    return "\"User is already logged in\"";

                Profile user = session.get(Profile.class, params.get("username"));

                if (user == null)
                    halt(401, "\"Invalid username\"");
                if (!user.login(params.get("password")))
                    halt(401, "\"Invalid password\"");

                loginTable.add(user.getEmail());

                return "\"Success\"";
            });

            // For all paths that require the user to be logged in
            path("/auth", () -> {

                /* WISHLIST FUNCTIONS */
                // Get wishlist as json list
                post("/wishlist", (req, res) -> {
                    ArrayList<JsonObject> jsonObjects = new ArrayList<>();
                    for (ClothingItem clothingItem : session.get(Profile.class, toMap(req.body()).get("username"))
                            .getWishlist()) {
                        JsonObject toJson = clothingItem.toJson();
                        jsonObjects.add(toJson);
                    }
                    return jsonObjects;              // collect in list
                }, new JsonTransformer());

                // Add, remove, clear
                post("/wishlist/:function", (req, res) -> {
                    HashMap<String, String> params = toMap(req.body());
                    Profile pro = session.get(Profile.class, params.get("username"));

                    Consumer<ClothingItem> applyFunction = null;
                    switch (req.params("function")) {
                        case "add":
                            applyFunction = pro.getWishlist()::add;
                            break;
                        case "remove":
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
                            .map(item_id -> session.get(ClothingItem.class, item_id))
                            .filter(Objects::nonNull)
                            .forEach(applyFunction);

                    session.update(pro);
                    return "\"Success\"";
                });

                /* OUTFIT FUNCTIONS */
                // Render outfits
                post("/outfits", (req, res) -> {
                    HashMap<String, String> params = toMap(req.body());
                    Profile pro = session.get(Profile.class, params.get("username"));
                    ArrayList<JsonObject> json = new ArrayList<>();

                    // Assemble outfits
                    if (params.containsKey("outfit_id")) {
                        // For requested outfits
                        for (Integer outfit_id : parseIDs(params.get("outfit_id"))) {
                            Outfit outfit = session.get(Outfit.class, outfit_id);
                            if (outfit != null) json.add(outfit.toJson());
                        }
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
                    Profile pro = session.get(Profile.class, params.get("username"));
                    // Params are all fields of "outfit" except outfit_id
                    // username (obviously), full_body, top_id, bottom_id, jacket_id
                    session.save(Outfit.build(pro, session, params));
                    return "\"Success\"";
                });

                // Remove outfits in batch
                post("/outfits/remove", (req, res) -> {
                    HashMap<String, String> params = toMap(req.body());
                    Profile pro = session.get(Profile.class, params.get("username"));

                    // Stream all ID's that correspond to non null Outfit, apply function
                    parseIDs(params.get("outfit_id")).stream()
                            .map(item_id -> session.get(Outfit.class, item_id))
                            .filter(Objects::nonNull)
                            .filter(outfit -> !outfit.getProfile().equals(pro))
                            .forEach(pro.getOutfits()::remove);

                    return "\"Success\"";
                });

                // Remove all outfits by hand, then clear profile
                post("/outfits/clear", (req, res) -> {
                    HashMap<String, String> params = toMap(req.body());
                    Profile pro = session.get(Profile.class, params.get("username"));
                    for (Outfit outfit : pro.getOutfits()) session.remove(outfit);
                    pro.getOutfits().clear();
                    session.update(pro);
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
                    CriteriaQuery<ClothingType> criteriaQuery = session.getCriteriaBuilder()
                            .createQuery(ClothingType.class);
                    criteriaQuery.select(criteriaQuery.from(ClothingType.class));
                    return session.createQuery(criteriaQuery)
                            .getResultList().stream()
                            .map(ClothingType::toJson)
                            .collect(Collectors.toList());
                }, new JsonTransformer());

                // Stream through type_ids of valid ClothingTypes, collect as list of JsonObjects
                get("/:type_id", (req, res) -> {
                    return parseIDs(req.params("type_id")).stream()
                            .map(type_id -> session.get(ClothingType.class, type_id))
                            .filter(Objects::nonNull)
                            .map(ClothingType::toJson)
                            .collect(Collectors.toList()
                            );
                }, new JsonTransformer());

                // Same thing, but returns items lists mapped by types
                get("/:type_id/items", (req, res) -> {
                    return parseIDs(req.params("type_id")).stream()
                            .map(type_id -> session.get(ClothingType.class, type_id))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toMap(ClothingType::getType_id, ClothingType::itemsToJson, (a, b) -> b));
                }, new JsonTransformer());
            });

            // Items
            path("/item", () -> {

                // Get all items
                get("", (req, res) -> {
                    CriteriaQuery<ClothingItem> criteriaQuery = session.getCriteriaBuilder()
                            .createQuery(ClothingItem.class);
                    criteriaQuery.select(criteriaQuery.from(ClothingItem.class));
                    return session.createQuery(criteriaQuery)
                            .getResultList().stream()
                            .map(ClothingItem::toJsonWithTypeID)
                            .collect(Collectors.toList());
                }, new JsonTransformer());

                // Stream through item_ids of valid ClothingItems, collect as list of JsonObjects
                get("/:item_id", (req, res) -> {
                    List<JsonObject> list = parseIDs(req.params("item_id"))
                            .stream().map(item_id -> session.get(ClothingItem.class, item_id))
                            .filter(Objects::nonNull)
                            .map(ClothingItem::toJson)
                            .collect(Collectors.toList());
                    return list;
                }, new JsonTransformer());

                // Same as above, but returns types mapped by items
                get("/:item_id/types", (req, res) -> {
                    return parseIDs(req.params("item_id")).stream()
                            .map(item_id -> session.get(ClothingItem.class, item_id))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toMap(ClothingItem::getItem_id, ClothingItem::typeToJson));
                }, new JsonTransformer());
            });
        });

        /* Error handing */
        internalServerError((req, res) -> "\"Unknown error\"");
        exception(Exception.class, (exception, request, response) -> exception.printStackTrace());
        notFound((req, res) -> "\"Invalid request\"");

        System.out.println("\n\n~~ Server is Running ~~\n\n");
    }

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

    private static HashMap<String, String> toMap(String body) {
        ArrayList<NameValuePair> pairs = (ArrayList<NameValuePair>) URLEncodedUtils.parse(body, Charset.defaultCharset());
        HashMap<String, String> map = new HashMap<>();
        for (NameValuePair pair : pairs) map.put(pair.getName(), pair.getValue());
        return map;
    }

    private static ArrayList<Integer> parseIDs(String body) {
        ArrayList<Integer> ids = new ArrayList<>();
        if (!Pattern.matches("\\A([\\d]*,)*\\d+$", body)) return ids;
        for (String str : body.split(",", 100)) ids.add(Integer.parseInt(str));
        return ids;
    }
}