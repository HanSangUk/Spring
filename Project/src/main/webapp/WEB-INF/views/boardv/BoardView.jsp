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
	function BoardUpdate(bnumber, page, bwriter){
		var loginId = '${sessionScope.loginId}';
		var admin = "admin";
		var bwriter = bwriter;
		if(loginId == admin){
			location.href='boardupdate?bnumber='+bnumber+'&page='+page;
		} else if(loginId != bwriter) {
			alert("작성자가 아닙니다");
		} else {
			location.href='boardupdate?bnumber='+bnumber+'&page='+page;
		}
	}
	function BoardDelete(bnumber, page, bwriter){
		var loginId = '${sessionScope.loginId}';
		var bwriter = bwriter;
		var admin = "admin";
		if(loginId == admin){
			location.href='boarddelete?bnumber='+bnumber+'&page='+page;
		} else if(loginId != bwriter) {
			alert("작성자가 아닙니다");
		} else {
			location.href='boarddelete?bnumber='+bnumber+'&page='+page;
		}
	}
	
	function commentajax(bnumber){
		var ccontents = document.getElementById("ccontents").value;
		var cwriter = document.getElementById("cwriter").value;
		console.log(cwriter);
		console.log(ccontents);
		$.ajax({
			type : "post",
			url : "boardcomment",
			data : {"bnumber" : bnumber, "ccontents" : ccontents, "cwriter" : cwriter},
			dataType : "json",
			success : function(result){
				var output = "<table border='1'>";
					output = "<tr><th>작성자</th><th>댓글</th><th>작성일</th><tr>";
					for(var i=0; i < result.length; i++){
						output += "<tr>";
						output += "<td>"+result[i].cwriter+"</td>";
						output += "<td>"+result[i].ccontents+"</td>";
						output += "<td>"+result[i].cdate+"</td>";
						output += "<td><button onclick='commentdeleteajax("+result[i].cnumber+","+bnumber+")'>삭제</button></td>";
						output += "<tr>";
					}
					output += "</table>";
					$("#commentlist").html(output);
			},
			error : function(){
				console.log("통신실패");
			}
		});
	}
	
	function commentdeleteajax(cnumber, bnumber){
		$.ajax({
			type : "post",
			url : "commentdelete",
			data : {"bnumber" : bnumber, "cnumber" : cnumber},
			dataType : "json",
			success : function(result){
				var output = "<table border='1'>";
					output = "<tr><th>작성자</th><th>댓글</th><th>작성일</th></tr>";
					for(var i=0; i < result.length; i++){
						output += "<tr>";
						output += "<td>"+result[i].cwriter+"</td>";
						output += "<td>"+result[i].ccontents+"</td>";
						output += "<td>"+result[i].cdate+"</td>";
						output += "<td><button onclick='commentdeleteajax("+result[i].cnumber+","+bnumber+")'>삭제</button></td>";
						output += "</tr>";
					}
					output += "</table>";
					$("#commentlist").html(output);
			},
			error : function(){
				console.log("통신실패");
			}
		});
	} 
</script>
<style>
	table{
		border : 1px solid black;
	}
</style>
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
	<br>
	<input type="text" id="cwriter" name="cwriter" value="${sessionScope.loginId}" readonly>
	<input type="text" id="ccontents" name="ccontents">
	<button onclick="commentajax('${boardview.bnumber}')">댓글작성</button>
<div id="commentlist">
	<table border="1">
		<tr>
			<th>작성자</th>
			<th>댓글</th>
			<th>작성일</th>
		</tr>
		<c:forEach var="boardcomment" items="${boardcomment}">
			<tr>
				<td>${boardcomment.cwriter}</td>
				<td>${boardcomment.ccontents}</td>
				<td>${boardcomment.cdate}</td>
				<td><button onclick="commentdeleteajax('${boardcomment.cnumber}','${boardcomment.bnumber}')">삭제</button></td>
			</tr>
		</c:forEach>
	</table>
</div>

</body>
</html>