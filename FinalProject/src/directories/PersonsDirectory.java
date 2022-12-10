package directories;

import domain.Application;
import models.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonsDirectory {

    List<Person> persons = new ArrayList<>();

    public PersonsDirectory() {
        loadFromDatabase();
    }

    public void loadFromDatabase() {
        try {
            this.persons = Application.Database.Persons.getAll();
            System.out.println("There are " + persons.stream().count() + " people in the database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Person> getPersons() {
        return persons;
    }

    public Person getPersonById(int id) {
        return persons.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
    }
}
