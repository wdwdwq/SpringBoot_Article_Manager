<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="DETAIL" />

<%@ include file="../common/header.jsp" %>

	<script>
		$(document).ready(function(){
			getRecommendPoint();
		})
	
		const getRecommendPoint = function(){
			$.ajax({
				url : "../recommendPoint/getRecommendPoint",
				method : "get",
				data : {
					"relTypeCode" : "article",
					"relId" : ${article.id }
				},
				dataType : "json",
				success : function(data){
					console.log(data);
				},
				error : function(xhr, status, error){
					console.error("ERROR : " + status + " - " + error);
				}
			})
		}
	</script>

	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<div>
				<table class="table table-lg">
					<tr>
						<th>번호</th>
						<td>${article.id }</td>
					</tr>
					<tr>
						<th>작성일</th>
						<td>${article.regDate }</td>
					</tr>
					<tr>
						<th>수정일</th>
						<td>${article.updateDate }</td>
					</tr>
					<tr>
						<th>조회수</th>
						<td>${article.hitCnt }</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td>${article.writerName }</td>
					</tr>
					<tr>
						<th>추천</th>
						<td>
							<c:if test="${rq.loginedMemberId == 0}">
								<span>${article.point }개</span>
							</c:if>
							
							<c:if test="${rq.loginedMemberId != 0}">
								<c:if test="${recommendPoint == null }">
									<a class="btn btn-outline btn-xs mr-8" href="../recommendPoint/insertPoint?id=${article.id }&relTypeCode=article">좋아요👍</a>
								</c:if>
								<c:if test="${recommendPoint != null }">
									<a class="btn btn-outline btn-active btn-xs mr-8" href="../recommendPoint/deletePoint?id=${article.id }&relTypeCode=article">좋아요👍</a>
								</c:if>
								<span>${article.point }개</span>
							</c:if>
							
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>${article.title }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>${article.body }</td>
					</tr>
				</table>
			</div>
			
			<div class="mt-2">
				<button class="btn btn-outline btn-sm" onclick="history.back();">뒤로가기</button>
				
				<c:if test="${rq.loginedMemberId == article.memberId }">
					<a class="btn btn-outline btn-sm" href="modify?id=${article.id }">수정</a>
					<a class="btn btn-outline btn-sm" href="doDelete?id=${article.id }" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
				</c:if>
			</div>
		</div>
	</section>
	
<%@ include file="../common/footer.jsp" %>