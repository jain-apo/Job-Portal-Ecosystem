package enterprise.company;

import domain.Application;
import helpers.TableHelpers;
import models.tablemodels.CandidateTable;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.sql.SQLException;

public class CandidatePage extends BaseFrame {
    private JPanel mainPane;
    private JTable candidate;

    public CandidatePage() {
        super();
        setContentPane(mainPane);
        displayPeople();
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
