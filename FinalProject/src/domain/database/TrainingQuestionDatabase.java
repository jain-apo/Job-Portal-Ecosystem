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
        String sql = "insert into(id, question, answer, option1, option2, option3, trainingModuleId) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setInt(1, item.getId());
        statement.setString(2, item.getQuestion());
        statement.setString(3, item.getAnswer());
        statement.setString(4, item.getOption1());
        statement.setString(5, item.getOption2());
        statement.setString(6, item.getOption3());
        statement.setInt(7, item.getTrainingModuleId());

        statement.executeUpdate();

    }

    @Override
    public void update(models.TrainingQuestion item) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

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
                    resultSet.getString("answer"),
                    resultSet.getString("option1"),
                    resultSet.getString("option2"),
                    resultSet.getString("option3"),
                    resultSet.getInt("trainingModuleId")

            );

            trainingQuestions.add(trainingQuestion);

        }

        return trainingQuestions;
    }
}

