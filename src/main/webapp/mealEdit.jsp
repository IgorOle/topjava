<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit form</title>
</head>
<body>
<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
<form name="formElement" action="meals?action=new" method="post"
      enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="mealId" value="${meal.id}">
    <table>
        <tr><th>Дата</th><th>Описание</th><th>Каллории</th></tr>
        <td><input type="datetime-local" name="dateTimeMeal" value="${meal.dateTime}"></td>
        <td><input type="text" name="descr" value="${meal.description}"></td>
        <td><input type="text" name="calories" value="${meal.calories}"></td>
    </table>
    <input name="saveBtn" type="submit" value="Сохранить">
</form>
</body>
</html>
