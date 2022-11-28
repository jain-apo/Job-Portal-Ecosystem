package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseLayer {
    private static String connectionString = "jdbc:sqlite:/D:\\OneDrive - Northeastern University\\Assignments\\AED\\KakkadSasikumar_SharunKumar_002774079\\Assignment2\\db\\database.db";

    DatabaseLayer() {
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString);
    }
}