package domain.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyEmployeeDatabase extends BaseDatabase<models.CompanyEmployee> {
    @Override
    public void add(models.CompanyEmployee item) throws SQLException {

    }

    @Override
    public void update(models.CompanyEmployee item) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public models.CompanyEmployee getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<models.CompanyEmployee> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from CompanyEmployee");

        ArrayList<models.CompanyEmployee> companyEmployees = new ArrayList<>();

        while (resultSet.next()) {

            models.CompanyEmployee companyEmployee = new models.CompanyEmployee(
                    resultSet.getInt("id"),
                    resultSet.getInt("personId"),
                    resultSet.getInt("companyId")
            );


            companyEmployees.add(companyEmployee);
        }

        return companyEmployees;
    }
}

