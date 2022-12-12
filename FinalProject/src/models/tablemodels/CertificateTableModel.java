package models.tablemodels;

import models.TrainingCertificate;

public class CertificateTableModel extends BaseTableModel<TrainingCertificate> {
    public CertificateTableModel() {
        super(new String[]{"Name"});
    }

    @Override
    public Object[] rowMapping(TrainingCertificate item) {
        return new Object[]{
                item.getName(),
        };
    }
}
