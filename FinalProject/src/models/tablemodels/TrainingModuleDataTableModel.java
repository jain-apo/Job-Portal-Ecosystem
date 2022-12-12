package models.tablemodels;

import models.TrainingModuleData;

public class TrainingModuleDataTableModel extends BaseTableModel<TrainingModuleData> {
    public TrainingModuleDataTableModel() {
        super(new String[]{"Id", "Title", "[view]"});
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
