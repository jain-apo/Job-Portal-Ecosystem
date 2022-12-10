package enterprise.company;

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

        candidate.setModel(model);

//        var details = Application.PersonsDirectory.getPerson();
//
//        for (var person : details) {
//            Object[] data = new Object[]{
//                    person.getId(),
//                    person.getFullName(),
//                    DateHelper.formatDate(person.getDateOfBirth(), "MMM-dd yyyy"),
//                    person.getRole(),
//                    "✖",
//                    "✖",
//            };
//
//            model.addRow(data);
//        }
//
//        candidate.setModel(model);
//
//        TableHelpers.centerColumn(people, 0);
//        TableHelpers.centerColumn(people, 4);
//        TableHelpers.centerColumn(people, 5);
    }
}
