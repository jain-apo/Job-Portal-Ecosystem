package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class JobApplication {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "personId")
    private int personId;
    @Basic
    @Column(name = "jobPostingId")
    private int jobPostingId;
    @Basic
    @Column(name = "yearsOfExperience")
    private int yearsOfExperience;

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
}
