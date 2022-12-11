package domain.database;

import models.ModuleDataComplettions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ModuleDataComplettionsDatabase extends BaseDatabase<ModuleDataComplettions> {

    public ModuleDataComplettionsDatabase() {
    }

    @Override
    public void add(ModuleDataComplettions item) throws SQLException {
        String sql = "insert into ModuleDataComplettions (moduleDataId, personId) values (?,?)";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setInt(1, item.getModuleDataId());
        statement.setInt(2, item.getPersonId());

        statement.executeUpdate();
    }

    @Override
    public void update(ModuleDataComplettions item) throws SQLException {
        String sql = "update ModuleDataComplettions set moduleDataId= ?, personId = ? where id = ?";

        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setInt(1, item.getModuleDataId());
        statement.setInt(2, item.getPersonId());
        statement.setInt(3, item.getId());


        statement.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE from ModuleDataComplettions where id = ?;";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @Override
    public ModuleDataComplettions getById(int id) throws SQLException {
        return null;
    }

    public List<ModuleDataComplettions> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from JobPosting");

        ArrayList<ModuleDataComplettions> jobPostings = new ArrayList<>();

        while (resultSet.next()) {

            ModuleDataComplettions jobPosting = new ModuleDataComplettions(
                    resultSet.getInt("id"),
                    resultSet.getInt("moduleDataId"),
                    resultSet.getInt("personId")
            );

        }

        return jobPostings;
    }
}





