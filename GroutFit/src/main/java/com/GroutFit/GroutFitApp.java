package com.GroutFit;

import com.GroutFit.Helper.pHash;
import com.GroutFit.Model.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
            path("/example", () -> {
                post("/login", (req, res) -> {
                    // http://sparkjava.com/documentation#request
                    return req.attribute("username") != null && req.attribute("password") != null;
                });
                post("/cart", (req, res) -> {
                    // http://sparkjava.com/documentation#response
                    res.body("Hello");
                    return res;
                });
            });
            post("/login", (req, res) -> {
                try {
                    String username = req.headers("username");
                    String password = req.headers("password");
                    Profile user = session.get(Profile.class, username);
                    Boolean valid = pHash.verify(password, user.getPassword());
                    if (valid) {
                        loginTable.put(username, true);
                        res.status(400);
                    } else
                        res.status(401);
                    res.body(valid.toString());
                    return valid;
                } catch(Exception e) {
                    e.printStackTrace();
                    res.status(0);
                    return res;
                }
            });
            get("/item/:id", (req, res) -> {
                return null;
            });
        });
        get("/cart", (req, res) -> "This will load all items in shopping cart");
        get("/wishlist", (req, res) -> "This will load all items in wishlist");
        get("/outfits", (req, res) -> "This will load all user outfits/outfit feed");
        get("/outfit/:id", (req, res) -> "This will load an outfit with id" + req.params(":id"));
    }
}