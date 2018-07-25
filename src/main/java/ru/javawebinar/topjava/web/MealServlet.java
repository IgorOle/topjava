package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.DaoMeal;
import ru.javawebinar.topjava.dao.DaoMealMemmory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.storage.DataMemmory;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MealServlet extends HttpServlet {
    private DaoMeal daoMeal = new DaoMealMemmory(new DataMemmory());

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        if (action != null && id != null) {
            if ("del".equals(action))
                daoMeal.delete(Integer.parseInt(id));
        }
        List<MealWithExceed> filteredWithExceeded = MealsUtil.getFilteredWithExceeded(daoMeal.getAll(), 4001);
        req.setAttribute("filteredWithExceeded", filteredWithExceeded);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("saveBtn") != null) {
            String id = req.getParameter("mealId");
            daoMeal.delete(Integer.parseInt(id));
            daoMeal.save(
                    new Meal(LocalDateTime.of(LocalDate.parse(req.getParameter("dateMeal")), LocalTime.parse(req.getParameter("timeMeal"))),
                            req.getParameter("descr"),
                            Integer.parseInt(req.getParameter("calories")),
                            daoMeal.getNewId())
            );
        }
        List<MealWithExceed> filteredWithExceeded = MealsUtil.getFilteredWithExceeded(daoMeal.getAll(), 4001);
        req.setAttribute("filteredWithExceeded", filteredWithExceeded);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
