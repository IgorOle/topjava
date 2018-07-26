<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="fragments/header.jsp"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table border="0">
    <c:forEach var="meal" items="${filteredWithExceeded}">
        <c:set var="className" value="${meal.exceed == true ? 'overLimit':'limitOk'}"></c:set>
        <tr class="${className}">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealWithExceed"/>
            <form name="formElement" action="meals?action=upd&id=${meal.id}" method="post"
                  enctype="application/x-www-form-urlencoded">
                <input type="hidden" name="mealId" value="${meal.id}">
                <td><input type="datetime-local" name="dateTimeMeal" value="${meal.dateTime}"></td>
                <td><input type="text" name="descr" value="${meal.description}"></td>
                <td><input type="text" name="calories" value="${meal.calories}"></td>
                <td><input name="saveBtn" type="submit" value="сохранить"></td>
                <td><a href="meals?action=del&id=${meal.id}">удалить</a></td>
            </form>
        </tr>
    </c:forEach>
    <form name="formElement" action="meals?action=new" method="post"
          enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="mealId">
        <td><input type="datetime-local" name="dateTimeMeal"></td>
        <td><input type="text" name="descr"></td>
        <td><input type="text" name="calories"></td>
        <td><input name="newBtn" type="submit" value="добавить"></td>
        <td>&nbsp;</td>
    </form>
</table>

</body>
</html>
