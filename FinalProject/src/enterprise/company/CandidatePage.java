package enterprise.company;

import domain.Application;
import helpers.TableHelpers;
import models.tablemodels.CandidateTable;
import views.BaseFrame;

import javax.swing.*;

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


        var details = Application.CandidateDirectory.getCandidate();

        model.loadData(details);

        candidate.setModel(model);

        TableHelpers.centerColumn(candidate, 0);
        TableHelpers.centerColumn(candidate, 3);
        TableHelpers.centerColumn(candidate, 4);
    }
}
