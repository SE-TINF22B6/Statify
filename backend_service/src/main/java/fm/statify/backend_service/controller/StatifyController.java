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


    @GetMapping("generate/songs")
    @ResponseBody
    public TopTrackStatistics generateSongStatistics(String userId) {
        //todo: logic for getting top songs
        return new TopTrackStatistics(userId, "track1Id", "track2Id", "track3Id", "track4Id", "track5Id");
    }


    @GetMapping("generate/artists")
    @ResponseBody
    public TopArtistStatistics generateArtistStatistics(String userId) {
        //todo: logic for getting top artists
        return new TopArtistStatistics(userId, "artist1ID", "artist2ID", "artist3ID", "artist4ID", "artist5ID");
    }


    @GetMapping("generate/playlists")
    @ResponseBody
    public PlaylistStatistics generatePlaylistStatistics(String userId, String playlistId) {
        //todo: logic for generating playlist statistics
        return new PlaylistStatistics(userId, playlistId, "Playlist", 62, 420, "Edm", 26, "The Weeknd", 23);
    }

    @GetMapping("statistics/artists")
    @ResponseBody
    public List<TopArtistStatistics> getTopArtistsStatistics(String userId){
        //todo: get top artist statistics for user from database

        List<TopArtistStatistics> list = new ArrayList<>();
        list.add(new TopArtistStatistics(userId, "artist1ID", "artist2ID", "artist3ID", "artist4ID", "artist5ID"));
        list.add(new TopArtistStatistics(userId, "artist1ID", "artist2ID", "artist3ID", "artist4ID", "artist5ID"));

        return list;
    }

    @GetMapping("statistics/tracks")
    @ResponseBody
    public List<TopTrackStatistics> getTopTracksStatistics(String userId){
        //todo: get top tracks statistics for user from database

        List<TopTrackStatistics> list = new ArrayList<>();
        list.add(new TopTrackStatistics(userId, "track1Id", "track2Id", "track3Id", "track4Id", "track5Id"));
        list.add(new TopTrackStatistics(userId, "track1Id", "track2Id", "track3Id", "track4Id", "track5Id"));

        return list;
    }

    @GetMapping("statistics/playlists")
    @ResponseBody
    public List<PlaylistStatistics> getPlaylistStatistics(String userId){
        //todo: get playlist statistics for user from database

        List<PlaylistStatistics> list = new ArrayList<>();
        list.add(new PlaylistStatistics(userId, "playlist1Id", "Playlist 1", 123, 40, "Pop", 22, "Giant Rooks", 12));
        list.add(new PlaylistStatistics(userId, "playlist2Id", "Playlist 2", 109, 120, "Rock", 31, "Imagine Dragons", 20));

        return list;
    }
}

