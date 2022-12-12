package enterprise.training;

import domain.Application;
import domain.Roles;
import domain.Validator;
import helpers.TableHelpers;
import models.TrainingModule;
import models.TrainingModuleData;
import models.tablemodels.BaseTableModel;
import models.tablemodels.TrainingModuleDataAdminTableModel;
import models.tablemodels.TrainingModuleDataTableModel;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrainingModulePage extends BaseFrame {
    private final int VIEW_COLUMN_NUMBER = 2;
    private final int EDIT_COLUMN_NUMBER = 3;
    private final int DELETE_COLUMN_NUMBER = 4;
    private final TrainingModule module;
    private boolean editMode;
    private int currentlyEditingEmployee;
    private JPanel p;
    private JPanel mainPanel;
    private JLabel heading;
    private JButton addModuleButton;
    private JTable modules;
    private JTextArea validationText;
    private JButton startTrainingButton;
    private JPanel addPersonPane;
    private JTextArea description;
    private JTextField name;
    private JButton cancelButton;
    private JButton startModuleButton;
    private boolean isTrainer;
    private boolean isTrainee;

    public TrainingModulePage(TrainingModule module) {
        this.module = module;
        heading.setText(module.getName());

        setupActions();
        setupRoles();
        displayModules();
        setContentPane(p);
        setEditMode(false);
        startModuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartModule(module).setVisible(true);
            }
        });
    }

    private void setupRoles() {
        var person = Application.getCurrentlyLoggedInPerson();

        isTrainer = person.hasRole(Roles.TRAINING_SITE_ADMIN);
        isTrainee = person.hasRole(Roles.TRAINEE);

        // TODO Roles Restriction

        if (!isTrainer) {
            addPersonPane.setVisible(false);
            addModuleButton.setVisible(false);
        } else {
            addPersonPane.setVisible(true);
            addModuleButton.setVisible(true);
        }
    }

    private void displayModules() {
        try {

            List<TrainingModuleData> moduleData = Application.Database.TrainingModuleDataDatabase.getAll().stream().filter(x -> x.getTrainingModuleId() == module.getId()).collect(Collectors.toList());

            if (isTrainer) {
                modules.setModel(new TrainingModuleDataAdminTableModel().loadData(moduleData));
            } else {
                modules.setModel(new TrainingModuleDataTableModel().loadData(moduleData));
            }

            for (int i = 0; i < modules.getColumnCount(); i++) {
                TableHelpers.centerColumn(modules, i);
            }
        } catch (SQLException e) {
            Dialog.error("Error loading training modules");
        }
    }

    private void addPerson() {
        if (!validateFields()) return;


        var moduleData = new TrainingModuleData(currentlyEditingEmployee, name.getText(), description.getText(), module.getId());

        if (!editMode) {

            try {
                Application.Database.TrainingModuleDataDatabase.add(moduleData);
                Dialog.show(moduleData.getTitle() + " added successfully.");
                displayModules();


            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else {
            try {


                Application.Database.TrainingModuleDataDatabase.update(moduleData);
                Dialog.show(moduleData.getTitle() + " updated successfully.");
                displayModules();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        setEditMode(false);
    }

    private void setEditMode(boolean mode) {
        editMode = mode;
        cancelButton.setVisible(mode);

        if (mode) {
            addModuleButton.setText("Update Section");
            addPersonPane.setBorder(BorderFactory.createTitledBorder("Edit Section"));
        } else {
            addModuleButton.setText("Add Section");
            addPersonPane.setBorder(BorderFactory.createTitledBorder("Add Section"));

            for (var text : new JTextComponent[]{name, description}) {
                text.setText("");
                text.setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }
    }

    private boolean validateFields() {
        var validationMessages = new ArrayList<String>();
        if (!Validator.checkTextsBlank(new JTextComponent[]{name, description}))
            validationMessages.add("Enter all the mandatory fields");


        if (validationMessages.size() == 0) {
            validationText.setText("");
            return true;
        }

        validationText.setText(String.join("\n", validationMessages));

        return false;
    }

    private void setupActions() {
        addModuleButton.addActionListener(e -> addPerson());
        cancelButton.addActionListener(e -> setEditMode(false));

        modules.addMouseListener(new MouseAdapter() {
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

                    if (column == VIEW_COLUMN_NUMBER) {

                        TrainingModuleData trainingModuleData = ((BaseTableModel<TrainingModuleData>) target.getModel()).getDataAt(row);

                        new TrainingModuleDataPage(trainingModuleData).setVisible(true);

                    } else if (column == DELETE_COLUMN_NUMBER) {
                        System.out.println("Delete Clicked");


                        int result = Dialog.confirm("Are you sure you want to delete " + personName + "?", "Delete Person", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                        System.out.println(result);

                        if (result == JOptionPane.YES_OPTION) {
                            try {
                                Application.Database.TrainingModuleDataDatabase.delete(personId);
                                displayModules();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (column == EDIT_COLUMN_NUMBER) {


                        setEditMode(true);
                        currentlyEditingEmployee = personId;
                        try {
                            TrainingModuleData person = Application.Database.TrainingModuleDataDatabase.getById(personId);
                            name.setText(person.getTitle());
                            description.setText(person.getDescription());

                        } catch (SQLException e) {
                            Dialog.error("Error getting person");
                        }

                    }

                }
            }
        });

    }
}
