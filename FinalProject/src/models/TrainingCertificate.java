package models;

import domain.Application;

import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

public class TrainingCertificate {
    private int id;
    private String name;
    private int trainingModuleId;
    private int personId;
    private Date certifiedDate;

    public TrainingCertificate(int id, String name, int trainingModuleId, int personId, Date certifiedDate) {
        this.id = id;
        this.name = name;
        this.trainingModuleId = trainingModuleId;
        this.personId = personId;
        this.certifiedDate = certifiedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrainingModule getTrainingModule() throws SQLException {
        return Application.Database.TrainingModules.getById(trainingModuleId);
    }

    public int getTrainingModuleId() {
        return trainingModuleId;
    }

    public void setTrainingModuleId(int trainingModuleId) {
        this.trainingModuleId = trainingModuleId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingCertificate that = (TrainingCertificate) o;
        return id == that.id && trainingModuleId == that.trainingModuleId && personId == that.personId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, trainingModuleId, personId);
    }

    public Date getCertifiedDate() {
        return certifiedDate;
    }

    public void setCertifiedDate(Date certifiedDate) {
        this.certifiedDate = certifiedDate;
    }
}
