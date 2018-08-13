package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public Collection<MealWithExceed> getAll() {
        return service.getAll(SecurityUtil.authUserId());
    }

    public Collection<MealWithExceed> getAll(String startDate, String endDate, String startTime, String endTime) {
        return service.getAll(SecurityUtil.authUserId(),
                "".equals(startDate) ? DateTimeUtil.MIN_DATE : LocalDate.parse(startDate),
                "".equals(endDate) ? DateTimeUtil.MAX_DATE : LocalDate.parse(endDate),
                "".equals(startTime) ? LocalTime.MIN : LocalTime.parse(startTime),
                "".equals(endTime) ? LocalTime.MAX : LocalTime.parse(endTime)
        );
    }

    public void delete(int id) {
        service.delete(id, SecurityUtil.authUserId());
    }

    public Meal get(Integer id) {
        return service.get(id, SecurityUtil.authUserId());
    }

    public void save(Meal meal) {
        service.save(SecurityUtil.authUserId(), meal);
    }

}