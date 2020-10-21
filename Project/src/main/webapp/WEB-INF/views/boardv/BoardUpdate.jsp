<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
	var sel_file;
	$(document).ready(function(){
		$("#bfile").on("change", handleImgFileSelect);
	});
	
	function handleImgFileSelect(e){
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);
		
		filesArr.forEach(function(f){
			sel_file = f;
			var reader = new FileReader();
			reader.onload = function(e){
				$("#img").attr("src", e.target.result);
			}
			reader.readAsDataURL(f);
		});
	}
	
</script>
</head>
<body>
<h1>글 수정</h1>
<form action="boardupdateform" method="post" id="fileForm" name="updateform" enctype="multipart/form-data">
	작성자 <input type="text" id="bwriter" name="bwriter" value="${boardupdate.bwriter}" readonly><br>
	제목 <input type="text" id="btitle" name="btitle" value="${boardupdate.btitle}"><br>
	내용 <input type="text" id="bcontents" name="bcontents" value="${boardupdate.bcontents}"><br>
	<input type="hidden" id="page" name="page" value="${page}">
	<input type="hidden" id="bnumber" name="bnumber" value="${boardupdate.bnumber}">
	<input type="hidden" id="preexistence" name="preexistence" value="${boardupdate.bfilename}">
	첨부파일 <input type="file" id="bfile" name="bfile"><br>
	이미지<br>
	<img id="img" src="${pageContext.request.contextPath}/resources/uploadFile/${boardupdate.bfilename}"><br>
	<input type="submit" value="수정">
</form>
</body>
</html>