package enterprise.college;

import domain.Application;
import models.CollegeStudent;
import models.Course;
import models.PersonRole;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRegistrationPage extends BaseFrame {
    private JLabel heading;
    private JPanel mainPane;
    private JButton registerButton;
    private JPanel registrationPane;
    private JComboBox<Course> course;
    private JComboBox<Integer> yearOfPassing;

    public StudentRegistrationPage() {
        super();
        setContentPane(mainPane);

        loadFields();

        registerButton.addActionListener(e -> register());
    }

    private void loadFields() {
        List<Integer> years = new ArrayList<>();
        for (int i = 2023; i <= 2026; i++) {
            years.add(i);
        }

        try {
            Application.Database.Courses.getAll().forEach(course -> this.course.addItem(course));
        } catch (SQLException e) {
            Dialog.error("Failed to load courses");
        }

        yearOfPassing.setModel(new DefaultComboBoxModel<>(years.toArray(new Integer[0])));
    }

    private void register() {
        List<String> errors = new ArrayList<>();

        if (yearOfPassing.getSelectedItem() == null) {
            errors.add("Year of passing is required");
        }

        if (course.getSelectedItem() == null) {
            errors.add("Course is required");
        }

        if (errors.size() > 0) {
            Dialog.error(String.join("\n", errors), "Can't Register");
            return;
        }

        double min = 3.2;
        double max = 4.0;

        double gpa = min + Math.random() * (max - min);

        try {
            var student = new CollegeStudent(0, (float) gpa, Integer.parseInt(yearOfPassing.getSelectedItem().toString()),
                    Application.getCurrentlyLoggedInPerson().getId(),
                    ((Course) course.getSelectedItem()).getId()
            );


            var studentRole = Application.Database.Roles.getAll().stream().filter(role -> role.getName().equals("COLLEGE_STUDENT")).findFirst().orElse(null);

            Application.Database.CollegeStudents.add(student);
            Application.Database.PersonRoles.add(new PersonRole(0, Application.getCurrentlyLoggedInPerson().getId(), studentRole.getId()));
            Application.refreshLoggedInPerson();

            Dialog.info("Registration successful");

            switchToWindow(new CollegeHomePage());

        } catch (Exception e) {
            Dialog.error("Failed to register");
        }

    }
}
