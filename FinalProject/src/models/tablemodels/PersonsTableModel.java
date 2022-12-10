package models.tablemodels;

import helpers.DateHelper;
import models.Person;

public class PersonsTableModel extends BaseTableModel<Person> {
    public PersonsTableModel() {
        super(new String[]{"Id", "Full Name", "Date of Birth", "[edit]", "[delete]"});
    }

    @Override
    public Object[] rowMapping(Person item) {
        return new Object[]{
                item.getId(),
                item.getFullName(),
                DateHelper.formatDate(item.getDateOfBirth(), "MMM-dd yyyy"),
                "✖",
                "✖"};
    }
}
