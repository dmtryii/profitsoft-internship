<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Info</title>
</head>
<body>
    <div class="wrapper">
        <h1>Info Page</h1>
        <table>
            <thead>
                <tr>
                    <th scope="col">Username</th>
                    <th scope="col">Email</th>
                </tr>
            </thead>
            <c:forEach var="user" items="${userInfo}">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                </tr>
            </c:forEach>
        </table>
        <a href="<c:url value="/home"/>">Home Page</a>
        <a href="<c:url value="/logout"/>">Logout</a>
    </div>
</body>
</html>