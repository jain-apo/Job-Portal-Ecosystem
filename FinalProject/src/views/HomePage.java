package views;

import domain.Application;
import enterprise.college.CollegeHomePage;
import enterprise.company.CompanyHomePage;
import enterprise.job.JobHomePage;
import enterprise.training.TrainingHomePage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends BaseFrame {
    private JPanel mainPanel;
    private JButton companyPortalButton;
    private JButton jobPortalButton;
    private JButton collegePortalButton;
    private JButton trainingPortalButton;
    private JButton logoutButton;
    private JLabel heading;
    private JButton adminPersonsDirectoryButton;
    private JTable notifications;
    private JScrollPane notificationsPane;

    HomePage() {
        super();

        heading.setText("Welcome, " + Application.getCurrentlyLoggedInPerson().getFirstName());

        setupNotifications();

        setContentPane(mainPanel);

        setupActions();
    }

    private void setupNotifications() {
        notificationsPane.setBorder(BorderFactory.createTitledBorder("Notifications"));

        notifications.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Title", "Message"}));

    }

    private void setupActions() {
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.setCurrentlyLoggedInPerson(null);
                switchToWindow(new LoginPage());
            }
        });
        companyPortalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CompanyHomePage().setVisible(true);
            }
        });
        jobPortalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JobHomePage().setVisible(true);
            }
        });
        adminPersonsDirectoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PersonsDirectoryPage().setVisible(true);
            }
        });
        trainingPortalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrainingHomePage().setVisible(true);
            }
        });
        collegePortalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CollegeHomePage().setVisible(true);
            }
        });
    }
}
