<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>마이페이지</h2>
	<table border="1">
		<tr>
			<th>사업자번호</th>
			<th>기업</th>
			<th>지점</th>
			<th>전화번호</th>
			<th>주소</th>
		</tr>
		<tr>
			<td>${commypage.comnumber}</td>
			<td>${commypage.company}</td>
			<td>${commypage.comoffice}</td>
			<td>${commypage.comphone}</td>
			<td>${commypage.comaddress1} ${commypage.comaddress2} ${commypage.comaddress3} ${commypage.comaddress4}</td>
		</tr>
	</table>
	<button onclick="location.href='commemberupdate?comnumber=${sessionScope.loginId}'">회원정보수정</button>
	<button onclick="location.href='commemberout'">회원탈퇴</button>
</body>
</html>