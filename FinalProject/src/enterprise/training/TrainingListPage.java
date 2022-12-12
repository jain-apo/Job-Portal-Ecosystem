package enterprise.training;

import domain.Application;
import domain.Roles;
import helpers.TableHelpers;
import models.tablemodels.TrainingModuleAdminTableModel;
import models.tablemodels.TrainingModuleTableModel;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TrainingListPage extends BaseFrame {
    private JPanel p;
    private JPanel mainPanel;
    private JLabel heading;
    private JButton addModuleButton;
    private JTable modules;
    private JTextArea validationText;
    private JButton startTrainingButton;
    private boolean isTrainer;
    private boolean isTrainee;

    public TrainingListPage() {
        setupActionListeners();
        setupRoles();
        displayModules();
        setContentPane(p);
    }

    private void setupRoles() {
        var person = Application.getCurrentlyLoggedInPerson();

        isTrainer = person.matchRole(Roles.TRAINING_SITE_ADMIN);
        isTrainee = person.matchRole(Roles.TRAINEE);

        // TODO Roles Restriction
    }

    private void displayModules() {
        try {

            if (isTrainee) {
                modules.setModel(new TrainingModuleTableModel().loadData(Application.Database.TrainingModules.getAll()));
            }

            if (isTrainer) {
                modules.setModel(new TrainingModuleAdminTableModel().loadData(Application.Database.TrainingModules.getAll()));
            }

            for (int i = 0; i < modules.getColumnCount(); i++) {
                TableHelpers.centerColumn(modules, i);
            }
        } catch (SQLException e) {
            Dialog.error("Error loading training modules");
        }
    }

    private void setupActionListeners() {
        startTrainingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartModule().setVisible(true);
            }
        });
    }
}
