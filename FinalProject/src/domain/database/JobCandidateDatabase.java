package domain.database;

import models.JobCandidate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobCandidateDatabase extends BaseDatabase<models.JobCandidate> {

    @Override
    public void add(models.JobCandidate item) throws SQLException {
        String sql = "insert into JobCandidate (id, personId, jobApplicationId, interviewRound, result) values (?,?,?,?,?)";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setInt(1, item.getId());
        statement.setInt(2, item.getPersonId());
        statement.setInt(3, item.getJobApplicationId());
        statement.setInt(4, item.getInterviewRound());
        statement.setString(5, item.getResult());

        statement.executeUpdate();
    }

    @Override
    public void update(models.JobCandidate item) throws SQLException {
        String sql = "update JobCandidate set id = ?,  personId= ?, jobApplicationId = ?, interviewRound = ?, result = ? where id = ?";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setInt(1, item.getId());
        statement.setInt(2, item.getPersonId());
        statement.setInt(3, item.getJobApplicationId());
        statement.setInt(4, item.getInterviewRound());
        statement.setString(5, item.getResult());


        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public models.JobCandidate getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<JobCandidate> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from JobCandidate");

        ArrayList<models.JobCandidate> jobCandidates = new ArrayList<>();

        while (resultSet.next()) {

            models.JobCandidate jobCandidate = new models.JobCandidate(
                    resultSet.getInt("Id"),
                    resultSet.getInt("personId"),
                    resultSet.getInt("jobApplicationid"),
                    resultSet.getInt("interviewRound"),
                    resultSet.getString("result")

            );

            jobCandidates.add(jobCandidate);

        }

        return jobCandidates;
    }

    public JobCandidate getCandidateById(int candidateId) throws SQLException {
        return getAll().stream().filter(candidate -> candidate.getId() == candidateId).findFirst().orElse(null);
    }
}

