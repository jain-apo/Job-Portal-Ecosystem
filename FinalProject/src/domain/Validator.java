package domain;

import helpers.DateHelper;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static domain.Application.sqlDateFormat;

public class Validator {

    public static boolean validateMobileNumber(String mobile) {
        if (mobile == null || mobile.isBlank())
            return false;

        Pattern p = Pattern.compile("^\\d{10}$");

        Matcher m = p.matcher(mobile);

        return (m.matches());
    }

    public static boolean validateEmail(String email) {
        if (email == null || email.isBlank())
            return false;

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        return pat.matcher(email).matches();
    }


    public static boolean checkTextBlank(JTextComponent text) {
        if (text.getText().trim().isBlank()) {
            text.setBorder(BorderFactory.createLineBorder(Color.RED));
            return false;
        }

        text.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return true;
    }

    public static boolean checkTextsBlank(JTextField[] texts) {
        var result = true;

        for (var text : texts) {
            result = checkTextBlank(text) && result;
        }

        return result;
    }

    public static boolean checkTextsBlank(JTextComponent[] texts) {
        var result = true;

        for (var text : texts) {
            result = checkTextBlank(text) && result;
        }

        return result;
    }

    public static void setBorder(JTextField text, Color color) {
        text.setBorder(BorderFactory.createLineBorder(color));
    }

    public static boolean checkDate(JTextField text) {
        if (DateHelper.tryGetDate(text.getText(), sqlDateFormat) == null) {
            setBorder(text, Color.red);
            return false;
        }

        setBorder(text, Color.black);
        return true;
    }

    public static String validatePassword(JPasswordField pass) {
        if (pass.getPassword().length < 8) {
            return "Password must be at least 8 characters long";
        }

        return "";
    }

    public static String validateIntRange(JTextField text, int minValue, int maxValue) {
        String result = null;

        var txt = text.getText();

        try {
            int val = Integer.parseInt(txt);
            if (val < minValue || val > maxValue) {
                result = text.getToolTipText() + " should be between " + minValue + " and " + maxValue;
            }
        } catch (Exception e) {
            result = text.getToolTipText() + " should be a valid number.";
        }

        if (result == null) {
            setBorder(text, Color.gray);
        } else {
            setBorder(text, Color.red);
        }

        return result;
    }
}
