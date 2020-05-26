<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class ="empty-row"></div>
<div class ="empty-row"></div> 

<article class="w80">

<div class="row center title">게시글 수정</div>
<div class ="empty-row"></div>

<div class="row center normal">
<form action="edit.do" method="post" enctype="multipart/form-data">
<input type="hidden" name="no" value="${bdto.no }">

<table class="table table-hover">
	<tbody>
		<tr>
			<th width="20%">말머리</th>
			<td>
				<select name="head">
					<c:choose>
						<c:when test="${bdto.head != null && bdto.head eq '정보' }">
							<option value="">말머리 선택</option>
							<option selected>정보</option>
							<option>유머</option>
							<option>공부</option>
							<option>게임</option>
						</c:when>
						<c:when test="${bdto.head != null && bdto.head eq '유머' }">
							<option value="">말머리 선택</option>
							<option>정보</option>
							<option selected>유머</option>
							<option>공부</option>
							<option>게임</option>
						</c:when>
						<c:when test="${bdto.head != null && bdto.head eq '공부' }">
							<option value="">말머리 선택</option>
							<option>정보</option>
							<option>유머</option>
							<option selected>공부</option>
							<option>게임</option>
						</c:when>
						<c:when test="${bdto.head != null && bdto.head eq '게임' }">
							<option value="">말머리 선택</option>
							<option>정보</option>
							<option>유머</option>
							<option>공부</option>
							<option selected>게임</option>
						</c:when>
						<c:otherwise>
							<option value="">말머리 선택</option>
							<option>정보</option>
							<option>유머</option>
							<option>공부</option>
							<option>게임</option>
						</c:otherwise>
					</c:choose>
				</select>
				
				<c:if test="${sessionScope.power eq '관리자' }">
					<c:choose>
						<c:when test="${bdto.status != null && bdto.status eq '공지' }">
							<input type="checkbox" name="status" value="공지" checked>공지사항으로 등록
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="status" value="공지">공지사항으로 등록
						</c:otherwise>
					</c:choose>
				</c:if>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>
				<input class="form-input" type="text" name="subject" size="100" required value="${bdto.subject }">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea name="content" rows="10" cols="100" required
						>${bdto.content }</textarea>
			</td>
		</tr>
		<!-- 첨부파일 추가 출력 -->
		<tr>
			<th>첨부파일</th>
			<td>
				<input class="form-input" type="file" name="file">
				(기존파일 : ${bdto.userfile })
			</td>
		</tr>
		
		<tr>
			<th colspan="2">
				<input class="form-btn" type="submit" value="수정">
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