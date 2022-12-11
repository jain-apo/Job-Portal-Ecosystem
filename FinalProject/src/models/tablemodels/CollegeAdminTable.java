package models.tablemodels;

import models.Course;

public class CollegeAdminTable extends BaseTableModel<Course> {
    public CollegeAdminTable() {
        super(new String[]{"id", "name", "[edit]", "[delete]"});
    }

    @Override
    public Object[] rowMapping(Course item) {
        return new Object[]{
                item.getId(),
                item.getName(),
                "✖",
                "✖"};
    }
}

