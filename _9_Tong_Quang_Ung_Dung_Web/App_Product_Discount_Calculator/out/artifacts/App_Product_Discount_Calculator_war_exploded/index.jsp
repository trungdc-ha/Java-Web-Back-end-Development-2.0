<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 5/13/20
  Time: 4:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Product Discount Calculator</title>
  </head>
  <body>
    <div id="page">
      <form method="get" action="/discount">
          <label>Product Description:</label>
          <input type="text" name="des"/><br/>
          <label>List Price: </label>
          <input type="text" name="price"/><br/>
          <label>Discount Percent: </label>
          <input type="text" name="discount"/>(%)
          <input type="submit"/>
      </form>
    </div>
  </body>
</html>
