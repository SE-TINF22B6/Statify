package fm.statify.backend_service.util;

import fm.statify.backend_service.entities.UserProfile;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


//This Class was created to manage the database, but it will not be used in the final version of the project due to changes in the project's scope
public class DatabaseManager {

    private static final Logger LOGGER = Logger.getLogger(DatabaseManager.class.getName());
    private String url;
    private String username;
    private String password;

    public DatabaseManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

/*    public void init() {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            String sqlDropStreamingStats = "DROP TABLE IF EXISTS StreamingStats";
            stmt.executeUpdate(sqlDropStreamingStats);

            String sqlDropUsers = "DROP TABLE IF EXISTS Users";
            stmt.executeUpdate(sqlDropUsers);

            String sqlCreateStat = "CREATE TABLE StreamingStats (" +
                    "UserID VARCHAR(255), " +
                    "TrackID VARCHAR(255), " +
                    "StreamedUnixTime BIGINT)";
            stmt.executeUpdate(sqlCreateStat);

            String sqlCreateUsers = "CREATE TABLE Users (" +
                    "UserID VARCHAR(255) PRIMARY KEY, " +
                    "UserEmail VARCHAR(255), " +
                    "UserName VARCHAR(255), " +
                    "APIKey VARCHAR(255))";
            stmt.executeUpdate(sqlCreateUsers);

            String sqlInsertStats = "INSERT INTO StreamingStats VALUES " +
                    "('3134aaalgubelqdmovf6ghyr5bsy', '1iPTSLcoezNpFMaXK29EsG', 1700179218)";
            stmt.executeUpdate(sqlInsertStats);

            String sqlInsertUsers = "INSERT INTO Users VALUES " +
                    "('3134aaalgubelqdmovf6ghyr5bsy', 'crispynachos23@gmail.com', 'LaggyNacho', 'blubbediblub')";
            stmt.executeUpdate(sqlInsertUsers);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to initialize database: ", e);
        }
    }*/

    public void init() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("The connection was established successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Prototyp zum Einfügen eines Users in die Datenbank (Parameter & weiteres kann beliebig erweitert werden)
    public void addUser(UserProfile user, String accesstoken) {
        String sql = "INSERT INTO Users (UserID, UserEmail, UserName, APIKey) VALUES (?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, accesstoken);
            pstmt.executeUpdate();
            LOGGER.info("User added successfully: " + user);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to add user: ", e);
        }
    }


    public void updateAPIKey(UserProfile user, String newAPIKey) {
    }

    //Prototyp zum Überprüfen, ob ein User existiert
    public boolean userExists(String id) {
        String sql = "SELECT COUNT(*) FROM Users WHERE UserID = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // True, wenn der Benutzer existiert, sonst false
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to check if user exists: ", e);
        }
        return false; // Rückgabe von false, wenn ein Fehler auftritt oder kein Benutzer gefunden wird
    }


    public List<UserProfile> getAllUsers() {
        return null;
    }

    public void updateUser(UserProfile user, String accesstoken) {
    }

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        String basePath = System.getProperty("user.dir");
        String filePath = basePath + "\\backend_service\\src\\main\\resources\\application.properties";
        try {
            properties.load(new FileInputStream(filePath));

            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            DatabaseManager databaseManager = new DatabaseManager(url, username, password);

            databaseManager.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}