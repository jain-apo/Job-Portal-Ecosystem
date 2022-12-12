package domain.database;

import models.TrainingModuleDataCompletions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TrainingModuleDataCompletionsDatabase extends BaseDatabase<TrainingModuleDataCompletions> {

    public TrainingModuleDataCompletionsDatabase() {
    }

    @Override
    public void add(TrainingModuleDataCompletions item) throws SQLException {
        String sql = "insert into TrainingModuleDataCompletions (moduleDataId, personId) values (?,?)";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setInt(1, item.getModuleDataId());
        statement.setInt(2, item.getPersonId());

        statement.executeUpdate();
    }

    @Override
    public void update(TrainingModuleDataCompletions item) throws SQLException {
        String sql = "update TrainingModuleDataCompletions set moduleDataId= ?, personId = ? where id = ?";

        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setInt(1, item.getModuleDataId());
        statement.setInt(2, item.getPersonId());
        statement.setInt(3, item.getId());


        statement.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE from TrainingModuleDataCompletions where id = ?;";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @Override
    public TrainingModuleDataCompletions getById(int id) throws SQLException {
        return null;
    }

    public List<TrainingModuleDataCompletions> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from JobPosting");

        ArrayList<TrainingModuleDataCompletions> jobPostings = new ArrayList<>();

        while (resultSet.next()) {

            TrainingModuleDataCompletions jobPosting = new TrainingModuleDataCompletions(
                    resultSet.getInt("id"),
                    resultSet.getInt("moduleDataId"),
                    resultSet.getInt("personId")
            );

        }

        return jobPostings;
    }
}





