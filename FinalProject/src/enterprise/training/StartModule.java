package enterprise.training;

import models.TrainingModule;
import views.BaseFrame;

import javax.swing.*;
import java.awt.*;

public class StartModule extends BaseFrame {

    private final TrainingModule module;
    private JPanel p;
    private JPanel mainPanel;
    private JLabel heading;
    private JPanel descriptionPanel;
    private JTextArea moduleDescription;

    public StartModule(TrainingModule module) {
        super();
        this.module = module;
        setContentPane(p);
        loadData();
    }

    private void loadData() {
        heading.setText(module.getName());
        descriptionPanel.setMaximumSize(new Dimension(800, 1000));
        //moduleDescription.setText(Application.Database.TrainingQuestions.getAll().stream().filter(trainingQuestion -> trainingQuestion.getTrainingModuleId() == module.getId()));
    }
}
