package models;

import domain.Application;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String username;
    private String password;
    private String email;
    private String phone;
    private boolean isAdministrator;
    private Collection<CollegeStudent> collegeStudentsById;
    private Collection<CompanyEmployee> companyEmployeesById;
    private Collection<JobApplication> jobApplicationsById;
    private Collection<JobCandidate> jobCandidatesById;
    private CollegeStudent collegeStudentData;

    public Person(int id, String firstName, String lastName, Date dateOfBirth, String username, String password, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public CollegeStudent getCollegeStudentData() {
        return collegeStudentData;
    }

    public void setCollegeStudentData(CollegeStudent collegeStudentData) {
        this.collegeStudentData = collegeStudentData;
    }

    public List<Role> getRoles() throws SQLException {
        return Application.Database.PersonRoles.getAll().stream().filter(personRole -> personRole.getPersonId() == id).map(personRole -> {
            try {
                return personRole.getRole();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }).toList();
    }

    public boolean hasRole(String role) {
        try {
            return getRoles().stream().anyMatch(role1 -> role1.getName().equals(role));
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(dateOfBirth, person.dateOfBirth) && Objects.equals(username, person.username) && Objects.equals(password, person.password) && Objects.equals(email, person.email) && Objects.equals(phone, person.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth, username, password, email, phone);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Collection<CollegeStudent> getCollegeStudentsById() {
        return collegeStudentsById;
    }

    public void setCollegeStudentsById(Collection<CollegeStudent> collegeStudentsById) {
        this.collegeStudentsById = collegeStudentsById;
    }

    public Collection<CompanyEmployee> getCompanyEmployeesById() {
        return companyEmployeesById;
    }

    public void setCompanyEmployeesById(Collection<CompanyEmployee> companyEmployeesById) {
        this.companyEmployeesById = companyEmployeesById;
    }

    public Collection<JobApplication> getJobApplicationsById() {
        return jobApplicationsById;
    }

    public void setJobApplicationsById(Collection<JobApplication> jobApplicationsById) {
        this.jobApplicationsById = jobApplicationsById;
    }

    public Collection<JobCandidate> getJobCandidatesById() {
        return jobCandidatesById;
    }

    public void setJobCandidatesById(Collection<JobCandidate> jobCandidatesById) {
        this.jobCandidatesById = jobCandidatesById;
    }
}
