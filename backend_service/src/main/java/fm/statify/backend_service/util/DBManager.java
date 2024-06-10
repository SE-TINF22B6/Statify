package fm.statify.backend_service.util;

import fm.statify.backend_service.auth.SpotifyOAuth;
import fm.statify.backend_service.controller.SpotifyController;
import fm.statify.backend_service.entities.Artist;
import fm.statify.backend_service.entities.SimpleTrack;
import fm.statify.backend_service.entities.User;
import fm.statify.backend_service.stats.PlaylistStatistics;
import fm.statify.backend_service.stats.TopArtistStatistics;
import fm.statify.backend_service.stats.TopTrackStatistics;

import jakarta.annotation.PostConstruct;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class DBManager {

    private Connection con;
    private final HTTPHelper http;
    private final Parser parser;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String userName;

    @Value("${db.password}")
    private String password;

    private final UserManager userManager;


    @Autowired
    public DBManager(HTTPHelper http, Parser parser, UserManager userManager) {
        this.http = http;
        this.parser = parser;
        this.userManager = userManager;
    }


    @PostConstruct
    public void establishConnection() {
        try {
            con = DriverManager.getConnection(url, userName, password);
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public void insertUser(String accessToken, String refreshToken, String userID, java.util.Date expireDate) {
        try {
            String sql = "INSERT INTO user (guid, access_token, refresh_token, user_id, expires) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);

            String guid = UUID.randomUUID().toString();
            statement.setString(1, guid);
            statement.setString(2, accessToken);
            statement.setString(3, refreshToken);
            statement.setString(4, userID);
            statement.setTimestamp(5, new Timestamp(expireDate.getTime()));

            statement.executeUpdate();

            System.out.println("User created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Boolean userExists(String userID) {
        String foundGuid = getUserGuid(userID);
        if (foundGuid.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public String getUserGuid(String userID) {
        try {
            String guid = new String();

            String sql = "SELECT guid FROM user WHERE user_id = ?";

            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, userID);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                guid = result.getString("guid");
            }

            return guid;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    private User getUser(String userID){
        try {
            String accessToken = new String();
            String refreshToken = new String();
            java.util.Date expires = new java.util.Date();

            String sql = "SELECT * FROM user WHERE user_id = ?";

            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, userID);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                accessToken = result.getString("access_token");
                refreshToken = result.getString("refresh_token");
                expires = new java.util.Date(result.getTimestamp("expires").getTime());
            }

            User user = new User(
                    userID,
                    accessToken,
                    expires,
                    refreshToken
            );

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateTokens(String userId, String accessToken, java.util.Date expires){
        String sql = "UPDATE user SET access_token = ?, expires = ? WHERE user_id = ?";

        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, accessToken);
            statement.setTimestamp(2, new Timestamp(expires.getTime()));
            statement.setString(3, userId);

            statement.executeUpdate();

            System.out.println("Access Token updated successfully.");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertTopTrackStatistics(String user_guid, String first_track_id, String second_track_id, String third_track_id, String fourth_track_id, String fifth_track_id, Date generate_date) {
        try {
            String sql = "INSERT INTO top_tracks (guid, user_guid, first_track_id, second_track_id, third_track_id, fourth_track_id, fifth_track_id, generate_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);

            String guid = UUID.randomUUID().toString();
            statement.setString(1, guid);
            statement.setString(2, user_guid);
            statement.setString(3, first_track_id);
            statement.setString(4, second_track_id);
            statement.setString(5, third_track_id);
            statement.setString(6, fourth_track_id);
            statement.setString(7, fifth_track_id);
            statement.setDate(8, generate_date);

            statement.executeUpdate();

            System.out.println("Top track statistics created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTopArtistsStatistics(String user_guid, String first_artist_id, String second_artist_id, String third_artist_id, String fourth_artist_id, String fifth_artist_id, Date generate_date) {
        try {
            String sql = "INSERT INTO top_artists (guid, user_guid, first_artist_id, second_artist_id, third_artist_id, fourth_artist_id, fifth_artist_id, generate_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);

            String guid = UUID.randomUUID().toString();
            statement.setString(1, guid);
            statement.setString(2, user_guid);
            statement.setString(3, first_artist_id);
            statement.setString(4, second_artist_id);
            statement.setString(5, third_artist_id);
            statement.setString(6, fourth_artist_id);
            statement.setString(7, fifth_artist_id);
            statement.setDate(8, generate_date);

            statement.executeUpdate();

            System.out.println("Top artist statistics created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPlaylist(String user_guid, String playlist_id, String name, int tracks_number, int duration, String top_genre, int top_genre_tracks_number, String top_artist, int top_artist_tracks_number, Date generate_date) {
        try {
            String sql = "INSERT INTO playlist (guid, user_guid, playlist_id, name, tracks_number, duration, top_genre, top_genre_tracks_number, top_artist, top_artist_tracks_number, generate_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);

            String guid = UUID.randomUUID().toString();
            statement.setString(1, guid);
            statement.setString(2, user_guid);
            statement.setString(3, playlist_id);
            statement.setString(4, name);
            statement.setInt(5, tracks_number);
            statement.setInt(6, duration);
            statement.setString(7, top_genre);
            statement.setInt(8, top_genre_tracks_number);
            statement.setString(9, top_artist);
            statement.setInt(10, top_artist_tracks_number);
            statement.setDate(11, generate_date);

            statement.executeUpdate();

            System.out.println("Playlist created.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TopTrackStatistics> getUsersTopTracksStats(String userID) {
        try {
            List<String> trackIDs = new ArrayList<>();

            String sql = "SELECT * FROM top_tracks WHERE user_guid = ?";

            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, getUserGuid(userID));

            ResultSet result = statement.executeQuery();

            List<TopTrackStatistics> topTrackStatisticsList = new ArrayList<>();

            if (result.next()) {
                while (!result.isAfterLast()) {
                    trackIDs.add(result.getString("first_track_id"));
                    trackIDs.add(result.getString("second_track_id"));
                    trackIDs.add(result.getString("third_track_id"));
                    trackIDs.add(result.getString("fourth_track_id"));
                    trackIDs.add(result.getString("fifth_track_id"));

                    List<SimpleTrack> simpleTracks = new ArrayList<>();

                    String accessToken = userManager.getAccessTokenByUserID(userID);

                    if(accessToken == null) {
                        User user = getUser(userID);
                        userManager.addUser(user);
                        accessToken = userManager.getAccessTokenByUserID(userID);
                    }

                    for (String id : trackIDs) {
                        String responseTrack = http.performRequest("https://api.spotify.com/v1/tracks/" + id, accessToken);
                        JSONObject trackJSON = new JSONObject(responseTrack);
                        simpleTracks.add(parser.parseSimpleTrack(trackJSON));
                    }

                    Date generateDate = result.getDate("generate_date");
                    TopTrackStatistics topTrackStatistics = new TopTrackStatistics(generateDate, userID, simpleTracks.get(0), simpleTracks.get(1), simpleTracks.get(2), simpleTracks.get(3), simpleTracks.get(4));

                    topTrackStatisticsList.add(topTrackStatistics);

                    trackIDs.clear();
                    result.next();
                }
            }

            return topTrackStatisticsList;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<TopArtistStatistics> getUsersTopArtistsStats(String userID) {
        try {
            List<String> artistIDs = new ArrayList<>();

            String sql = "SELECT * FROM top_artists WHERE user_guid = ?";

            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, getUserGuid(userID));

            ResultSet result = statement.executeQuery();

            List<TopArtistStatistics> topArtistsStatisticsList = new ArrayList<>();

            if (result.next()) {
                while (!result.isAfterLast()) {
                    artistIDs.add(result.getString("first_artist_id"));
                    artistIDs.add(result.getString("second_artist_id"));
                    artistIDs.add(result.getString("third_artist_id"));
                    artistIDs.add(result.getString("fourth_artist_id"));
                    artistIDs.add(result.getString("fifth_artist_id"));

                    List<Artist> artists = new ArrayList<>();

                    String accessToken = userManager.getAccessTokenByUserID(userID);

                    if(accessToken == null) {
                        User user = getUser(userID);
                        userManager.addUser(user);
                        accessToken = userManager.getAccessTokenByUserID(userID);
                    }


                    for (String id : artistIDs) {
                        String responseArtist = http.performRequest("https://api.spotify.com/v1/artists/" + id, accessToken);
                        artists.add(parser.parseArtist(responseArtist));
                    }

                    Date generateDate = result.getDate("generate_date");
                    TopArtistStatistics topArtistStatistics = new TopArtistStatistics(generateDate, userID, artists.get(0), artists.get(1), artists.get(2), artists.get(3), artists.get(4));

                    topArtistsStatisticsList.add(topArtistStatistics);

                    artistIDs.clear();
                    result.next();
                }
            }

            return topArtistsStatisticsList;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<PlaylistStatistics> getUsersPlaylistStats(String userID) {
        try {
            String sql = "SELECT * FROM playlist WHERE user_guid = ?";

            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, getUserGuid(userID));

            ResultSet result = statement.executeQuery();

            List<PlaylistStatistics> playlistStatistics = new ArrayList<>();

            if (result.next()) {
                while (!result.isAfterLast()) {
                    String playlistId = result.getString("playlist_id");
                    String name = result.getString("name");
                    int tracksNumber = result.getInt("tracks_number");
                    int duration = result.getInt("duration");
                    String topGenre = result.getString("top_genre");
                    int topGenreTracksNumber = result.getInt("top_genre_tracks_number");
                    String topArtist = result.getString("top_artist");
                    int topArtistTracksNumber = result.getInt("top_artist_tracks_number");
                    Date generateDate = result.getDate("generate_date");

                    playlistStatistics.add(new PlaylistStatistics(generateDate,
                            userID,
                            playlistId,
                            name,
                            tracksNumber,
                            duration,
                            topGenre,
                            topGenreTracksNumber,
                            topArtist,
                            topArtistTracksNumber));

                    result.next();
                }
            }

            return playlistStatistics;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeAllStatisticsForUser(String userID) {
        try {
            remove("top_tracks", userID);
            remove("top_artists", userID);
            remove("playlist", userID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void remove(String table_name, String userID) {
        try {
            String sql = "DELETE FROM" + table_name + "WHERE user_guid = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, getUserGuid(userID));
            statement.executeUpdate();
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAll(String userID) {
        try {
            removeAllStatisticsForUser(userID);
            remove("user", userID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
