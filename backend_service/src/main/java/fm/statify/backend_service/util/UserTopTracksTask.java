package fm.statify.backend_service.util;

import fm.statify.backend_service.auth.SpotifyOAuth;
import fm.statify.backend_service.auth.UserAuth;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserTopTracksTask implements Runnable {

    private final String userId;
    // Database Parameter
    String databaseURL;
    String databaseUsername;
    String databasePassword;

    // code? Was ist gemeint?
    String code;

    //This will be updated when the event is updated and the necessary parameters are implemented
    String trackID;
    String albumID;
    private static final Logger LOGGER = Logger.getLogger(DatabaseManager.class.getName());


    UserTopTracksTask(String userId) {
        this.userId = userId;
    }

    @Override
    public void run() {
        try {
            // Placeholder for token management
            String token = getTokenForUser(userId);

            String[] uris = fetchTopTracksFromSpotify(token);
            // Placeholder for database operation to save fetched tracks
            saveTopTracksToDatabase(userId, uris);

            Button btn = new Button();
            // Ereignis-Handler für Streamen eines Songs
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        streamSong(getTokenForUser(userId),albumID , uris, 5,0);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            // Proper error handling and logging should be implemented here
        }
    }

    private String getTokenForUser(String userId) throws IOException, InterruptedException {

        // Get Spotify access token
        SpotifyOAuth spotifyOAuth = new SpotifyOAuth();

        // Authenticate User
        UserAuth user = new UserAuth(new DatabaseManager(databaseURL,databaseUsername,databasePassword), spotifyOAuth);
        return user.authenticateUser(code);
    }

    private String[] fetchTopTracksFromSpotify(String accessToken) throws IOException, InterruptedException {

        // Placeholder for actual Spotify API call
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("'https://api.spotify.com/v1/tracks?" + "?limit=5"))
                // API call der die top 5 tracks holt
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse the JSON response
        JSONObject data = new JSONObject(response.body());

        // Extract the top tracks array
        JSONArray tracksArray = data.getJSONArray("tracks");

        String[] trackIds = new String[5];
        // Loop through each track and extract relevant information (TBD)
        for (int i = 0; i < tracksArray.length(); i++) {
            JSONObject track = tracksArray.getJSONObject(i);
            String trackId = track.getString("id");
            trackIds[i] = trackId;
        }
        return trackIds;
    }

    private void saveTopTracksToDatabase(String userID, String[] trackIds) {
        // Save necessary identifications for a track ; maybe albumID, artistID etc.
        String sql = "INSERT INTO Tracks (user, trackId) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userID);
            pstmt.setString(2, Arrays.toString(trackIds));
            pstmt.executeUpdate();
            LOGGER.info("Top Tracks added successfully: " + userID);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to add top tracks: ", e);
        }
    }

    private void streamSong(String accessToken, String context_uri, String[] uris, Integer offset, Integer position_ms) throws IOException, InterruptedException {

        //Jsonbody überarbeiten
        String jsonBody = "{'context_uri':" + context_uri + ",'offset':{'position':" + offset + "},'position_ms':" + position_ms + "'uris':[" + uris + "]}";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("'https://api.spotify.com/v1/me/player/play"))
                // API call der die top 5 tracks holt
                .header("Authorization", "Bearer " + accessToken)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}