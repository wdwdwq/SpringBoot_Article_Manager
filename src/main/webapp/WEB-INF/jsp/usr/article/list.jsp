<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="${board.name }" />

<%@ include file="../common/header.jsp" %>

	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<div class="mb-2 text-base">
				<div><span>총 : ${articlesCnt }개</span></div>
			</div>
			<div>
				<table class="table table-lg">
					<thead class="text-lg">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="article" items="${articles }">
							<tr class="hover">
								<td>${article.id }</td>
								<td class="hover:underline"><a href="detail?id=${article.id }">${article.title }</a></td>
								<td>${article.writerName }</td>
								<td>${article.regDate.substring(2, 16) }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<c:if test="${rq.getLoginedMemberId() != 0 }">
				<div class="mt-2 flex justify-end">
					<a class="btn btn-outline btn-sm" href="write">글쓰기</a>
				</div>
			</c:if>
			<div class="mt-2 flex justify-center">
				<div class="join">
					<c:if test="${from != 1 }">
						<a class="join-item btn btn-square btn-sm" href="?boardId=${board.id }&page=1">&lt;&lt;</a>
						<a class="join-item btn btn-square btn-sm" href="?boardId=${board.id }&page=${from - 1 }">&lt;</a>
					</c:if>
					
					<c:forEach begin="${from }" end="${end }" var="i">
						<a class="join-item btn btn-square btn-sm ${page == i ? 'btn-active' : '' }" href="?boardId=${board.id }&page=${i }">${i }</a>
					</c:forEach>
					
					<c:if test="${end != pagesCnt }">
						<a class="join-item btn btn-square btn-sm" href="?boardId=${board.id }&page=${end + 1 }">&gt;</a>
						<a class="join-item btn btn-square btn-sm" href="?boardId=${board.id }&page=${pagesCnt }">&gt;&gt;</a>
					</c:if>
				</div>
			</div>
		</div>
	</section>
	
<%@ include file="../common/footer.jsp" %>