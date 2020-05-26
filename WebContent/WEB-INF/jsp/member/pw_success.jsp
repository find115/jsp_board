<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class ="empty-row"></div>
<div class ="empty-row"></div>


<article class="w70">
		<div class="row center title">비밀번호 변경이 완료되었습니다</div>
		<div class="row center big">변경된 비밀번호는 다음 로그인때부터 적용됩니다</div>
		
		<div class ="empty-row"></div>
		<div class ="empty-row"></div>

		<div class ="empty-row"></div>
		<div class ="empty-row"></div>

		<div class="row center big">
			<a href="information.do">내정보 보기</a>
		</div>
		<div class="row center big">
			<a href="${pageContext.request.contextPath }/index.do">홈으로</a>
		</div>
		
</article>

<div class ="empty-row"></div>
<div class ="empty-row"></div>


<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>