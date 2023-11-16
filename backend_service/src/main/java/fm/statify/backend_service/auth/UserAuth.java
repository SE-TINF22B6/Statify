package fm.statify.backend_service.auth;

import fm.statify.backend_service.entities.User;
import fm.statify.backend_service.util.DatabaseManager;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserAuth {
    private static final Logger LOGGER = Logger.getLogger(UserAuth.class.getName());

    private final DatabaseManager databaseManager;
    private final SpotifyOAuth spotifyOAuth;

    public UserAuth(DatabaseManager databaseManager, SpotifyOAuth spotifyOAuth) {
        this.databaseManager = databaseManager;
        this.spotifyOAuth = spotifyOAuth;
    }

    public void authenticateAndAddUser(String code) {
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
        URL url = new URL("https://api.spotify.com/v1/me");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        Scanner scanner = new Scanner(conn.getInputStream());
        String response = scanner.useDelimiter("\\A").next();
        scanner.close();
        JSONObject json = new JSONObject(response);
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
        // ...
    }
}
