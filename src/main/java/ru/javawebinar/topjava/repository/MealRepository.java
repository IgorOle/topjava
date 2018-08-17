package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public interface MealRepository {
    Meal save(Integer userId, Meal meal);

    boolean delete(int userId, int id);

    Meal get(int id, int userId);

    Collection<Meal> getAll(int userId);

    Collection<Meal> getAllFiltered(int userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime);

}
