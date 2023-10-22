package fm.statify.backend_service.controller;

import fm.statify.backend_service.auth.SpotifyOAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SpotifyController {

    private final SpotifyOAuth spotifyOAuth;

    public SpotifyController(SpotifyOAuth spotifyOAuth) {
        this.spotifyOAuth = spotifyOAuth;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/authorize")
    public String authorize() throws Exception {
        return "redirect:" + spotifyOAuth.getAuthUrl();
    }
}

