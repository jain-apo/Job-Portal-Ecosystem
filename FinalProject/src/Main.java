import domain.Application;
import views.LoginPage;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        try {
            var persons = Application.Database.getPersons();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        new LoginPage().setVisible(true);
    }
}