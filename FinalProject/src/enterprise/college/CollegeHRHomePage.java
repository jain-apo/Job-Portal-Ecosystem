package enterprise.college;

import domain.Application;
import helpers.TableHelpers;
import models.tablemodels.HRTableModel;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.sql.SQLException;

public class CollegeHRHomePage extends BaseFrame {
    private JPanel p;
    private JPanel mainPane;
    private JTable candidate;
    private JLabel heading;
    private JScrollPane students;

    public CollegeHRHomePage() {
        super();
        setContentPane(p);
        displayPeople();
    }

    private void displayPeople() {
        HRTableModel model = new HRTableModel();

        try {
            candidate.setModel(new HRTableModel().loadData(Application.Database.Persons.getAll().stream().filter(x -> x.getCollegeStudentData() != null).toList()));
        } catch (SQLException e) {
            Dialog.error("Error getting people");
            return;
        }

        TableHelpers.centerColumn(candidate, 0);
        TableHelpers.centerColumn(candidate, 2);
        TableHelpers.centerColumn(candidate, 3);
    }
}


