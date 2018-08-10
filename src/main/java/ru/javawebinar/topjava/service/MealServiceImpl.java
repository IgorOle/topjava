package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;

public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Override
    public Collection<Meal> getAll() {
        return repository.getAll();
    }
}