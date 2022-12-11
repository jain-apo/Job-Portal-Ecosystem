package models.tablemodels;

import helpers.DateHelper;
import models.Person;

public class HRTableModel extends BaseTableModel<Person> {
    public HRTableModel() {
        super(new String[]{"Id", "Full Name", "Date of Birth", "Gpa", "Year of Passing", "Course Name"});
    }

    @Override
    public Object[] rowMapping(Person item) {
        return new Object[]{
                item.getId(),
                item.getFullName(),
                DateHelper.formatDate(item.getDateOfBirth(), "MMM-dd yyyy"),
                item.getCollegeStudentData().getGpa(),
                item.getCollegeStudentData().getPassYear(),
                item.getCollegeStudentData().getCourseId(),
                "✖",
                "✖"};
    }
}
