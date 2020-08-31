<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
	<button class="btn btn-secondary" onclick="history.back()">뒤로가기</button>
	<c:if test="${board.user.id == principal.user.id }">
		<a href="/board/${board.id}/modify"><button class="btn btn-warning" >글 수정</button></a>
		<button id="btn-delete" class= "btn btn-danger">글 삭제</button>
	</c:if>
	<br/><br/>
		글번호 : <span id="id"><i>${board.id}</i></span>
		작성자 :<span><i>${board.user.username}</i></span>
	<br>
	<div>
	  <h3>${board.title}</h3>
	</div>
	<div>
		<div>${board.content}</div>
	</div>
</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>
