package fm.statify.backend_service.controller;

import fm.statify.backend_service.auth.SpotifyOAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

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
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return "redirect:/";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exceptionHandler(Exception ex, Model model) {
        ex.printStackTrace();

        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}

