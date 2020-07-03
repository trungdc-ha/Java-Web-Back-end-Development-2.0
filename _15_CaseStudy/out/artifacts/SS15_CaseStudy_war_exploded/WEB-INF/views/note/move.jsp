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
        <h2 class="form-group">Note thuộc type Note: ${noteType.getName()}</h2>
    </center>
    <p>
        <c:if test='${requestScope["message"]!=null}'>
            <span class="alert alert-info" role="alert">${requestScope["message"]}</span>
        </c:if>
    </p>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th>Title</th>
            <th>Content</th>
            <th>Type Note</th>
            <th>Move</th>
        </tr>
        </thead>
        <c:forEach items='${requestScope["notes"]}' var="note">
            <form method="post">
                <tr>
                    <input type="hidden" title="idNote" name="idNote" value="${note.getId()}">
                    <td>${note.getTitle()}</td>
                    <td>${note.getContent()}</td>
                    <td><select title="type_note" name="typeNoteId">
                        <c:forEach items="${noteTypes}" var="temp">
                            <c:if test="${requestScope['note'].getNoteType().getId()==temp.getId()}">
                                <option value="${temp.getId()}" selected> ${temp.getName()}</option>
                            </c:if>
                            <c:if test="${requestScope['note'].getNoteType().getId()!=temp.getId()}">
                                <option value="${temp.getId()}"> ${temp.getName()}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    </td>
                    <td>
                        <button type="submit" class="btn btn-lg" style="background-color:transparent;">
                            <i class="fa fa-arrow-right"></i>
                        </button>
                    </td>
                </tr>
            </form>
        </c:forEach>
    </table>
    <div class="row">
        <div class="col-sm-12">
            <div class="text-center">
                <button class="btn btn-primary" type="button"
                        onclick="location.href='/departments?action=delete&id=${noteType.getId()}';">Hoàn tất
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>

