package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Collection<Meal> getAllFiltered(int userId, LocalDateTime start, LocalDateTime end) {
        return repository.getAllFiltered(userId, start, end);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFound(repository.delete(id, userId), "not fond mealId=" + id);
    }

    @Override
    public Meal create(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    @Override
    public void update(int userId, Meal meal) throws NotFoundException {
        checkNotFound(repository.save(userId, meal), "not fond mealId=" + meal.getId());
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        return checkNotFound(repository.get(id, userId), "not fond mealId=" + id);
    }
}