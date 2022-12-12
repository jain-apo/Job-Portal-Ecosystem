package domain.database;

import models.JobPosting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobPostingDatabase extends BaseDatabase<JobPosting> {

    public JobPostingDatabase() {
    }

    @Override
    public void add(JobPosting item) throws SQLException {
        String sql = "insert into JobPosting (title, jobDescription, category, companyId) values (?,?,?,?)";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setString(1, item.getTitle());
        statement.setString(2, item.getJobDescription());
        statement.setString(3, item.getCategory());
        statement.setInt(4, item.getCompanyId());

        statement.executeUpdate();
    }

    @Override
    public void update(JobPosting item) throws SQLException {
        String sql = "update JobPosting set title= ?, jobDescription = ?, category = ?, companyId = ? where id = ?";

        PreparedStatement statement = getConnection().prepareStatement(sql);


        statement.setString(1, item.getTitle());
        statement.setString(2, item.getJobDescription());
        statement.setString(3, item.getCategory());
        statement.setInt(4, item.getCompanyId());
        statement.setInt(5, item.getId());


        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE from JobPosting where id = ?;";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @Override
    public JobPosting getById(int id) throws SQLException {
        return getAll().stream().filter(jobPosting -> jobPosting.getId() == id).findFirst().orElse(null);
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

            jobPostings.add(jobPosting);

        }

        return jobPostings;
    }
}
