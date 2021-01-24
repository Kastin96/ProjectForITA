<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <a class="nav-link" href="${pageContext.request.contextPath}">Home</a>
            </li>
            <% if (session.getAttribute("isAdmin") != null) { %>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/mygroups">All Groups</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/addgroup">Add Group</a>
                </li>
            <% } else { %>
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/mygroups">My Groups</a>
            </li>
            <% } %>
            <% if (session.getAttribute("isTrainer") != null) { %>
                <li class="nav-item">
                    <a class="nav-link"href="${pageContext.request.contextPath}/salarylist">Salary</a>
                </li>
            <% } %>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
            </li>
        </ul>
        <div class="mb-3">
            <form action="showgroupspage" method="post">
                <ul class="list-group">
                    <li class="list-group-item active" aria-current="true">Show group details: </li>
                    <li class="list-group-item">
                        <input type="text" name="showGroupName" required placeholder="Group name">
                        <input type="submit" value="show">
                    </li>
                    <% if (request.getAttribute("showGroupName") != null) { %>
                        <li class="list-group-item">Group Name: <c:out value="${showGroupName}"/></li>
                        <li class="list-group-item">Trainer: <c:out value="${showGroupTrainerName}"/></li>
                        <c:forEach var="UserListName" items="${showGroupUserListName}">
                            <li class="list-group-item">Student: <c:out value="${UserListName}"/></li>
                        </c:forEach>
                    <% } %>
                </ul>
                <% if (request.getAttribute("notFoundGroupToShow") != null) { %>
                    <hr>
                    <div class="alert alert-danger" role="alert">
                        <%= request.getAttribute("notFoundGroupToShow")%>
                    </div>
                <% } %>
            </form>
        </div>
        <div class="mb-3">
            <form action="mygroupspage" method="post">
                <ul class="list-group">
                    <li class="list-group-item active" aria-current="true">My Groups: </li>
                    <li class="list-group-item"><input type="submit" value="show"></li>
                    <c:forEach var="valueNameGroup" items="${myGroupNamesListResult}">
                        <li class="list-group-item">Group: <c:out value="${valueNameGroup}"/></li>
                    </c:forEach>
                </ul>
                <% if (request.getAttribute("notFoundGroup") != null) { %>
                    <hr>
                    <div class="alert alert-danger" role="alert">
                        <%= request.getAttribute("notFoundGroup")%>
                    </div>
                <% } %>
            </form>
        </div>
        <hr>
    </body>
</html>