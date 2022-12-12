package models;

import java.util.Collection;
import java.util.Objects;

public class JobPosting {
    private int id;
    private String title;
    private String jobDescription;
    private String category;
    private int companyId;
    private Collection<JobApplication> jobApplicationsById;
    private Company companyByCompanyId;

    public JobPosting(int id, String title, String jobDescription, String category, int companyId) {
        this.id = id;
        this.title = title;
        this.jobDescription = jobDescription;
        this.category = category;
        this.companyId = companyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobPosting that = (JobPosting) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(jobDescription, that.jobDescription) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, jobDescription, category);
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Collection<JobApplication> getJobApplicationsById() {
        return jobApplicationsById;
    }

    public void setJobApplicationsById(Collection<JobApplication> jobApplicationsById) {
        this.jobApplicationsById = jobApplicationsById;
    }

    public Company getCompanyByCompanyId() {
        return companyByCompanyId;
    }

    public void setCompanyByCompanyId(Company companyByCompanyId) {
        this.companyByCompanyId = companyByCompanyId;
    }
}
