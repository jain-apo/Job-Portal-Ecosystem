package views;

import domain.Application;
import utils.Encryption;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginPage extends BaseFrame {
    private JPanel mainPane;
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton;

    public LoginPage() {
        super();
        setContentPane(mainPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO: change these default values later
//        username.setText("sharun");
//        password.setText("sharun");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    private void login() {
        System.out.println("username is " + username.getText());
        System.out.println("password is " + password.getText());

        try {
            var dbperson =
                    Application.Database.Persons.getAll().stream().filter(person -> person.getUsername().equals(username.getText()))
                            .findFirst().orElse(null);

            if (dbperson == null) {
                System.out.println("No person exists with that username");
            } else {
                if (Encryption.verify(password.getText(), dbperson.getPassword())) {
                    System.out.println("Password is correct");

                    Application.setCurrentlyLoggedInPerson(dbperson);

                    switchToWindow(new HomePage());

                } else {
                    System.out.println("Password is incorrect");
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
