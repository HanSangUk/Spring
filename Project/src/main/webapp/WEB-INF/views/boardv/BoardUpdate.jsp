<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>글 수정</h1>
<form action="boardupdateform" method="post"  enctype="multipart/form-data">
	작성자 <input type="text" id="bwriter" name="bwriter" value="${boardupdate.bwriter}" readonly><br>
	제목 <input type="text" id="btitle" name="btitle" value="${boardupdate.btitle}"><br>
	내용 <input type="text" id="bcontents" name="bcontents" value="${boardupdate.bcontents}"><br>
	<input type="hidden" id="page" name="page" value="${page}">
	<input type="hidden" id="bnumber" name="bnumber" value="${boardupdate.bnumber}">
	
	<input type="submit" value="수정">
</form>
</body>
</html>