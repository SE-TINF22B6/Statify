package fm.statify.backend_service.controller;

import fm.statify.backend_service.auth.SpotifyOAuth;
import fm.statify.backend_service.entities.AudioFeatures;
import fm.statify.backend_service.entities.Playlist;
import fm.statify.backend_service.entities.Track;
import fm.statify.backend_service.entities.UserProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.json.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Controller
@RequestMapping("/")
public class SpotifyController {

    // This Class is responsible for handling the Spotify OAuth flow
    private final SpotifyOAuth spotifyOAuth;
    private String ACCESS_TOKEN;
    private String REFRESH_TOKEN;


    public SpotifyController(SpotifyOAuth spotifyOAuth) {
        this.spotifyOAuth = spotifyOAuth;
    }

    public Map<String, String> tokenData;

    @GetMapping
    public String index() {
        return "index";
    }

    // Redirect the user to the Spotify login page
    @GetMapping("/authorize")
    @ResponseBody
    public String authorize() {
        return spotifyOAuth.getAuthUrl();
    }


    // Most important part currently - Handle the callback from Spotify after the user has logged in - currently just prints the code
    @GetMapping("/callback")
    public String callback(@RequestParam String code) {
        System.out.println("Received code: " + code);

        try {
            String response = spotifyOAuth.requestAccessToken(code);
            tokenData = spotifyOAuth.parseResponse(response);
            JSONObject tokenDataJson = new JSONObject(tokenData);
            ACCESS_TOKEN = tokenDataJson.getString("access_token");
            REFRESH_TOKEN = tokenDataJson.getString("refresh_token");
            // TODO: add the data into the user table in the database
            System.out.println("Token Data: " + tokenData);
            // TODO: return userID
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "redirect:/";
    }

    @GetMapping("/profile")
    @ResponseBody
    public UserProfile getProfileInfo() throws IOException {
        URL url = new URL("https://api.spotify.com/v1/me");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");

        httpConn.setRequestProperty("Authorization", "Bearer " + ACCESS_TOKEN);

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";

        return jsonToUserProfile(response);
    }

    @GetMapping("/playlists")
    @ResponseBody
    public List<Playlist> getUsersPlaylists() {
        //TODO: get users playlists from Spotify
        List<Playlist> list = new ArrayList<>();

        list.add(new Playlist("1234", "1. Playlist", "https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228"));
        list.add(new Playlist("5678", "2. Playlist", "https://wrapped-images.spotifycdn.com/image/yts-2023/default/your-top-songs-2023_DEFAULT_en-GB.jpg"));

        return list;
    }

    @GetMapping("/track")
    @ResponseBody
    public Track getTrackWithAudioFeatures(@RequestParam String trackId){
        // TODO: get track from Spotify
        AudioFeatures audioFeatures = new AudioFeatures(0.77f, 0.56f, 0.61f, 0.91f, 0.73f, -31, 0.73f, 0.74f, 0.36f, 5, 1);
        return new Track(trackId, "Track Name", "https://i.scdn.co/image/ab67616d0000b2737359994525d219f64872d3b1", List.of("Artist 1", "Artist 2"), 125, 12, audioFeatures);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exceptionHandler(Exception ex, Model model) {
        ex.printStackTrace();

        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }


    public UserProfile jsonToUserProfile(String response) {
        JSONObject userProfileJson = new JSONObject(response);
        try {
            String id = userProfileJson.getString("id");
            String userName = userProfileJson.getString("display_name");
            String email = userProfileJson.getString("email");

            JSONObject external_urls = userProfileJson.getJSONObject("external_urls");
            String userURL = external_urls.getString("spotify");

            JSONArray images = userProfileJson.getJSONArray("images");
            JSONObject firstImage = images.getJSONObject(1);
            String profilePictureURL = firstImage.getString("url");

            String product = userProfileJson.getString("product");
            return new UserProfile(id, userName, email, userURL, profilePictureURL, product);
        } catch (Exception e) {
            return null;
        }
    }
}

