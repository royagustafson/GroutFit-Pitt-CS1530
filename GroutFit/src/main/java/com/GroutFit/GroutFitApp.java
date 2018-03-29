package com.GroutFit;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static spark.Spark.*;

public class GroutFitApp {

    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory(); // Hibernate

        staticFiles.location("public");

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