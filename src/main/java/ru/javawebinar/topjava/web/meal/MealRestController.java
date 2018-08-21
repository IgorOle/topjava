package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.MealsUtil.getWithExceeded;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Collection<MealWithExceed> getAll() {
        return MealsUtil.getWithExceeded(service.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay());
    }

    public Collection<MealWithExceed> getAllFiltered(String startDate, String endDate, String startTime, String endTime) {
        Collection<MealWithExceed> meals = MealsUtil.getWithExceeded(service.getAllFiltered(SecurityUtil.authUserId(),
                "".equals(startDate) ? DateTimeUtil.MIN_DATE : LocalDate.parse(startDate),
                "".equals(endDate) ? DateTimeUtil.MAX_DATE : LocalDate.parse(endDate),
                LocalTime.MIN,
                LocalTime.MAX),
                SecurityUtil.authUserCaloriesPerDay()
        );
        LocalTime ltStart = "".equals(startTime) ? LocalTime.MIN : LocalTime.parse(startTime);
        LocalTime ltEnd = "".equals(startTime) ? LocalTime.MAX : LocalTime.parse(endTime);
        return meals.stream()
                .filter(mealWithExceed ->
                        DateTimeUtil.isBetween(mealWithExceed.getDateTime().toLocalTime(), ltStart, ltEnd))
                .collect(Collectors.toList());
    }

    public void delete(int id) {
        service.delete(id, SecurityUtil.authUserId());
    }

    public Meal get(int id) {
        return service.get(id, SecurityUtil.authUserId());
    }

    public void update(int id, Meal meal) {
        assureIdConsistent(meal, id);
        service.update(SecurityUtil.authUserId(), meal);
    }

    public Meal create(Meal meal) {
        checkNew(meal);
        return service.create(SecurityUtil.authUserId(), meal);
    }

}