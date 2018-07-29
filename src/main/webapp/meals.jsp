<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="fragments/header.jsp"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

<a href="mealsEdit?action=add">Добавить</a>
<table border="0">
    <c:forEach var="meal" items="${filteredWithExceeded}">
        <c:set var="className" value="${meal.exceed == true ? 'overLimit':'limitOk'}"></c:set>
        <tr class="${className}">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealWithExceed"/>
            <form name="formElement" action="meals?action=upd&id=${meal.id}" method="post"
                  enctype="application/x-www-form-urlencoded">
                <td>${TimeUtil.toString(meal.dateTime)}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="mealsEdit?action=edit&id=${meal.id}">изменить</a></td>
                <td><a href="meals?action=del&id=${meal.id}">удалить</a></td>
            </form>
        </tr>
    </c:forEach>

</table>

</body>
</html>
