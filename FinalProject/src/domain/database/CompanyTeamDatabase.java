package domain.database;

import models.CompanyTeam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyTeamDatabase extends BaseDatabase<models.CompanyTeam> {

    @Override
    public void add(models.CompanyTeam person) throws SQLException {
        String sql = "insert into CompanyTeam (id, name) values (?, ?)";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        statement.setInt(1, person.getId());
        statement.setString(2, person.getName());


        statement.executeUpdate();

    }


    @Override
    public void update(models.CompanyTeam item) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public models.CompanyTeam getById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<CompanyTeam> getAll() throws SQLException {
        Connection connection = getConnection();

        ResultSet resultSet = connection.createStatement().executeQuery("Select * from CompanyTeam");

        ArrayList<CompanyTeam> companyTeams = new ArrayList<>();

        while (resultSet.next()) {

            CompanyTeam companyTeam = new CompanyTeam(
                    resultSet.getInt("id"),
                    resultSet.getString("Name")

            );

            companyTeams.add(companyTeam);
        }
        return companyTeams;
    }
}