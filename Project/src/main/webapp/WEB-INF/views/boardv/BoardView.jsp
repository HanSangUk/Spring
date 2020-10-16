<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
	function BoardUpdate(bnumber, page, bwriter){
		var loginId = '${sessionScope.loginId}';
		var bwriter = bwriter;
		if(loginId != bwriter){
			alert("작성자가 아닙니다");
		} else {
			location.href='boardupdate?bnumber='+bnumber+'&page='+page;
		}
	}
	function BoardDelete(bnumber, page, bwriter){
		var loginId = '${sessionScope.loginId}';
		var bwriter = bwriter;
		if(loginId != bwriter){
			alert("작성자가 아닙니다");
		} else {
			location.href='boarddelete?bnumber='+bnumber+'&page='+page;
		}
	}
</script>
</head>
<body>
<h1>게시글 상세보기</h1>
	<table border="1">
		<tr>
			<th>글번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>내용</th>
			<th>조회수</th>
		</tr>
		<tr>
			<td>${boardview.bnumber}</td>
			<td>${boardview.bwriter}</td>
			<td>${boardview.btitle}</td>
			<td>${boardview.bcontents}</td>
			<td>${boardview.bhits}</td>
		</tr>
	</table>
	이미지 <img src="${pageContext.request.contextPath}/resources/uploadFile/${boardview.bfilename}"><br>
	<button onclick="BoardUpdate('${boardview.bnumber}','${page}','${boardview.bwriter}')">수정</button>
	<button onclick="location.href='boardlist?page=${page}'">리스트로~</button><br>
	<button onclick="BoardDelete('${boardview.bnumber}','${page}','${boardview.bwriter}')">삭제</button>
</body>
</html>