package domain.database;

import domain.Application;
import models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonsDatabase extends BaseDatabase<Person> {


    @Override
    public void add(Person person) throws SQLException {
        String sql = "insert into Person ( firstName, lastName, dateOfBirth, username, password, email, phone) values (?,?,?,?,?,?,?)";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setString(1, person.getFirstName());
        statement.setString(2, person.getLastName());
        statement.setDate(3, new java.sql.Date(person.getDateOfBirth().getTime()));
        statement.setString(4, person.getUsername());
        statement.setString(5, person.getPassword());
        statement.setString(6, person.getEmail());
        statement.setString(7, person.getPhone()
        );

        statement.executeUpdate();

        Application.PersonsDirectory.loadFromDatabase();
    }

    @Override
    public void update(Person item) throws SQLException {
        String sql = "update Person set firstName = ?, lastName = ?, dateOfBirth = ?, username = ?, password = ?, email = ?, phone = ? where id = ?";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setString(1, item.getFirstName());
        statement.setString(2, item.getLastName());
        statement.setDate(3, new java.sql.Date(item.getDateOfBirth().getTime()));
        statement.setString(4, item.getUsername());
        statement.setString(5, item.getPassword());
        statement.setString(6, item.getEmail());
        statement.setString(7, item.getPhone());
        statement.setInt(8, item.getId());

        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
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
