package domain.database;

import models.PersonRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonRoleDatabase extends BaseDatabase<PersonRole> {

    @Override
    public void add(PersonRole item) throws SQLException {
        Connection connection = getConnection();

        String sql = "insert into PersonRole (personId, roleId) values (?,?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, item.getPersonId());
        statement.setInt(2, item.getRoleId());

        statement.executeUpdate();
    }

    @Override
    public void update(PersonRole item) throws SQLException {
        String sql = "update PersonRole set personId= ?, roleId = ? where id = ?";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setInt(1, item.getPersonId());
        statement.setInt(2, item.getRoleId());
        statement.setInt(3, item.getId());


        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE from PersonRole where id = ?;";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setInt(1, id);

        statement.executeUpdate();
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

