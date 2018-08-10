package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

public class MealRestController {

    @Autowired
    private MealService service;

    public Collection<Meal> getAll() {
        return service.getAll();
    }
}