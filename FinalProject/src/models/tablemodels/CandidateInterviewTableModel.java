package models.tablemodels;

import domain.Application;
import helpers.DateHelper;
import models.JobCandidate;
import models.Person;
import utils.Dialog;

import java.sql.SQLException;
import java.util.HashMap;

public class CandidateInterviewTableModel extends BaseTableModel<JobCandidate> {

    private HashMap<Integer, Person> personsCache;

    public CandidateInterviewTableModel() {
        super(new String[]{"Id", "Full Name", "Date of Birth", "[profile]", "[Take interview]"});

        personsCache = new HashMap<>();

        try {
            Application.Database.Persons.getAll().forEach(person -> {
                personsCache.put(person.getId(), person);
            });
        } catch (SQLException e) {
            Dialog.error("Error loading persons for candidate table");
        }
    }

    @Override
    protected boolean canShowObject(JobCandidate item) {
        return !(item.getIsRejected() || item.getIsAccepted());
    }

    @Override
    public Object[] rowMapping(JobCandidate item) {

        var person = personsCache.get(item.getPersonId());

        return new Object[]{
                item.getId(),
                person.getFullName(),
                DateHelper.formatDate(person.getDateOfBirth(), "MMM-dd yyyy"),
                "✖",
                "✖"
        };
    }
}
