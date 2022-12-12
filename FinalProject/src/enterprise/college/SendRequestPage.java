package enterprise.college;

import domain.Application;
import domain.Roles;
import models.PersonNotification;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class SendRequestPage extends BaseFrame {

    private JPanel p;
    private JButton sendButton;
    private JLabel heading;
    private JTextArea remakrs;

    public SendRequestPage() {
        super();
        setContentPane(p);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendRequest();
            }
        });
    }

    private void sendRequest() {
        var result = Dialog.confirm("Are you sure you want to send this request?");

        if (result == JOptionPane.YES_OPTION) {

            var myName = Application.getCurrentlyLoggedInPerson().getFullName();

            try {
                var companyHrs = Application.Database.Persons.getAll().stream().filter(person -> {
                    try {
                        return person.getRoles().stream().anyMatch(r -> r.getName().equals(Roles.COMPANY_HR));
                    } catch (SQLException e) {
                        return false;
                    }
                }).collect(Collectors.toList());

                if (companyHrs.size() == 0) {
                    Dialog.info("There are no company HRs in the system");
                    return;
                }

                System.out.println(companyHrs);

                companyHrs.forEach(hr -> {
                    try {
                        new PersonNotification(hr.getId(), "Placement Request", remakrs.getText()).create();
                    } catch (SQLException e) {
                        System.out.println("Error while sending notification");
                    }
                });

                Dialog.info("Request sent successfully to " + companyHrs.size() + " company HRs");
            } catch (SQLException e) {
                //
            }

            dispose();
        }
    }
}
