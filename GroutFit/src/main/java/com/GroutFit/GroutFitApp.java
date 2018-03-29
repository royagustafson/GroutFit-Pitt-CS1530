package com.GroutFit;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class GroutFitApp {
    public static void main(String[] args) {
        // Perform setup, load public files
        /*
        SessionFactory sf = new Configuration()
                .configure()
                .buildSessionFactory(); // Hibernate
        */

        staticFiles.location("public");

        // Example routes
        get("/hello", (req, res) -> "Hello World");

        // Api calls
        get("/cart", (req, res) -> "This will load all items in shopping cart");
        get("/wishlist", (req, res) -> "This will load all items in wishlist");
        get("/outfits", (req, res) -> "This will load all user outfits/outfit feed");
        get("/outfit/:id", (req, res) -> "This will load an outfit with id" + req.params(":id"));
    }


}