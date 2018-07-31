package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DaoMealMemmory implements DaoMeal {

    public final Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    public final AtomicInteger IDs = new AtomicInteger(1);

    public DaoMealMemmory() {
        init();
    }

    private void init() {
        int id = getNewId();
        meals.put(id, new Meal(id, LocalDateTime.of(2018, Month.JUNE, 21, 10, 0), "Завтрак", 500));
        id = getNewId();
        meals.put(id, new Meal(id, LocalDateTime.of(2018, Month.JUNE, 21, 13, 0), "Обед", 1500));
        id = getNewId();
        meals.put(id, new Meal(id, LocalDateTime.of(2018, Month.JUNE, 21, 18, 0), "ужин", 2500));
        id = getNewId();
        meals.put(id, new Meal(id, LocalDateTime.of(2018, Month.JUNE, 22, 10, 0), "Завтрак", 500));
        id = getNewId();
        meals.put(id, new Meal(id, LocalDateTime.of(2018, Month.JUNE, 22, 12, 0), "Обед", 100));
        id = getNewId();
        meals.put(id, new Meal(id, LocalDateTime.of(2018, Month.JUNE, 22, 19, 0), "ужин", 1500));
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.getId() == null) {
            meal.setId(getNewId());
        }
        return meals.put(meal.getId(), meal);
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
        return new ArrayList<>(meals.values());
    }

    private int getNewId() {
        return IDs.getAndIncrement();
    }
}
