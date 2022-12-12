package views;

import domain.Application;
import enterprise.college.CollegeHomePage;
import enterprise.company.CompanyHomePage;
import enterprise.job.JobHomePage;
import enterprise.training.TrainingListPage;
import models.tablemodels.NotificationsTableModel;
import utils.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.stream.Collectors;

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
            // TODO filter for currently logged in user
            notifications.setModel(new NotificationsTableModel()
                    .loadData(Application.Database.PersonNotifications.getAll()
                            .stream()
                            .filter(x -> x.getPersonId() == Application.getCurrentlyLoggedInPerson().getId())
                            .sorted((x, y) -> Long.compare(y.getId(), x.getId()))
                            .collect(Collectors.toList())));
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
        trainingPortalButton.addActionListener(e -> new TrainingListPage().setVisible(true));
        collegePortalButton.addActionListener(e -> navigateToCollege());
    }

    private void navigateToCollege() {

        new CollegeHomePage().setVisible(true);

    }
}
