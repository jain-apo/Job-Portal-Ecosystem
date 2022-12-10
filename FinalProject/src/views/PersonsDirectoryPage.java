package views;

import domain.Application;
import helpers.DateHelper;
import helpers.TableHelpers;
import models.tablemodels.PersonsTableModel;

import javax.swing.*;

public class PersonsDirectoryPage extends BaseFrame {
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
    private JComboBox cities;
    private JComboBox communities;
    private JComboBox houses;
    private JTable people;
    private JTextArea validationText;

    public PersonsDirectoryPage() {
        super();
        setContentPane(p);

        displayPeople();
    }

    private void displayPeople() {
        PersonsTableModel model = new PersonsTableModel();

        var details = Application.PersonsDirectory.getPersons();

        for (var person : details) {
            Object[] data = new Object[]{
                    person.getId(),
                    person.getFullName(),
                    DateHelper.formatDate(person.getDateOfBirth(), "MMM-dd yyyy"),
                    "✖",
                    "✖",
            };

            model.addRow(data);
        }

        people.setModel(model);

        TableHelpers.centerColumn(people, 0);
        TableHelpers.centerColumn(people, 3);
        TableHelpers.centerColumn(people, 4);
    }
}
