<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class ="empty-row"></div>
<div class ="empty-row"></div>

<article class="w70">
		<div class="row center title">로그인 정보가 일치하지 않습니다</div>
		<div class="row center big">기존 비밀번호를 잘못 입력하셨습니다</div>
		
		<div class ="empty-row"></div>
		<div class ="empty-row"></div>

		<div class ="empty-row"></div>
		<div class ="empty-row"></div>

		<div class="row center big">
			<a href="<c:url value="/index.do"/>">홈으로</a>
		</div>
		<div class="row center big">
			<a href="login.do">다시 로그인</a>
		</div>
</article>

<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>