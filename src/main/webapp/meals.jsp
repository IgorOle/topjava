<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="fragments/header.jsp"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table border="0">
    <c:forEach var="meal" items="${filteredWithExceeded}">
        <c:set var="className" value="limitOk"></c:set>
        <c:if test="${meal.exceed == true}">
            <c:set var="className" value="overLimit"></c:set>
        </c:if>
        <tr class="${className}">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealWithExceed"/>
            <form name="formElement" action="meals?action=upd&id=${meal.id}" method="post" enctype="application/x-www-form-urlencoded">
                <input type="hidden" name="mealId" value="${meal.id}">
                <td><input type="date" name="dateMeal" value="${meal.dateTime.toLocalDate()}"></td>
                <td><input type="time" name="timeMeal" value="${meal.dateTime.toLocalTime()}"></td>
                <td><input type="text" name="descr" value="${meal.description}"></td>
                <td><input type="text" name="calories" value="${meal.calories}"></td>
                <td><input name="saveBtn" type="submit" value="сохранить"></td>
                <td><a href="meals?action=del&id=${meal.id}">удалить</a></td>
            </form>
        </tr>
    </c:forEach>
</table>

</body>
</html>
