<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>상세조회</h1>
<table border="1">
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>가입일</th>
		<th>이메일</th>
		<th>전화번호</th>
		<th>주소</th>
	</tr>
	<tr>
		<td>${mDTO.mid}</td>
		<td>${mDTO.mname}</td>
		<td>${mDTO.mbirth}</td>
		<td>${mDTO.memail}</td>
		<td>${mDTO.mphone}</td>
		<td>${mDTO.maddress1} ${mDTO.maddress2} ${mDTO.maddress3} ${mDTO.maddress4}</td>
	</tr>
</table>
이미지 <img src="${pageContext.request.contextPath}/resources/uploadFile/${mDTO.mfilename}"><br>
<button onclick="location.href='memberlistpaging?page=${page}'">회원목록으로</button>
</body>
</html>