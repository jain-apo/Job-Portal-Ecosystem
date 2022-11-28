package domain;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseLayer {
    private static String connectionString;

    DatabaseLayer() {
        connectionString = "jdbc:sqlite:/" + new File(".").getAbsolutePath() + "\\db\\database.db";
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString);
    }
}