package fm.statify.backend_service.controller;

import fm.statify.backend_service.auth.SpotifyOAuth;
import fm.statify.backend_service.entities.Playlist;
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

    private final Parser parser = new Parser();


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
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "redirect:/";
    }

    @GetMapping("/profile")
    @ResponseBody
    public UserProfile getProfileInfo() throws IOException {
        String response = performRequest("https://api.spotify.com/v1/me");
        return parser.parseUserProfile(response);
    }

    @GetMapping("/playlists")
    @ResponseBody
    public List<Playlist> getUsersPlaylists() throws IOException {
        String response = performRequest("https://api.spotify.com/v1/me/playlists");
        return parser.parsePlaylists(response);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exceptionHandler(Exception ex, Model model) {
        ex.printStackTrace();

        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    private String performRequest(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");

        httpConn.setRequestProperty("Authorization", "Bearer " + ACCESS_TOKEN);

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");

        return s.hasNext() ? s.next() : "";
    }
}

