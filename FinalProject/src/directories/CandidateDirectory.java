package directories;

import domain.Application;
import models.JobCandidate;

import java.util.ArrayList;
import java.util.List;

public class CandidateDirectory {

    List<JobCandidate> jobCandidateList = new ArrayList<>();

    public CandidateDirectory() {
        loadFromDatabase();
    }

    public void loadFromDatabase() {
        try {
            this.jobCandidateList = Application.Database.JobCandidates.getAll();
            System.out.println("There are " + jobCandidateList.stream().count() + " people in the database");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public List<JobCandidate> getCandidate() {
        return jobCandidateList;
    }

    public JobCandidate getCandidateById(int id) {
        return jobCandidateList.stream().filter(candidate -> candidate.getId() == id).findFirst().orElse(null);
    }
}


