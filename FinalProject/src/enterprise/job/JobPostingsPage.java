package enterprise.job;

import domain.Application;
import domain.Validator;
import helpers.TableHelpers;
import models.JobPosting;
import models.tablemodels.CompanyPostingsTableModel;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class JobPostingsPage extends BaseFrame {
    private final int EDIT_COLUMN_NUMBER = 3;
    private final int DELETE_COLUMN_NUMBER = 4;
    private JPanel p;
    private JPanel mainPanel;
    private JLabel heading;
    private JButton addPersonButton;
    private JButton cancelEditButton;
    private JPanel addPersonPane;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField dateOfBirth;
    private JComboBox role;
    private JTextField username;
    private JPasswordField password;
    private JTextField jobTitle;
    private JTextField description;
    private JTextField category;

    private JTable people;
    private JTextArea validationText;
    private JScrollPane jobPostings;
    private JTextField email;
    private JTextField phone;

    private boolean editMode;
    private int currentlyEditingEmployee;
    private String existingPassword;

    public JobPostingsPage() {
        super();
        setContentPane(p);

        displayJobPostings();

        setupActions();

    }


    private void addPerson() {
        if (!validateFields()) return;


        var person = new JobPosting(0, jobTitle.getText(), description.getText(), category.getText(), 1);

        if (!editMode) {

            try {
                Application.Database.JobPostings.add(person);
                Dialog.show(person.getTitle() + " added successfully.");
                displayJobPostings();


            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else {
            try {


                Application.Database.JobPostings.update(person);
                Dialog.show(person.getTitle() + " updated successfully.");
                displayJobPostings();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }


    private boolean validateFields() {
        var validationMessages = new ArrayList<String>();
        if (!Validator.checkTextsBlank(new JTextField[]{jobTitle, description, category}))
            validationMessages.add("Enter all the mandatory fields");


        if (validationMessages.size() == 0) {
            validationText.setText("");
            return true;
        }

        validationText.setText(String.join("\n", validationMessages));

        return false;
    }

    private void setupActions() {
        addPersonButton.addActionListener(e -> addPerson());
    }

    private void displayJobPostings() {
        try {
            people.setModel(new CompanyPostingsTableModel().loadData(Application.Database.JobPostings.getAll()));
        } catch (SQLException e) {
            Dialog.error("Error getting people");
            return;
        }

        TableHelpers.centerColumn(people, 0);
        TableHelpers.centerColumn(people, 3);
//        TableHelpers.centerColumn(people, 4);
    }
}
