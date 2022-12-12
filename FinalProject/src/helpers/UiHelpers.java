package helpers;

import models.Person;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class UiHelpers {
    public static void buttonRole(JButton button, String[] roleList, Person person) throws SQLException {
        var personRoles = person.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());
        personRoles.retainAll(Arrays.stream(roleList).toList());
        button.setVisible(personRoles.size() > 0);
    }
}
