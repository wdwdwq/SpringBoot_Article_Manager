<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:set var="pageTitle" value="DETAIL" />

<%@ include file="../common/header.jsp" %>

	<script>
		$(function(){
			getRecommendPoint();
			
			$('#recommendBtn').click(function(){
				let recommendBtn = $('#recommendBtn').hasClass('btn-active');
				
				$.ajax({
					url : "../recommendPoint/doRecommendPoint",
					method : "get",
					data : {
						"relTypeCode" : "article",
						"relId" : ${article.id },
						"recommendBtn" : recommendBtn
					},
					dataType : "text",
					success : function(data){
					},
					error : function(xhr, status, error){
						console.error("ERROR : " + status + " - " + error);
					}
				})
				
				location.reload();
			})
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
					if (data.success) {
						$('#recommendBtn').addClass('btn-active');
					}
				},
				error : function(xhr, status, error){
					console.error("ERROR : " + status + " - " + error);
				}
			})
		}
	</script>

	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3 border-bottom-line pb-8">
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
								<button id="recommendBtn" class="btn btn-outline btn-xs mr-8">좋아요👍</button>
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
	
	<script>
		const replyForm_onSubmit = function(form){
			form.body.value = form.body.value.trim();
			
			if (form.body.value.length < 2) {
				alert('2글자 이상 입력해주세요');
				form.body.focus();
				return;
			}
			
			form.submit();
		}
	</script>
	
	<section class="my-8 text-base">
		<div class="container mx-auto px-3">
			<div>댓글</div>
			<form action="../reply/doWrite" method="post" onsubmit="replyForm_onSubmit(this); return false;">
				<input type="hidden" name="relTypeCode" value="article"/>
				<input type="hidden" name="relId" value="${article.id }"/>
				<div class="mt-4 border border-gray-500 rounded-lg p-4">
					<div class="mb-2">닉네임</div>
					<textarea class="textarea textarea-bordered textarea-info w-full" name="body" placeholder="댓글을 작성해보세요"></textarea>
					<div class="flex justify-end"><button class="btn btn-outline btn-sm">작성</button>
					<a class="btn btn-outline btn-sm" href="modify?id=${article.id }">수정</a>
					<a class="btn btn-outline btn-sm" href="doDelete?id=${article.id }" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
					</div>
				</div>
			</form>
		</div>
	</section>
	
	
<%@ include file="../common/footer.jsp" %>