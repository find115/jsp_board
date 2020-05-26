<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class ="empty-row"></div>
<div class ="empty-row"></div> 


<div class="jumbotron text-center">
<h1>자유 게시판</h1>
</div>

<div class ="empty-row"></div>

<div class="table-responsive col-md-offset-2 col-md-8">
<table class="table table-stripe">
	<thead>
		<tr>
			<th>번호</th>
			<th width="45%">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			
		</tr>
		<!-- 공지사항만 출력 -->
		<c:forEach var="bdto"  items="${pm.notice}">
		<tr align="center" bgcolor="yellow">
			<td>공지</td>
			<td align="left">
				<c:if test="${not empty bdto.head}"><!-- bdto.head != null -->
					[${bdto.head}]
				</c:if>
				${bdto.subject}
				<c:if test="${bdto.reply > 0}">
				[${bdto.reply}]
				</c:if>
				
				<!-- 첨부파일이 있다면 이미지를 표시 -->
				<c:if test="${bdto.filesize > 0}">
					<img src="${pageContext.request.contextPath}/res/image/file.PNG" width="15" height="15">
				</c:if>
			</td>
			<td>${bdto.writer}</td>
			<td>${bdto.auto}</td>
			<td>${bdto.read}</td>
			
		</tr>
		</c:forEach>
	</thead>
	<tbody align="center">
		<c:forEach var="bdto" items="${pm.list}">
		<tr>
			<td>${bdto.no}</td>
			<td align="left">
			<c:choose>
			<c:when test="${bdto.isBlind()}">
				<font color="gray">블라인드 처리된 글입니다</font>		
			</c:when>
			<c:otherwise>
				<!-- 띄어쓰기 처리 -->
				<c:forEach var="i" begin="1" end="${bdto.depth}" step="1">
					&nbsp;&nbsp;&nbsp;
				</c:forEach>
				
				<c:if test="${bdto.depth > 0}">
					<img src="${pageContext.request.contextPath}/res/image/reply.jpg" width="15" height="15">
				</c:if>
			
				<c:if test="${bdto.head != null}">
					[${bdto.head}]
				</c:if>
				<a href="content.do?no=${bdto.no}">
				${bdto.subject}
				
				<c:if test="${bdto.reply > 0}">
				[${bdto.reply}]
				</c:if>
				</a>
				
				<!-- 첨부파일이 있다면 이미지를 표시 -->
				<c:if test="${bdto.filesize > 0}">
					<img src="${pageContext.request.contextPath}/res/image/file.PNG" width="15" height="15">
				</c:if>
			</c:otherwise>
			</c:choose>
			</td>
			<td>${bdto.writer}</td>
			<td>${bdto.auto}</td>
			<td>${bdto.read}</td>
			
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
</div>


<!-- 페이징 부분 -->
<div class="col-md-offset-4 col-md-4 text-center">
	<!-- 페이징 링크 영역을 ul, li로 구현 -->
	<ul class="pagination pagination-lg">
		<c:if test="${!pm.isFirst()}">
			<li><a href="#">&lt;</a></li>
		</c:if>

		<c:forEach var="i" begin="${pm.startPage}" end="${pm.finishPage}">
			<c:choose>
				<c:when test="${pm.pageNo == i}">
					<li class="active">
						<a href="list.do?page=${i}${pm.queryString}">
							${i}
						</a>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="list.do?page=${i}${pm.queryString}">
							${i}
						</a>
					</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:if test="${!pm.isLast()}">
			<li><a href="#">&gt;</a></li>
		</c:if>
	</ul>
</div>

<div class ="empty-row"></div> 
<!-- 검색 부분 -->
<div class="table-responsive col-md-offset-2 col-md-8 text-center">
<!-- 검색어 입력창 -->
<form action="list.do" method="get">
	<table class="table table-hover">
		<tr>
			<td>
				<select class="form-input" name="type">
					<c:choose>
					<c:when test="${pm.isTitleMode()}">
					<option value="subject||content">제목+내용</option>
					<option value="subject" selected>제목</option>
					<option value="writer">작성자</option>
					</c:when>
					<c:when test="${pm.isWriterMode()}">
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
			</td>
			<td><input class="form-input" type="search" name="keyword" value="${pm.keyword}" placeholder="검색어" required></td>
			<td><input class="form-input" type="submit" value="검색"></td>
		</tr>
	</table>
</form>
</div>


<div class ="empty-row"></div>
<div class ="empty-row"></div> 

<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>








