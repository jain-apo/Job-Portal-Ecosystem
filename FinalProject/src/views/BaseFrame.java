package views;

import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {
    BaseFrame() {
        setResizable(false);
        setSize(500, 500);
        setLocationRelativeTo(null);
    }

    @Override
    public void setContentPane(Container contentPane) {
        super.setContentPane(contentPane);
        repack();
    }

    protected void repack() {
        pack();
        setSize(getWidth() + 50, getHeight() + 50);
    }

    protected void switchToWindow(JFrame nextWindow) {
        setVisible(false);
        nextWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nextWindow.setVisible(true);
    }
}
