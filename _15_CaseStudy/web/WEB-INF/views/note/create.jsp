<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 15/10/2019
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm mới Note</title>
    <style>
        fieldset.scheduler-border {
            border: 1px groove #ddd !important;
            padding: 0 1.4em 1.4em 1.4em !important;
            margin: 0 0 1.5em 0 !important;
            -webkit-box-shadow: 0px 0px 0px 0px #000;
            box-shadow: 0px 0px 0px 0px #000;
            background-color: #dddddd;
        }

        legend.scheduler-border {
            width: inherit; /* Or auto */
            padding: 0 10px; /* To give a bit of padding on the left and right */
            border-bottom: none;
        }
    </style>
    <jsp:include page="/WEB-INF/libBootrap.jsp"/>
</head>
<header>
    <jsp:include page="/WEB-INF/views/header.jsp"/>
</header>
<body>
<div class="container d-flex justify-content-center align-items-center">
    <div class="col-md-8">
        <h2 class="form-group">Thêm mới Note</h2>
        <p>
            <c:if test='${requestScope["message"]!=null}'>
                <span class="alert alert-info" role="alert">${requestScope["message"]}</span>
            </c:if>
        </p>
        <form method="post">
            <fieldset class="scheduler-border">
                <legend class="scheduler-border">Thông tin của Note</legend>
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="control-label input-label" for="inputTitle">Title :</label>
                            <input type="text" class="form-control" id="inputTitle" name="title" title="title"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="control-label input-label" for="inputContent">Content :</label>
                            <textarea type="text" class="form-control" name="content" id="inputContent" title="Content"></textarea>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="control-label input-label" for="inputTypeNote">Type note:</label>
                            <select title="typeNoteId" id="inputTypeNote" class="form-control" name="typeNoteId">
                                <c:forEach items="${noteTypes}" var="noteType">
                                    <option value="${noteType.getId()}">${noteType.getName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-3">
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary" value="Thêm mới">
                    </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <input type="reset" class="btn btn-warning" value="Reset">
                        </div>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>

</div>
</body>
</html>

