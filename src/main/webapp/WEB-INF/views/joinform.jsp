<%--
  Created by IntelliJ IDEA.
  User: jungmisu
  Date: 2019-01-29
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="ko">

<head>

    <meta charset="UTF-8">

    <title>회원가입</title>

    <link rel="stylesheet" type="text/css" href="/join.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="js/brand.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700&amp;subset=korean" rel="stylesheet">


    <style>



    </style>


</head>

<body>


<!-- ---------------------form------------------------------->
<div class="info">
    <h2>< 회원 정보 입력 ></h2>

    <div class="bord"></div>

    <form action="/join" method="post">

        <fieldset id="sec">
            <legend>정보</legend>
            <ul>
                <li><label class="userId" for="nickName">이름</label>
                    <input type="text" id="name" name = "name" placeholder="한글,영문 / 특수문자 불가" autofocus required>
                </li>
                <li><label class="userId" for="nickName">닉네임</label>
                    <input type="text" id="nickname" name = "nickname" placeholder="한글,영문 / 중복X" autofocus required>

                </li>

                <li><label for="user-pw" class="labelStyle">비밀번호</label>
                    <input type="password" name = "passwd" id="user-pw" placeholder="영문 / 숫자 8~12자입력해주세요."  maxLength="12" required />
                </li>

                <li><label for="user-pw_" class="labelStyle">비밀번호 확인</label>
                    <input type="password" id="user-pw_" name="passwd_" placeholder="영문 / 숫자 8~12자입력해주세요."  maxLength="12" required /> &nbsp; <span id = "check" style="color:red;"></span>
                </li>

                <li><label class="email" for="email">이메일</label>
                    <input type="text" id="email" name="email" placeholder="이메일을 입력해주세요." required>
                </li>

            </ul>
        </fieldset>

        <div class="bord"></div>



        <!----------------------버튼------------------------------------------>
        <div class="bt">

            <div class="before button"><a href="#">이전 </a></div>
            <input type="submit" value="완료" id="btnjoin"/>

        </div>


    </form>
</div>
<script type="text/javascript">
    function tocheckpw() {
        var userPWD = document.getElementById("user-pw").value;
        var userPWD1 = document.getElementById("user-pw_").value;

        if (userPWD != userPWD1) {
            document.getElementById('check').innerHTML = '비밀번호가 틀렸습니다. 다시 입력해 주세요';
            return false;
        }
    }
</script>


</body>

</html>
