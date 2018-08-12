package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public interface MealService {
    Collection<MealWithExceed> getAll(Integer userId);

    Collection<MealWithExceed> getAll(Integer userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime);

    void delete(int id, int userId) throws NotFoundException;

    void save(Integer userId, Meal meal) throws NotFoundException;

    Meal get(int id, int userId) throws NotFoundException;

}
