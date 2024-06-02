package fm.statify.backend_service.util;

import fm.statify.backend_service.entities.Playlist;
import fm.statify.backend_service.entities.UserProfile;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public UserProfile parseUserProfile(String response) {
        JSONObject userProfileJson = new JSONObject(response);
        try {
            String id = userProfileJson.getString("id");
            String userName = userProfileJson.getString("display_name");
            String email = userProfileJson.getString("email");

            JSONObject external_urls = userProfileJson.getJSONObject("external_urls");
            String userURL = external_urls.getString("spotify");

            String profilePictureURL = null;
            JSONArray images = userProfileJson.getJSONArray("images");
            if (!images.isEmpty()) {
                profilePictureURL = images.getJSONObject(1).getString("url");
            }

            String product = userProfileJson.getString("product");
            return new UserProfile(id, userName, email, userURL, profilePictureURL, product);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Playlist> parsePlaylists(String response){
        List<Playlist> playlists = new ArrayList<>();

        try {
            JSONObject responseJson = new JSONObject(response);

            JSONArray playlistsJson = responseJson.getJSONArray("items");

            for (int i = 0; i < playlistsJson.length(); i++) {
                JSONObject playlistJson = playlistsJson.getJSONObject(i);
                String id = playlistJson.getString("id");
                String name = playlistJson.getString("name");

                String imageURL = null;
                JSONArray images = playlistJson.getJSONArray("images");
                if (!images.isEmpty()) {
                    imageURL = images.getJSONObject(0).getString("url");
                }

                Playlist playlist = new Playlist(id, name, imageURL);
                playlists.add(playlist);
            }

            return playlists;
        } catch (Exception e) {
            return null;
        }

    }
}
