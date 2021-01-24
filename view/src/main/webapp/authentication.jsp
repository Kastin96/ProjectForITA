<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>Authentication</title>
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
                <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}">SignIn</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/reg">Registration</a>
            </li>
        </ul>
        <hr>
        <form action="authentication" method="post">
            <div class="mb-3">
                Enter your login and password for Authorization :
                <input name="login" type="text" placeholder="Login" autocomplete="on">
                <input type="password" name="password" placeholder="Password" autocomplete="on">
                <input type="submit" value="Sign in">
                <div>
                    <% if (request.getAttribute("badAuthentication") != null) { %>
                        <hr>
                        <div class="alert alert-danger" role="alert">
                            <%= request.getAttribute("badAuthentication")%>
                        </div>
                    <% } %>
                </div>
                <div>
                    <% if (request.getAttribute("goodRegistration") != null) { %>
                        <hr>
                        <div class="alert alert-success" role="alert">
                            <%= request.getAttribute("goodRegistration")%>
                        </div>
                    <% } %>
                </div>
            </div>
        </form>
        <hr>
    </body>
</html>