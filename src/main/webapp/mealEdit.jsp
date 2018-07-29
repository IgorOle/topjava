<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit form</title>
</head>
<body>
<form name="formElement" action="meals?action=new" method="post"
      enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="mealId" value="${mealId}">

    <table>
        <tr><th>Дата</th><th>Описание</th><th>Каллории</th></tr>
        <td><input type="datetime-local" name="dateTimeMeal" value="${dateTimeMeal}"></td>
        <td><input type="text" name="descr" value="${descr}"></td>
        <td><input type="text" name="calories" value="${calories}"></td>
    </table>
    <input name="saveBtn" type="submit" value="Сохранить">
</form>
</body>
</html>
