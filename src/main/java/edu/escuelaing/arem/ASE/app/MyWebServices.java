package edu.escuelaing.arem.ASE.app;

import java.io.IOException;
import java.net.URISyntaxException;

public class MyWebServices {
    public static void main(String[] args) throws IOException, URISyntaxException {
        HttpServer.get("/arep", () -> {
            String resp = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type:text/html\r\n"
                    + "\r\n"
                    + "<h1> Welcome to AREP! </h1>";
            return resp;
        });

        HttpServer.get("/arsw", () -> {
            String resp = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type:text/html\r\n"
                    + "\r\n"
                    + "<h1> Welcome to ARSW! </h1>";
            return resp;
        });

        HttpServer.get("/ieti", () -> {
            String resp = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type:text/html\r\n"
                    + "\r\n"
                    + "<h1> Welcome to IETI! </h1>";
            return resp;
        });

        HttpServer.getInstance().runServer();
    }
}
