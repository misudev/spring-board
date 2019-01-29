<%--
  Created by IntelliJ IDEA.
  User: jungmisu
  Date: 2019-01-29
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>joinform</title>
</head>
<body>
<h1>joinform</h1>

<form method="post" action="/join">
    이름 : <input type="text" name="name"><br>
    닉네임 : <input type="text" name="nickname"><br>
    이메일 : <input type="text" name="email"><br>
    암호 : <input type="text" name="passwd"><br>
    <input type="submit">
</form>

</body>
</html>
