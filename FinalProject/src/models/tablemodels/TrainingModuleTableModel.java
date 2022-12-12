package models.tablemodels;

import models.TrainingModule;

public class TrainingModuleTableModel extends BaseTableModel<TrainingModule> {
    public TrainingModuleTableModel() {
        super(new String[]{"Id", "Name", "Description", "[Start Module]"});
    }

    @Override
    public Object[] rowMapping(TrainingModule item) {
        return new Object[]{
                item.getId(),
                item.getName(),
                item.getDescription(),
                "✖"};
    }
}
