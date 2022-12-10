package models;

import java.util.Objects;

public class CompanyEmployee {
    private int id;
    private int personId;
    private int companyId;
    private int companyTeamId;
    private Company companyByCompanyId;

    public CompanyEmployee(int id, int personId, int companyId, int companyTeamId) {
        this.id = id;
        this.personId = personId;
        this.companyId = companyId;
        this.companyTeamId = companyTeamId;
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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getCompanyTeamId() {
        return companyTeamId;
    }

    public void setCompanyTeamId(int companyTeamId) {
        this.companyTeamId = companyTeamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEmployee that = (CompanyEmployee) o;
        return id == that.id && personId == that.personId && companyId == that.companyId && companyTeamId == that.companyTeamId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, companyId, companyTeamId);
    }

    public Company getCompanyByCompanyId() {
        return companyByCompanyId;
    }

    public void setCompanyByCompanyId(Company companyByCompanyId) {
        this.companyByCompanyId = companyByCompanyId;
    }
}
