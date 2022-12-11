package models.tablemodels;

import models.Company;

public class HRTableModel extends BaseTableModel<Company> {
    public HRTableModel() {
        super(new String[]{"Company Name", "Send Request"});
    }

    @Override
    public Object[] rowMapping(Company item) {
        return new Object[]{
                item.getName(),
                "✖",
        };
    }
}
