package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
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
    public Collection<MealWithExceed> getAll(Integer userId) throws NotFoundException {
        return checkNotFound(repository.getAll(userId), "not found meals");
    }

    @Override
    public Collection<MealWithExceed> getAll(Integer userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) throws NotFoundException {
        return repository.getAll(userId, startDate, endDate, startTime, endTime);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFound(repository.delete(id, userId), "not fond meal");
    }

    @Override
    public void save(Integer userId, Meal meal) throws NotFoundException {
        checkNotFound(repository.save(userId, meal), "not fond meal");
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        return checkNotFound(repository.get(id, userId), "not found meal");
    }
}