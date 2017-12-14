package pl.mqs.beword.db.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {
    public static String getDateAsString(LocalDate date) {
        if(date == null)
            return "";

        return getDateAsString(date, ModelConsts.DATE_FORMAT);
    }

    public static String getDateAsString(LocalDate date, String format) {
        if(date == null)
            return "";

        return date.format(DateTimeFormatter.ofPattern(format));
    }
}
