package models;

import java.util.Collection;
import java.util.Objects;

public class Role {
    private int id;
    private String name;
    private Collection<PersonRole> personRolesById;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Collection<PersonRole> getPersonRolesById() {
        return personRolesById;
    }

    public void setPersonRolesById(Collection<PersonRole> personRolesById) {
        this.personRolesById = personRolesById;
    }

    @Override
    public String toString() {
        return name;
    }
}
