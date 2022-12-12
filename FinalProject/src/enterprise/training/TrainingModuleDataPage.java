package enterprise.training;

import models.TrainingModuleData;
import views.BaseFrame;

import javax.swing.*;
import java.awt.*;

public class TrainingModuleDataPage extends BaseFrame {
    private final TrainingModuleData moduleData;
    private JPanel p;
    private JPanel mainPanel;
    private JLabel heading;
    private JTextArea moduleDescription;
    private JPanel descriptionPanel;

    public TrainingModuleDataPage(TrainingModuleData moduleData) {
        this.moduleData = moduleData;

        loadData();

        setContentPane(p);
    }

    private void loadData() {
        heading.setText(moduleData.getTitle());
        descriptionPanel.setMaximumSize(new Dimension(800, 1000));
        moduleDescription.setText(moduleData.getDescription());
        moduleDescription.setEditable(false);
    }
}
