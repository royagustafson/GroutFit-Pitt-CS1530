package com.GroutFit;

import static spark.Spark.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        // Perform setup, load public files
        SessionFactory sf = new Configuration()
                .configure()
                .buildSessionFactory();
        staticFiles.location("/public");

        // Example routes

        get("/hello", (req, res) -> "Hello World");

        // Api calls

        path("/api", () -> {
            path("/cart", () -> {

            });
            path("/wishlist", () -> {

            });
        });

        /*
        get("/", (request, response) -> {
            // Show something
        });

        post("/", (request, response) -> {
            // Create something
        });

        put("/", (request, response) -> {
            // Update something
        });

        delete("/", (request, response) -> {
            // Annihilate something
        });

        options("/", (request, response) -> {
            // Appease something
        });

        */
    }
}