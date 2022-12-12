package domain.database;

import models.TrainingQuestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainingQuestionDatabase extends BaseDatabase<models.TrainingQuestion> {

    @Override
    public void add(models.TrainingQuestion item) throws SQLException {
        String sql = "insert into TrainingQuestion (question, answer, trainingModuleId) values (?,?,?)";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1, item.getQuestion());
        statement.setBoolean(2, item.getAnswer());
        statement.setInt(3, item.getTrainingModuleId());

        statement.executeUpdate();

    }

    @Override
    public void update(models.TrainingQuestion item) throws SQLException {
        String sql = "update TrainingQuestion set  question= ?, answer= ?, trainingModuleId= ? where id = ?";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setString(1, item.getQuestion());
        statement.setBoolean(2, item.getAnswer());
        statement.setInt(3, item.getTrainingModuleId());
        statement.setInt(4, item.getId());

        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE from TrainingQuestion where id = ?;";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @Override
    public models.TrainingQuestion getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<TrainingQuestion> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from TrainingQuestion");

        ArrayList<TrainingQuestion> trainingQuestions = new ArrayList<>();

        while (resultSet.next()) {

            models.TrainingQuestion trainingQuestion = new models.TrainingQuestion(
                    resultSet.getInt("Id"),
                    resultSet.getString("question"),
                    resultSet.getBoolean("answer"),
                    resultSet.getInt("trainingModuleId")
            );

            trainingQuestions.add(trainingQuestion);

        }

        return trainingQuestions;
    }
}

