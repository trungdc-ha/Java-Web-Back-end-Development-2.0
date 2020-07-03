<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 5/18/20
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<a href="/users?">List All Users</a>
<form method="post">
    <table border="1" cellpadding="5">
        <caption>
            <h2>Edit User</h2>
        </caption>
        <tr>
            <th>User Name:</th>
            <td>
                <input type="text" name="name" id="name" size="45" value="${existingUser.getName()}"/>
            </td>
        </tr>
        <tr>
            <th>User Email:</th>
            <td>
                <input type="text" name="email" id="email" size="45" value="${existingUser.getEmail()}"/>
            </td>
        </tr>
        <tr>
            <th>Country:</th>
            <td>
                <input type="text" name="country" id="country" size="15" value="${existingUser.getCountry()}"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Update"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
