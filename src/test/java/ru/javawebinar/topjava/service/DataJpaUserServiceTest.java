package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.UserTestData.assertMatch;


@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {

    @Transactional
    @Test
    public void getUserWithMeal() throws Exception {
        User userWithMeal = service.getUserWithMeal(USER_ID);
        List<Meal> meals = userWithMeal.getMeals();
        if (meals != null)
            assertMatch(meals, MEALS);
        assertMatch(userWithMeal, USER);
    }
}
