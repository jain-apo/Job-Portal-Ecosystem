package models;

import java.util.Collection;
import java.util.Objects;

public class CompanyTeam {
    private int id;
    private String name;
    private Collection<CompanyEmployee> companyEmployeesById;

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
        CompanyTeam that = (CompanyTeam) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Collection<CompanyEmployee> getCompanyEmployeesById() {
        return companyEmployeesById;
    }

    public void setCompanyEmployeesById(Collection<CompanyEmployee> companyEmployeesById) {
        this.companyEmployeesById = companyEmployeesById;
    }
}
