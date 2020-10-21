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
<h1>마이페이지</h1>
	
	<h2>내가 쓴 글</h2>
				<table border="1">
		<tr>
			<td>글번호</td>
			<td>작성자</td>
			<td>글제목</td>
			<td>작성일자</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="myboard" items="${myboard}" >
			<tr>
				<td>${myboard.bnumber}</td>
				<td><a
					href="#" onclick="popup('${myboard.bwriter}')">${myboard.bwriter}</a></td>
				<td><a
					href="boardview?bnumber=${myboard.bnumber}&page=${paging.page}">${myboard.btitle}</a></td>
				<td>${myboard.bdate}</td>
				<td>${myboard.bhits}</td>
			</tr>
		</c:forEach>
	</table>

	<!-- 페이징 처리 -->
	<c:if test="${paging.page<=1}">
	[이전]&nbsp;
	</c:if>
	
	<c:if test="${paging.page>1}">
		<a href="mypage?page=${paging.page-1}&bwriter=${paging.keyword}&searchtype=${paging.searchtype}">[이전]</a>&nbsp;
	</c:if>
	
	<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
		<c:choose>
			<c:when test="${i eq paging.page}">
			${i}
			</c:when>
			<c:otherwise>
				<a href="mypage?page=${i}&bwriter=${paging.keyword}&searchtype=${paging.searchtype}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:if test="${paging.page>=paging.maxPage}">
		[다음]
	</c:if>
	
	<c:if test="${paging.page<paging.maxPage}">
		<a href="mypage?page=${paging.page+1}&bwriter=${paging.keyword}&searchtype=${paging.searchtype}">[다음]</a>
	</c:if>
	<br>
<button onclick="location.href='main'">메인으로</button>
<button onclick="location.href='memberupdate?mid=${sessionScope.loginId}'">회원정보수정</button><br>
<button onclick="location.href='logout'">로그아웃</button>
</body>
</html>