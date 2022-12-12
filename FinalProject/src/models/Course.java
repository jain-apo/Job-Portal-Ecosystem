package models;

import java.util.Collection;
import java.util.Objects;

public class Course {
    private int id;
    private String name;
    private Collection<CollegeStudent> collegeStudentsById;

    public Course(int id, String name) {
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
        Course course = (Course) o;
        return id == course.id && Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Collection<CollegeStudent> getCollegeStudentsById() {
        return collegeStudentsById;
    }

    public void setCollegeStudentsById(Collection<CollegeStudent> collegeStudentsById) {
        this.collegeStudentsById = collegeStudentsById;
    }

    @Override
    public String toString() {
        return name;
    }
}
