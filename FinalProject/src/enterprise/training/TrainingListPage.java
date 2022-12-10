package enterprise.training;

import views.BaseFrame;

import javax.swing.*;

public class TrainingListPage extends BaseFrame {
    private JPanel p;
    private JPanel mainPanel;
    private JLabel heading;
    private JButton addModuleButton;
    private JTable people;
    private JTextArea validationText;

    public TrainingListPage() {
        setContentPane(p);
    }
}
