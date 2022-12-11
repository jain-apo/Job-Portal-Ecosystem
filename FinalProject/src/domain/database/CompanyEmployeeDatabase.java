package domain.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyEmployeeDatabase extends BaseDatabase<models.CompanyEmployee> {
    @Override
    public void add(models.CompanyEmployee person) throws SQLException {
        String sql = "insert into CompanyEmployee ( personId, companyId, companyTeamId) values ( ?, ?, ?)";
        PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setInt(1, person.getPersonId());
        statement.setInt(2, person.getCompanyId());
        statement.setInt(3, person.getCompanyTeamId()
        );

        statement.executeUpdate();

    }

    @Override
    public void update(models.CompanyEmployee item) throws SQLException {
        String sql = "update CompanyEmployee set personId = ?, companyId = ?, companyTeamId = ? where id = ?";

        PreparedStatement statement = getConnection().prepareStatement(sql);


        statement.setInt(1, item.getPersonId());
        statement.setInt(2, item.getCompanyId());
        statement.setInt(3, item.getCompanyTeamId());
        statement.setInt(4, item.getId());

        statement.executeUpdate();
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

