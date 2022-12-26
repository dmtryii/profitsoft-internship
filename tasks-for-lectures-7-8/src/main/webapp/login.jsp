<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
</head>
<body>
    <div class="wrapper">
        <h1>Login Page</h1>
        <form action="<c:url value="/login"/>" method="post">
            <table>
                <tr>
                    <td>
                        <label for="login" class="sr-only">Login</label>
                    </td>
                    <td>
                        <input type="text" id="login" name="login" required autofocus>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="password" class="sr-only">Password</label>
                    </td>
                    <td>
                        <input type="password" id="password" name="password" required autofocus>
                    </td>
                </tr>
            </table>
            <p><strong>${credentials_error}</strong></p>
            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>
