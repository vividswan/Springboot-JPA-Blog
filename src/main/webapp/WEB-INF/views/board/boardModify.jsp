<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
<form>
	<input type="hidden"  value ="${board.id }"  id="id"/>
  <div class="form-group">
    <label for="title">Title:</label>
    <input type="text" value="${board.title}"  class="form-control" placeholder="Enter UserName" id="title">
  </div>
<div class="form-group">
  <label for="cotent">Content:</label>
  <textarea class="form-control summernote"  id="content">${board.content}</textarea>
</div>
</form>
   <button id="btn-update" class="btn btn-warning">글 수정</button>

</div>
<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
  </script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>
