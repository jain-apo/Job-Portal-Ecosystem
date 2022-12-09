package domain;

import entities.Person;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseLayer {
    private static String connectionString;

    DatabaseLayer() {
        connectionString = "jdbc:sqlite:/" + new File(".").getAbsolutePath() + "/db/prisma/database.db";
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString);
    }

    public ArrayList<Person> getPersons() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from Person");

        ArrayList<Person> persons = new ArrayList<>();

        while (resultSet.next()) {

            Person person = new Person(
                    resultSet.getInt("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getDate("dateOfBirth"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("phone")
            );

            persons.add(person);
        }

        return persons;
    }
}