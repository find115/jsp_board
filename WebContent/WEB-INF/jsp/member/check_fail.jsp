<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class ="empty-row"></div>
<div class ="empty-row"></div> 

<article class="w50">

<div class="row center title">비밀번호가 일치하지 않습니다</div>

<div class ="empty-row"></div>
<div class ="empty-row"></div> 
<div class ="empty-row"></div>

<div class="row center big"><a href="${pageContext.request.contextPath }/index.do">홈으로</a></div>
<div class="row center big"><a href="check.do?go=${go}">다시 로그인</a></div>

</article>
<div class ="empty-row"></div>
<div class ="empty-row"></div> 

<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>