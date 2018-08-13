package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final LocalDate MIN_DATE = LocalDate.parse("1990-01-01 00:00", DATE_TIME_FORMATTER);
    public static final LocalDate MAX_DATE = LocalDate.parse("3000-01-01 23:59", DATE_TIME_FORMATTER);


    public static boolean isBetweenTime(LocalTime lt, LocalTime startTime, LocalTime endTime) {
//        return isBetween(lt, startTime,endTime);
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static boolean isBetweenDate(LocalDate dt, LocalDate startDate, LocalDate endDate) {
        return dt.compareTo(startDate) >= 0 && dt.compareTo(endDate) <= 0;
    }

    //???
    public static <T extends LocalDateTime> boolean isBetween(T cur, T start, T end) {
        return cur.compareTo(start) >= 0 && cur.compareTo(end) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}
