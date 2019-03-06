package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.datajpa.CrudMealRepository;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

@Controller
public class UniqueMealValidator implements org.springframework.validation.Validator {
    @Autowired
    private CrudMealRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Meal.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Meal meal = ((Meal) target);
        List<Meal> meals = repository.getAll(SecurityUtil.safeGet().getId());
        if (meals != null && meals.stream().filter(m -> m.getDateTime().equals(meal.getDateTime())).findAny().isPresent()) {
            errors.rejectValue("dateTime", "notUniqueDateTime", "meal with this time is present");
        }
    }
}
