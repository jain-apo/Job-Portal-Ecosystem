package enterprise.company;

import domain.Application;
import helpers.TableHelpers;
import models.tablemodels.CertificateTableModel;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.sql.SQLException;

public class ViewCertificate extends BaseFrame {

    private JPanel p;
    private JTable certificate;
    private JPanel mainPane;
    private JLabel heading;

    public ViewCertificate() {
        super();
        setContentPane(p);
        displayPeople();
    }

    private void displayPeople() {
        CertificateTableModel model = new CertificateTableModel();

        try {
            certificate.setModel(new CertificateTableModel().loadData(Application.Database.TrainingCertificates.getAll()));
            //TODO filter by id
        } catch (SQLException e) {
            Dialog.error("Error getting people");
            return;
        }

        TableHelpers.centerColumn(certificate, 0);

    }
}
