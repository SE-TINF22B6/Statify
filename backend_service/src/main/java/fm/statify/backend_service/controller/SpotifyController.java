package fm.statify.backend_service.controller;

import fm.statify.backend_service.auth.SpotifyOAuth;
import fm.statify.backend_service.entities.*;
import fm.statify.backend_service.util.DBManager;
import fm.statify.backend_service.util.DatabaseManager;
import fm.statify.backend_service.util.HTTPHelper;
import fm.statify.backend_service.util.Parser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.json.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Controller
@RequestMapping("/")
@Component
public class SpotifyController {

    // This Class is responsible for handling the Spotify OAuth flow
    private final SpotifyOAuth spotifyOAuth;
    private Map<String, User> userData = new HashMap<>();

    private final Parser parser = new Parser();
    private final HTTPHelper http = new HTTPHelper();

    private final DBManager db = new DBManager();


    SpotifyController(SpotifyOAuth spotifyOAuth) {
        this.spotifyOAuth = spotifyOAuth;
    }

    public Map<String, String> tokenData;

    public Map<String, User> getUserData() {
        return userData;
    }

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
    @ResponseBody
    public String callback(@RequestParam String code) throws IOException, InterruptedException {
        System.out.println("Received code: " + code);

        String response = spotifyOAuth.requestAccessToken(code);
        tokenData = spotifyOAuth.parseResponse(response);
        JSONObject tokenDataJson = new JSONObject(tokenData);
        String access_token = tokenDataJson.getString("access_token");
        String refresh_token = tokenDataJson.getString("refresh_token");
        int expires_in = Integer.parseInt(tokenDataJson.getString("expires_in"));

        UserProfile profile = getProfile(access_token);

        userData.put(profile.getId(), new User(
                profile.getId(),
                access_token,
                new Date(System.currentTimeMillis() + 1000 * expires_in),
                refresh_token
        ));

        if (!(db.userExists(profile.getId()))) {
            db.insertUser(access_token, refresh_token, profile.getId());
        }

        System.out.println("Token Data: " + tokenData);

        return profile.getId();
    }

    @GetMapping("/profile")
    @ResponseBody
    public UserProfile getProfileInfo(String userId) throws Exception {
        UserProfile profile = getProfile(getUser(userId).getValidAccessToken(spotifyOAuth));

        return profile;
    }

    public UserProfile getProfile(String access_token) throws IOException, InterruptedException {
        String response = http.performRequest("https://api.spotify.com/v1/me", access_token);
        return parser.parseUserProfile(response);

    }

    @GetMapping("/playlists")
    @ResponseBody
    public List<Playlist> getUsersPlaylists(@RequestParam String userId) throws Exception {
        String response = http.performRequest("https://api.spotify.com/v1/me/playlists", getUser(userId).getValidAccessToken(spotifyOAuth));
        return parser.parsePlaylists(response);
    }

    @GetMapping("/track")
    @ResponseBody
    public Track getTrackWithAudioFeatures(@RequestParam String userID, @RequestParam String trackId) throws Exception {
        String accessToken = getUser(userID).getValidAccessToken(spotifyOAuth);
        String responseTrack = http.performRequest("https://api.spotify.com/v1/tracks/" + trackId, accessToken);
        String responseAudioFeatures = http.performRequest("https://api.spotify.com/v1/audio-features/" + trackId, accessToken);
        AudioFeatures audioFeatures = parser.parseAudioFeatures(responseAudioFeatures);
        return parser.parseTrackForTrackPage(trackId, responseTrack, audioFeatures);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exceptionHandler(Exception ex, Model model) {
        ex.printStackTrace();

        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    private User getUser(String userId) throws Exception {
        if (userData.containsKey(userId)) {
            return userData.get(userId);
        } else {
            throw new Exception("User ID not found");
        }
    }
}

