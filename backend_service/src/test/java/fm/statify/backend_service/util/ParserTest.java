package fm.statify.backend_service.util;

import fm.statify.backend_service.entities.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.spy;

public class ParserTest {

    @Spy
    private Parser parser;

    @Before
    public void setUp(){
        parser = new Parser();
    }

    @Test
    public void testParseUserProfile() {
        String userProfileJsonString = "{\"id\":\"user123\",\"display_name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"external_urls\":{\"spotify\":\"https://open.spotify.com/user/user123\"},\"images\":[{\"url\":\"https://example.com/profile.jpg\"}, {\"url\":\"https://example.com/profile.jpg\"}],\"product\":\"premium\"}";


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

        Artist artist = parser.parseArtist(artistJsonString);

        assertNotNull(artist);
        assertEquals("artist1", artist.getId());
        assertEquals("Artist 1", artist.getName());
        assertEquals("https://example.com/artist1.jpg", artist.getImageUrl());
    }

    @Test
    public void testParsePlaylistWithSimpleTracks(){
        String playlistJsonString = """
                {
                  "id": "2IMSkENFQPDS7n0s98emD6",
                  "images": [
                    {
                      "url": "https://i.scdn.co/image/ab67616d00001e027b9888dc365b8f957c63df14",
                      "height": null,
                      "width": null
                    },
                    {
                      "url": "https://i.scdn.co/image/ab67616d00001e027b9888dc365b8f957c63df15",
                      "height": null,
                      "width": null
                    }
                  ],
                  "name": "Test",
                  "tracks": {
                    "total": 3,
                    "items": [
                      {
                        "track": {
                          
                        }
                      },
                      {
                        "track": {
                          
                        }
                      },
                      {
                        "track": {
                          
                        }
                      }
                    ]
                  },
                  "type": "playlist"
                }
                """;

        parser = spy(parser);

        doReturn(new SimplePlaylistTrack("id", "name", "image", new ArrayList<>(), new HashSet<>(), 42)).when(parser).parsePlaylistTrack(any());

        PlaylistWithSimplePlaylistTracks playlist = parser.parsePlaylistWithSimpleTracks(playlistJsonString);

        assertNotNull(playlist);
        assertEquals(playlist.getTracks().size(), 3);
        assertEquals(playlist.getId(), "2IMSkENFQPDS7n0s98emD6");
        assertEquals(playlist.getName(), "Test");
        assertEquals(playlist.getImageURL(), "https://i.scdn.co/image/ab67616d00001e027b9888dc365b8f957c63df14");
    }

    @Test
    public void testParsePlaylistTrack(){
        String trackString = """
                {
                  "artists": [
                    {
                      "external_urls": {
                        "spotify": "https://open.spotify.com/artist/4dpARuHxo51G3z768sgnrY"
                      },
                      "href": "https://api.spotify.com/v1/artists/4dpARuHxo51G3z768sgnrY",
                      "id": "4dpARuHxo51G3z768sgnrY",
                      "name": "Adele",
                      "type": "artist",
                      "uri": "spotify:artist:4dpARuHxo51G3z768sgnrY"
                    }
                  ],
                  "duration_ms": 295502,
                  "id": "1Yk0cQdMLx5RzzFTYwmuld",
                  "name": "Hello",
                  "popularity": 71,
                  "track": true
                }
                """;
        try {
            JSONObject trackJson = new JSONObject(trackString);
            SimplePlaylistTrack track = parser.parsePlaylistTrack(trackJson);

            assertNotNull(track);
            assertEquals(track.getName(),"Hello");
            assertEquals(track.getId(), "1Yk0cQdMLx5RzzFTYwmuld");
            assertNull(track.getImageUrl());
            assertEquals(track.getDuration(), 295502);
            assertEquals(track.getGenres(), new HashSet<>());
            assertEquals(track.getArtists(), new ArrayList<>(List.of("4dpARuHxo51G3z768sgnrY")));
        }
        catch (Exception e){ assert false; }
    }

    @Test
    public void testParseArtists(){
        String artistsString = """
                {
                  "artists": [
                    {
                      "genres": ["electropop", "etherpop", "indie poptimism", "pop"],
                      "id": "26VFTg2z8YR0cCuwLzESi2",
                      "name": "Halsey",
                      "popularity": 80,
                      "type": "artist"
                    },
                    {
                      "genres": ["pop"],
                      "id": "69GGBxA162lTqCwzJG5jLp",
                      "name": "The Chainsmokers",
                      "popularity": 81,
                      "type": "artist"
                    }
                  ]
                }
                """;

        List<ArtistWithGenre> artists = parser.parseArtists(artistsString);

        assertNotNull(artists);
        assertEquals(artists.size(), 2);
        assertEquals(artists.get(0).getId(), "26VFTg2z8YR0cCuwLzESi2");
        assertEquals(artists.get(0).getName(), "Halsey");
        assertNull(artists.get(0).getImageUrl());
        assertEquals(artists.get(0).getGenres(), List.of(new String[]{"electropop", "etherpop", "indie poptimism", "pop"}));

        assertEquals(artists.get(1).getId(), "69GGBxA162lTqCwzJG5jLp");
        assertEquals(artists.get(1).getName(), "The Chainsmokers");
        assertNull(artists.get(1).getImageUrl());
        assertEquals(artists.get(1).getGenres(), List.of(new String[]{"pop"}));
    }

    @Test
    public void testParseAudioFeatures(){
        String audioFeaturesString = """
                {
                  "acousticness": 0.414,
                  "analysis_url": "https://api.spotify.com/v1/audio-analysis/7BKLCZ1jbUBVqRi2FVlTVw",
                  "danceability": 0.748,
                  "duration_ms": 244960,
                  "energy": 0.524,
                  "id": "7BKLCZ1jbUBVqRi2FVlTVw",
                  "instrumentalness": 0,
                  "key": 8,
                  "liveness": 0.111,
                  "loudness": -5.599,
                  "mode": 1,
                  "speechiness": 0.0338,
                  "tempo": 95.01,
                  "time_signature": 4,
                  "track_href": "https://api.spotify.com/v1/tracks/7BKLCZ1jbUBVqRi2FVlTVw",
                  "type": "audio_features",
                  "uri": "spotify:track:7BKLCZ1jbUBVqRi2FVlTVw",
                  "valence": 0.661
                }
                """;

        AudioFeatures audioFeatures = parser.parseAudioFeatures(audioFeaturesString);

        assertNotNull(audioFeatures);
        assertEquals(audioFeatures.getAcousticness(), 0.414f, 0.00001);
        assertEquals(audioFeatures.getDanceability(), 0.748f, 0.00001);
        assertEquals(audioFeatures.getEnergy(), 0.524f, 0.00001);
        assertEquals(audioFeatures.getLiveness(), 0.111f, 0.00001);
        assertEquals(audioFeatures.getLoudness(), -5.599f, 0.00001);
        assertEquals(audioFeatures.getInstrumentalness(), 0f, 0.00001);
        assertEquals(audioFeatures.getTempo(), 95.01f, 0.00001);
        assertEquals(audioFeatures.getValence(), 0.661f, 0.00001);
        assertEquals(audioFeatures.getSpeechiness(), 0.0338f, 0.00001);
        assertEquals(audioFeatures.getKey(), 8);
        assertEquals(audioFeatures.getMode(), 1);
    }

    @Test
    public void testParseTrackForTrackPage(){
        String trackString = """
                {
                     "album": {
                       "images": [
                         {
                           "url": "https://i.scdn.co/image/ab67616d0000b273495ce6da9aeb159e94eaa453",
                           "height": 640,
                           "width": 640
                         },
                         {
                           "url": "https://i.scdn.co/image/ab67616d00001e02495ce6da9aeb159e94eaa453",
                           "height": 300,
                           "width": 300
                         },
                         {
                           "url": "https://i.scdn.co/image/ab67616d00004851495ce6da9aeb159e94eaa453",
                           "height": 64,
                           "width": 64
                         }
                       ]
                     },
                     "artists": [
                       {
                         "id": "69GGBxA162lTqCwzJG5jLp",
                         "name": "The Chainsmokers",
                         "type": "artist"
                       },
                       {
                         "id": "26VFTg2z8YR0cCuwLzESi2",
                         "name": "Halsey",
                         "type": "artist"
                       }
                     ],
                     "duration_ms": 244960,
                     "id": "7BKLCZ1jbUBVqRi2FVlTVw",
                     "name": "Closer",
                     "popularity": 83,
                     "type": "track"
                   }
                """;

        parser = spy(parser);
        AudioFeatures audioFeatures = new AudioFeatures(1,1,1,1,1,1,1,1,1,1, 1);

        Track track = parser.parseTrackForTrackPage("7BKLCZ1jbUBVqRi2FVlTVw", trackString, audioFeatures);

        assertNotNull(track);
        assertEquals(track.getId(), "7BKLCZ1jbUBVqRi2FVlTVw");
        assertEquals(track.getName(), "Closer");
        assertEquals(track.getDuration(), "4:04");
        assertEquals(track.getImageUrl(), "https://i.scdn.co/image/ab67616d0000b273495ce6da9aeb159e94eaa453");
        assertEquals(track.getArtists(), List.of("The Chainsmokers", "Halsey"));
        assertEquals(track.getPopularity(), 83);
        assertEquals(track.getAudioFeatures(), audioFeatures);
    }

}