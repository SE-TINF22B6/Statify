package fm.statify.backend_service.auth;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import com.sun.net.httpserver.HttpServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

@Service
public class SpotifyOAuth {
    @Value("${app.clientId}")
    private String clientId;

    @Value("${app.clientSecret}")
    private String clientSecret;
    private static final String REDIRECT_URI = "http://localhost:3000/callback";
    private static final String AUTH_URL = "https://accounts.spotify.com/authorize";
    private static final String TOKEN_URL = "https://accounts.spotify.com/api/token";


    // Get the URL to redirect the user to the Spotify login page
    public String getAuthUrl() {
        String allScopes = "ugc-image-upload+user-read-playback-state+user-modify-playback-state+user-read-currently-playing+app-remote-control" +
                "+streaming+playlist-read-private+playlist-read-collaborative+playlist-modify-private+playlist-modify-public+user-follow-modify+user-follow-read+user-read-playback-position" +
                "+user-top-read+user-read-recently-played+user-library-modify+user-library-read+user-read-email+user-read-private";
        return AUTH_URL + "?client_id=" + clientId
                + "&response_type=code"
                + "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, StandardCharsets.UTF_8)
                + "&scope=" + allScopes;
    }

    // Get the access token from the response
    public String getAccessToken() throws IOException, InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/callback", exchange -> {
            String query = exchange.getRequestURI().getQuery();
            String response = "You can close this page now.";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
            server.stop(1);
            queue.add(query);
        });
        server.start();
        System.out.println(getAuthUrl());
        String query = queue.take();
        Map<String, String> params = parseQuery(query);
        String code = params.get("code");
        return requestAccessToken(code);
    }

    // Parse the query string - used to get the code from the URL
    private Map<String, String> parseQuery(String query) {
        Map<String, String> params = new HashMap<>();
        for (String pair : query.split("&")) {
            int idx = pair.indexOf("=");
            params.put(pair.substring(0, idx), pair.substring(idx + 1));
        }
        return params;
    }

    // Request the access token from Spotify
    public String requestAccessToken(String code) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String auth = clientId + ":" + clientSecret;
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());

        String body = "grant_type=authorization_code"
                + "&code=" + code
                + "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TOKEN_URL))
                .header("Authorization", basicAuth)
                .header("Content-Type", "application/x-www-form-urlencoded")
                //Here the actual request is made
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    // Parse the response from Spotify to get different token data (for example in Class Main)
    public Map<String, String> parseResponse(String response) {
        Map<String, String> tokenData = new HashMap<>();
        JSONObject jsonObject = new JSONObject(response);

        tokenData.put("access_token", jsonObject.getString("access_token"));
        tokenData.put("token_type", jsonObject.getString("token_type"));
        tokenData.put("expires_in", String.valueOf(jsonObject.getInt("expires_in")));
        tokenData.put("refresh_token", jsonObject.optString("refresh_token"));

        return tokenData;
    }
}
