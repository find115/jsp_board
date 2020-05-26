<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class ="empty-row"></div>
<div class ="empty-row"></div>

<form action="regist.do" method="post">

	<article class="w40">
		<div class="row center title">회원 정보 입력</div>
		
		<div class="row left">아이디 <input class="form-input" type="text" name="id" required></div>
		
		<div class="row leftt">비밀번호 <input class="form-input" type="password" name="pw" required></div>
		
		<div class="row left">닉네임 <input class="form-input" type="text" name="nick" required></div>
		
		<div class="row left">생년월일 <input class="form-input" type="date" name="birth" required></div>
		
		<div class="row left">전화번호 <input class="form-input" type="text" name="tel" required></div>
		
		<div class="row left">이메일 <input class="form-input" type="email" name="email"></div>
		
		<div class="row left">주소
			<input class="form-input" type="text" name="post" size="6" maxlength="6" placeholder="우편번호">
		</div>
		<div class="row inline">
			<input class="form-btn" type="button" value="우편번호 찾기">
		</div>    
		<div class="row left">
			<input class="form-input" type="text" name="addr1" size="50" placeholder="기본주소">
			<input class="form-input" type="text" name="addr2" size="50" placeholder="상세주소">
		</div>
		<div class="row"><input class="form-btn" type="submit" value="가입"></div>
	</article>
</form>

<div class ="empty-row"></div>
<div class ="empty-row"></div>

<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>