<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home</title>
</head>
<body>

    <div class="wrapper">
        <h1 class="header-log">Home Page</h1>
        <a href="<c:url value="/userInfo"/>">Info</a>
        <a href="<c:url value="/logout"/>">Logout</a>
    </div>

</body>
</html>