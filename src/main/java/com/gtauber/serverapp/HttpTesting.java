package com.gtauber.serverapp;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpTesting {

    public static void main(String[] args) throws IOException, InterruptedException {
        String clientId = "YOUR_ACCESS_KEY";
        var httpClient = HttpClient.newHttpClient();
        var req = HttpRequest.newBuilder(URI.create("https://api.unsplash.com/photos/random/" + clientId))
                .header("accept", "application/json")
                .header("Accept-Version", "v1")
                .GET()
                .build();
        var res = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(res.body());
    }
}
