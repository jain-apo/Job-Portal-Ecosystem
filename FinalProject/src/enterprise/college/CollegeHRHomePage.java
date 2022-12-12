package enterprise.college;

import domain.Application;
import helpers.TableHelpers;
import models.tablemodels.HRTableModel;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class CollegeHRHomePage extends BaseFrame {
    private final int SEND_REQUEST = 2;
    private JPanel p;
    private JPanel mainPane;
    private JTable candidate;
    private JLabel heading;
    private JScrollPane students;

    public CollegeHRHomePage() {
        super();
        setContentPane(p);
        displayPeople();
        setupActions();
    }

    private void setupActions() {


        candidate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);

                JTable target = (JTable) me.getSource();
                int row = target.getSelectedRow(); // selected row
                int column = target.getSelectedColumn(); // selected column

                if (me.getClickCount() == 2) {
                    System.out.println("double click");
                    int personId = Integer.parseInt(target.getModel().getValueAt(row, 0) + "");

                    String personName = target.getModel().getValueAt(row, 1) + "";

                    if (column == SEND_REQUEST) {
                        new SendRequestPage().setVisible(true);
                        System.out.println("send request");
                    }

                }
            }
        });

    }

    private void displayPeople() {
        HRTableModel model = new HRTableModel();

        try {
            candidate.setModel(new HRTableModel().loadData(Application.Database.Companies.getAll()));
        } catch (SQLException e) {
            Dialog.error("Error getting people");
            return;
        }
        TableHelpers.centerColumn(candidate, 0);
        TableHelpers.centerColumn(candidate, 1);
        TableHelpers.centerColumn(candidate, 2);
    }
}


