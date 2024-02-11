package edu.escuelaing.arem.ASE.app;

import java.io.IOException;
import java.net.URISyntaxException;

public class MyWebServices {
    public static void main(String[] args) throws IOException, URISyntaxException {
        // route and lambda function to handle the request to that route
        HttpServer.get("/arep", (param) -> {
            String resp = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type:text/html\r\n"
                    + "\r\n"
                    + "<h1> Welcome to AREP! </h1>";

            if (param != "") {
                resp += "<p> The parameter of your query was: " + param + "</p>";
            }

            return resp;
        });

        HttpServer.get("/arsw", (param) -> {
            String resp = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type:text/html\r\n"
                    + "\r\n"
                    + "<h1> Welcome to ARSW! </h1>";

            if (param != "") {
                resp += "<p> The parameter of your query was: " + param + "</p>";
            }

            return resp;
        });

        HttpServer.get("/ieti", (param) -> {
            String resp = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type:text/html\r\n"
                    + "\r\n"
                    + "<h1> Welcome to IETI! </h1>";

            if (param != "") {
                resp += "<p> The parameter of your query was: " + param + "</p>";
            }

            return resp;
        });

        HttpServer.getInstance().runServer("json");
    }
}
