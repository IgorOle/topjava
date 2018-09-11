package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    MealService service;

    @Test
    public void create() {
        Meal meal = new Meal(LocalDateTime.of(2018, 9, 9, 9, 14, 0), "завтрак0", 500);
        service.create(meal, USER_ID);
        assertMatch(service.getAll(USER_ID), Arrays.asList(meal, MEAL4, MEAL3, MEAL2));
    }

    @Test
    public void update() {
        int mealId = 100003;
        Meal expectedMeal = new Meal(mealId, LocalDateTime.of(2011, 1, 1, 9, 14, 0), "завтрак1", 111);
        service.update(expectedMeal , USER_ID);
        assertMatch(service.get(mealId, USER_ID), expectedMeal);
    }

    @Test
    public void get() {
        Meal actual = service.get(MEAL2.getId(), USER_ID);
        assertMatch(actual, MEAL2);
    }

    @Test
    public void delete() {
        List<Meal> expected = Arrays.asList(MEAL4, MEAL3);
        service.delete(MEAL2.getId(), USER_ID);
        assertMatch(expected, service.getAll(USER_ID));
    }

    @Test
    public void getBetweenDates() {
        List<Meal> expected = Arrays.asList(MEAL4, MEAL3, MEAL2);
        List<Meal> actual = service.getBetweenDates(
                LocalDate.of(2018, 9, 1),
                LocalDate.of(2018, 9, 20),
                USER_ID);
        assertMatch(actual, expected);
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> expected = Arrays.asList(MEAL2);
        List<Meal> actual = service.getBetweenDateTimes(
                LocalDateTime.of(2018, 9, 4, 1, 1, 1),
                LocalDateTime.of(2018, 9, 4, 10, 59, 1),
                USER_ID);
        assertMatch(actual, expected);
    }

    @Test
    public void getAll() {
        List<Meal> expected = Arrays.asList(MEAL4, MEAL3, MEAL2);
        assertMatch(service.getAll(USER_ID), expected);
    }

    @Test(expected = NotFoundException.class)
    public void getForeign() {
        service.get(MEAL2.getId(), USER_ID + 1);
    }

    @Test(expected = NotFoundException.class)
    public void updateForeign() {
        Meal meal = new Meal(MEAL3.getId(), LocalDateTime.of(2011, 1, 1, 9, 14, 0), "завтрак1", 111);
        service.update(meal, USER_ID + 1);
    }

    @Test(expected = NotFoundException.class)
    public void deleteForeign() {
        service.delete(MEAL2.getId(), USER_ID + 1);
    }
}