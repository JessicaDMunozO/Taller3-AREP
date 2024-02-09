package edu.escuelaing.arem.ASE.app;

import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

public class HttpServer {

    /**
     * ApiMovie instance to search for information about a given movie name
     */
    private static ApiMovie myAPI = new ApiMovie();

    public static void main(String[] args) throws IOException, URISyntaxException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine = "";

            boolean firstLine = true;
            String uriStr = "";
            String movieName = "";

            while ((inputLine = in.readLine()) != null) {
                if (firstLine) {
                    uriStr = inputLine.split(" ")[1];
                    firstLine = false;
                }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            URI fileuri = new URI(uriStr);
            System.out.println("Path: " + fileuri.getPath());

            if (uriStr.contains("movie?title")) {
                String[] parts = uriStr.split("=");
                movieName = parts[1];
                printMovieResult(movieName, out);
            } else {
                try {
                    outputLine = httpClientHtml(fileuri.getPath(), clientSocket);
                } catch (IOException e) {
                    outputLine = httpError();
                }
            }

            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    private static String httpError() {
        String outputLine = "HTTP/1.1 400 Not Found\r\n"
                + "Content-Type:text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Error Not found</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <h1>Error</h1>\n"
                + "    </body>\n";
        return outputLine;

    }

    /**
     * HTTP answer with the content of the specified file on the path
     * 
     * @param path         route to the specified file
     * @param clientSocket socket of the client to send immediately the response, in
     *                     case of an image file
     * @return string with the http answer with the content of the file
     * @throws IOException if there is an error with the inputs or outputs
     */
    public static String httpClientHtml(String path, Socket clientSocket) throws IOException {
        String content_type = "";

        // content type related to the media type of the resource
        if (path.endsWith(".html")) {
            content_type = "text/html";
        } else if (path.endsWith(".js")) {
            content_type = "application/javascript";
        } else if (path.endsWith(".css")) {
            content_type = "text/css";
        } else if (path.endsWith(".png")) {
            content_type = "image/png";
        }

        String outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type:" + content_type + "\r\n"
                + "\r\n";

        Path file = Paths.get("target/classes/public" + path);

        Charset charset = Charset.forName("UTF-8");

        if (content_type.equals("image/png")) {
            byte[] imageData = Files.readAllBytes(file);
            OutputStream output = clientSocket.getOutputStream();
            output.write(outputLine.getBytes());
            output.write(imageData);

        } else {
            BufferedReader reader = Files.newBufferedReader(file, charset);
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                outputLine += line;
            }
        }

        return outputLine;

    }

    /**
     * Print information about the specific movie
     * 
     * @param movieName name of the movie to be seached
     * @param out       PrintWriter to send response to the client
     */
    private static void printMovieResult(String movieName, PrintWriter out) {
        String movieInfo;

        try {
            movieInfo = myAPI.searchMovieInformation(movieName);
        } catch (Exception e) {
            movieInfo = "Ups, try later.";
            e.printStackTrace();
        }

        String movieResponse = "HTTP/1.1 200 OK\r\n"
                + "Content-Type:text/html; charset=ISO-8859-1\r\n"
                + "\r\n"
                + movieInfo;

        out.println(movieResponse);
    }
}