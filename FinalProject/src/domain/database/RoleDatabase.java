package domain.database;

import models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDatabase extends BaseDatabase<Role> {

    @Override
    public void add(Role item) throws SQLException {
        String sql = "insert into Role ( name)values (?)";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1, item.getName());
        statement.executeUpdate();

    }

    @Override
    public void update(Role item) throws SQLException {
        String sql = "update Role set name = ? where id = ?";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setString(1, item.getName());
        statement.setInt(2, item.getId());

        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public Role getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Role> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from Role");

        ArrayList<Role> roles = new ArrayList<>();

        while (resultSet.next()) {

            Role role = new Role(
                    resultSet.getInt("id"),
                    resultSet.getString("Name")

            );

            roles.add(role);
        }

        return roles;
    }

    public List<Role> getRolesOfPerson(int personId) throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select Role.id, Role.name from PersonRole inner join Person on PersonRole.PersonId = Person.id inner join Role on PersonRole.RoleId = Role.Id where PersonId = " + personId);

        ArrayList<Role> roles = new ArrayList<>();

        while (resultSet.next()) {

            Role role = new Role(
                    resultSet.getInt("id"),
                    resultSet.getString("Name")

            );

            roles.add(role);
        }

        return roles;
    }
}
