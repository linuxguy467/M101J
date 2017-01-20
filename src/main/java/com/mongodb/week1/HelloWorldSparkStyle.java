package com.mongodb.week1;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by mchem on 1/11/2017.
 */
public class HelloWorldSparkStyle {
    public static void main(String[] args) {
        Spark.get(new Route("/") {
            @Override
            public Object handle(final Request request,
                                 final Response response) {
                return "Hello World from Spark!";
            }
        });
    }
}
