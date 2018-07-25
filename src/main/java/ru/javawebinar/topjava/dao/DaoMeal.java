package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface DaoMeal {
    void clear();

    void update(Meal meal);

    void save(Meal meal);

    Meal get(int id);

    void delete(int id);

    List<Meal> getAll();

    int size();

    int getNewId();

}
