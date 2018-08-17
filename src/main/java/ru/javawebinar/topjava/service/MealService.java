package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public interface MealService {
    Collection<Meal> getAll(int userId);

    Collection<Meal> getAllFiltered(int userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime);

    void delete(int id, int userId) throws NotFoundException;

    Meal create(int userId, Meal meal);

    void update(int userId, Meal meal) throws NotFoundException;

    Meal get(int id, int userId) throws NotFoundException;

}
