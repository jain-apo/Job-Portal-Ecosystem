package enterprise.company;

import domain.Application;
import helpers.TableHelpers;
import models.JobCandidate;
import models.tablemodels.BaseTableModel;
import models.tablemodels.CandidateTableModel;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class CandidatesPage extends BaseFrame {

    private final int PROFILE_PAGE = 3;
    private JPanel mainPane;
    private JTable candidate;
    private JLabel heading;

    public CandidatesPage() {
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

                    var c = ((BaseTableModel<JobCandidate>) candidate.getModel()).getDataAt(row);

                    String personName = target.getModel().getValueAt(row, 1) + "";

                    if (column == PROFILE_PAGE) {
                        System.out.println("Profile button Clicked");
                        new CandidateProfile(c).setVisible(true);
                    }

                }
            }
        });
    }

    private void displayPeople() {
        CandidateTableModel model = new CandidateTableModel();

        try {
            candidate.setModel(new CandidateTableModel().loadData(Application.Database.JobCandidates.getAll()
                    .stream().filter(jobCandidate -> !jobCandidate.getIsRejected()).collect(Collectors.toList())));
        } catch (SQLException e) {
            Dialog.error("Error loading candidates");
        }

        TableHelpers.centerColumn(candidate, 0);
        TableHelpers.centerColumn(candidate, 3);
    }
}
