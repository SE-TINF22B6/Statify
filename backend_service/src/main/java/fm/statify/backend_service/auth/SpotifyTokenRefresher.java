package fm.statify.backend_service.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.logging.Logger;
import java.util.logging.Level;

@Service
public class SpotifyTokenRefresher {
    private static final Logger LOGGER = Logger.getLogger(SpotifyTokenRefresher.class.getName());
    @Value("${app.clientId}")
    private String clientId;
    @Value("${app.clientSecret}")
    private String clientSecret;
    private static final String TOKEN_URL = "https://accounts.spotify.com/api/token";

    public String refreshAccessToken(String refreshToken) {
        HttpClient client = HttpClient.newHttpClient();
        String auth = clientId + ":" + clientSecret;
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
        String body = "grant_type=refresh_token" + "&refresh_token=" + refreshToken;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TOKEN_URL))
                .header("Authorization", basicAuth)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            LOGGER.info("Access token refreshed successfully.");
            return response.body();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to refresh access token: ", e);
            return null;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.log(Level.SEVERE, "Thread was interrupted", e);
            return null;
        }
    }

}
