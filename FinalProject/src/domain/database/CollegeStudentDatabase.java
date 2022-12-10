package domain.database;

import models.CollegeStudent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollegeStudentDatabase extends BaseDatabase<CollegeStudent> {

    @Override
    public void add(CollegeStudent item) throws SQLException {

    }

    @Override
    public void update(CollegeStudent item) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public CollegeStudent getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<CollegeStudent> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from CollegeStudent");

        ArrayList<CollegeStudent> collegeStudents = new ArrayList<>();

        while (resultSet.next()) {

            CollegeStudent collegeStudent = new CollegeStudent(
                    resultSet.getInt("id"),
                    resultSet.getFloat("Gpa"),
                    resultSet.getInt("PassYear"),
                    resultSet.getInt("PersonId"),
                    resultSet.getInt("CourseId")
            );

            collegeStudents.add(collegeStudent);

        }

        return collegeStudents;
    }
}
