package domain.database;

import models.TrainingModuleData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainingModuleDataDatabase extends BaseDatabase<TrainingModuleData> {

    public TrainingModuleDataDatabase() {
    }

    @Override
    public void add(TrainingModuleData item) throws SQLException {
        String sql = "insert into TrainingModuleData ( title, description, trainingModuleId) values (?,?,?)";

        PreparedStatement statement = getConnection().prepareStatement(sql);


        statement.setString(1, item.getTitle());
        statement.setString(2, item.getDescription());
        statement.setInt(3, item.getTrainingModuleId());

        statement.executeUpdate();
    }

    @Override
    public void update(TrainingModuleData item) throws SQLException {
        String sql = "update TrainingModuleData set title= ?, description = ?, trainingModuleId = ? where id = ?";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setString(1, item.getTitle());
        statement.setString(2, item.getDescription());
        statement.setInt(3, item.getTrainingModuleId());
        statement.setInt(4, item.getId());


        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE from TrainingModuleData where id = ?;";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @Override
    public TrainingModuleData getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<TrainingModuleData> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from TrainingModuleData");

        ArrayList<TrainingModuleData> result = new ArrayList<>();

        while (resultSet.next()) {

            TrainingModuleData trainingModuleData = new TrainingModuleData(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getInt("trainingModuleId")
            );

            result.add(trainingModuleData);
        }

        return result;
    }
}

