<%--
  Created by IntelliJ IDEA.
  User: jungmisu
  Date: 2019-01-28
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>user-list</title>
</head>
<body>
<h1>user-list</h1>
<img src="/duck.png">
<br><br data-tomark-pass>

<c:forEach items="${users}" var="user">
    name : ${user.name}, email : ${user.email}<br data-tomark-pass>
</c:forEach>

<button type="button" class = "btn btn-default" id = "btn-list" onclick="location.href='/joinform'">회원가입</button>
</body>
</html>