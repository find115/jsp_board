<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class ="empty-row"></div>
<div class ="empty-row"></div> 

<article class="w70">

<div class="row center title">회원 정보 보기</div>

<div class ="empty-row"></div>
 
<div class="row center normal">
<table class="table table-stripe">
	<tbody>
		<tr>
			<th width="20%">아이디</th>
			<td>${mdto.id}</td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td>${mdto.nick}</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>${mdto.birth}</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${mdto.tel}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${mdto.email}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>[${mdto.post}] ${mdto.addr1} ${mdto.addr2}</td>
		</tr>
		<tr>
			<th>가입일</th>
			<td>${mdto.date}</td>
		</tr>
		<tr>
			<th>권한</th>
			<td>${mdto.authority}</td>
		</tr>
	</tbody>
</table>
</div>

<div class ="empty-row"></div>

<div class="row center big"><a href="">비밀번호 변경하기</a></div>
<div class="row center big"><a href="">개인정보 변경하기</a></div>
<div class="row center big"><a href="">회원 탈퇴하기</a></div>


</article>
<div class ="empty-row"></div>
<div class ="empty-row"></div> 

<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>




