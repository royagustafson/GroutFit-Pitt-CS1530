package com.GroutFit;

import com.GroutFit.Helper.JsonTransformer;
import com.GroutFit.Model.ClothingItem;
import com.GroutFit.Model.ClothingType;
import com.GroutFit.Model.Outfit;
import com.GroutFit.Model.Profile;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import static spark.Spark.*;

public class GroutFitApp {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(GroutFitApp.class);
        SessionFactory sf = new Configuration().configure().buildSessionFactory(); // Hibernate
        Session session = sf.openSession();

        staticFiles.location("public");
        HashMap<String, Boolean> loginTable = new HashMap<>();

        before("/*", (req, res) -> res.type("application/json"));

        /* API calls */
        path("/api", () -> {

            /* USER FUNCTIONS */
            post("/register", (req, res) -> {
                HashMap<String, String> params = toMap(req.body());
                Profile pro = Profile.register(params);

                session.beginTransaction();
                session.save(pro);
                session.getTransaction().commit();

                return "Success";
            }, new JsonTransformer());
            post("/login", (req, res) -> {
                HashMap<String, String> params = toMap(req.body());
                Profile user = session.get(Profile.class, params.get("username"));

                if (user == null) halt(401, "Invalid username");
                if (!user.login(params.get("password"))) halt(401, "Invalid passowrd");

                loginTable.put(user.getEmail(), true);

                return "Success";
            }, new JsonTransformer());

            // For all paths that require the user to be logged in
            path("/auth", () -> {
                before((req, res) -> {
                    if (!loginTable.get(toMap(req.body()).get("username")))
                        halt(401, "User not logged in");
                });

                // Long term store for desired items
                post("/wishlist", (req, res) -> {
                    ArrayList<JsonObject> json = new ArrayList<>();
                    Profile pro = session.get(Profile.class, toMap(req.body()).get("username"));
                    for (ClothingItem item : pro.getWishlist())
                        json.add(toJsonObject(item));
                    return json;
                }, new JsonTransformer());

                //
                post("/outfits", (req, res) -> {
                    ArrayList<JsonObject> json = new ArrayList<>();
                    Profile pro = session.get(Profile.class, toMap(req.body()).get("username"));
                    for (Outfit outfit : pro.getOutfits())
                        json.add(toJsonObject(outfit));
                    return json;
                }, new JsonTransformer());

                post("/logout", (req, res) -> {
                    loginTable.remove(toMap(req.body()).get("username"));
                    return "Success";
                }, new JsonTransformer());
            });

            /* CLOTHING */
            // Types
            path("/type", () -> {
                get("/:type_id", (req, res) -> {
                    ArrayList<JsonObject> json = new ArrayList<>();
                    for (Integer type_id : parseIDs(req.params("type_id")))
                        json.add(toJsonObject(session.get(ClothingType.class, type_id)));
                    return json;
                }, new JsonTransformer());
                get("/:type_id/items", (req, res) -> {
                    HashMap<Integer, ArrayList<JsonObject>> json = new HashMap<>();
                    for (Integer type_id : parseIDs(req.params("type_id"))) {
                        json.put(type_id, new ArrayList<>());
                        ClothingType type = session.get(ClothingType.class, type_id);
                        if (type == null) continue;
                        for (ClothingItem item : type.getItems())
                            json.get(type_id).add(toJsonObject(item));
                    }
                    return json;
                }, new JsonTransformer());
            });

            // Items
            path("/item", () -> {
                get("/:item_id", (req, res) -> {
                    ArrayList<JsonObject> json = new ArrayList<>();
                    for (Integer item_id : parseIDs(req.params("item_id")))
                        json.add(toJsonObject(session.get(ClothingItem.class, item_id)));
                    return json;
                }, new JsonTransformer());
                get("/:item_id/types", (req, res) -> {
                    HashMap<Integer, JsonObject> json = new HashMap<>();
                    for (Integer item_id : parseIDs(req.params("item_id"))) {
                        ClothingItem item = session.get(ClothingItem.class, item_id);
                        if (item == null) continue;
                        json.put(item_id, toJsonObject(item));
                    }
                    return json;
                }, new JsonTransformer());
            });
        });

        /* Error handing */
        notFound((req, res) -> "\"Invalid request\"");
        internalServerError((req, res) -> "\"Unknown error\"");
        exception(Exception.class, (exception, request, response) -> exception.printStackTrace());
    }

    private static HashMap<String, String> toMap(String body) {
        ArrayList<NameValuePair> pairs = (ArrayList<NameValuePair>) URLEncodedUtils.parse(body, Charset.defaultCharset());
        HashMap<String, String> map = new HashMap<>();
        for (NameValuePair pair : pairs) map.put(pair.getName(), pair.getValue());
        return map;
    }

    private static JsonObject toJsonObject(Object obj) {
        try {
            return new Gson().fromJson(obj.toString(), JsonObject.class);
        } catch (NoSuchMethodError ex) {
            return (JsonObject) obj;
        }
    }

    private static ArrayList<Integer> parseIDs(String body) {
        ArrayList<Integer> ids = new ArrayList<>();
        if (!Pattern.matches("\\A([\\d]*,)*\\d+$", body)) return ids;
        for (String str : body.split(",", 100)) ids.add(Integer.parseInt(str));
        return ids;
    }
}