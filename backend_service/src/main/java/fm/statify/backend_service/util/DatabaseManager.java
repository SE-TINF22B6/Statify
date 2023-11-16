package fm.statify.backend_service.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
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

            // Drop table if it exists
            String sqlDrop = "DROP TABLE IF EXISTS StreamingStats";
            stmt.executeUpdate(sqlDrop);

            // Create table
            String sqlCreate = "CREATE TABLE StreamingStats (" +
                    "UserID INT, " +
                    "TrackID INT, " +
                    "LastStreamedUnixTime BIGINT)";
            stmt.executeUpdate(sqlCreate);

            // Insert example data
            String sqlInsert = "INSERT INTO StreamingStats VALUES " +
                    "(1, 101, 1620000000), " + // Example data
                    "(2, 102, 1620000100)";
            stmt.executeUpdate(sqlInsert);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/mysql","root","root");
        databaseManager.init();
    }

}
