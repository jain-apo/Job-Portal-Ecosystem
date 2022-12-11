package models;

import java.util.Objects;

public class ModuleDataComplettions {
    private int id;
    private int moduleDataId;
    private int personId;

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
        ModuleDataComplettions that = (ModuleDataComplettions) o;
        return id == that.id && moduleDataId == that.moduleDataId && personId == that.personId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moduleDataId, personId);
    }
}
