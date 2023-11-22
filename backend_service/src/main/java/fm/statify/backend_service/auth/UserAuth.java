package fm.statify.backend_service.auth;

import fm.statify.backend_service.entities.User;
import fm.statify.backend_service.util.DatabaseManager;
import org.json.JSONObject;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class UserAuth {
    private static final Logger LOGGER = Logger.getLogger(UserAuth.class.getName());
    private final DatabaseManager databaseManager;
    private final SpotifyOAuth spotifyOAuth;

    public UserAuth(DatabaseManager databaseManager, SpotifyOAuth spotifyOAuth) {
        this.databaseManager = databaseManager;
        this.spotifyOAuth = spotifyOAuth;
    }

    public void authenticateUser(String code) {
        try {
            String response = spotifyOAuth.requestAccessToken(code);
            Map<String, String> tokenData = spotifyOAuth.parseResponse(response);
            String accessToken = tokenData.get("access_token");
            User user = fetchSpotifyUser(accessToken);
            addUserToDatabaseIfNotExists(user);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to authenticate user: ", e);
        }
    }

    private User fetchSpotifyUser(String accessToken) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/me"))
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        JSONObject json = new JSONObject(response.body());

        String id = json.getString("id");
        String displayName = json.getString("display_name");
        String email = json.getString("email");
        String userURL = json.getJSONObject("external_urls").getString("spotify");
        String profilePictureURL = json.has("images") && !json.getJSONArray("images").isEmpty()
                ? json.getJSONArray("images").getJSONObject(0).getString("url")
                : null;
        String product = json.getString("product");
        return new User(id, displayName, email, userURL, profilePictureURL, product);
    }

    private void addUserToDatabaseIfNotExists(User user) {
        if (!databaseManager.userExists(user.getId())) {
            databaseManager.addUser(user);
        } else {
            databaseManager.updateUser(user);
        }
    }
}