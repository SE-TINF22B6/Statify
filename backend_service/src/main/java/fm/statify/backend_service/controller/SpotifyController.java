package fm.statify.backend_service.controller;

import fm.statify.backend_service.auth.SpotifyOAuth;
import fm.statify.backend_service.entities.Playlist;
import fm.statify.backend_service.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

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
    public String authorize(){
        return spotifyOAuth.getAuthUrl();
    }


    // Most important part currently - Handle the callback from Spotify after the user has logged in - currently just prints the code
    @GetMapping("/callback")
    public String callback(@RequestParam String code) {
        System.out.println("Received code: " + code);

        try {
            String response = spotifyOAuth.requestAccessToken(code);
            tokenData = spotifyOAuth.parseResponse(response);
            System.out.println("Token Data: " + tokenData);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "redirect:/";
    }

    @GetMapping("/profile")
    @ResponseBody
    public User getProfileInfo() throws IOException {

        System.out.println(tokenData.get("access_token"));
            URL url = new URL("https://api.spotify.com/v1/me");
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");

            httpConn.setRequestProperty("Authorization", "Bearer " + tokenData.get("access_token"));

            InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                    ? httpConn.getInputStream()
                    : httpConn.getErrorStream();
            Scanner s = new Scanner(responseStream).useDelimiter("\\A");
            String response = s.hasNext() ? s.next() : "";
            System.out.println(response);
        //TODO: get users profile info from Spotify

        return new User("1234", "userName", "max.mustermann@web.de", "https://open.spotify.com/user/smedjan", "https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228", "premium");
    }

    @GetMapping("/playlists")
    @ResponseBody
    public List<Playlist> getUsersPlaylists(){
        //TODO: get users playlists from Spotify
        List<Playlist> list = new ArrayList<>();

        list.add(new Playlist("1234", "1. Playlist", "https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228"));
        list.add(new Playlist("5678", "2. Playlist", "https://wrapped-images.spotifycdn.com/image/yts-2023/default/your-top-songs-2023_DEFAULT_en-GB.jpg"));

        return list;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exceptionHandler(Exception ex, Model model) {
        ex.printStackTrace();

        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}

