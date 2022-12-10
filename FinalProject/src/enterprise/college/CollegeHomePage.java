package enterprise.college;

import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CollegeHomePage extends BaseFrame {
    private JPanel mainPane;
    private JButton studentButton;
    private JButton HRButton;

    public CollegeHomePage() {
        super();
        setContentPane(mainPane);

        HRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        HRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HRPortal().setVisible(true);
            }
        });
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentPortal().setVisible(true);
            }
        });
    }
}
