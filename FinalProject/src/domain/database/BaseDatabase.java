package domain.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDatabase<T> {

    private static String connectionString = "jdbc:sqlite:/" + new File(".").getAbsolutePath() + "/db/prisma/database.db";

    public abstract void add(T item) throws SQLException;

    public abstract void update(T item) throws SQLException;

    public abstract void delete(int id) throws SQLException;

    public abstract T getById(int id) throws SQLException;

    public abstract List<T> getAll() throws SQLException;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString);
    }
}
