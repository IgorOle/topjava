package ru.javawebinar.topjava;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.util.Arrays;
import java.util.Collection;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
//            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            Collection<MealWithExceed> all = mealRestController.getAll();
            System.out.println("START");
            all.stream().forEach(System.out::println);
        }
    }
}
