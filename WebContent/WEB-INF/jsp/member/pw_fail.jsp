<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class ="empty-row"></div>
<div class ="empty-row"></div>

<article class="w70">
		<div class="row center title">비밀번호 변경에 실패했습니다</div>
		<div class="row center big">기존 비밀번호를 잘못 입력하셨습니다</div>
		
		<div class ="empty-row"></div>
		<div class ="empty-row"></div>

		<div class ="empty-row"></div>
		<div class ="empty-row"></div>

		<div class="row center big">
			<a href="pw.do">다시 입력하기</a>
		</div>
		<div class="row center big">
			<a href="information.do">내 정보 보기</a>
		</div>
		<div class="row center big">
			<a href="${pageContext.request.contextPath }/index.do">홈으로</a>
		</div>
		
</article>
<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>