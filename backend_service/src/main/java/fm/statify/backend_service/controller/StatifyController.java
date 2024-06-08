package fm.statify.backend_service.controller;

import fm.statify.backend_service.entities.Artist;
import fm.statify.backend_service.entities.SimpleTrack;
import fm.statify.backend_service.entities.User;
import fm.statify.backend_service.stats.PlaylistStatistics;
import fm.statify.backend_service.stats.TopArtistStatistics;
import fm.statify.backend_service.stats.TopTrackStatistics;
import fm.statify.backend_service.util.DBManager;
import fm.statify.backend_service.util.HTTPHelper;
import fm.statify.backend_service.util.Parser;
import fm.statify.backend_service.util.PlaylistStatisticsGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class StatifyController {

    private final HTTPHelper http = new HTTPHelper();
    private final Parser parser = new Parser();
    private final SpotifyController spotifyController;
    private final DBManager db = new DBManager();

    @Autowired
    public StatifyController(SpotifyController spotifyController) {
        this.spotifyController = spotifyController;
    }

    @GetMapping("generate/tracks")
    @ResponseBody
    public TopTrackStatistics generateSongStatistics(@RequestParam String userId, @RequestParam String time_range) throws IOException {
        String accessToken = getAccessTokenByUserID(userId);
        List<SimpleTrack> topTracks = parser.parseTopTracks(http.performRequest("https://api.spotify.com/v1/me/top/tracks/?limit=5&time_range=" + time_range, accessToken));
        String user_guid = db.getUserGuid(userId);
        Date date = java.sql.Date.valueOf(LocalDate.now());
        db.insertTopTrackStatistics(user_guid,
                topTracks.get(0).getId(),
                topTracks.get(1).getId(),
                topTracks.get(2).getId(),
                topTracks.get(3).getId(),
                topTracks.get(4).getId(),
                date);
        return new TopTrackStatistics(userId, topTracks.get(0), topTracks.get(1), topTracks.get(2), topTracks.get(3), topTracks.get(4));
    }


    @GetMapping("generate/artists")
    @ResponseBody
    public TopArtistStatistics generateArtistStatistics(@RequestParam String userId, @RequestParam String time_range) throws IOException {
        String accessToken = getAccessTokenByUserID(userId);
        List<Artist> topArtists = parser.parseTopArtists(http.performRequest("https://api.spotify.com/v1/me/top/artists/?limit=5&time_range=" + time_range, accessToken));
        String user_guid = db.getUserGuid(userId);
        Date date = java.sql.Date.valueOf(LocalDate.now());
        db.insertTopArtistsStatistics(user_guid,
                topArtists.get(0).getId(),
                topArtists.get(1).getId(),
                topArtists.get(2).getId(),
                topArtists.get(3).getId(),
                topArtists.get(4).getId(),
                date);
        return new TopArtistStatistics(userId, topArtists.get(0), topArtists.get(1), topArtists.get(2), topArtists.get(3), topArtists.get(4));
    }


    @GetMapping("generate/playlists")
    @ResponseBody
    public PlaylistStatistics generatePlaylistStatistics(@RequestParam String userId, @RequestParam String playlistId) {
        String accessToken = getAccessTokenByUserID(userId);
        PlaylistStatistics playlistStatistics = PlaylistStatisticsGenerator.generatePlaylistStatistics(userId, playlistId, accessToken);

        java.sql.Date generate_date = new java.sql.Date(playlistStatistics.getGenerateDate().getTime());
        String user_guid = db.getUserGuid(userId);

        db.insertPlaylist(user_guid,
                playlistId,
                playlistStatistics.getName(),
                playlistStatistics.getTracksNumber(),
                playlistStatistics.getDuration(),
                playlistStatistics.getTopGenre(),
                playlistStatistics.getTopGenreTracksNumber(),
                playlistStatistics.getTopArtist(),
                playlistStatistics.getTopArtistTracksNumber(),
                generate_date);

        return playlistStatistics;
    }


    @GetMapping("statistics/tracks")
    @ResponseBody
    public List<TopTrackStatistics> getTopTracksStatistics(@RequestParam String userId) {
        try {
            return db.getUsersTopTracksStats(userId);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("statistics/artists")
    @ResponseBody
    public List<TopArtistStatistics> getTopArtistsStatistics(@RequestParam String userId) {
        try {
            return db.getUsersTopArtistsStats(userId);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("statistics/playlists")
    @ResponseBody
    public List<PlaylistStatistics> getPlaylistStatistics(@RequestParam String userId) {
        try {
            return db.getUsersPlaylistStats(userId);
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("statistics/delete")
    public void deleteStatistics(@RequestParam String userId) {
        // TODO: remove all statistics for user from database
    }

    @DeleteMapping("delete")
    public void deleteData(@RequestParam String userId) {
        // TODO: remove all statistics and user data for user from database
    }

    private String getAccessTokenByUserID(String userID) {
        Map<String, User> userData = this.spotifyController.getUserData();
        User user = userData.get(userID);
        return user.getAccessToken();
    }
}

