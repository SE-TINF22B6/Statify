package fm.statify.backend_service;

import fm.statify.backend_service.util.DatabaseManager;

import java.sql.SQLException;

//THIS CLASS IS TEMPORARY

public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        try {
            dbManager.initDatabase();
            dbManager.addUser("Max Mustermann", "max.mustermann@email.com");
            //...
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
