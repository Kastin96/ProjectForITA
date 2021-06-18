<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>HomePage</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
                crossorigin="anonymous"></script>
    </head>
    <body>
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/mygroups">My Groups</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/salarylist">Salary</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
            </li>
        </ul>
        <div class="mb-3">
            <form action="salarylist" method="post">
                <ul class="list-group">
                    <li class="list-group-item active" aria-current="true">Add salary:</li>
                    <li class="list-group-item">
                        <input name="addSalary" type="number" min="0" required placeholder="Salary">
                        <input type="submit" value="Add">
                    </li>
                </ul>
            </form>
        </div>
        <div class="mb-3">
            <ul class="list-group">
                <li class="list-group-item active" aria-current="true">Your average salary:</li>
                <li class="list-group-item">
                    <c:out value="${averageSalary}" />
                </li>
            </ul>
        </div>
        <div class="mb-3">
            <ul class="list-group">
                <li class="list-group-item active" aria-current="true">Your salary list:</li>
                <c:forEach var="salary" items="${salaryList}">
                    <li class="list-group-item"><c:out value="${salary}" /></li>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>