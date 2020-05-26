<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class ="empty-row"></div>
<div class ="empty-row"></div>

<form action="pw.do" method="post">
	<article class="w40">
		<div class="row center title">비밀번호 변경</div>
		
		<div class="row">
			<input class = "form-input" type="password" name="pw" placeholder="기존 비밀번호" required>
		</div>
		<div class="row">
			<input class = "form-input" type="password" name="npw" placeholder="신규 비밀번호" required>
		</div>
		<div class="row">
			<input class = "form-btn" type="submit" value="변경하기">
		</div>
		
	</article>
</form>

<div class ="empty-row"></div>
<div class ="empty-row"></div>

<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>