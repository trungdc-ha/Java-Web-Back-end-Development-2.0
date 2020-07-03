<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách Note</title>
    <jsp:include page="/WEB-INF/libBootrap.jsp"/>
</head>
<header>
    <jsp:include page="/WEB-INF/views/header.jsp"/>
</header>
<body>
<div class="container">
    <center>
        <h2>Danh sách Note</h2>
    </center>
    <div class="row form-group">
        <button class="btn btn-lg " style="background-color:transparent;"
                onclick="location.href='/notes?action=create';">
            <i class="fa fa-plus-square"></i> Thêm mới Note
        </button>
    </div>
    <table class="table">
        <thead class="thead-light">
        <tr class="row">
            <th class="col-md-2 ">Title</th>
            <th class="col-md-6 ">Content</th>
            <th class="col-md-2 ">Type note</th>
            <th class="col-md-2">Chức năng</th>

        </tr>
        </thead>
        <c:forEach items='${requestScope["notes"]}' var="note">
            <tr class="row">
                <td class="col-md-2 "><a href="/notes?action=viewNote&id=${note.getId()}">${note.getTitle()}</a></td>
                <td class="col-md-6 ">${note.getContent()}</td>
                <td class="col-md-2 ">${note.getNoteType().getName()}</td>
                <td class="col-md-2">
                    <button class="btn btn-lg" style="background-color:transparent;"
                            onclick="location.href='/notes?action=edit&id=${note.getId()}';">
                        <i class="fa fa-edit"></i>
                    </button>
                    <button class="btn btn-lg" style="background-color:transparent;"
                            onclick="location.href='/notes?action=delete&id=${note.getId()}';">
                        <i class="fa fa-trash"></i>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

