package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DataMemmory {
    public final static Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    public final static AtomicInteger IDs = new AtomicInteger(1);

    public DataMemmory() {
        init();
    }
    private void init(){
        int id = IDs.getAndIncrement();
        meals.put(id, new Meal(LocalDateTime.of(2018, Month.JUNE, 21, 10, 0), "Завтрак", 500, id));
        id = IDs.getAndIncrement();
        meals.put(id, new Meal(LocalDateTime.of(2018, Month.JUNE, 21, 14, 0), "Обед", 1500, id));
        id = IDs.getAndIncrement();
        meals.put(id, new Meal(LocalDateTime.of(2018, Month.JUNE, 21, 19, 0), "ужин", 2000, id));

        id = IDs.getAndIncrement();
        meals.put(id, new Meal(LocalDateTime.of(2018, Month.JUNE, 22, 11, 0), "Завтрак", 500, id));
        id = IDs.getAndIncrement();
        meals.put(id, new Meal(LocalDateTime.of(2018, Month.JUNE, 22, 14, 0), "Обед", 1500, id));
        id = IDs.getAndIncrement();
        meals.put(id, new Meal(LocalDateTime.of(2018, Month.JUNE, 22, 20, 0), "Ужин", 5000, id));

        id = IDs.getAndIncrement();
        meals.put(id, new Meal(LocalDateTime.of(2018, Month.JUNE, 23, 11, 0), "Завтрак", 500, id));
        id = IDs.getAndIncrement();
        meals.put(id, new Meal(LocalDateTime.of(2018, Month.JUNE, 23, 13, 0), "Обед", 1800, id));
        id = IDs.getAndIncrement();
        meals.put(id, new Meal(LocalDateTime.of(2018, Month.JUNE, 23, 21, 0), "ужин", 2500, id));
    }
}
