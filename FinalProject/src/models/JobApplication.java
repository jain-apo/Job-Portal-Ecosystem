package models;

import java.util.Collection;
import java.util.Objects;

public class JobApplication {
    private int id;
    private int personId;
    private int jobPostingId;
    private int yearsOfExperience;
    private String resumeFile;
    private Collection<JobCandidate> jobCandidatesById;

    public JobApplication(int id, int personId, int jobPostingId, int yearsOfExperience, String resumeFile) {
        this.id = id;
        this.personId = personId;
        this.jobPostingId = jobPostingId;
        this.yearsOfExperience = yearsOfExperience;
        this.resumeFile = resumeFile;
    }

    public String getResumeFile() {
        return resumeFile;
    }

    public void setResumeFile(String resumeFile) {
        this.resumeFile = resumeFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getJobPostingId() {
        return jobPostingId;
    }

    public void setJobPostingId(int jobPostingId) {
        this.jobPostingId = jobPostingId;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobApplication that = (JobApplication) o;
        return id == that.id && personId == that.personId && jobPostingId == that.jobPostingId && yearsOfExperience == that.yearsOfExperience;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, jobPostingId, yearsOfExperience);
    }

    public Collection<JobCandidate> getJobCandidatesById() {
        return jobCandidatesById;
    }

    public void setJobCandidatesById(Collection<JobCandidate> jobCandidatesById) {
        this.jobCandidatesById = jobCandidatesById;
    }
}
