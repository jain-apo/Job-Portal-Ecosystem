package enterprise.training;

import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainingListPage extends BaseFrame {
    private JPanel p;
    private JPanel mainPanel;
    private JLabel heading;
    private JButton addModuleButton;
    private JTable modules;
    private JTextArea validationText;
    private JButton startTrainingButton;

    public TrainingListPage() {
        setupActionListeners();
        setContentPane(p);
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
