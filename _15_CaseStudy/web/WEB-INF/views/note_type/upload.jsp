<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload file</title>
    <jsp:include page="/WEB-INF/libBootrap.jsp"/>
</head>
<header>
    <jsp:include page="/WEB-INF/views/header.jsp"/>
</header>

<body>
<div class="container">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <form method="post" enctype="multipart/form-data">
            <input style="margin-top: 5%" type="file" id="files" title="files" multiple="multiple" name="file"/>
            <p style="text-align: right; margin-top: 20px;">
                <input type="submit" value="Upload Files" class="btn btn-lg btn-primary" />
            </p>
        </form>
    </div>
    <div class="col-md-4"></div>
</div>

</body>
</html>
