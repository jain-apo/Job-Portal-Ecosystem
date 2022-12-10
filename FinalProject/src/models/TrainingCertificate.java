package models;

import java.util.Objects;

public class TrainingCertificate {
    private int id;
    private String name;
    private int trainingModuleId;
    private int personId;
    private Person personByPersonId;

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

    public Person getPersonByPersonId() {
        return personByPersonId;
    }

    public void setPersonByPersonId(Person personByPersonId) {
        this.personByPersonId = personByPersonId;
    }
}
