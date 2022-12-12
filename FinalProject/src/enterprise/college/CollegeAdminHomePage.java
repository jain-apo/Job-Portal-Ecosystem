package enterprise.college;

import domain.Application;
import domain.Validator;
import helpers.TableHelpers;
import models.Course;
import models.tablemodels.CollegeAdminTable;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class CollegeAdminHomePage extends BaseFrame {

    private final int EDIT_COLUMN_NUMBER = 2;
    private final int DELETE_COLUMN_NUMBER = 3;

    private boolean editMode;
    private int currentlyEditingEmployee;
    private JPanel p;
    private JPanel mainPane;
    private JLabel heading;
    private JPanel mainPanel;
    private JButton addCourseButton;
    private JButton cancelEditButton;
    private JPanel addCoursePanel;
    private JTextField courseNAme;
    private JTable people;
    private JTextArea validationText;
    private JScrollPane courses;

    public CollegeAdminHomePage() {
        super();
        setContentPane(p);
        displayPeople();
        setupActions();
        setEditMode(false);

    }

    private void addPerson() {
        if (!validateFields()) return;


        var person = new Course(currentlyEditingEmployee, courseNAme.getText());

        if (!editMode) {

            try {
                Application.Database.Courses.add(person);
                Dialog.show(person.getName() + " added successfully.");
                displayPeople();


            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else {
            try {


                Application.Database.Courses.update(person);
                Dialog.show(person.getName() + " updated successfully.");
                displayPeople();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        setEditMode(false);
    }

    private void setEditMode(boolean mode) {
        editMode = mode;
        cancelEditButton.setVisible(mode);

        if (mode) {
            addCourseButton.setText("Update Person");
            courses.setBorder(BorderFactory.createTitledBorder("Edit Person"));
        } else {
            addCourseButton.setText("Add Person");
            courses.setBorder(BorderFactory.createTitledBorder("Add Person"));

            for (var text : new JTextField[]{courseNAme}) {
                text.setText("");
                text.setBorder(BorderFactory.createLineBorder(Color.black));
            }
//            role.setSelectedIndex(0);
        }
    }

    private boolean validateFields() {
        var validationMessages = new ArrayList<String>();
        if (!Validator.checkTextsBlank(new JTextField[]{courseNAme}))
            validationMessages.add("Enter all the mandatory fields");


        if (validationMessages.size() == 0) {
            validationText.setText("");
            return true;
        }

        validationText.setText(String.join("\n", validationMessages));

        return false;
    }

    private void setupActions() {
        addCourseButton.addActionListener(e -> addPerson());
        cancelEditButton.addActionListener(e -> setEditMode(false));

        people.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);

                JTable target = (JTable) me.getSource();
                int row = target.getSelectedRow(); // selected row
                int column = target.getSelectedColumn(); // selected column

                if (me.getClickCount() == 2) {
                    System.out.println("double click");
                    int personId = Integer.parseInt(target.getModel().getValueAt(row, 0) + "");

                    String personName = target.getModel().getValueAt(row, 1) + "";

                    if (column == DELETE_COLUMN_NUMBER) {
                        System.out.println("Delete Clicked");


                        int result = Dialog.confirm("Are you sure you want to delete " + personName + "?", "Delete Person", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                        System.out.println(result);

                        if (result == JOptionPane.YES_OPTION) {
                            try {
                                Application.Database.Courses.delete(personId);
                                displayPeople();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (column == EDIT_COLUMN_NUMBER) {
                        

                        // TODO implement edit operation

                        setEditMode(true);
                        currentlyEditingEmployee = personId;
                        try {
                            Course person = Application.Database.Courses.getById(personId);
                            courseNAme.setText(person.getName());
                        } catch (SQLException e) {
                            Dialog.error("Error getting person");
                        }

                    }

                }
            }
        });

    }


    private void displayPeople() {
        CollegeAdminTable model = new CollegeAdminTable();

        try {
            people.setModel(new CollegeAdminTable().loadData(Application.Database.Courses.getAll()));
        } catch (SQLException e) {
            Dialog.error("Error getting people");
            return;
        }

        TableHelpers.centerColumn(people, 0);
        TableHelpers.centerColumn(people, 2);
        TableHelpers.centerColumn(people, 3);
    }
}
