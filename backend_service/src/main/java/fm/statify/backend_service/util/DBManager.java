package fm.statify.backend_service.util;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.UUID;

public class DBManager {
    public Connection establishConnection() {
        Properties properties = new Properties();
        String basePath = System.getProperty("user.dir");
        String filePath = basePath + "\\backend_service\\src\\main\\resources\\application.properties";
        try {
            properties.load(new FileInputStream(filePath));

            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return null;
        }
    }

    public void insertUser(String accessToken, String refreshToken, String userID) {
        try {
            String sql = "INSERT INTO user (guid, access_token, refresh_token, user_id) VALUES (?, ?, ?, ?)";

            Connection con = this.establishConnection();

            PreparedStatement statement = con.prepareStatement(sql);

            String guid = UUID.randomUUID().toString();
            statement.setString(1, guid);
            statement.setString(2, accessToken);
            statement.setString(3, refreshToken);
            statement.setString(4, userID);

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

            Connection con = this.establishConnection();

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

    public void insertTopTrackStatistics(String user_guid, String first_track_id, String second_track_id, String third_track_id, String fourth_track_id, String fifth_track_id, Date generate_date) {
        try {
            String sql = "INSERT INTO top_tracks (guid, user_guid, first_track_id, second_track_id, third_track_id, fourth_track_id, fifth_track_id, generate_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            Connection con = this.establishConnection();

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
}
