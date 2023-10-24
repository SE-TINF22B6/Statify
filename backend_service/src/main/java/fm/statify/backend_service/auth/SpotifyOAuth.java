package fm.statify.backend_service.auth;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import org.springframework.stereotype.Service;

@Service
public class SpotifyOAuth {
    private static final String CLIENT_ID = null; //KEEP NULL WHEN COMMITTING AS LONG ITS HARDCODED!
    private static final String CLIENT_SECRET = null; //KEEP NULL WHEN COMMITTING AS LONG ITS HARDCODED!
    private static final String REDIRECT_URI = "http://localhost:8080/callback";
    private static final String AUTH_URL = "https://accounts.spotify.com/authorize";
    private static final String TOKEN_URL = "https://accounts.spotify.com/api/token";

    public String getAuthUrl() {
        return AUTH_URL + "?client_id=" + CLIENT_ID
                + "&response_type=code"
                + "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, StandardCharsets.UTF_8)
                + "&scope=user-top-read";
    }

    public String getAccessToken() throws Exception {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/callback", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String query = exchange.getRequestURI().getQuery();
                String response = "You can close this page now.";
                exchange.sendResponseHeaders(200, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
                server.stop(1);
                queue.add(query);
            }
        });
        server.start();

        System.out.println("Visit the following URL to authorize:");
        System.out.println(getAuthUrl());

        String query = queue.take();
        Map<String, String> params = parseQuery(query);
        String code = params.get("code");
        return requestAccessToken(code);
    }

    private Map<String, String> parseQuery(String query) {
        Map<String, String> params = new HashMap<>();
        for (String pair : query.split("&")) {
            int idx = pair.indexOf("=");
            params.put(pair.substring(0, idx), pair.substring(idx + 1));
        }
        return params;
    }

    private String requestAccessToken(String code) throws Exception {
        URL url = new URL(TOKEN_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        String auth = CLIENT_ID + ":" + CLIENT_SECRET;
        String basicAuth = "Basic " + java.util.Base64.getEncoder().encodeToString(auth.getBytes());
        con.setRequestProperty("Authorization", basicAuth);

        String body = "grant_type=authorization_code"
                + "&code=" + code
                + "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, StandardCharsets.UTF_8);
        try (OutputStream os = con.getOutputStream()) {
            os.write(body.getBytes());
        }

        Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
        scanner.useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
