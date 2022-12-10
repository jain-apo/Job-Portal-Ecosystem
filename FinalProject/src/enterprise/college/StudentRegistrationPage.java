package enterprise.college;

import views.BaseFrame;

import javax.swing.*;

public class StudentRegistrationPage extends BaseFrame {
    private JLabel heading;
    private JPanel addPersonPane;
    private JTextField dateOfBirth;
    private JComboBox role;
    private JPanel mainPane;

    public StudentRegistrationPage() {
        super();
        setContentPane(mainPane);
    }
}
