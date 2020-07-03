<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 5/14/20
  Time: 9:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="com.codegym.Customer" %>
<%@ page language="java" import="java.util.*" %>
<%

%>
<html>
  <head>
    <title>Hien Thi Danh Sach Khach Hang</title>
  </head>
  <body>
  <div id="page">
    <table>
        <tr>
          <td><img src="images/home_log.png" width="100" height="100"/></td>
        </tr>
    </table>
    <form method="get" action="/display">
      <label>Name:</label>
      <input type="text" name="name"/>
      <label>Ngay Sinh:</label>
      <input type="text" name="birthday"/>
      <label>Dia Chi:</label>
      <input type="text" name="address"/>
      <label>Image:</label>
      <input type="text" name="image"/><br/>
      <button>Add</button>
      <input type="submit" value="Submit"/>
    </form>
  </div>
  </body>
</html>
