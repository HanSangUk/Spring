<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	function commemberout(password){
		if(confirm("정말 삭제하시겠습니까?"))
		comoutform.submit();
	}
</script>
<body>
	<h2>회원탈퇴</h2>
	<form action="commemberoutform" method="post" name="comoutform">
		사업자번호 : ${sessionScope.loginId} <br>
		<input type="hidden" id="comnumber" name="comnumber" value="${sessionScope.loginId}">
		비밀번호 <input type="password" id="compassword" name="compassword"><br>
		<button onclick="commemberout('compassword')">회원탈퇴</button>
	</form>
</body>
</html>