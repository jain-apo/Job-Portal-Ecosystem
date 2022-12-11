package enterprise.company;

import domain.Application;
import helpers.TableHelpers;
import models.tablemodels.CandidateTable;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class CandidatePage extends BaseFrame {

    private final int PROFILE_PAGE = 3;
    private JPanel mainPane;
    private JTable candidate;
    private JLabel heading;

    public CandidatePage() {
        super();
        setContentPane(mainPane);
        displayPeople();
        setupAction();
    }

    public void setupAction() {
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

                    if (column == PROFILE_PAGE) {
                        System.out.println("Profile button Clicked");
                        new CandidateProfile(personId).setVisible(true);
                    }

                }
            }
        });
    }

    private void displayPeople() {
        CandidateTable model = new CandidateTable();

        try {
            candidate.setModel(new CandidateTable().loadData(Application.Database.JobCandidates.getAll()));
        } catch (SQLException e) {
            Dialog.error("Error loading candidates");
        }

        TableHelpers.centerColumn(candidate, 0);
        TableHelpers.centerColumn(candidate, 3);
    }
}
