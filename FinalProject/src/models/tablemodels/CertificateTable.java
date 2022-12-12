package models.tablemodels;

import models.TrainingCertificate;

public class CertificateTable extends BaseTableModel<TrainingCertificate> {
    public CertificateTable() {
        super(new String[]{"Name"});
    }

    @Override
    public Object[] rowMapping(TrainingCertificate item) {
        return new Object[]{
                item.getName(),
        };
    }
}
