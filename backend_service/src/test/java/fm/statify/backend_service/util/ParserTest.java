package fm.statify.backend_service.util;

import fm.statify.backend_service.entities.Artist;
import fm.statify.backend_service.entities.Playlist;
import fm.statify.backend_service.entities.SimpleTrack;
import fm.statify.backend_service.entities.UserProfile;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParserTest {

    @Test
    public void testParseUserProfile() {
        String userProfileJsonString = "{\"id\":\"user123\",\"display_name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"external_urls\":{\"spotify\":\"https://open.spotify.com/user/user123\"},\"images\":[{\"url\":\"https://example.com/profile.jpg\"}, {\"url\":\"https://example.com/profile.jpg\"}],\"product\":\"premium\"}";

        Parser parser = new Parser();

        UserProfile userProfile = parser.parseUserProfile(userProfileJsonString);

        assertNotNull(userProfile);
        assertEquals("user123", userProfile.getId());
        assertEquals("John Doe", userProfile.getUserName());
        assertEquals("john.doe@example.com", userProfile.getEmail());
        assertEquals("https://open.spotify.com/user/user123", userProfile.getUserURL());
        assertEquals("https://example.com/profile.jpg", userProfile.getProfilePictureURL());
        assertEquals("premium", userProfile.getProduct());
    }

    @Test
    public void testParsePlaylists() {
        String playlistsJsonString = "{\"items\":[{\"id\":\"playlist1\",\"name\":\"My Playlist\",\"images\":[{\"url\":\"https://example.com/playlist.jpg\"}]}]}";

        Parser parser = new Parser();

        List<Playlist> playlists = parser.parsePlaylists(playlistsJsonString);

        assertNotNull(playlists);
        assertEquals(1, playlists.size());

        Playlist playlist = playlists.get(0);
        assertEquals("playlist1", playlist.getId());
        assertEquals("My Playlist", playlist.getName());
        assertEquals("https://example.com/playlist.jpg", playlist.getImageURL());
    }

    @Test
    public void testParseTopTracks() {
        String topTracksJsonString = "{\"items\":[{\"id\":\"track1\",\"name\":\"Track 1\",\"album\":{\"images\":[{\"url\":\"https://example.com/track1.jpg\"}]},\"artists\":[{\"name\":\"Artist 1\"}]}]}";

        Parser parser = new Parser();

        List<SimpleTrack> topTracks = parser.parseTopTracks(topTracksJsonString);

        assertNotNull(topTracks);
        assertEquals(1, topTracks.size());

        SimpleTrack track = topTracks.get(0);
        assertEquals("track1", track.getId());
        assertEquals("Track 1", track.getName());
        assertEquals("https://example.com/track1.jpg", track.getImageUrl());
        assertEquals(1, track.getArtists().size());
        assertEquals("Artist 1", track.getArtists().get(0));
    }

    @Test
    public void testParseSimpleTrack() throws JSONException {
        String trackJsonString = "{\"id\":\"track1\",\"name\":\"Track 1\",\"album\":{\"images\":[{\"url\":\"https://example.com/track1.jpg\"}]},\"artists\":[{\"name\":\"Artist 1\"}]}";

        Parser parser = new Parser();

        SimpleTrack simpleTrack = parser.parseSimpleTrack(new JSONObject(trackJsonString));

        assertNotNull(simpleTrack);
        assertEquals("track1", simpleTrack.getId());
        assertEquals("Track 1", simpleTrack.getName());
        assertEquals("https://example.com/track1.jpg", simpleTrack.getImageUrl());
        assertEquals(1, simpleTrack.getArtists().size());
        assertEquals("Artist 1", simpleTrack.getArtists().get(0));
    }

    @Test
    public void testParseTopArtists() {
        String topArtistsJsonString = "{\"items\":[{\"id\":\"artist1\",\"name\":\"Artist 1\",\"images\":[{\"url\":\"https://example.com/artist1.jpg\"}]}]}";

        Parser parser = new Parser();

        List<Artist> topArtists = parser.parseTopArtists(topArtistsJsonString);

        assertNotNull(topArtists);
        assertEquals(1, topArtists.size());

        Artist artist = topArtists.get(0);
        assertEquals("artist1", artist.getId());
        assertEquals("Artist 1", artist.getName());
        assertEquals("https://example.com/artist1.jpg", artist.getImageUrl());
    }

    @Test
    public void testParseArtist() {
        String artistJsonString = "{\"id\":\"artist1\",\"name\":\"Artist 1\",\"images\":[{\"url\":\"https://example.com/artist1.jpg\"}]}";

        Parser parser = new Parser();

        Artist artist = parser.parseArtist(artistJsonString);

        assertNotNull(artist);
        assertEquals("artist1", artist.getId());
        assertEquals("Artist 1", artist.getName());
        assertEquals("https://example.com/artist1.jpg", artist.getImageUrl());
    }
}