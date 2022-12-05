package views;

import domain.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends BaseFrame {
    private JPanel mainPanel;
    private JButton companyPortalButton;
    private JButton jobPortalButton;
    private JButton collegePortalButton;
    private JButton trainingPortalButton;
    private JButton logoutButton;

    HomePage() {
        super();
        setContentPane(mainPanel);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.setCurrentlyLoggedInPerson(null);
                switchToWindow(new LoginPage());
            }
        });
    }
}
