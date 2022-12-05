package domain;

import models.Person;

public class Application {
    public static DatabaseLayer Database = new DatabaseLayer();
    static Person currentlyLoggedInPerson;

    public static Person getCurrentlyLoggedInPerson() {
        return currentlyLoggedInPerson;
    }

    public static void setCurrentlyLoggedInPerson(Person currentlyLoggedInPerson) {
        Application.currentlyLoggedInPerson = currentlyLoggedInPerson;
    }
}
