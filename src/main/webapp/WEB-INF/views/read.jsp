<%--
  Created by IntelliJ IDEA.
  User: jungmisu
  Date: 2019-01-09
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <link href="/read.css" type="text/css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link href="bs/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div id = "container">
    <div id="view-top">
        <div id = "view-title">${board.title}</div>
    </div>
    <div id = "second-line">
        <div id = "view-date" class="top">${board.regdate}</div>
        <div id = "view-writer" class="top">${board.nickname}</div>
    </div>
    <div id = "content">
        ${board.content}
    </div>
    <div id = "view-bottom">

        <c:if test="${sessionScope.logininfo.id == board.userId}">
            <button type="button" class = "btn btn-default" id = "btn-update" onclick="location.href='/update?id=${board.id}'">수정</button>
            <button type="button" class = "btn btn-default" id = "btn-delete" onclick="location.href='/delete?id=${board.id}'">삭제</button>
        </c:if>
        <button type="button" class = "btn btn-default" id = "btn-list" onclick="location.href='/board'">목록</button>
        <button type="button" class = "btn btn-default" id = "btn-reply" onclick="location.href='/reply?id=${board.id}'">답글</button>
    </div>
</div>

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
