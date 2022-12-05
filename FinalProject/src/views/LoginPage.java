package views;

import at.favre.lib.crypto.bcrypt.BCrypt;
import helpers.Encryption;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends BaseFrame {
    private JPanel mainPane;
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton;

    public LoginPage() {
        super();
        setContentPane(mainPane);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("username is " + username.getText());
                System.out.println("password is " + password.getText());

                String hashed = Encryption.hash(password.getText());

                System.out.println("Hashed password is: " + hashed);

                System.out.println(Encryption.verify(password.getText(), hashed));
            }
        });
    }
}
