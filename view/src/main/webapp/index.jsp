<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>MainPage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>
</head>
    <body>
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}">Main</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/authentication">Sign in</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/registration">Registration</a>
        </li>
    </ul>
    <hr>
    <form>
        <div class="mb-3">
            How did you get here?
        </div>
    </form>
    <hr>
    </body>
</html>