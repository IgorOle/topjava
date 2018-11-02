package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.UserTestData.assertMatch;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaMealServiceTest extends AbstractMealServiceTest {

    @Test
    public void getMealWithUser() {
        Meal meal = service.getMealWithUser(MEAL1_ID, USER_ID);
        User user = meal.getUser();
        assertMatch(meal, MEAL1);
        assertMatch(user, USER);
    }

    @Test(expected = NotFoundException.class)
    public void getMealWithUserNotFound() {
        service.getMealWithUser(MEAL1_ID, ADMIN_ID);
    }


}
