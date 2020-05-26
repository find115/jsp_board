<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class ="empty-row"></div>
<div class ="empty-row"></div> 

<article class="w80">

<div class="row center title">게시글 쓰기</div>
<div class ="empty-row"></div>

<div class="row center normal">
<form action="write.do" method="post" enctype="multipart/form-data">

<!-- 답글이라면 상태값 3개를 추가로 전송(gno, parent, depth) -->
<c:if test="${param.gno != null}">
	<input type="hidden" name="gno" value="${param.gno }">
	<input type="hidden" name="parent" value="${param.parent }">
	<input type="hidden" name="depth" value="${param.depth }">
</c:if>

<table class="table table-hover">
	<tbody>
		<tr>
			<th width="20%">말머리</th>
			<td>
				<select name="head">
					<option value="">말머리 선택</option>
					<option>정보</option>
					<option>유머</option>
					<option>공부</option>
					<option>게임</option>
				</select>
				<c:if test="${sessionScope.power eq '관리자' }">
					<input type="checkbox" name="status" value="공지">공지사항으로 등록
				</c:if>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>
				<input class="form-input" type="text" name="subject" size="100" required>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea name="content" rows="10" cols="100" required></textarea>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<input class="form-input" type="file" name="file">
			</td>
		</tr>
		<tr>
			<th colspan="2">
				<input class="form-btn" type="submit" value="등록">
			</th>
		</tr>
	</tbody>
</table>
</form>

</div>

</article>
<div class ="empty-row"></div>
<div class ="empty-row"></div>
<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>