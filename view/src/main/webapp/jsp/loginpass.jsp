<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html lang="ru">
<head>
    <title>Employees</title>
</head>
<body>
<table>
    <th>Имя</th>
    <th>Зарплата</th>
    <th>Должность</th>
    <th>Подразделения</th>
    <c:forEach var="employee" items="${requestScope.employees}">
        <tr>
            <td><c:out value="${employee.name}"/></td>
            <td><c:out value="${empty employee.salary ? 0 : employee.salary}"/></td>
            <c:set var="title" scope="page" value="${employee.title}"/>
            <td><c:out value="${empty title ? 'null' : title.name}"/></td>
            <c:set var="departments" scope="page" value="${employee.departments}"/>
            <c:forEach items="${departments}" var="department">
                <td><c:out value="${department.name} (${department.city.name})"/></td>
            </c:forEach>
        </tr>

    </c:forEach>
</table>

</body>
</html>