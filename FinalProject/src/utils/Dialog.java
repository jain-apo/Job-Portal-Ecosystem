package utils;

import javax.swing.*;

public class Dialog {
    public static void show(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static void show(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void show(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

    public static void show(String message, String title, int messageType, Icon icon) {
        JOptionPane.showMessageDialog(null, message, title, messageType, icon);
    }

    public static String input(String message) {
        return JOptionPane.showInputDialog(message);
    }

    public static String input(String message, String title) {
        return JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
    }

    public static String input(String message, String title, Icon icon) {
        return JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE, icon, null, null).toString();
    }

    public static int confirm(String message) {
        return JOptionPane.showConfirmDialog(null, message);
    }

    public static int confirm(String message, String title) {
        return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
    }

    public static int confirm(String message, String title, int optionType) {
        return JOptionPane.showConfirmDialog(null, message, title, optionType);
    }

    public static int confirm(String message, String title, int optionType, int messageType) {
        return JOptionPane.showConfirmDialog(null, message, title, optionType, messageType);
    }

    public static int confirm(String message, String title, int optionType, int messageType, Icon icon) {
        return JOptionPane.showConfirmDialog(null, message, title, optionType, messageType, icon);
    }

    public static void error(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void error(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void error(String message, String title, Icon icon) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE, icon);
    }

    public static void warning(String message) {
        JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public static void warning(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }

    public static void warning(String message, String title, Icon icon) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE, icon);
    }

    public static void info(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void info(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void info(String message, String title, Icon icon) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public static void question(String message) {
        JOptionPane.showMessageDialog(null, message, "Question", JOptionPane.QUESTION_MESSAGE);
    }

    public static void question(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
    }

    public static void question(String message, String title, Icon icon) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.QUESTION_MESSAGE, icon);
    }

    public static void plain(String message) {
        JOptionPane.showMessageDialog(null, message, "Plain", JOptionPane.PLAIN_MESSAGE);
    }

    public static void plain(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
    }

    public static void plain(String message, String title, Icon icon) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE, icon);
    }
}
