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

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    public static final int USER_ID = START_SEQ;

    @Autowired
    MealService service;

    @Test
    public void create() {
        service.delete(MEAL2.getId(), USER_ID);
        Meal actual = service.create(new Meal(MEAL2.getDateTime(), MEAL2.getDescription(), MEAL2.getCalories()), USER_ID);
        assertMatch(actual, MEAL2);
    }

    @Test
    public void update() {
        int calories = 3000;
        Meal expected = new Meal(MEAL2);
        expected.setCalories(calories);
        Meal actual = service.get(expected.getId(), USER_ID);
        actual.setCalories(calories);
        actual = service.update(actual, USER_ID);
        assertMatch(actual, expected);
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
        Meal meal = service.get(MEAL2.getId(), USER_ID + 1);
        assertMatch(meal, MEAL2);
    }

    @Test(expected = NotFoundException.class)
    public void updateForeign() {
        int calories = 3000;
        Meal expected = new Meal(MEAL2);
        expected.setCalories(calories);
        Meal actual = service.get(expected.getId(), USER_ID);
        actual.setCalories(calories);
        actual = service.update(actual, USER_ID + 1);
        assertMatch(actual, expected);
    }

    @Test(expected = NotFoundException.class)
    public void deleteForeign() {
        List<Meal> expected = Arrays.asList(MEAL4, MEAL3);
        service.delete(MEAL2.getId(), USER_ID + 1);
        assertMatch(service.getAll(USER_ID), expected);
    }

}