package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface MealRepository {
    Meal save(Integer userId, Meal meal);

    boolean delete(Integer userId, Integer id);

    Meal get(Integer id, Integer userId);

    Collection<MealWithExceed> getAll(Integer userId);
    Collection<MealWithExceed> getAll(Integer userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime);

}
