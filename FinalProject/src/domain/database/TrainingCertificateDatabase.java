package domain.database;

import models.TrainingCertificate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainingCertificateDatabase extends BaseDatabase<models.TrainingCertificate> {

    public TrainingCertificateDatabase() {
    }

    @Override
    public void add(models.TrainingCertificate person) throws SQLException {
        String sql = "insert into TrainingCertificate (name, trainingModuleId, personId) values (?,?,?)";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setString(1, person.getName());
        statement.setInt(2, person.getTrainingModuleId());
        statement.setInt(3, person.getPersonId());

        statement.executeUpdate();


    }

    @Override
    public void update(models.TrainingCertificate person) throws SQLException {
        String sql = "update TrainingCertificate set name = ?, trainingModuleId = ?, personId = ? where id = ?";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setString(1, person.getName());
        statement.setInt(2, person.getTrainingModuleId());
        statement.setInt(3, person.getPersonId());
        statement.setInt(4, person.getId());

        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE from TrainingCertificate where id = ?;";

        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @Override
    public models.TrainingCertificate getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<TrainingCertificate> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from TrainingCertificate");

        ArrayList<TrainingCertificate> trainingCertificates = new ArrayList<>();

        while (resultSet.next()) {

            models.TrainingCertificate trainingCertificate = new models.TrainingCertificate(
                    resultSet.getInt("Id"),
                    resultSet.getString("name"),
                    resultSet.getInt("trainingModuleId"),
                    resultSet.getInt("personId"),
                    resultSet.getDate("certifiedDate")

            );

            trainingCertificates.add(trainingCertificate);

        }

        return trainingCertificates;
    }
}

