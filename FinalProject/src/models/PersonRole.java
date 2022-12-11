package models;

import java.util.Objects;

public class PersonRole {
    private int id;
    private int personId;
    private int roleId;
    private Person personByPersonId;

    public PersonRole(int id, int personId, int roleId) {
        this.id = id;
        this.personId = personId;
        this.roleId = roleId;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonRole that = (PersonRole) o;
        return id == that.id && personId == that.personId && roleId == that.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, roleId);
    }

    public Person getPersonByPersonId() {
        return personByPersonId;
    }

    public void setPersonByPersonId(Person personByPersonId) {
        this.personByPersonId = personByPersonId;
    }
}
