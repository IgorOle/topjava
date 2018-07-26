package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.DaoMeal;
import ru.javawebinar.topjava.dao.DaoMealMemmory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class MealServlet extends HttpServlet {
    private DaoMeal daoMeal;
    private static final int CALORIES_PER_DAY_MAX = 2001;

    @Override
    public void init() throws ServletException {
        super.init();
        this.daoMeal = new DaoMealMemmory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        if (action != null && id != null && "del".equals(action)) {
            daoMeal.delete(Integer.parseInt(id));
            resp.sendRedirect("meals");
            return;
        }
        List<MealWithExceed> filteredWithExceeded = MealsUtil.getFilteredWithExceeded(daoMeal.getAll(), CALORIES_PER_DAY_MAX);
        req.setAttribute("filteredWithExceeded", filteredWithExceeded);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        Meal meal;
        if (req.getParameter("newBtn") != null) {
            meal = new Meal();
            fillMeal(meal, req);
            daoMeal.save(meal);
        }
        if (req.getParameter("saveBtn") != null) {
            meal = daoMeal.get(Integer.parseInt(req.getParameter("mealId")));
            fillMeal(meal, req);
            daoMeal.update(meal);
        }
        List<MealWithExceed> filteredWithExceeded = MealsUtil.getFilteredWithExceeded(daoMeal.getAll(), CALORIES_PER_DAY_MAX);
        req.setAttribute("filteredWithExceeded", filteredWithExceeded);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

    private void fillMeal(Meal meal, HttpServletRequest req) {
        meal.setCalories(Integer.parseInt(req.getParameter("calories")));
        meal.setDateTime(LocalDateTime.parse(req.getParameter("dateTimeMeal")));
        meal.setDescription(req.getParameter("descr"));
    }
}
