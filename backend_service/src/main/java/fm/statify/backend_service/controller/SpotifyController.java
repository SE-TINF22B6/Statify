package fm.statify.backend_service.controller;

import fm.statify.backend_service.auth.SpotifyOAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @GetMapping("/callback")
    public String callback(@RequestParam String code) {
        // Here, you'll handle the callback and extract the authorization code
        // For now, let's just print the code and redirect the user to the homepage
        System.out.println("Received code: " + code);
        return "redirect:/";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exceptionHandler(Exception ex, Model model) {
        // Log the exception for debugging purposes
        ex.printStackTrace();

        // Send the error message to the frontend
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";  // This will use an error.html template
    }
}

