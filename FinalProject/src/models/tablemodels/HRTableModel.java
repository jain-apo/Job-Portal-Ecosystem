package models.tablemodels;

import models.Company;

public class HRTableModel extends BaseTableModel<Company> {
    public HRTableModel() {
        super(new String[]{"Id", "Company Name", "Send Request"});
    }

    @Override
    public Object[] rowMapping(Company item) {
        return new Object[]{
                item.getId(),
                item.getName(),
                "✖",
        };
    }
}
