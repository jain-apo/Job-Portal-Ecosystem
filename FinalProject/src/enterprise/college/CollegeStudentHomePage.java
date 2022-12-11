package enterprise.college;

import domain.Application;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.sql.SQLException;

public class CollegeStudentHomePage extends BaseFrame {
    private JPanel p;
    private JPanel mainPane;
    private JLabel heading;
    private JLabel courseName;
    private JLabel year;
    private JPanel myDetailsPane;

    public CollegeStudentHomePage() {
        super();

        setupVisuals();

        try {
            hydrateData();
        } catch (Exception e) {
            Dialog.error("Failed to load my student data");
        }
        
        setContentPane(p);
    }

    private void hydrateData() throws SQLException {
        var person = Application.getCurrentlyLoggedInPerson();

        var course = Application.Database.Courses.getById(person.getCollegeStudentData().getCourseId());

        if (course != null) {
            courseName.setText(course.getName());
        }

        year.setText(person.getCollegeStudentData().getPassYear() + "");
    }

    private void setupVisuals() {
        myDetailsPane.setBorder(BorderFactory.createTitledBorder("My Details"));
    }
}
