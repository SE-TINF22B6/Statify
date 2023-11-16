package fm.statify.backend_service.util;

import fm.statify.backend_service.entities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void init() {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            String sqlDropStreamingStats = "DROP TABLE IF EXISTS StreamingStats";
            stmt.executeUpdate(sqlDropStreamingStats);

            String sqlDropUsers = "DROP TABLE IF EXISTS Users";
            stmt.executeUpdate(sqlDropUsers);

            String sqlCreateStat = "CREATE TABLE StreamingStats (" +
                    "UserID INT, " +
                    "TrackID INT, " +
                    "StreamedUnixTime BIGINT)";
            stmt.executeUpdate(sqlCreateStat);

            String sqlCreateUsers = "CREATE TABLE Users (" +
                    "UserID INT PRIMARY KEY, " +
                    "UserEmail VARCHAR(255), " +
                    "UserName VARCHAR(255), " +
                    "APIKey VARCHAR(255))";
            stmt.executeUpdate(sqlCreateUsers);

            String sqlInsertStats = "INSERT INTO StreamingStats VALUES " +
                    "(1, 101, 1620000000), " +
                    "(2, 102, 1620000100)";
            stmt.executeUpdate(sqlInsertStats);

            String sqlInsertUsers = "INSERT INTO Users VALUES " +
                    "(1, 'user1@example.com', 'User1', 'APIKey1'), " +
                    "(2, 'user2@example.com', 'User2', 'APIKey2')";
            stmt.executeUpdate(sqlInsertUsers);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to initialize database: ", e);
        }
    }

    public void addUser(User newUser){
    }

    public void addStream(){
    }

    public void updateAPIKey(){
    }

}
