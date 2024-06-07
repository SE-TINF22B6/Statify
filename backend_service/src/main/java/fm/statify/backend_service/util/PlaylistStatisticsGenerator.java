package fm.statify.backend_service.util;

import fm.statify.backend_service.entities.*;
import fm.statify.backend_service.stats.PlaylistStatistics;

import java.io.IOException;
import java.util.*;

public class PlaylistStatisticsGenerator {
    private static final HTTPHelper httpHelper = new HTTPHelper();

    private static final Parser parser = new Parser();

    public static PlaylistStatistics generatePlaylistStatistics(String userId, String playlistId, String userAccessToken){
        PlaylistWithSimplePlaylistTracks playlistWithSimpleTracks = getPlaylist(playlistId, userAccessToken);
        if(playlistWithSimpleTracks == null){
            return null;
        }
        addGenresAndArtistNames(playlistWithSimpleTracks, userAccessToken);
        return calculatePlaylistStatistics(userId, playlistId, playlistWithSimpleTracks, userAccessToken);
    }

    private static PlaylistWithSimplePlaylistTracks getPlaylist(String playlistId, String userAccessToken){
        try {
            String result = httpHelper.performRequest("https://api.spotify.com/v1/playlists/" + playlistId, userAccessToken);
            PlaylistWithSimplePlaylistTracks playlist = parser.parsePlaylistWithSimpleTracks(result);
            return playlist;
        }
        catch (IOException e) {
            return null;
        }
    }

    private static void addGenresAndArtistNames(PlaylistWithSimplePlaylistTracks playlist, String userAccessToken){
        for(SimplePlaylistTrack t: playlist.getTracks()){
            try{
                String endpoint = "https://api.spotify.com/v1/artists?ids=" + String.join(",", t.getArtists());
                String artistsResult = httpHelper.performRequest(endpoint, userAccessToken);
                List<ArtistWithGenre> artists = parser.parseArtists(artistsResult);

                List<String> artistNames = artists.stream().map(Artist::getName).toList();

                Set<String> genres = new HashSet<>();
                for(ArtistWithGenre a: artists){
                    genres.addAll(a.getGenres());
                }

                t.setArtists(artistNames);

                t.setGenres(genres);

            }
            catch (IOException e) {
               continue;
            }
        }
    }

    private static PlaylistStatistics calculatePlaylistStatistics(String userId, String playlistId, PlaylistWithSimplePlaylistTracks playlist, String userAccessToken){
        Map<String, Integer> countGenres = new HashMap<>();
        Map<String, Integer> countArtists = new HashMap<>();
        int sumDuration = 0;

        for(SimplePlaylistTrack t: playlist.getTracks()){
            for(String genre: t.getGenres()){
                if(countGenres.containsKey(genre)){
                    countGenres.put(genre, countGenres.get(genre) + 1);
                }
                else{
                    countGenres.put(genre, 1);
                }
            }

            for(String artist: t.getArtists()){
                if(countArtists.containsKey(artist)){
                    countArtists.put(artist, countArtists.get(artist) + 1);
                }
                else{
                    countArtists.put(artist, 1);
                }
            }

            sumDuration += t.getDuration();
        }
        Map.Entry<String, Integer> maxGenre = maxUsingCollectionsMaxAndLambda(countGenres);
        Map.Entry<String, Integer> maxArtist = maxUsingCollectionsMaxAndLambda(countArtists);


        String maxGenreName = maxGenre != null ? maxGenre.getKey() : null;
        int maxGenreCount = maxGenre != null ? maxGenre.getValue() : 0;

        String maxArtistName = maxArtist.getKey();
        int maxArtistCount = maxArtist.getValue();

        int songCount = playlist.getTracks().size();

        return new PlaylistStatistics(userId, playlistId, playlist.getName(), songCount, sumDuration, maxGenreName, maxGenreCount, maxArtistName, maxArtistCount);
    }


    private static Map.Entry<String, Integer> maxUsingCollectionsMaxAndLambda(Map<String, Integer> map) {
        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        return maxEntry;
    }
}