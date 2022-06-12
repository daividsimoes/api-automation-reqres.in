package br.com.restassured.apiautomation.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String getCurrentDateToString() {

        return LocalDate.now().toString();
    }

    public static String getDateToString(LocalDateTime localDateTime) {

        return formatter.format(localDateTime);
    }

    public static long convertMilliSecondsToSeconds(long millis) {

        return TimeUnit.MILLISECONDS.toSeconds(millis);
    }
}
