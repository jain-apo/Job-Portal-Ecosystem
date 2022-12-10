package domain.database;

import models.PersonNotification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonNotificationDatabase extends BaseDatabase<models.PersonNotification> {
    @Override
    public void add(models.PersonNotification item) throws SQLException {

    }

    @Override
    public void update(models.PersonNotification item) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public models.PersonNotification getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<models.PersonNotification> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from PersonNotification order by date desc");

        ArrayList<PersonNotification> personNotifications = new ArrayList<>();

        while (resultSet.next()) {

            models.PersonNotification personNotification = new models.PersonNotification(
                    resultSet.getInt("Id"),
                    resultSet.getInt("personId"),
                    resultSet.getString("title"),
                    resultSet.getString("message"),
                    resultSet.getDate("date")
            );

            personNotifications.add(personNotification);

        }

        return personNotifications;
    }
}
