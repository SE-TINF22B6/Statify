package fm.statify.backend_service.controller;

import fm.statify.backend_service.entities.Artist;
import fm.statify.backend_service.entities.SimpleTrack;
import fm.statify.backend_service.entities.User;
import fm.statify.backend_service.stats.PlaylistStatistics;
import fm.statify.backend_service.stats.TopArtistStatistics;
import fm.statify.backend_service.stats.TopTrackStatistics;
import fm.statify.backend_service.util.HTTPHelper;
import fm.statify.backend_service.util.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class StatifyController {

    private final HTTPHelper http = new HTTPHelper();
    private final Parser parser = new Parser();
    private final SpotifyController spotifyController;

    @Autowired
    public StatifyController(SpotifyController spotifyController) {
        this.spotifyController = spotifyController;
    }

    @GetMapping("generate/tracks")
    @ResponseBody
    public TopTrackStatistics generateSongStatistics(@RequestParam String userId, @RequestParam String time_range) throws IOException {
        String accessToken = getAccessTokenByUserID(userId);
        List<SimpleTrack> topTracks = parser.parseTopTracks(http.performRequest("https://api.spotify.com/v1/me/top/tracks/?limit=5&time_range=" + time_range, accessToken));
        return new TopTrackStatistics(userId, topTracks.get(0), topTracks.get(1), topTracks.get(2), topTracks.get(3), topTracks.get(4));
    }


    @GetMapping("generate/artists")
    @ResponseBody
    public TopArtistStatistics generateArtistStatistics(@RequestParam String userId) {
        //todo: logic for getting top artists
        return new TopArtistStatistics(userId,
                new Artist("artist1Id", "Artist 1", "https://i.scdn.co/image/ab67616d0000b2737359994525d219f64872d3b1"),
                new Artist("artist2Id", "Artist 2", "https://i.scdn.co/image/ab67616d0000b2737359994525d219f64872d3b1"),
                new Artist("artist3Id", "Artist 3", "https://i.scdn.co/image/ab67616d0000b2737359994525d219f64872d3b1"),
                new Artist("artist4Id", "Artist 4", "https://i.scdn.co/image/ab67616d0000b2737359994525d219f64872d3b1"),
                new Artist("artist5Id", "Artist 5", "https://i.scdn.co/image/ab67616d0000b2737359994525d219f64872d3b1")
        );
    }


    @GetMapping("generate/playlists")
    @ResponseBody
    public PlaylistStatistics generatePlaylistStatistics(@RequestParam String userId, @RequestParam String playlistId) {
        //todo: logic for generating playlist statistics
        return new PlaylistStatistics(userId, playlistId, "Playlist", 62, 420, "Edm", 26, "The Weeknd", 23);
    }


    @GetMapping("statistics/tracks")
    @ResponseBody
    public List<TopTrackStatistics> getTopTracksStatistics(@RequestParam String userId) {
        //todo: get top tracks statistics for user from database

        List<TopTrackStatistics> list = new ArrayList<>();
        list.add(new TopTrackStatistics(userId,
                new SimpleTrack("trackId1", "Track 1", "https://i.scdn.co/image/ab67616d0000b2737359994525d219f64872d3b1", List.of("Artist 1, Artist 2")),
                new SimpleTrack("trackId2", "Track 2", "https://i.scdn.co/image/ab67616d0000b2737359994525d219f64872d3b1", List.of("Artist 1, Artist 2")),
                new SimpleTrack("trackId3", "Track 3", "https://i.scdn.co/image/ab67616d0000b2737359994525d219f64872d3b1", List.of("Artist 1, Artist 2")),
                new SimpleTrack("trackId4", "Track 4", "https://i.scdn.co/image/ab67616d0000b2737359994525d219f64872d3b1", List.of("Artist 1, Artist 2")),
                new SimpleTrack("trackId5", "Track 5", "https://i.scdn.co/image/ab67616d0000b2737359994525d219f64872d3b1", List.of("Artist 1, Artist 2"))
        ));

        list.add(new TopTrackStatistics(userId,
                new SimpleTrack("trackId1", "Track 11", "https://i.scdn.co/image/ab67616d0000b2734d53259fd1703f4d345d2ac3", List.of("Artist 1, Artist 2")),
                new SimpleTrack("trackId2", "Track 22", "https://i.scdn.co/image/ab67616d0000b2734d53259fd1703f4d345d2ac3", List.of("Artist 1, Artist 2")),
                new SimpleTrack("trackId3", "Track 33", "https://i.scdn.co/image/ab67616d0000b2734d53259fd1703f4d345d2ac3", List.of("Artist 1, Artist 2")),
                new SimpleTrack("trackId4", "Track 44", "https://i.scdn.co/image/ab67616d0000b2734d53259fd1703f4d345d2ac3", List.of("Artist 1, Artist 2")),
                new SimpleTrack("trackId5", "Track 55", "https://i.scdn.co/image/ab67616d0000b2734d53259fd1703f4d345d2ac3", List.of("Artist 1, Artist 2"))
        ));

        return list;
    }

    @GetMapping("statistics/artists")
    @ResponseBody
    public List<TopArtistStatistics> getTopArtistsStatistics(@RequestParam String userId) {
        //todo: get top artist statistics for user from database

        List<TopArtistStatistics> list = new ArrayList<>();
        list.add(new TopArtistStatistics(userId,
                new Artist("artist1Id", "Artist 1", "https://i.scdn.co/image/ab67616d0000b2735076e4160d018e378f488c33"),
                new Artist("artist2Id", "Artist 2", "https://i.scdn.co/image/ab67616d0000b2735076e4160d018e378f488c33"),
                new Artist("artist3Id", "Artist 3", "https://i.scdn.co/image/ab67616d0000b2735076e4160d018e378f488c33"),
                new Artist("artist4Id", "Artist 4", "https://i.scdn.co/image/ab67616d0000b2735076e4160d018e378f488c33"),
                new Artist("artist5Id", "Artist 5", "https://i.scdn.co/image/ab67616d0000b2735076e4160d018e378f488c33")
        ));
        list.add(new TopArtistStatistics(userId,
                new Artist("artist1Id", "Artist 11", "https://i.scdn.co/image/ab67616d0000b2734d53259fd1703f4d345d2ac3"),
                new Artist("artist2Id", "Artist 22", "https://i.scdn.co/image/ab67616d0000b2734d53259fd1703f4d345d2ac3"),
                new Artist("artist3Id", "Artist 33", "https://i.scdn.co/image/ab67616d0000b2734d53259fd1703f4d345d2ac3"),
                new Artist("artist4Id", "Artist 44", "https://i.scdn.co/image/ab67616d0000b2734d53259fd1703f4d345d2ac3"),
                new Artist("artist5Id", "Artist 55", "https://i.scdn.co/image/ab67616d0000b2734d53259fd1703f4d345d2ac3")
        ));

        return list;
    }

    @GetMapping("statistics/playlists")
    @ResponseBody
    public List<PlaylistStatistics> getPlaylistStatistics(@RequestParam String userId) {
        //todo: get playlist statistics for user from database

        List<PlaylistStatistics> list = new ArrayList<>();
        list.add(new PlaylistStatistics(userId, "playlist1Id", "Playlist 1", 123, 40, "Pop", 22, "Giant Rooks", 12));
        list.add(new PlaylistStatistics(userId, "playlist2Id", "Playlist 2", 109, 120, "Rock", 31, "Imagine Dragons", 20));

        return list;
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

