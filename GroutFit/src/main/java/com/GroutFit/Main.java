package com.GroutFit;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        // Perform setup, load public files
        /*
        SessionFactory sf = new Configuration()
                .configure()
                .buildSessionFactory(); // Hibernate
        */
        staticFiles.location("/src/main/webapp");

        // Example routes
        get("/hello", (req, res) -> "Hello World");

        // Api calls
        path("/api", () -> {
            post("/cart", (request, response) -> "This will load all items in shopping cart");
            post("/wishlist", (request, response) -> "This will load all items in wishlist");
            post("/outfits", (request, response) -> "This will load all user outfits/outfit feed");
            post("/outfit/:id", (request, response) -> "This will load an outfit with id" + request.params(":id"));
        });
    }
}