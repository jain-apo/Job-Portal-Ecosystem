package models.tablemodels;

import models.TrainingModule;

public class TrainingModuleAdminTableModel extends BaseTableModel<TrainingModule> {
    public TrainingModuleAdminTableModel() {
        super(new String[]{"Id", "Name", "Description", "[Start Module]", "[Edit]", "[Delete]"});
    }

    @Override
    public Object[] rowMapping(TrainingModule item) {
        return new Object[]{
                item.getId(),
                item.getName(),
                item.getDescription(),
                "✖", "✖", "✖"};
    }
}
