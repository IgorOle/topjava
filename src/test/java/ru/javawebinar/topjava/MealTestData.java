package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


public class MealTestData {

    public static final Meal MEAL2 = new Meal(100002, LocalDateTime.of(2018, 9, 4, 9, 14, 0), "завтрак", 500);
    public static final Meal MEAL3 = new Meal(100003, LocalDateTime.of(2018, 9, 4, 12, 15, 0), "обед", 1000);
    public static final Meal MEAL4 = new Meal(100004, LocalDateTime.of(2018, 9, 4, 18, 15, 0), "ужин", 1500);
    public static final Meal MEAL5 = new Meal(100005, LocalDateTime.of(2018, 9, 5, 8, 15, 0), "завтрак1", 400);
    public static final Meal MEAL6 = new Meal(100006, LocalDateTime.of(2018, 9, 5, 12, 16, 0), "обед1", 800);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

}
