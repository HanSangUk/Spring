<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function comlogin(){
		comloginform.submit();
	}
</script>
</head>
<body>
	<form action="comlogin" method="post" name="comloginform">
		사업자번호 <input type="text" id="comnumber" name="comnumber"> <br>
		비밀번호 <input type="password" id="compassword" name="compassword">
		<button onclick="comlogin()">로그인</button>
	</form>
</body>
</html>