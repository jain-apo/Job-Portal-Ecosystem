package models;

import java.util.Objects;

public class CollegeStudent {
    private int id;
    private float gpa;
    private int passYear;
    private int personId;
    private int courseId;

    public CollegeStudent(int id, float gpa, int passYear, int personId, int courseId) {
        this.id = id;
        this.gpa = gpa;
        this.passYear = passYear;
        this.personId = personId;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public int getPassYear() {
        return passYear;
    }

    public void setPassYear(int passYear) {
        this.passYear = passYear;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollegeStudent that = (CollegeStudent) o;
        return id == that.id && Float.compare(that.gpa, gpa) == 0 && passYear == that.passYear && personId == that.personId && courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gpa, passYear, personId, courseId);
    }
}
