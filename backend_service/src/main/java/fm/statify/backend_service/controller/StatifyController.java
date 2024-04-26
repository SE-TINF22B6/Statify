package fm.statify.backend_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class StatifyController {

    // Handler-Methode für die Startseite des Projekts
    @GetMapping("/home")
    public String homePage() {
        return "home"; // Rückgabe des Namens der HTML-Seite (z.B. "home.html")
    }

    // Handler-Methode für die Generierung von Statistiken über Songs
    @GetMapping("/songs")
    @ResponseBody
    public String generateSongStatistics() {
        // Hier könnte Logik zur Generierung von Song-Statistiken stehen
        return "Song statistics generated successfully!";
    }

    // Handler-Methode für die Generierung von Statistiken über Künstler
    @GetMapping("/artists")
    @ResponseBody
    public String generateArtistStatistics() {
        // Hier könnte Logik zur Generierung von Künstler-Statistiken stehen
        return "Artist statistics generated successfully!";
    }
}

