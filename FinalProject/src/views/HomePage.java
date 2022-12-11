package views;

import domain.Application;
import enterprise.college.CollegeAdminHomePage;
import enterprise.college.CollegeHRHomePage;
import enterprise.college.CollegeStudentHomePage;
import enterprise.college.StudentRegistrationPage;
import enterprise.company.CompanyHomePage;
import enterprise.job.JobHomePage;
import enterprise.training.TrainingHomePage;
import models.tablemodels.NotificationsTableModel;
import utils.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

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
        notifications.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // handle double click
                if (e.getClickCount() == 2) {
                    var row = notifications.getSelectedRow();
                    var model = (NotificationsTableModel) notifications.getModel();
                    var notification = model.getDataAt(row);

                    Dialog.info(notification.getMessage(), notification.getTitle());
                }
            }
        });
    }

    private void setupNotifications() {
        notificationsPane.setBorder(BorderFactory.createTitledBorder("Notifications"));

        try {
            notifications.setModel(new NotificationsTableModel().loadData(Application.Database.PersonNotifications.getAll()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        notifications.setMaximumSize(new Dimension(500, 200));

    }

    private void setupActions() {
        logoutButton.addActionListener(e -> {
            Application.setCurrentlyLoggedInPerson(null);
            switchToWindow(new LoginPage());
        });
        companyPortalButton.addActionListener(e -> new CompanyHomePage().setVisible(true));
        jobPortalButton.addActionListener(e -> new JobHomePage().setVisible(true));
        adminPersonsDirectoryButton.addActionListener(e -> new PersonsDirectoryPage().setVisible(true));
        trainingPortalButton.addActionListener(e -> new TrainingHomePage().setVisible(true));
        collegePortalButton.addActionListener(e -> navigateToCollege());
    }

    private void navigateToCollege() {

        var person = Application.getCurrentlyLoggedInPerson();

        // roles are COLLEGE_STUDENT COLLEGE_HR COLLEGE_ADMIN

        boolean isAdmin = person.getRoles().stream().anyMatch(role -> role.getName().equals("COLLEGE_ADMIN"));
        boolean isHr = person.getRoles().stream().anyMatch(role -> role.getName().equals("COLLEGE_HR"));
        boolean isStudent = person.getRoles().stream().anyMatch(role -> role.getName().equals("COLLEGE_STUDENT"));

        if (isAdmin) {
            new CollegeAdminHomePage().setVisible(true);
        } else if (isHr) {
            new CollegeHRHomePage().setVisible(true);
        } else if (isStudent) {
            new CollegeStudentHomePage().setVisible(true);
        } else {
            new StudentRegistrationPage().setVisible(true);
        }
    }
}
