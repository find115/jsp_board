<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class ="empty-row"></div>
<div class ="empty-row"></div> 

<article class="w80">

<div class="row center big">회원 정보 수정</div>

<div class ="empty-row"></div>
<div class ="empty-row"></div> 

<div>
<form action="edit.do" method="post">
<table class="table table-hover">
	<tbody>
		<tr>
			<th width="20%">아이디</th>
			<td>
				<input type="hidden" name="id" value="${mdto.id }">
				<input type="text" name="id" value="${mdto.id }" required disabled>
			</td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td>
				<input type="text" name="nick" value="${mdto.nick }" required>
			</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>
				<input type="date" name="birth" value="${mdto.birth }" required>
			</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>
				<input type="text" name="tel" value="${mdto.tel }" required>
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
				<input type="email" name="email" value="${mdto.email }">
			</td>
		</tr>
		<tr>
			<th rowspan="3">주소</th>
			<td>
				<input type="text" name="post" value="${mdto.post }" 
						size="6" maxlength="6" placeholder="우편번호">
				<input type="button" value="우편번호 찾기"> 
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="addr1" size="50" value="${mdto.addr1 }" placeholder="기본주소">
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="addr2" size="50" value="${mdto.addr2 }" placeholder="상세주소">
			</td>
		</tr>
		<tr>
			<th>권한</th>
			<td>
				<c:choose>
					<c:when test="${mdto.authority eq '일반회원' }">
						<input type="radio" name="authority" value="일반회원" checked>일반회원
						<input type="radio" name="authority" value="우수회원">우수회원
					</c:when>
					<c:when test="${mdto.authority eq '우수회원' }">
						<input type="radio" name="authority" value="일반회원">일반회원
						<input type="radio" name="authority" value="우수회원" checked>우수회원
					</c:when>
					<c:otherwise>
						<input type="hidden" name="authority" value="관리자">
						관리자
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<th colspan="2">
				<input type="submit" value="수정">
			</th>
		</tr>
	</tfoot>
</table>
</form>

</div>
</article>

<div class ="empty-row"></div>
<div class ="empty-row"></div> 


<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>