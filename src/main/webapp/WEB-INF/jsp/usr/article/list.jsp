<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pagerTitle" value="LIST" />

<%@ include file="../common/header.jsp"%>
	<h1>LIST PAGE</h1>
	
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>작성일</th>
				<th>작성자</th>
				<th>제목</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${articles }">
				<tr>
					<td>${article.id }</td>
					<td>${article.regDate }</td>
					<td>${article.memberId }</td>
					<td>${article.title }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<%@ include file="../common/footer.jsp"%>
