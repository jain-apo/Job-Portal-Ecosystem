package domain.database;

import models.ModuleData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModuleDataDatabase extends BaseDatabase<ModuleData> {

    public ModuleDataDatabase() {
    }

    @Override
    public void add(ModuleData item) throws SQLException {
        String sql = "insert into ModuleData ( title, description, trainingModuleId) values (?,?,?)";

        PreparedStatement statement = getConnection().prepareStatement(sql);


        statement.setString(1, item.getTitle());
        statement.setString(2, item.getDescription());
        statement.setInt(3, item.getTrainingModuleId());

        statement.executeUpdate();
    }

    @Override
    public void update(ModuleData item) throws SQLException {
        String sql = "update ModuleData set title= ?, description = ?, trainingModuleId = ? where id = ?";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setString(1, item.getTitle());
        statement.setString(2, item.getDescription());
        statement.setInt(3, item.getTrainingModuleId());
        statement.setInt(4, item.getId());


        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE from ModuleData where id = ?;";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @Override
    public ModuleData getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<ModuleData> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from ModuleData");

        ArrayList<ModuleData> moduleDatas = new ArrayList<>();

        while (resultSet.next()) {

            ModuleData moduleData = new ModuleData(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getInt("trainingModuleId")
            );

            moduleDatas.add(moduleData);
        }

        return moduleDatas;
    }
}

