package domain.database;

import models.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonsDatabase extends BaseDatabase<Person> {
    @Override
    public void add(Person item) {
        // TODO: Implement this
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
