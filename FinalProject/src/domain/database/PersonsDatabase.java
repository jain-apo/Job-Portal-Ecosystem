package domain.database;

import domain.Application;
import models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PersonsDatabase extends BaseDatabase<Person> {

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String getSqlDate(java.util.Date date) {
        return dateFormat.format(date);
    }

    @Override
    public void add(Person person) throws SQLException {
        // TODO: Implement this
        String sql = "insert into Person (id, firstName, lastName, dateOfBirth, username, password, email, phone) values (?,?,?,?,?,?,?,?)";

        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1, String.valueOf(person.getId()));
        statement.setString(2, person.getFirstName());
        statement.setString(3, person.getLastName());
        statement.setString(4, getSqlDate(person.getDateOfBirth()));
        statement.setString(5, person.getUsername());
        statement.setString(6, person.getPassword());
        statement.setString(7, person.getEmail());
        statement.setString(8, person.getPhone()
        );

        statement.executeUpdate();

        Application.PersonsDirectory.loadFromDatabase();
    }

    @Override
    public void update(Person item) {
        // TODO: Implement this
    }

    @Override
    public void delete(Person item) {
        // TODO: Implement this
    }

    @Override
    public Person getById(int id) {
        return null;
    }

    @Override
    public List<Person> getAll() throws SQLException {
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
