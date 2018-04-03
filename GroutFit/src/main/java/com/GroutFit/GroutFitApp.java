package com.GroutFit;

import com.GroutFit.Helper.pHash;
import com.GroutFit.Model.ClothingItem;
import com.GroutFit.Model.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import spark.Response;

import java.util.HashMap;

import static spark.Spark.*;

public class GroutFitApp {

    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory(); // Hibernate
        Session session = sf.openSession();

        staticFiles.location("public");
        HashMap<String, Boolean> loginTable = new HashMap<>();

        // Example routes
        get("/hello", (req, res) -> "Hello World");

        // Api calls
        path("/api", () -> {

            // Example queries
            path("/example", () -> {
                post("/login", (req, res) -> {
                    // http://sparkjava.com/documentation#request
                    return req.attribute("username") != null && req.attribute("password") != null;
                });
                get("/cart", (req, res) -> "This will load all items in shopping cart");
                get("/wishlist", (req, res) -> "This will load all items in wishlist");
                get("/outfits", (req, res) -> "This will load all user outfits/outfit feed");
                get("/outfit/:id", (req, res) -> "This will load an outfit with id" + req.params(":id"));
            });

            // Basic user functionality
            post("/register", (req, res) -> {
                String username = req.headers("username");
                String password = req.headers("password");
                String size_shirt = req.headers("size_shirt");
                String size_pants = req.headers("size_pants");
                String size_dress = req.headers("size_dress");

                Profile pro = new Profile();
                pro.setEmail(username);
                pro.setPassword(password);
                pro.saveSizes(size_shirt, size_pants, size_dress);
                session.beginTransaction();
                session.save(pro);
                session.getTransaction().commit();
                res.body("Successful");
                res.status(200);
                return res.body();
            });
            post("/login", (req, res) -> {
                try {
                    String username = req.headers("username");
                    String password = req.headers("password");
                    Profile user = session.get(Profile.class, username);
                    Boolean valid = pHash.verify(password, user.getPassword());
                    if (valid) {
                        loginTable.put(username, true);
                        res.status(200);
                    } else
                        res.status(401);
                    res.body(valid.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    res.status(500);
                }
                return res.body();
            });
            post("/logout", (req, res) -> {
                try {
                    String username = req.headers("username");
                    if (loginTable.get(username)) {
                        loginTable.remove(username);
                        res.body("Successful");
                        res.status(200);
                    } else {
                        res.body(String.format("User %s is not logged in", username));
                        res.status(401);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    res.status(500);
                }
                return res.body();
            });

            // Item functionality
            get("/item/:item_id", (req, res) -> {
                try {
                    int id = Integer.parseInt(req.params("item_id"));
                    ClothingItem item = session.get(ClothingItem.class, id);
                    if (item != null) {
                        res.body(item.toString());
                        res.status(200);
                    } else {
                        res.body(String.format("No results for id %d", id));
                        res.status(200);
                    }
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    res.status(500);
                }
                return res.body();
            });
            get("/item/:query", (req, res) -> {
                res.body("Not implemented");
                res.status(200);
                // To be implemented
                return res.body();
            });
        });
    }

    // TODO is this sort of thing helpful
    private static Response success(Response res) {
        res.body("Successful");
        res.status(200);
        return res;
    }
}