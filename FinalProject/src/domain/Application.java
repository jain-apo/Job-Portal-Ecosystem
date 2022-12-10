package domain;

import directories.PersonsDirectory;
import domain.database.DatabaseLayer;
import models.Person;

public class Application {
    public static String sqlDateFormat = "yyyy-MM-dd";
    public static DatabaseLayer Database = new DatabaseLayer();
    public static PersonsDirectory PersonsDirectory = new PersonsDirectory();
    static Person currentlyLoggedInPerson;

    public static void initialize() {

    }

    public static Person getCurrentlyLoggedInPerson() {
        return currentlyLoggedInPerson;
    }

    public static void setCurrentlyLoggedInPerson(Person currentlyLoggedInPerson) {
        Application.currentlyLoggedInPerson = currentlyLoggedInPerson;
    }
}
