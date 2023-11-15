package fm.statify.backend_service.auth;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class SpotifyTokenRefresher {

    private static final Logger LOGGER = Logger.getLogger(SpotifyTokenRefresher.class.getName());
    private static final String CLIENT_ID = null; // Replace with your client ID
    private static final String CLIENT_SECRET = null; // Replace with your client secret
    private static final String TOKEN_URL = "https://accounts.spotify.com/api/token";

    public String refreshAccessToken(String refreshToken) {
        try {
            URL url = new URL(TOKEN_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            String auth = CLIENT_ID + ":" + CLIENT_SECRET;
            String basicAuth = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
            connection.setRequestProperty("Authorization", basicAuth);
            String body = "grant_type=refresh_token"
                    + "&refresh_token=" + refreshToken;
            try (OutputStream os = connection.getOutputStream()) {
                os.write(body.getBytes());
            }
            Scanner scanner = new Scanner(connection.getInputStream(), "UTF-8");
            scanner.useDelimiter("\\A");
            String response = scanner.hasNext() ? scanner.next() : "";
            LOGGER.info("Access token refreshed successfully.");
            return response;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to refresh access token: ", e);
            return null;
        }
    }
}
