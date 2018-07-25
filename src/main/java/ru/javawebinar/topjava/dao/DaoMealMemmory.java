package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.DataMemmory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DaoMealMemmory implements DaoMeal {
    private Map<Integer, Meal> meals;
    private AtomicInteger IDs;

    public DaoMealMemmory(DataMemmory dataMemmory) {
        this.meals = dataMemmory.meals;
        this.IDs = dataMemmory.IDs;
    }

    @Override
    public void clear() {
        meals.clear();
    }

    @Override
    public void update(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    @Override
    public void save(Meal meal) {
        update(meal);
    }

    @Override
    public Meal get(int id) {
        return meals.get(id);
    }

    @Override
    public void delete(int id) {
        meals.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<Meal>(meals.values());
    }

    @Override
    public int size() {
        return meals.size();
    }

    @Override
    public int getNewId() {
        return IDs.getAndIncrement();
    }
}
