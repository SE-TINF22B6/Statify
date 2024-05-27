package fm.statify.backend_service.auth;

import fm.statify.backend_service.entities.UserProfile;
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


    // Test Constructor, no usage
    public UserAuth(DatabaseManager databaseManager, SpotifyOAuth spotifyOAuth) {
        this.databaseManager = databaseManager;
        this.spotifyOAuth = spotifyOAuth;
    }

    public String authenticateUser(String code) {
        try {
            String response = spotifyOAuth.requestAccessToken(code);
            Map<String, String> tokenData = spotifyOAuth.parseResponse(response);
            String accessToken = tokenData.get("access_token");
            UserProfile user = fetchSpotifyUser(accessToken);
            addUserToDatabaseIfNotExists(user, accessToken);
            return accessToken;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to authenticate user: ", e);
            return null;
        }
    }

    // Fetch the user's data from Spotify API and fetch an JSON Object from the response containing the user's data
    private UserProfile fetchSpotifyUser(String accessToken) throws Exception {
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
        return new UserProfile(id, displayName, email, userURL, profilePictureURL, product);
    }


    // Test code for Database
    private void addUserToDatabaseIfNotExists(UserProfile user, String accesstoken) {
        if (!databaseManager.userExists(user.getId())) {
            databaseManager.addUser(user, accesstoken);
        } else {
            databaseManager.updateUser(user, accesstoken);
        }
    }
}
