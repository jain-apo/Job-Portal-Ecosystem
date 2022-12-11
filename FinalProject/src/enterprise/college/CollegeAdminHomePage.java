package enterprise.college;

import views.BaseFrame;

import javax.swing.*;

public class CollegeAdminHomePage extends BaseFrame {
    private JPanel p;
    private JPanel mainPane;
    private JLabel heading;
    private JPanel mainPanel;
    private JButton addCourseButton;
    private JButton cancelEditButton;
    private JPanel addCoursePanel;
    private JTextField courseNAme;
    private JTable people;
    private JTextArea validationText;
    private JScrollPane courses;

    public CollegeAdminHomePage() {
        super();
        setContentPane(p);
    }
}
