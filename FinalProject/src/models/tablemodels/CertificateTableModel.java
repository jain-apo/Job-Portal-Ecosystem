package models.tablemodels;

import models.TrainingCertificate;

import java.sql.SQLException;

public class CertificateTableModel extends BaseTableModel<TrainingCertificate> {
    public CertificateTableModel() {
        super(new String[]{"Module Name", "Certificate Name", "Received On"});
    }

    @Override
    public Object[] rowMapping(TrainingCertificate item) {

        String trainingModuleName = "";

        try {
            trainingModuleName = item.getTrainingModule().getName();
        } catch (SQLException e) {
            //
        }

        return new Object[]{
                trainingModuleName,
                item.getName(),
                "",
        };
    }
}
