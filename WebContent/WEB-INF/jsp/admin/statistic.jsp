<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class ="empty-row"></div>
<div class ="empty-row"></div> 

<article class="w80">

<div class="row center title">통계(일자별 요청 / 사용자수)</div>

<div class="row center normal">
<table class="table table-stripe">
	<thead>
		<tr>
			<th width="33%">일자</th>
			<th width="33%">요청수</th>
			<th width="33%">사용자수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="day" items="${map.keySet()}">
			<tr>
				<th>${map.get(day).getDay() }</th>
				<th>${map.get(day).getRequest() }</th>
				<th>${map.get(day).getPeople() }</th>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>

</article>

<div class ="empty-row"></div>
<div class ="empty-row"></div> 
<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>


