<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
<style>
	img{
		height: 70px;
        width: 300px;
	}
</style>
	
</head>
<body>
	<h1> Home </h1>
	<button onclick="location.href='memberjoin'">회원가입</button>
	<button onclick="location.href='login'">로그인</button>
	
	
	
<h2>카카오로 회원가입</h2>
<a href="kakaojoin">
	<img src="${pageContext.request.contextPath}/resources/img/kakao_login_medium_narrow.png">
</a><br>

<h2>카카오로 로그인</h2>
<a href="kakaologin">
	<img src="${pageContext.request.contextPath}/resources/img/kakao_login_medium_narrow.png">
</a><br>

<h2>네이버로 회원가입</h2>
<a href="naverjoin">
	<img src="${pageContext.request.contextPath}/resources/img/네이버 아이디로 로그인_완성형_Green.PNG">
</a><br>

<h2>네이버로 로그인</h2>
<a href="naverlogin">
	<img src="${pageContext.request.contextPath}/resources/img/네이버 아이디로 로그인_완성형_Green.PNG">
</a><br>
</body>
</html>
