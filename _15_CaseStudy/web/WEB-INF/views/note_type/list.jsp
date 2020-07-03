<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách type Note</title>
    <jsp:include page="/WEB-INF/libBootrap.jsp"/>
</head>
<header>
    <jsp:include page="/WEB-INF/views/header.jsp"/>
</header>

<body>
<div class="container">
    <center>
        <h2>Danh sách type Note</h2>
    </center>
    <p>
        <c:if test='${requestScope["message"]!=null}'>
            <span class="alert alert-info" role="alert">${requestScope["message"]}</span>
        </c:if>
    </p>
    <div class="row form-group">
        <button class="btn btn-lg " style="background-color:transparent;"
                onclick="location.href='/typeNote?action=create';">
            <i class="fa fa-plus-square"></i> Thêm mới type Note
        </button>
    </div>
    <table class="table">
        <thead class="thead-light">
        <tr class="row">
            <th scope="col" class="col-md-3">Name type Note</th>
            <th scope="col" class="col-md-4">Description</th>
            <th scope="col" class="col-md-5">
                <span scope="col" class="col-md-3">Chỉnh sửa</span>
                <span scope="col" class="col-md-3">Xóa</span>
                <span scope="col" class="col-md-3">Upload</span>
                <span scope="col" class="col-md-3">Download</span>
            </th>


        </tr>
        </thead>
        <c:forEach items='${requestScope["noteTypes"]}' var="noteType">
            <tr class="row">
                <td class="col-md-3"><a
                        href="/typeNote?action=view&id=${noteType.getId()}">${noteType.getName()}</a></td>
                <td class="col-md-4">${noteType.getDescription()}</td>
                <td class="col-md-5">
                    <span class="col-md-3">
                        <button class="btn btn-lg" style="background-color:transparent;"
                                onclick="location.href='/typeNote?action=edit&id=${noteType.getId()}';">
                            <i class="fa fa-edit"></i>
                        </button>
                    </span>
                    <span class="col-md-3">
                        <button class="btn btn-lg" style="background-color:transparent;"
                                onclick="location.href='/typeNote?action=delete&id=${noteType.getId()}';">
                            <i class="fa fa-trash"></i>
                        </button>
                    </span>
                    <span class="col-md-3">
                        <button class="btn btn-lg" style="background-color:transparent;"
                                onclick="location.href='/typeNote?action=upload&id=${noteType.getId()}';">
                            <i class="fa fa-upload"></i>
                        </button>
                    </span>
                    <span class="col-md-3">
                        <button class="btn btn-lg" style="background-color:transparent;"
                                onclick="location.href='/typeNote?action=export&id=${noteType.getId()}';">
                            <i class="fa fa-download"></i>
                        </button>
                    </span>
                </td>
            </tr>

        </c:forEach>
    </table>
</div>

</body>
</html>

