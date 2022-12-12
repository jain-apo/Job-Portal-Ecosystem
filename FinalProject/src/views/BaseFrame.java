package views;

import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {
    public BaseFrame() {
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
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    protected void switchToWindow(JFrame nextWindow) {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(false);
        nextWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nextWindow.setVisible(true);
    }

    protected void swapWindow(JFrame nextWindow) {
        nextWindow.setVisible(true);
        dispose();
    }
}
