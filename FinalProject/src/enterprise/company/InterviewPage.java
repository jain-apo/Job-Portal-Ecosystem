package enterprise.company;

import domain.Application;
import helpers.TableHelpers;
import models.JobCandidate;
import models.tablemodels.BaseTableModel;
import models.tablemodels.CandidateInterviewTableModel;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class InterviewPage extends BaseFrame {
    private final int PROFILE_PAGE = 3;
    private final int INTERVIEW_PAGE = 4;
    private JPanel mainPane;
    private JTable candidate;
    private JLabel heading;


    public InterviewPage() {
        super();
        setContentPane(mainPane);
        displayPeople();
        setupAction();
    }

    private void displayPeople() {
        CandidateInterviewTableModel model = new CandidateInterviewTableModel();

        try {
            candidate.setModel(new CandidateInterviewTableModel().loadData(Application.Database.JobCandidates.getAll()
                    .stream().filter(jobCandidate -> !jobCandidate.getIsRejected()).collect(Collectors.toList())));
        } catch (SQLException e) {
            Dialog.error("Error loading candidates");
        }

        TableHelpers.centerColumn(candidate, 0);
        TableHelpers.centerColumn(candidate, 3);
        TableHelpers.centerColumn(candidate, 4);
    }

    private void setupAction() {
        candidate.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                JTable target = (JTable) me.getSource();
                int row = target.getSelectedRow(); // selected row
                int column = target.getSelectedColumn(); // selected column
                if (me.getClickCount() == 2) {
                    System.out.println("double click");

                    JobCandidate jobCandidate = ((BaseTableModel<JobCandidate>) target.getModel()).getDataAt(row);

                    if (column == PROFILE_PAGE) {
                        System.out.println("Profile button Clicked");
                        new CandidateProfile(jobCandidate).setVisible(true);
                    }
                    if (column == INTERVIEW_PAGE) {
                        System.out.println("Profile button Clicked");
                        new TakeInterviewPage(jobCandidate, () -> {
                            displayPeople();
                        }).setVisible(true);
                    }

                }
            }
        });
    }
}

