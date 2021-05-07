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
                <a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/mygroups">All Groups</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/addgroup">Add Group</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
            </li>
        </ul>
        <hr>
        <form action="addgroup" method="post">
            <div class="mb-3">
                Enter group details : <input name="groupName" type="text" required placeholder="Group name">
                <input name="groupTrainer" type="text" required placeholder="Trainer's name">
                <input name="groupUser" type="text" size="35" required placeholder="Students. The separator is a space! ' '">
                <input type="submit" value="Add">
                <div>
                    <% if (request.getAttribute("goodAddGroup") != null) { %>
                        <hr>
                        <div class="alert alert-success" role="alert">
                            <%= request.getAttribute("goodAddGroup")%>
                        </div>
                    <% } %>
                </div>
                <div>
                    <% if (request.getAttribute("badAddGroup") != null) { %>
                        <hr>
                        <div class="alert alert-danger" role="alert">
                            <%= request.getAttribute("badAddGroup")%>
                        </div>
                    <% } %>
                </div>
            </div>
        </form>
        <hr>
    </body>
</html>