package models;

import domain.Application;

import java.sql.SQLException;
import java.util.Objects;

public class PersonRole {
    private int id;
    private int personId;
    private int roleId;

    public PersonRole(int id, int personId, int roleId) {
        this.id = id;
        this.personId = personId;
        this.roleId = roleId;
    }

    public PersonRole(int personId, int roleId) {
        this.personId = personId;
        this.roleId = roleId;
    }

    public void create() throws SQLException {
        Application.Database.PersonRoles.add(this);
    }

    public Role getRole() throws SQLException {
        return Application.Database.Roles.getById(roleId);
    }

    public Person getPerson() throws SQLException {
        return Application.Database.Persons.getById(personId);
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
}
