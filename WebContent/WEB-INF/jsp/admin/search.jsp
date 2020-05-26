<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <c:set var="root" value="${pageContext.request.contextPath}"></c:set> --%>	
<%-- <c:url var="root" value="/"></c:url>		이런식을 변수 만들어서 주소 쓰는게 편함 위에 밑에 둘다 됨. --%>

<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class ="empty-row"></div>


<div class="jumbotron text-center">
<c:choose>
	<c:when test="${param.type eq null}">
		<h1>회원 목록</h1>
	</c:when>
	<c:otherwise>
		<h1>회원 검색</h1>
	</c:otherwise>
</c:choose>
</div>


<div class="row"><hr color="blue"></div>

<form class="" action="search.do" method="get">
<div class="form-group row text-center">
				<select class="col--md-4 form-controll" name="type">
					<option value="id">아이디</option>
					<option value="nick">닉네임</option>
					<option value="tel">전화번호</option>
					<option value="email">이메일</option>
					<option value="authority">권한</option>
				</select>
				<input class="col--md-4 form-controll" name="keyword" type="search" placeholder="검색어" required>
				<input class="col--md-4 btn btn-primary" type="submit" value="검색">
</div>
</form>

<div class="row"><hr color="blue"></div>


<div class="table-responsive">
<!-- 데이터 출력 -->
<table class="table table-stripe">
	<thead>
		<tr>
			<th>아이디</th>
			<th>닉네임</th>
			<th>생년월일</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>권한</th>
			<th>가입일</th>
			<th>관리메뉴</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="mdto" items="${list}">
		<tr>
			<td>${mdto.id }</td>
			<td>${mdto.nick }</td>
			<td>${mdto.birth }</td>
			<td>${mdto.tel }</td>
			<td>${mdto.email }</td>
			<td>${mdto.authority }</td>
			<td>${mdto.date }</td>
			<td>
				<a href="${pageContext.request.contextPath }/admin/view.do?id=${mdto.id }">보기</a>
				<a href="${pageContext.request.contextPath }/member/check.do?go=/admin/edit.do?id=${mdto.id}">수정</a>
				<a href="${pageContext.request.contextPath }/member/check.do?go=/admin/drop.do?id=${mdto.id}">탈퇴</a>
				<a href="temp.do?id=${mdto.id}">임시비번</a>
			</td>
		</tr>	
		</c:forEach>
	</tbody>
</table>
</div>



<div class ="empty-row"></div>
<div class ="empty-row"></div>

<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>


	
