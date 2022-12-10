package enterprise.training;

import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainingHomePage extends BaseFrame {

    private JPanel mainPane;
    private JButton trainingSiteButton;

    public TrainingHomePage() {
        setContentPane(mainPane);
        trainingSiteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TrainingListPage().setVisible(true);
            }
        });
    }
}
