package fm.statify.backend_service.util;

import fm.statify.backend_service.entities.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

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

    public List<Playlist> parsePlaylists(String response) {
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
            return playlists;
        }

    }

    public List<SimpleTrack> parseTopTracks(String response) {
        List<SimpleTrack> topTracks = new ArrayList<>();
        try {
            JSONObject responseJson = new JSONObject(response);
            JSONArray tracks = responseJson.getJSONArray("items");

            for (int i = 0; i < tracks.length(); i++) {
                JSONObject trackJson = tracks.getJSONObject(i);

                String id = trackJson.getString("id");
                String name = trackJson.getString("name");

                String imageURL = null;
                JSONObject album = trackJson.getJSONObject("album");
                JSONArray images = album.getJSONArray("images");
                if (!images.isEmpty()) {
                    imageURL = images.getJSONObject(0).getString("url");
                }

                List<String> artists = new ArrayList<>();
                JSONArray artistsJson = trackJson.getJSONArray("artists");
                for (int x = 0; x < artistsJson.length(); x++) {
                    JSONObject artist = artistsJson.getJSONObject(x);
                    artists.add(artist.getString("name"));
                }

                topTracks.add(new SimpleTrack(id, name, imageURL, artists));
            }

            return topTracks;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Artist> parseTopArtists(String response) {
        List<Artist> topArtists = new ArrayList<>();
        try {
            JSONObject responseJson = new JSONObject(response);
            JSONArray artists = responseJson.getJSONArray("items");

            for (int i = 0; i < artists.length(); i++) {
                JSONObject artistJson = artists.getJSONObject(i);

                String id = artistJson.getString("id");
                String name = artistJson.getString("name");

                String imageURL = null;
                JSONArray images = artistJson.getJSONArray("images");
                if (!images.isEmpty()) {
                    imageURL = images.getJSONObject(0).getString("url");
                }
                topArtists.add(new Artist(id, name, imageURL));
            }

            return topArtists;
        } catch (Exception e) {
            return null;
        }
    }

    public PlaylistWithSimplePlaylistTracks parsePlaylistWithSimpleTracks(String response){
        try{
            JSONObject responseJson = new JSONObject(response);
            String id = responseJson.getString("id");
            String name = responseJson.getString("name");

            String imageURL = null;

            JSONArray images = responseJson.getJSONArray("images");
            if (!images.isEmpty()) {
                imageURL = images.getJSONObject(0).getString("url");
            }

            List<SimplePlaylistTrack> simpleTracks = new ArrayList<>();

            JSONArray tracks = responseJson.getJSONObject("tracks").getJSONArray("items");
            for (int i = 0; i < tracks.length(); i++) {
                JSONObject track = tracks.getJSONObject(i).getJSONObject("track");
                SimplePlaylistTrack t = parsePlaylistTrack(track);
                simpleTracks.add(t);
            }

            return new PlaylistWithSimplePlaylistTracks(id, name, imageURL, simpleTracks); //hier duration 0
        }
        catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    private SimplePlaylistTrack parsePlaylistTrack(JSONObject track){
        String id = track.getString("id");
        String name = track.getString("name");
        int duration = track.getInt("duration_ms");
        List<String> artists = new ArrayList<>();
        Set<String> genres = new HashSet<>();

        JSONArray artistsArr = track.getJSONArray("artists");
        for (int i = 0; i < artistsArr.length(); i++) {
            JSONObject artist = artistsArr.getJSONObject(i);
            artists.add(artist.getString("name"));

            // Never returns genres // TODO: add genres later -> artists endpoint

            if(artist.has("genres")){
                JSONArray genresArr = artist.getJSONArray("genres");
                for (int ii = 0; ii < genresArr.length(); ii++) {
                    String genre = genresArr.getString(ii);
                    genres.add(genre);
                }
            }
        }
        SimplePlaylistTrack t = new SimplePlaylistTrack(id, name, null, artists, genres, duration);
        return t;
    }
}
