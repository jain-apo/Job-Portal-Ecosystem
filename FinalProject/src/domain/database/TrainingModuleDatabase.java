package domain.database;

import models.TrainingModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainingModuleDatabase extends BaseDatabase<models.TrainingModule> {

    @Override
    public void add(models.TrainingModule item) throws SQLException {
        String sql = "insert into TrainingModule (id, name, description) values (?, ?, ?)";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setInt(1, item.getId());
        statement.setString(2, item.getName());
        statement.setString(3, item.getDescription());
        statement.executeUpdate();

    }

    @Override
    public void update(models.TrainingModule item) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public models.TrainingModule getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<TrainingModule> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from TrainingModule");

        ArrayList<TrainingModule> trainingModules = new ArrayList<>();

        while (resultSet.next()) {

            models.TrainingModule trainingModule = new models.TrainingModule(
                    resultSet.getInt("Id"),
                    resultSet.getString("name"),
                    resultSet.getString("description")

            );

            trainingModules.add(trainingModule);

        }

        return trainingModules;
    }
}

