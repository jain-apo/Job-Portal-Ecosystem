package models;

import java.util.Objects;

public class TrainingModuleDataCompletions {
    private int id;
    private int moduleDataId;
    private int personId;

    public TrainingModuleDataCompletions(int id, int moduleDataId, int personId) {
        this.id = id;
        this.moduleDataId = moduleDataId;
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModuleDataId() {
        return moduleDataId;
    }

    public void setModuleDataId(int moduleDataId) {
        this.moduleDataId = moduleDataId;
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
        TrainingModuleDataCompletions that = (TrainingModuleDataCompletions) o;
        return id == that.id && moduleDataId == that.moduleDataId && personId == that.personId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moduleDataId, personId);
    }
}
