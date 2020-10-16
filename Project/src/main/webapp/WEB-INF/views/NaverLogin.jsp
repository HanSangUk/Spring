<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function goNaverLogin(){
		location.href = "${naverUrl}";
	}
</script>
</head>
<body onload="goNaverLogin()">
	<h2>네이버로그인</h2>
</body>
</html>