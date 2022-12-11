package enterprise.college;

import views.BaseFrame;

import javax.swing.*;

public class StudentRegistrationPage extends BaseFrame {
    private JLabel heading;
    private JPanel mainPane;
    private JButton registerButton;
    private JPanel registrationPane;
    private JTextField dateOfBirth;
    private JComboBox role;

    public StudentRegistrationPage() {
        super();
        setContentPane(mainPane);
    }
}
