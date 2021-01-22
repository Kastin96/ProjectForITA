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
        <a class="nav-link active" aria-current="page" href="/new/">Home</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/new/mygroups">All Groups</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/new/addgroup">Add Group</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/new/logout">Logout</a>
    </li>
</ul>
<div class="mb-3">
    <ul class="list-group">
      <li class="list-group-item active" aria-current="true">Admin information:</li>
      <li class="list-group-item">Login: <%= session.getAttribute("login") %></li>
    </ul>
</div>
</body>
</html>