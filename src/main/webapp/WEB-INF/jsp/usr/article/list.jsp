<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="LIST" />

<%@ include file="../common/header.jsp" %>

	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<div>
				<table class="table table-lg">
					<thead class="text-lg">
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
			</div>
		</div>
	</section>
	
<%@ include file="../common/footer.jsp" %>