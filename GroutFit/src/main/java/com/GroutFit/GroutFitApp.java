package com.GroutFit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        Logger logger = LoggerFactory.getLogger(GroutFitApp.class);
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
                try {
                    Profile pro = Profile.register(
                            req.headers("username"),
                            req.headers("password"),
                            req.headers("size_shirt"),
                            req.headers("size_pants"),
                            req.headers("size_dress")
                    );
                    session.beginTransaction();
                    session.save(pro);
                    session.getTransaction().commit();

                    res = success(res);
                } catch (Exception e) {
                    res.body("Registration failed");
                    res.status(500); // internal server error
                }
                return res.body();
            });
            post("/login", (req, res) -> {
                try {
                    Profile user = session.get(Profile.class, req.headers("username"));
                    if (user == null) {
                        res.body("Invalid username");
                        res.status(401);
                    } else if (user.login(req.headers("password"))) {
                        logger.info(String.format("User %s logged in", user.getEmail()));
                        loginTable.put(user.getEmail(), true);
                        res = success(res);
                    } else {
                        res.body("Invalid password");
                        res.status(401);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    res.status(500);
                }
                return res.body();
            });
            post("/logout", (req, res) -> {
                for (String email : loginTable.keySet()) System.out.println(email);
                try {
                    String username = req.headers("username");
                    if (loginTable.get(username) != null) {
                        logger.info(String.format("User %s logged out", username));
                        loginTable.remove(username);
                        res = success(res);
                    } else {
                        res.body("User is not logged in");
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