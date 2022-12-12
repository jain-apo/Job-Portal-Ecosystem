import domain.Application;
import utils.Encryption;
import views.LoginPage;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Application.initialize();
        Encryption.initialize();

        new LoginPage().setVisible(true);
    }
}