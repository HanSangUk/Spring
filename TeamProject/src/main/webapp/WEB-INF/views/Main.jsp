<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Home</title>
<script>

</script>	
</head>
<body>
	<c:if test="${empty sessionScope.loginId}">
		<button onclick="location.href='memberinsertpick'">회원가입</button>
		<button onclick="location.href='loginpick'">로그인</button>
	</c:if>
	
	<c:if test="${not empty sessionScope.loginId}">
		${sessionScope.loginId} 님<br>
		<button onclick="location.href='logout'">로그아웃</button>
		<button onclick="location.href='mypage?comnumber=${sessionScope.loginId}'">마이페이지</button>
	</c:if>
</body>
</html>
