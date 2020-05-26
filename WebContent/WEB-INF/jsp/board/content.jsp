<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class ="empty-row"></div>
<div class ="empty-row"></div> 



<div class="jumbotron text-center">
	<h1>게시글 보기</h1>
</div>
<div class ="empty-row"></div>

<div class="table-responsive col-md-offset-2 col-md-8">
<table class="table table-stripe">
	<tbody align="left">
		<tr>
			<th align="left" >
				<c:if test="${bdto.head != null }">
					[${bdto.head }]
				</c:if>
				${bdto.subject }
			</th>
		</tr>
		<tr>
			<th align="left" >${bdto.writer }</th>
		</tr>
		<tr>
			<th height="250" valign="top" align="left" >
				<!-- 첨부파일이 있다면 표시 -->
				<c:if test="${bdto.getFilesize() > 0 }">
					<div align="right">
	<%-- 					<a href="<%=request.getContextPath()%>/board/file/<%=bdto.getServerfile()%>"> --%>
						<a href="download.do?no=${bdto.no }">
							${bdto.userfile }(${bdto.filesize } byte)
						</a>
					</div>
				</c:if>
				<hr class="row">
				<!-- xmp는 있는 그대로 출력하는 영역 -->
				<xmp>${bdto.content }</xmp>
			</th>
		</tr>
		<tr>
			<th>댓글 ${bdto.reply } 조회수 ${bdto.read }</th>
		</tr>
		<!-- 댓글 목록 표시란 -->
		<c:if test="${replyList.size() > 0 }">
		<tr>
			<td><hr class="row"></td>
		</tr>
		<tr>
			<td align="center">
				
				<div class="table-responsive row">
				<table class="table table-stripe">
					<tbody>
						<c:forEach var="rdto" items="${replyList}">
						<tr>
							<th width="10%"><img src="http://placehold.it/80x80"></th>
							<td>
								${rdto.writer }
								
								<c:if test="${rdto.writer eq bdto.writer }">
									<font color="red">(작성자)</font>
								</c:if>
								${rdto.reg }
								-> 답글
							<br>
								${rdto.content }
							</td>
							<td width="10%">
								<c:if test="${rdto.writer eq sessionScope.login }">
									수정 | 
								</c:if>
								<c:if test="${rdto.writer eq sessionScope.login || sessionScope.power eq '관리자' }">
									<a href="reply_delete.do?no=${rdto.no }">삭제</a>
								</c:if>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</td>
		</tr>
		</c:if>
		<!-- 댓글 입력창 표시란 -->
		<tr>
			<th>
				<form action="reply_insert.do" method="post">
					<input type="hidden" name="origin" value="${bdto.no }">
					<input type="hidden" name="writer" value="${sessionScope.login }">
					<textarea name="content" required placeholder="댓글 입력" rows="4" cols="120"></textarea>
					<input type="submit" value="등록">
				</form>
			</th>
		</tr>
		<tr>
			<th>
				<!-- 글쓰기와 답글쓰기를 구분하기 위하여 상태값을 추가 -->
				<a href="write.do">글쓰기</a>
				<a href="write.do?gno=${bdto.gno }&parent=${bdto.no }&depth=${bdto.depth+1}">답글쓰기</a>
				<c:if test="${sessionScope.power eq '관리자' || sessionScope.login eq bdto.writer }">
					<!-- 본인 글일 경우에만 수정과 삭제를 표시 -->
					<a href="edit.do?no=${bdto.no }">수정하기</a>
					<a href="delete.do?no=${bdto.no }">삭제하기</a>
				</c:if>
				<a href="list.do">목록으로</a>
			</th>
		</tr>
	</tbody>
</table>

</div>


<div class ="empty-row"></div>
<div class ="empty-row"></div>

<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
