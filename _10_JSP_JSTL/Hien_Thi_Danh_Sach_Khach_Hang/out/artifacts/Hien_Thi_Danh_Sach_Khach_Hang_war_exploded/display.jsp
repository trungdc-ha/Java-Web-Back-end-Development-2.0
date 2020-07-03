<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 5/14/20
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="com.codegym.Customer" %>
<%@ page language="java" import="java.util.*" %>
<%
    ArrayList<Customer> customers = (ArrayList<Customer>)request.getAttribute("listCustomer");
%>
<html>
<head>
    <title>Display</title>
</head>
<body>
    <h1>Hien Thi Danh Sach Khach Hang</h1>
    <%--<table style="border: 1px solid black; width:600px; height:600px;">
        <%
            for (Customer c : customers) {
        %>
            <tr>
                <td>
                    <%=
                        c.getName()
                    %>
                </td>
                <td>
                    <%=
                        c.getBirthday()
                    %>
                </td>
                <td>
                    <%=
                        c.getAddress()
                    %>
                </td>
                <td>
                    <img src="<%=c.getImage()%>" width="20px" height="20px"/>
                </td>
            </tr>
        <%
            }
        %>
    </table>--%>

    <table>
    <c:forEach items="${listCustomer}" var="customer">
        <tr>
            <td>${customer.getName()}</td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>
