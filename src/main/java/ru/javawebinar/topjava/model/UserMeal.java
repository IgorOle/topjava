package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class UserMeal {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;
    private static Map<LocalDate, Integer> daysCalories = new HashMap<>();

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        LocalDate ld = LocalDate.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth());
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        daysCalories.put(ld, daysCalories.getOrDefault(ld, 0) + calories);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public int getCaloriesPerDay(LocalDateTime dateTime) {
        LocalDate ld = LocalDate.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth());
        return (daysCalories.get(ld) == null) ? 0 : daysCalories.get(ld);
    }
}
