package enterprise.college;

import views.BaseFrame;

import javax.swing.*;

public class CollegeAdminHomePage extends BaseFrame {
    private JPanel p;
    private JPanel mainPane;
    private JLabel heading;
    private JPanel registrationPane;
    private JTextField dateOfBirth;
    private JComboBox role;
    private JButton registerButton;

    public CollegeAdminHomePage() {
        super();
        setContentPane(p);
    }
}
