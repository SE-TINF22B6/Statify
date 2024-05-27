package fm.statify.backend_service.controller;

import fm.statify.backend_service.stats.PlaylistStatistics;
import fm.statify.backend_service.stats.TopArtistStatistics;
import fm.statify.backend_service.stats.TopTrackStatistics;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class StatifyController {


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

    @GetMapping("statistics/artists")
    @ResponseBody
    public List<TopArtistStatistics> getTopArtistsStatistics(String userId){
        //todo get top artist statistics for user from database

        List<TopArtistStatistics> list = new ArrayList<>();
        list.add(new TopArtistStatistics());
        list.add(new TopArtistStatistics());

        return list;
    }
    @GetMapping("statistics/tracks")
    @ResponseBody
    public List<TopTrackStatistics> getTopTracksStatistics(String userId){
        //todo get top tracks statistics for user from database

        List<TopTrackStatistics> list = new ArrayList<>();
        list.add(new TopTrackStatistics());
        list.add(new TopTrackStatistics());

        return list;
    }
    @GetMapping("statistics/playlists")
    @ResponseBody
    public List<PlaylistStatistics> getPlaylistStatistics(String userId){
        //todo get playlist statistics for user from database

        List<PlaylistStatistics> list = new ArrayList<>();
        list.add(new PlaylistStatistics());
        list.add(new PlaylistStatistics());

        return list;
    }
}

