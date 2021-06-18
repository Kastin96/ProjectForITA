<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>


</head>
<body>
<hr>
<ul class="nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}">SignIn</a>
    </li>
    <li class="nav-item">
        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/reg">Registration</a>
    </li>
</ul>
<hr>
<form action="registration" method="post">
    <div class="mb-3">
        Enter user details :
        <input name="login" type="text" required placeholder="Login">
        <input name="password" type="password" required placeholder="Password">
        <input name="name" type="text" required placeholder="Name">
        <input name="age" type="number" min="0" required placeholder="Age">
        <input type="submit">
        <div>
            <c:if test="${not empty badRegistration}">
                <hr>
                <div class="alert alert-danger" role="alert">
                    <c:out value="${badRegistration}"/>
                </div>
            </c:if>
        </div>
    </div>

</form>
<hr>
</body>
</html>