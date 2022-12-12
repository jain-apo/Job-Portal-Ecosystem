package enterprise.job;

import domain.Application;
import domain.Roles;
import helpers.TableHelpers;
import models.tablemodels.MyApplicationsTableModel;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class MyApplicationPage extends BaseFrame {
    private JPanel p;
    private JPanel mainPanel;
    private JLabel heading;

    private JTable people;
    private boolean isStudent;
    private boolean isHr;

    public MyApplicationPage() {
        super();
        setContentPane(p);
        setupRoles();

        displayJobPostings();

        setupActions();
    }

    private void setupRoles() {
        var person = Application.getCurrentlyLoggedInPerson();

        isStudent = person.hasRole(Roles.COLLEGE_STUDENT);
        isHr = person.hasRole(Roles.COMPANY_HR);
    }


    private void setupActions() {
//        addPersonButton.addActionListener(e -> addPerson());
    }

    private void displayJobPostings() {
        try {
            people.setModel(new MyApplicationsTableModel().loadData(
                    Application.Database.JobApplications.getAll()
                            .stream().filter(a -> a.getPersonId() == Application.getCurrentlyLoggedInPerson().getId())
                            .collect(Collectors.toList())
            ));

        } catch (SQLException e) {
            Dialog.error("Error getting people");
            return;
        }

        try {
            TableHelpers.centerColumn(people, 0);
            TableHelpers.centerColumn(people, 3);
            TableHelpers.centerColumn(people, 4);
        } catch (Exception e) {
            // ignore
        }
    }
}
