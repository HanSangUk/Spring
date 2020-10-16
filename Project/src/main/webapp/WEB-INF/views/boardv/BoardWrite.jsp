<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#bcontents{
		height: 500px;
         width: 500px;
	}
</style>
<script>
	function boardwriter(){
		boardform.submit();
	}
</script>
</head>
<body>
<h1>글작성</h1>
	<form action="boardinsert" method="post" name="boardform" enctype="multipart/form-data">
		작성자 <input type="text" id="bwriter" name="bwriter" value="${sessionScope.loginId}" readonly><br>
		제목  <input type="text" id="btitle" name="btitle"><br>
		내용 <br><input type="text" id="bcontents" name="bcontents"><br>
		파일첨부 <input type="file" id="bfile" name="bfile"><br>
		<input type="submit" value="작성">
	</form>

</body>
</html>