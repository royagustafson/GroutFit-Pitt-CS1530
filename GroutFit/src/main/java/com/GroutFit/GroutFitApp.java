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

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
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
        HashMap<String, Boolean> loginTable = new HashMap<>();

        before("/*", (req, res) -> res.type("application/json"));

        /* API calls */
        path("/api", () -> {

            /* USER FUNCTIONS */
            post("/login", (req, res) -> {
                HashMap<String, String> params = toMap(req.body());

                if (loginTable.containsKey(params.get("username"))) return "User is already logged in";

                Profile user = session.get(Profile.class, params.get("username"));

                if (user == null) halt(401, "Invalid username");
                if (!user.login(params.get("password"))) halt(401, "Invalid password");

                loginTable.put(user.getEmail(), true);

                return "Success";
            }, new JsonTransformer());

            // For all paths that require the user to be logged in
            path("/auth", () -> {

                // Verify login
                // NOTE: security is TERRIBLE
                // But this allows for
                before((req, res) -> {
                    if (!loginTable.get(toMap(req.body()).get("username")))
                        halt(401, "User not logged in");
                });

                /* WISHLIST FUNCTIONS */
                // Get wishlist as json list
                post("/wishlist", (req, res) -> {
                    return session.get(Profile.class, toMap(req.body()).get("username"))    // load user model
                            .getWishlist().stream()                                         // stream wishlist items
                            .map(ClothingItem::toJson)                                      // convert items to json
                            .collect(Collectors.toCollection(ArrayList::new));              // collect in list
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
                            return "Success";
                        default:
                            halt(404, "Invalid function");
                    }

                    // Stream all ID's that correspond to non null ClothingItem, apply function
                    parseIDs(params.get("wishlist_id")).stream()
                            .map(item_id -> session.get(ClothingItem.class, item_id))
                            .filter(Objects::nonNull)
                            .forEach(applyFunction);

                    session.save(pro);
                    return "Success";
                }, new JsonTransformer());

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
                    return "Success";
                }, new JsonTransformer());

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

                    return "Success";
                });

                // Remove all outfits by hand, then clear profile
                post("/outfits/clear", (req, res) -> {
                    HashMap<String, String> params = toMap(req.body());
                    Profile pro = session.get(Profile.class, params.get("username"));
                    for (Outfit outfit : pro.getOutfits()) session.remove(outfit);
                    pro.getOutfits().clear();
                    session.save(pro);
                    return "Success";
                }, new JsonTransformer());

                // Logs user out
                post("/logout", (req, res) -> {
                    loginTable.remove(toMap(req.body()).get("username"));
                    return "Success";
                }, new JsonTransformer());
            });

            /* CLOTHING */
            // Types
            path("/type", () -> {

                // Gets all types

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
                            .collect(Collectors.toMap(ClothingType::getType_id, ClothingType::itemsToJson));
                }, new JsonTransformer());
            });

            // Items
            path("/item", () -> {

                // Stream through item_ids of valid ClothingItems, collect as list of JsonObjects
                get("/:item_id", (req, res) -> {
                    return parseIDs(req.params("item_id")).stream()
                            .map(item_id -> session.get(ClothingItem.class, item_id))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList());
                }, new JsonTransformer());

                // Same as above, but returns types mapped by items
                get("/:item_id/types", (req, res) -> {
                    return parseIDs(req.params("item_id")).stream()
                            .map(item_id -> session.get(ClothingItem.class, item_id))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toMap(ClothingItem::getItem_id, ClothingItem::typeToJson));
                });
            });
        });

        /* Error handing */
        internalServerError((req, res) -> "\"Unknown error\"");
        exception(Exception.class, (exception, request, response) -> exception.printStackTrace());
        notFound((req, res) -> "\"Invalid request\"");

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