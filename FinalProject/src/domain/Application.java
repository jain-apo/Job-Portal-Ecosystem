package domain;

import domain.database.DatabaseLayer;
import models.Person;

public class Application {
    public static String sqlDateFormat = "yyyy-MM-dd";
    public static DatabaseLayer Database = new DatabaseLayer();
    static Person currentlyLoggedInPerson;

    public static void initialize() {

    }

    public static Person getCurrentlyLoggedInPerson() {
        return currentlyLoggedInPerson;
    }

    public static void setCurrentlyLoggedInPerson(Person currentlyLoggedInPerson) {
        Application.currentlyLoggedInPerson = currentlyLoggedInPerson;
    }

    public static void refreshLoggedInPerson() {
        try {
            currentlyLoggedInPerson = Database.Persons.getById(currentlyLoggedInPerson.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
