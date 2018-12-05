package ru.javawebinar.topjava.util;


import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

final class StringToLocalDT {

    public static class StringToLocalDate implements Converter<String, LocalDate> {
        @Override
        public LocalDate convert(String s) {
            return (s == null ? LocalDate.MAX : LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }

    public static class StringToLocalTime implements Converter<String, LocalTime> {
        @Override
        public LocalTime convert(String s) {
            return (s == null ? LocalTime.MAX : LocalTime.parse(s, DateTimeFormatter.ISO_LOCAL_TIME));
        }
    }

    public static class StringToLocalDateTime implements Converter<String, LocalDateTime> {
        @Override
        public LocalDateTime convert(String s) {
            return (s == null ? LocalDateTime.MAX : LocalDateTime.parse(s, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }

    }

}
