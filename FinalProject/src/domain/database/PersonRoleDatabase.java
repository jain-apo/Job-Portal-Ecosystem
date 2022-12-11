package domain.database;

import models.PersonRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonRoleDatabase extends BaseDatabase<PersonRole> {

    @Override
    public void add(PersonRole item) throws SQLException {

    }

    @Override
    public void update(PersonRole item) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public PersonRole getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<PersonRole> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from PersonRole");

        ArrayList<PersonRole> personRoles = new ArrayList<>();

        while (resultSet.next()) {

            PersonRole personRole = new PersonRole(
                    resultSet.getInt("id"),
                    resultSet.getInt("personId"),
                    resultSet.getInt("roleId")

            );

            personRoles.add(personRole);
        }

        return personRoles;
    }
}

