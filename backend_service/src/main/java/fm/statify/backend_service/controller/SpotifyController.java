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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class SpotifyController {

    // This Class is responsible for handling the Spotify OAuth flow
    private final SpotifyOAuth spotifyOAuth;

    public SpotifyController(SpotifyOAuth spotifyOAuth) {
        this.spotifyOAuth = spotifyOAuth;
    }

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
            Map<String, String> tokenData = spotifyOAuth.parseResponse(response);
            System.out.println("Token Data: " + tokenData);
            //todo: return userID
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "redirect:/";
    }

    @GetMapping("/profile")
    @ResponseBody
    public UserProfile getProfileInfo(@RequestParam String userId){
        //TODO: get users profile info from Spotify
        return new UserProfile(userId, "userName", "max.mustermann@web.de", "https://open.spotify.com/user/smedjan", "https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228", "premium");
    }

    @GetMapping("/playlists")
    @ResponseBody
    public List<Playlist> getUsersPlaylists(@RequestParam String userId){
        //TODO: get users playlists from Spotify
        List<Playlist> list = new ArrayList<>();

        list.add(new Playlist("1234", "1. Playlist", "https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228"));
        list.add(new Playlist("5678", "2. Playlist", "https://wrapped-images.spotifycdn.com/image/yts-2023/default/your-top-songs-2023_DEFAULT_en-GB.jpg"));

        return list;
    }

    @GetMapping("/track")
    @ResponseBody
    public Track getTrackWithAudioFeatures(@RequestParam String trackId){
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
}

