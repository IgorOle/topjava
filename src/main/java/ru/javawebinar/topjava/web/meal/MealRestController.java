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
import java.time.LocalDateTime;
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
        LocalTime ltStart = "".equals(startTime) ? LocalTime.MIN : LocalTime.parse(startTime);
        LocalTime ltEnd = "".equals(endTime) ? LocalTime.MAX : LocalTime.parse(endTime);
        LocalDateTime start = "".equals(startDate) ? DateTimeUtil.MIN_DATE : LocalDateTime.parse(startDate + " 00:00", DateTimeUtil.DATE_TIME_FORMATTER);
        LocalDateTime end = "".equals(endDate) ? DateTimeUtil.MAX_DATE : LocalDateTime.parse(endDate + " 23:59", DateTimeUtil.DATE_TIME_FORMATTER);

        return MealsUtil.getFilteredWithExceeded(
                service.getAllFiltered(SecurityUtil.authUserId(), start, end),
                SecurityUtil.authUserCaloriesPerDay(),
                meal -> DateTimeUtil.isBetween(meal.getDateTime().toLocalTime(), ltStart, ltEnd)
        );
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