<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class ="empty-row"></div>
<div class ="empty-row"></div> 

<article class="w50">

<div class="row center title">비밀번호 입력 (목적지 = ${param.go })</div>

<div class ="empty-row"></div>
<div class ="empty-row"></div> 
<div class ="empty-row"></div>

<form action="check.do" method="post">
	<!-- 필요에 의해 사용자 몰래 보내는 데이터가 필요 : go 파라미터 -->
	
	<div class="row">
		<input class="form-input" type="hidden" name="go" value="${param.go }">
	</div>

	<div class="row">
		<input class="form-input" type="password" name="pw" placeholder="비밀번호 입력" required>
	</div>
	
	<div class="row">
		<input class="form-btn" type="submit" value="확인">
	</div>
</form>

</article>

<div class ="empty-row"></div>
<div class ="empty-row"></div> 

<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>

