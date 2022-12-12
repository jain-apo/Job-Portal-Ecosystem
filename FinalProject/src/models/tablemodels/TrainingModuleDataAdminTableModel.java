package models.tablemodels;

import models.TrainingModuleData;

public class TrainingModuleDataAdminTableModel extends BaseTableModel<TrainingModuleData> {
    public TrainingModuleDataAdminTableModel() {
        super(new String[]{"Id", "Title", "[View]", "[Edit]", "[Delete]"});
    }

    @Override
    public Object[] rowMapping(TrainingModuleData item) {
        return new Object[]{
                item.getId(),
                item.getTitle(),
                "✖",
                "✖"};
    }
}
