package domain.database;

import models.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDatabase extends BaseDatabase<Course> {

    @Override
    public void add(Course item) throws SQLException {
        String sql = "insert into Course (id, name) values (?,?)";

        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1, item.getName());
        statement.setInt(2, item.getId());
        statement.executeUpdate();
    }

    @Override
    public void update(Course item) throws SQLException {
        String sql = "update Course set name = ? where id = ?";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setString(1, item.getName());

        statement.setInt(2, item.getId());

        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE from Course where id = ?;";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @Override
    public Course getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Course> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from Course");

        ArrayList<Course> courses = new ArrayList<>();

        while (resultSet.next()) {

            Course course = new Course(
                    resultSet.getInt("id"),
                    resultSet.getString("Name")
            );

            courses.add(course);
        }

        return courses;
    }
}

