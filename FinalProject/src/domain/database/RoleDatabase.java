package domain.database;

import models.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDatabase extends BaseDatabase<Role> {

    @Override
    public void add(Role item) throws SQLException {

    }

    @Override
    public void update(Role item) throws SQLException {

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
}
