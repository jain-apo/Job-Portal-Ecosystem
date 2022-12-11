package domain.database;

import models.JobPosting;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobPostingDatabase extends BaseDatabase<JobPosting> {

    public JobPostingDatabase() {
    }

    @Override
    public void add(JobPosting item) throws SQLException {

    }

    @Override
    public void update(JobPosting item) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public JobPosting getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<JobPosting> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from JobPosting");

        ArrayList<JobPosting> jobPostings = new ArrayList<>();

        while (resultSet.next()) {

            JobPosting jobPosting = new JobPosting(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("jobDescription"),
                    resultSet.getString("category"),
                    resultSet.getInt("companyId")
            );

        }

        return jobPostings;
    }
}
