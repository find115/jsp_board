<%@page import="home.bean.BoardDto"%>
<%@page import="java.util.List"%>
<%@page import="home.bean.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 이게 페이지 매니저 만들기 전 -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<%-- <h3>type = <%=type%>, keyword = <%=keyword%>, search = <%=search%></h3> --%>
<!-- <h3> -->
<%-- 	pageStr = <%=pageStr%>, pageNo = <%=pageNo%>,  --%>
<%-- 	start = <%=start%>, finish = <%=finish%>, --%>
<!-- </h3> -->
<!-- <h3> -->
<%-- 	count = <%=count%>, pagecount = <%=pagecount %>, --%>
<%-- 	startPage = <%=startPage%>, finishPage = <%=finishPage%> --%>
<!-- </h3> -->

<h1>자유 게시판</h1>

<table border="1" width="90%">
	<thead>
		<tr>
			<th>번호</th>
			<th width="45%">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<!-- 공지사항만 출력 -->
		<c:forEach var="bdto" items="${notice}">
		<tr align="center" bgcolor="yellow">
			<td>공지</td>
			<td align="left">
			<c:choose>	
				<c:when test="${bdto.head == null }">
					${bdto.subject }
				</c:when>
				<c:otherwise>
					[${bdto.head }]${bdto.subject }
				</c:otherwise>
			</c:choose>
			</td>
			<td>${bdto.writer }</td>
			<td>${bdto.getTime() }</td>
			<td>${bdto.read }</td>
		</tr>
		</c:forEach>
	</thead>
	<tbody align="center">
		<c:forEach var="bdto" items="${list}">
			<tr>
				<td>${bdto.no }</td>
				<td align="left">
				<c:choose>
					<c:when test="${bdto.isBlind() }">
						<font color="gray">블라인드 처리된 글입니다</font>		
					</c:when>
					<c:otherwise>
						<c:forEach var="i" begin="1" end="${bdto.depth }" step="1">
							<c:choose>
								<c:when test="${i == bdto.depth }">
									&nbsp;&nbsp;&nbsp;
									<img src="${pageContext.request.contextPath }/image/reply.png" width = "15" height="15">
								</c:when>
								<c:otherwise>
									&nbsp;&nbsp;&nbsp;
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>	
							<c:when test="${bdto.head == null }">
								<a href="content.do?no=${bdto.no }">${bdto.subject }</a>
							</c:when>
							<c:otherwise>
								<a href="content.do?no=${bdto.no }">[${bdto.head }]${bdto.subject}</a>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
				</td>
				<td>${bdto.writer }</td>
				<td>${bdto.getTime() }</td>
				<td>${bdto.read }</td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td align="right" colspan="5">
				<a href="write.do">글쓰기</a>
			</td>
		</tr>
	</tfoot>
</table>

<br><br>

<c:set var="queryString"></c:set>
<c:choose>
	<c:when test="${search}">
		<c:set var="queryString" value="&type=${type }&keyword=${keyword }"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="queryString" value = ""></c:set>
	</c:otherwise>
</c:choose>

<c:if test="${startPage>1 }">
	<a href="list.do?page=${startPage-1}${queryString}">[이전] </a>
</c:if>

<c:forEach var="i" begin="${startPage }" end="${finishPage }" step="1">
	<c:choose>
		<c:when test="${pageNo == i }">
			${i }
		</c:when>
		<c:otherwise>
			<a href="list.do?page=${i}${queryString}">${i }</a>
		</c:otherwise>
	</c:choose>
</c:forEach>

<c:if test="${finishPage < pagecount }">
<a href="list.do?page=${finishPage+1}${queryString}">[다음]</a>
</c:if>

<br><br>

<!-- 검색어 입력창 -->
<form action="list.do" method="get">
	<select name="type">
		<c:choose>
			<c:when test="${search && type eq '제목' }">
				<option value="subject||content">제목+내용</option>
				<option value="subject" selected>제목</option>
				<option value="writer">작성자</option>
			</c:when>
			<c:when test="${search && type eq '작성자' }">
				<option value="subject||content">제목+내용</option>
				<option value="subject">제목</option>
				<option value="writer" selected>작성자</option>
			</c:when>
			<c:otherwise>
				<option value="subject||content">제목+내용</option>
				<option value="subject">제목</option>
				<option value="writer">작성자</option>
			</c:otherwise>
		</c:choose>
	</select>
	
	<c:choose>
		<c:when test="${keyword == null }">
			<input type="search" name="keyword" value="" placeholder="검색어" required>
		</c:when>
		<c:otherwise>
			<input type="search" name="keyword" value="${keyword }" placeholder="검색어" required>
		</c:otherwise>
	</c:choose>
	<input type="submit" value="검색">
</form>

<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>








