package fm.statify.backend_service.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public void initDatabase() throws SQLException {
        try (Connection conn = getConnection()) {
            String dropTables = "DROP TABLE IF EXISTS user_listening_history, songs, albums, artists, users;";

            String createUsers = "CREATE TABLE users ("
                    + "user_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "user_name VARCHAR(255) NOT NULL,"
                    + "user_email VARCHAR(255) UNIQUE NOT NULL"
                    + ");";

            conn.createStatement().execute(dropTables);
            conn.createStatement().execute(createUsers);
        }
    }

    public void addUser(String name, String email) throws SQLException {
        String sql = "INSERT INTO users (user_name, user_email) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
        }
    }

    public void removeUser(int userId) throws SQLException {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        }
    }

    //...
}