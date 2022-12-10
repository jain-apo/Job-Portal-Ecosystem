package models.tablemodels;

import models.PersonNotification;

public class NotificationsTableModel extends BaseTableModel<PersonNotification> {
    public NotificationsTableModel() {
        super(new String[]{"Title", "Message"});
    }

    @Override
    public Object[] rowMapping(PersonNotification item) {
        return new Object[]{
                item.getTitle(),
                item.getMessage()
        };
    }
}
