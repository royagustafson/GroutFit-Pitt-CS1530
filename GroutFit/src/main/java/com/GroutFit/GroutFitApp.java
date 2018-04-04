package com.GroutFit;

import com.GroutFit.Helper.JsonTransformer;
import com.GroutFit.Model.ClothingItem;
import com.GroutFit.Model.Profile;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Response;

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

        /* API calls */
        path("/api", () -> {
            before((req, res) -> res.type("application/json"));

            /* USER FUNCTIONS */
            /* For all paths that require the user to be logged in*/
            path("/auth", () -> {
                before((req, res) -> {
                    if (!loginTable.get(toMap(req.body()).get("username"))) halt(401, "User not logged in");
                });

                post("/logout", (req, res) -> {
                    loginTable.remove(toMap(req.body()).get("username"));
                    return "Success";
                }, new JsonTransformer());
            });

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

            /* ITEMS */
            get("/item/:item_id", (req, res) -> {
                HashMap<Integer, String> json = new HashMap<>();

                for (Integer item_id : parseIDs(req.params("item_id"))) {
                    ClothingItem item = session.get(ClothingItem.class, item_id);
                    if (item != null) json.put(item_id, item.toString());
                    else json.put(item_id, "null");
                }

                return json;
            }, new JsonTransformer());
        });

        /* Error handing */
        notFound((req, res) -> "Invalid request");
        internalServerError((req, res) -> "Unknown error");
        exception(Exception.class, (exception, request, response) -> System.out.println(exception.getMessage()));
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

    private static Response success(Response res) {
        res.body("Successful");
        res.status(200);
        return res;
    }
}