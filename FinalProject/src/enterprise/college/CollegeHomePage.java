package enterprise.college;

import domain.Application;
import views.BaseFrame;

import javax.swing.*;

public class CollegeHomePage extends BaseFrame {
    private JPanel p;
    private JPanel mainPane;
    private JLabel heading;
    private JButton adminPortalButton;
    private JButton HRPortalButton;
    private JButton studentPortalButton;
    private JButton studentRegistrationButton;

    public CollegeHomePage() {
        super();
        setContentPane(p);
        try {
            setupRoles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setupActions();
    }

    private void setupRoles() {
        var person = Application.getCurrentlyLoggedInPerson();

        boolean isAdmin = person.getRoles().stream().anyMatch(role -> role.getName().equals("COLLEGE_ADMIN"));
        boolean isHr = person.getRoles().stream().anyMatch(role -> role.getName().equals("COLLEGE_HR"));
        boolean isStudent = person.getRoles().stream().anyMatch(role -> role.getName().equals("COLLEGE_STUDENT"));

        if (!isAdmin) {
            adminPortalButton.setVisible(false);
        }

        if (!isHr) {
            HRPortalButton.setVisible(false);
        }

        if (!isStudent) {
            studentPortalButton.setVisible(false);
        }

        if (isAdmin || isHr || isStudent) {
            studentRegistrationButton.setVisible(false);
        }
    }

    private void setupActions() {
        adminPortalButton.addActionListener(e -> new CollegeAdminHomePage().setVisible(true));
        HRPortalButton.addActionListener(e -> new CollegeHRHomePage().setVisible(true));
        studentPortalButton.addActionListener(e -> new CollegeStudentHomePage().setVisible(true));
        studentRegistrationButton.addActionListener(e -> new StudentRegistrationPage().setVisible(true));
    }
}
