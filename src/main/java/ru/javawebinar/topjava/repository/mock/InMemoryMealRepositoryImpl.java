package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);


    {
        save(1, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        save(1, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        save(1, new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        save(2, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        save(2, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        save(2, new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public Meal save(Integer userId, Meal meal) {
        Map<Integer, Meal> meals = repository.computeIfAbsent(userId, newMeals -> new ConcurrentHashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        meals.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(Integer id, Integer userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals == null ? false : meals.remove(id) != null;
    }

    @Override
    public Meal get(Integer id, Integer userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public Collection<MealWithExceed> getAll(Integer userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals == null ?
                Collections.emptyList()
                :
                MealsUtil.getWithExceeded(
                        meals.values().stream()
                        .sorted((o1, o2) -> (-1 * o1.getDateTime().compareTo(o2.getDateTime())))
                        .collect(Collectors.toList()), MealsUtil.DEFAULT_CALORIES_PER_DAY
                );
    }

    @Override
    public Collection<MealWithExceed> getAll(Integer userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        return getAll(userId).stream()
                .filter((m) -> DateTimeUtil.isBetween(m.getDateTime().toLocalDate(), startDate, endDate) )
                .collect(Collectors.toList());
    }

//    public Collection<MealWithExceed> getAllFiltered(Integer userId, LocalDateTime startDT, LocalDateTime endDT) {
//        return getAll(userId).stream()
//                .filter((m) -> DateTimeUtil.isBetween(m.getDateTime(), startDT, endDT))
//                .collect(Collectors.toList());
//
//    }
}

