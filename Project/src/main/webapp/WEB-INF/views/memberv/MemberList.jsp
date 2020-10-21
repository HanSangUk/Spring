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
<h1>회원목록</h1>
		<table border="1">
		<tr>
			<td>아이디</td>
			<td>이름</td>
			<td>가입일</td>
		</tr>
		<c:forEach var="memberlist" items="${memberlist}" >
			<tr>
				<td><a href="memberview?mid=${memberlist.mid}&page=${paging.page}">${memberlist.mid}</a></td>
				<td>${memberlist.mname}</td>
				<td>${memberlist.mbirth}</td>
				<td><button onclick="location.href='memberdelete?mid=${memberlist.mid}'">삭제</button></td>
			</tr>
		</c:forEach>
	</table>
	<!-- 페이징 처리 -->
	<c:if test="${paging.page<=1}">
	[이전]&nbsp;
	</c:if>
	
	<c:if test="${paging.page>1}">
		<a href="memberlistpaging?page=${paging.page-1}">[이전]</a>&nbsp;
	</c:if>
	
	<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
		<c:choose>
			<c:when test="${i eq paging.page}">
			${i}
			</c:when>
			<c:otherwise>
				<a href="memberlistpaging?page=${i}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${paging.page>=paging.maxPage}">
		[다음]
	</c:if>
	<c:if test="${paging.page<paging.maxPage}">
		<a href="memberlistpaging?page=${paging.page+1}">[다음]</a>
	</c:if>
</body>
</html>