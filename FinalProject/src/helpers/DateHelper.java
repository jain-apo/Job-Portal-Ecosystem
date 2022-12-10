package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    public static String formatDate(Date date, String format) {
        SimpleDateFormat f = new SimpleDateFormat(format);
        return f.format(date);
    }

    public static Date tryGetDate(String dateString, String formatD) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatD);
            Date date = format.parse(dateString);
            return date;
        } catch (Exception e) {
            return null;
        }
    }
}
