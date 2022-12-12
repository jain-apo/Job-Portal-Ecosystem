package views;

import domain.Application;
import domain.Roles;
import enterprise.college.CollegeHomePage;
import enterprise.company.CompanyHomePage;
import enterprise.job.JobHomePage;
import enterprise.training.TrainingListPage;
import models.tablemodels.NotificationsTableModel;
import utils.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.stream.Collectors;

import static helpers.UiHelpers.buttonRole;

public class HomePage extends BaseFrame implements ActionListener {
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

        setupRoles();

        setupNotifications();

        setContentPane(mainPanel);

        setupActions();
    }

    private void setupRoles() {
        try {
            var roles =
                    Application.getCurrentlyLoggedInPerson().getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());

            if (roles.contains(Roles.ADMIN)) {
                return;
            } else {
                adminPersonsDirectoryButton.setVisible(false);
            }

            var companyAccess = new String[]{
                    Roles.COMPANY_EMPLOYEE,
                    Roles.COMPANY_HR,
                    Roles.COMPANY_SYSADMIN,
            };

            var jobPortalAccess = new String[]{
                    Roles.JOB_PORTAL_ADMIN,
                    Roles.JOB_PORTAL_USER,
                    Roles.COLLEGE_STUDENT,
                    Roles.COMPANY_HR,
            };

            var trainingPortalAccess = new String[]{
                    Roles.TRAINEE,
                    Roles.TRAINING_SITE_ADMIN,
                    Roles.COLLEGE_STUDENT,
            };

            buttonRole(companyPortalButton, companyAccess, Application.getCurrentlyLoggedInPerson());
            buttonRole(jobPortalButton, jobPortalAccess, Application.getCurrentlyLoggedInPerson());
            buttonRole(trainingPortalButton, trainingPortalAccess, Application.getCurrentlyLoggedInPerson());

        } catch (SQLException e) {
            Dialog.error("Error reading the roles for the user");
        }
    }


    private void setupNotifications() {
        notificationsPane.setBorder(BorderFactory.createTitledBorder("Notifications"));

        var timer = new Timer(1000, this);
        timer.setInitialDelay(0);
        timer.start();

        notifications.setMaximumSize(new Dimension(500, 200));

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

    private void loadNotifications() {
        try {
//            System.out.println("loading notifications...");
            notifications.setModel(new NotificationsTableModel()
                    .loadData(Application.Database.PersonNotifications.getAll()
                            .stream()
                            .filter(x -> x.getPersonId() == Application.getCurrentlyLoggedInPerson().getId())
                            .sorted((x, y) -> Long.compare(y.getId(), x.getId()))
                            .collect(Collectors.toList())));
        } catch (Exception e) {
            //
        }
    }

    private void setupActions() {
        logoutButton.addActionListener(e -> {
            Application.setCurrentlyLoggedInPerson(null);
            switchToWindow(new LoginPage());
        });
        companyPortalButton.addActionListener(e -> new CompanyHomePage().setVisible(true));
        jobPortalButton.addActionListener(e -> new JobHomePage().setVisible(true));
        adminPersonsDirectoryButton.addActionListener(e -> new PersonsDirectoryPage().setVisible(true));
        trainingPortalButton.addActionListener(e -> new TrainingListPage().setVisible(true));
        collegePortalButton.addActionListener(e -> navigateToCollege());
    }

    private void navigateToCollege() {

        new CollegeHomePage().setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            loadNotifications();
            setupRoles();
        } catch (Exception ex) {
            //
        }
    }
}
