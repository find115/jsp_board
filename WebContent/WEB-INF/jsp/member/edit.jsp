<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class ="empty-row"></div>
<div class ="empty-row"></div> 

<article class="w50">

<div class="row title center">회원 정보 수정</div>

<form action="edit.do" method="post">
<div class="row center">
<table class="table table-hover">
	<tbody>
		<tr>
			<th width="20%">아이디</th>
			<td>
				<input class="form-input" type="text" name="id" value="${mdto.id}" required disabled>
			</td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td>
				<input class="form-input" type="text" name="nick" value="${mdto.nick}" required>
			</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>
				${mdto.birth}
			</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>
				<input class="form-input" type="text" name="tel" value="${mdto.tel}" required>
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
				<input class="form-input" type="email" name="email" value="${mdto.email}">
			</td>
		</tr>
		<tr>
			<th rowspan="3">주소</th>
			<td>
				<input class="form-input" type="text" name="post" value="${mdto.post}" 
						size="6" maxlength="6" placeholder="우편번호">
				<input class="form-input" type="button" value="우편번호 찾기"> 
			</td>
		</tr>
		<tr>
			<td>
				<input class="form-input" type="text" name="addr1" size="50" value="${mdto.addr1}" placeholder="기본주소">
			</td>
		</tr>
		<tr>
			<td>
				<input class="form-input" type="text" name="addr2" size="50" value="${mdto.addr2}" placeholder="상세주소">
			</td>
		</tr>
	</tbody>
	<tfoot>
		<tr>
			<th colspan="2">
				<input class="form-btn" type="submit" value="수정">
			</th>
		</tr>
	</tfoot>
</table>
</div>
</form>

</article>
<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>