<%--
  Created by IntelliJ IDEA.
  User: jungmisu
  Date: 2019-01-29
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>


<body>

<h2 style="text-align:center; margin-top:20px;"><a href="/board" style="color:#2E2E2E;"> 게시판 </a></h2>
<div style="width:90%; padding-top:10px;">

    <c:choose>
        <c:when test="${sessionScope.logininfo != null}">
            <div style="margin-left: 15% "><b>${sessionScope.logininfo.nickname}</b>님 환영합니다.</div>
            <a class="btn btn-default" href="/writeform"  style='width:10%; float:right;'>글쓰기</a>
            <a class="btn btn-default" href="/logout" style='width:10%; float:right;'>로그아웃</a>
        </c:when>
        <c:when test="${sessionScope.logininfo == null}">
            <a class="btn btn-default" href="/joinform" style='width:10%; float:right;'>회원가입</a>
            <a class="btn btn-default" href="/loginform" style='width:10%; float:right;'>로그인</a></c:when>
        <c:otherwise>위조건이외의 실행</c:otherwise>
    </c:choose>

</div>
<table class = "table table-bordered table-hover" style='width:80%; margin: 50px auto;'>
    <thead style="background-color:#ebebeb;">
    <th width="10%">글번호</th>
    <th width="45%">제목</th>
    <th width="15%">글쓴이</th>
    <th width="15%">날짜</th>
    <th width="15%"> 조회수</th>
    </thead>
    <tbody>
    <c:set var="start" value="${count - start}" />
    <c:forEach items="${boards}" var="board">
        <tr>
            <td>${start}</td>
            <td><a href="/read?id=${board.id}">${board.title}</a></td>
            <td>${board.nickname}</td>
            <td>${board.regdate}</td>
            <td>${board.readCount}</td>
        </tr>
        <c:set var="start" value="${start - 1}" />
    </c:forEach>
    </tbody>
</table>

<div class="text-center" style="width:50%; margin:0px auto; margin-top:10px;">
    <ul class = "pagination">
        <li>
            <c:if test="${param.page != 1}">
                <a href="?page=${param.page-1}">&laquo;</a>
            </c:if>
            <c:if test="${param.page == 1}">
                <a href="?page=${param.page}">&laquo;</a>
            </c:if>
        </li>

        <c:forEach var="pageNo" begin="${pagestart}" end="${pageend}">
            <c:choose>
                <c:when test="${param.type != null}">
                    <li><a href="?page=${pageNo}&type=${param.type}&keyword=${param.keyword}">${pageNo}</a></li>
                </c:when>
                <c:otherwise><li><a href="?page=${pageNo}">${pageNo}</a></li></c:otherwise>
            </c:choose>
        </c:forEach>

        <li>
            <c:if test="${param.page != 10}">
                <a href="?page=${param.page+1}">&raquo;</a>
            </c:if>
            <c:if test="${param.page == 10}">
                <a href="?page=${param.page}">&laquo;</a>
            </c:if>
        </li>
    </ul>
</div>

<form id="searchForm" action="/search" method="post">
    <!-- <div class="box2" style="width:40%; margin:3% auto 10%; margin-bottom: 10%">-->
    <div style="width:40%; margin: 0px auto">
        <div class="form-group" style="width:17%; margin-right:2%; float:left;">
            <label class="sr-only" for="sop">sop</label>
            <select nickName=sop class="form-control" name="type">
                <option value="title">제목</option>
                <option value="content">내용</option>
                <option value=작성자>작성자</option>
            </select>
        </div>
        <div class="form-group" style="width:60%; float:left;">
            <label class="sr-only" for="stx">stx</label>
            <input type = "text" maxlength=15 size=10 class="form-control" name="keyword">
        </div>
        <div class="form-group" >
            <button type = "submit" class="btn btn-default" style="float: right; width:17%;">검색</button>
        </div>
    </div>
    <!-- </div>-->
</form>

</body>
</html>