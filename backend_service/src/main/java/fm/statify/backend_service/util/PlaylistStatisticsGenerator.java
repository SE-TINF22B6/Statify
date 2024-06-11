package fm.statify.backend_service.util;

import fm.statify.backend_service.entities.*;
import fm.statify.backend_service.stats.PlaylistStatistics;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PlaylistStatisticsGenerator {
    private static final HTTPHelper httpHelper = new HTTPHelper();

    private static final Parser parser = new Parser();

    private static final int MAX_IDS = 50;

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
        Set<String> artists = playlist.getTracks().stream().flatMap((track) -> track.getArtists().stream()).collect(Collectors.toSet());
        try {
            List<ArtistWithGenre> collectArtist = new ArrayList<>();
            for(int i = 0; i < artists.size(); i+=MAX_IDS){
                int toIndex = i * MAX_IDS + MAX_IDS;
                toIndex = toIndex <= artists.size() ? toIndex : artists.size();
                List<String> subList = artists.stream().toList().subList(i, toIndex);
                String endpoint = "https://api.spotify.com/v1/artists?ids=" + String.join(",", subList);
                String artistsResult = httpHelper.performRequest(endpoint, userAccessToken);
                collectArtist.addAll(parser.parseArtists(artistsResult));
                System.out.println();
            }
            Map<String, ArtistWithGenre> artistsMap = collectArtist.stream().collect(Collectors.toMap(item -> item.getId(), item -> item));

            for(SimplePlaylistTrack t: playlist.getTracks()){
                List<String> artistNames = new ArrayList<>();
                Set<String> genres = new HashSet<>();
                for (String a: t.getArtists()){
                    ArtistWithGenre artist = artistsMap.get(a);
                    artistNames.add(artist.getName());
                    genres.addAll(artist.getGenres());
                }

                t.setArtists(artistNames);
                t.setGenres(genres);
            }
        }
        catch (IOException e) {
            return;
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
