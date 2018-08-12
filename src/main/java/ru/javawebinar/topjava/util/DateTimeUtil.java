package ru.javawebinar.topjava.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetweenTime(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return isBetween(lt, startTime,endTime);
//        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static boolean isBetweenDate(LocalDate dt, LocalDate startDate, LocalDate endDate) {
        return dt.compareTo(startDate) >= 0 && dt.compareTo(endDate) <= 0;
    }
    public static <T extends LocalDateTime> boolean isBetween(T cur, T start,T end){
        return cur.compareTo(start) >= 0 && cur.compareTo(end) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}
