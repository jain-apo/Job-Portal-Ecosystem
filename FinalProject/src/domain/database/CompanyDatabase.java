package domain.database;

import models.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDatabase extends BaseDatabase<Company> {

    @Override
    public void add(Company person) throws SQLException {

        String sql = "insert into Company (id, name) values (?,?)";


        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setInt(1, person.getId());
        statement.setString(2, person.getName()
        );

        statement.executeUpdate();
    }

    @Override
    public void update(Company item) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public Company getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Company> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from Company");

        ArrayList<Company> companies = new ArrayList<>();

        while (resultSet.next()) {

            Company company = new Company(
                    resultSet.getInt("id"),
                    resultSet.getString("Name")

            );

            companies.add(company);
        }

        return companies;
    }
}

