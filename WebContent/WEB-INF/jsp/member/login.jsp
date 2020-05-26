<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class ="empty-row"></div>
<div class ="empty-row"></div>

<div class= "row text-center">
   <div class="col-md-offset-3 col-md-6">
      <img src="${pageContext.request.contextPath }/res/image/login.jpg" width="100%">
   </div>
</div>

<div class="row">
	<div class="col-md-offset-3 col-md-6">

		<!-- form 만들기 -->
		<form action="login.do" method="post">

			<!-- 입력창, 라벨, 등을 묶어서 div 단위로 배치 : form-group -->
			<div class="form-group">
				<label for="id-input">ID</label> 
				<input id="id-input" type="text" name="id" class="form-control" placeholder="ID 입력" value="${cookie.saveId.value}">
			</div>

			<div class="form-group">
				<label for="pw-input">pw</label> 
				<input id="pw-input" type="password" name="pw" class="form-control" placeholder="비밀번호 입력">
			</div>
			<div>
				<c:choose>
					<c:when test="${empty cookie.saveId}">
					<input class = "form-input inline" type="checkbox" name="save">아이디 저장하기
					</c:when>
					<c:otherwise>
					<input type="checkbox" name="save" checked>아이디 저장하기
					</c:otherwise>
				</c:choose>
			</div>
			<div class="form-group">
				<!-- <input type="submit"> -->
				<button type="submit" class="btn btn-primary btn-block">로그인</button>
			</div>

		</form>
	</div>
</div>

<div class ="empty-row"></div>
<div class ="empty-row"></div>

<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>