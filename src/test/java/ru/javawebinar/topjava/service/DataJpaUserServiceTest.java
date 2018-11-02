package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.UserTestData.*;


@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {

    @Test
    public void getUserWithMeal() throws Exception {
        User userWithMeal = service.getUserWithMeal(USER_ID);
        List<Meal> meals = userWithMeal.getMeals();
        if (meals != null)
            MealTestData.assertMatch(meals, MEALS);
        UserTestData.assertMatch(userWithMeal, USER);
    }

    @Test(expected = NotFoundException.class)
    public void getUserWithMealNotFound() {
        service.getUserWithMeal(USER_ID_WITHOUT_MEAL);
    }

}
