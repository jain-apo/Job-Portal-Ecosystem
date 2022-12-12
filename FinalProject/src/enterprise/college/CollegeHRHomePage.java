package enterprise.college;

import domain.Application;
import helpers.TableHelpers;
import models.tablemodels.HRTableModel;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CollegeHRHomePage extends BaseFrame {
    private final int SEND_REQUEST = 2;
    private JPanel p;
    private JPanel mainPane;
    private JTable candidate;
    private JLabel heading;
    private JScrollPane students;
    private JButton placementRequestButton;

    public CollegeHRHomePage() {
        super();
        displayPeople();
        setupActions();
        setContentPane(p);
    }

    private void setupActions() {
        placementRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SendRequestPage().setVisible(true);
            }
        });
    }

    private void displayPeople() {
        HRTableModel model = new HRTableModel();

        try {
            candidate.setModel(new HRTableModel().loadData(Application.Database.Companies.getAll()));
        } catch (SQLException e) {
            Dialog.error("Error getting people");
            return;
        }
        TableHelpers.centerColumn(candidate, 0);
        TableHelpers.centerColumn(candidate, 1);
    }
}


