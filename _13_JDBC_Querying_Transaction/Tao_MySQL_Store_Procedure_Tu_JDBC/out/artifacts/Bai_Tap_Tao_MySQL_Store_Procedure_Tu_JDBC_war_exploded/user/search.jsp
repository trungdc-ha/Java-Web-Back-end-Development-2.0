<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 5/19/20
  Time: 12:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Search User</title>
</head>
<body>
<form method="post">
    <label>Tim Kiem Theo Country: </label>
    <input type="text" name="search"/>
    <input type="submit"/>
</form>

<table>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Email</td>
        <td>Country</td>
    </tr>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getName()}</td>
            <td>${user.getEmail()}</td>
            <td>${user.getCountry()}</td>
            <td><a href="/users?action=edit&id=${user.getId()}">Edit</a></td>
            <td><a href="/users?action=delete&id=${user.getId()}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
