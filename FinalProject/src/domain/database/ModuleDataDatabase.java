package domain.database;

import models.ModuleData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModuleDataDatabase extends BaseDatabase<ModuleData> {

    public ModuleDataDatabase() {
    }

    @Override
    public void add(ModuleData item) throws SQLException {

    }

    @Override
    public void update(ModuleData item) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

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

