package fm.statify.backend_service.util;

import fm.statify.backend_service.entities.ArtistWithGenre;
import fm.statify.backend_service.entities.PlaylistWithSimplePlaylistTracks;
import fm.statify.backend_service.entities.SimplePlaylistTrack;
import fm.statify.backend_service.stats.PlaylistStatistics;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

public class PlaylistStatisticsGeneratorTest {

    @Mock
    HTTPHelper httpHelper;

    @Mock
    Parser parser;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        try {

            Field field1 = PlaylistStatisticsGenerator.class.getDeclaredField("httpHelper");
            Field field2 = PlaylistStatisticsGenerator.class.getDeclaredField("parser");

            field1.setAccessible(true);
            field2.setAccessible(true);

            field1.set(null, httpHelper);
            field2.set(null, parser);

            doReturn("playlistResult").when(httpHelper).performRequest("https://api.spotify.com/v1/playlists/" + "playlistId", "accessToken");

            List<SimplePlaylistTrack> tracks = new ArrayList<>();
            tracks.add(new SimplePlaylistTrack("id1", "name1", null, List.of("ArtistId1"), new HashSet<>(), 140));
            tracks.add(new SimplePlaylistTrack("id2", "name2", null, List.of("ArtistId1", "ArtistId2", "ArtistId3"), new HashSet<>(), 210));
            tracks.add(new SimplePlaylistTrack("id3", "name3", null, List.of("ArtistId2"), new HashSet<>(), 180));
            tracks.add(new SimplePlaylistTrack("id4", "name4", null, List.of("ArtistId2"), new HashSet<>(), 320));
            PlaylistWithSimplePlaylistTracks playlist1 = new PlaylistWithSimplePlaylistTracks("playlistId", "playlistName", null, tracks);
            doReturn(playlist1).when(parser).parsePlaylistWithSimpleTracks(any());

            doReturn("artistsResult").when(httpHelper).performRequest(ArgumentMatchers.startsWith("https://api.spotify.com/v1/artists?ids="), any());
            doReturn(List.of(new ArtistWithGenre("ArtistId1", "Artist1", null, List.of("genre1", "genre2")),
                    new ArtistWithGenre("ArtistId2", "Artist2", null, List.of("genre2", "genre4")),
                    new ArtistWithGenre("ArtistId3", "Artist3", null, List.of("genre3"))))
                    .when(parser).parseArtists(any());

        }catch (Exception e){
            System.out.println();
        }


    }

    @Test
    public void testGeneratePlaylistStatistics(){
        String userId = "userId";
        String playlistId = "playlistId";
        String accessToken = "accessToken";

        PlaylistStatistics stats = PlaylistStatisticsGenerator.generatePlaylistStatistics(userId, playlistId, accessToken);

        assertNotNull(stats);
        assertEquals(140+210+180+320, stats.getDuration());
        assertEquals("playlistId", stats.getPlaylistId());
        assertEquals("playlistName", stats.getName());
        assertEquals("genre2", stats.getTopGenre());
        assertEquals(4, stats.getTopGenreTracksNumber());
        assertEquals("Artist2", stats.getTopArtist());
        assertEquals(3, stats.getTopArtistTracksNumber());
        assertEquals(4, stats.getTracksNumber());
        assertEquals("userId", stats.getUserId());
    }
}
