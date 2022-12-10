package domain.database;

import models.JobApplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationDatabase extends BaseDatabase<models.JobApplication> {

    @Override
    public void add(models.JobApplication item) throws SQLException {

    }

    @Override
    public void update(models.JobApplication item) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public models.JobApplication getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<JobApplication> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from JobApplication");

        ArrayList<models.JobApplication> jobApplications = new ArrayList<>();

        while (resultSet.next()) {

            models.JobApplication jobApplication = new models.JobApplication(
                    resultSet.getInt("id"),
                    resultSet.getInt("personId"),
                    resultSet.getInt("jobPostingId"),
                    resultSet.getInt("yearsOFExperience")
            );

            jobApplications.add(jobApplication);
        }

        return jobApplications;
    }
}
