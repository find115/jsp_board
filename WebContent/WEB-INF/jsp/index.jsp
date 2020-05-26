<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- header.jsp를 이 위치에 가져다 놓겠다(동적) -->
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class="empty-row"></div>
<div class="empty-row"></div>

<!-- 중단 영역 -->
<div class="jumbotron text-center">
    <h1>환영합니다!</h1>
    <p>
     	해위~~
    </p>
</div>
            
<div class="row text-center">
   <div class="col-md-offset-2 col-md-8">
      <img src="${pageContext.request.contextPath }/res/image/main_bg.jpg" width="100%">
   </div>
</div>

<div class="empty-row"></div>
<div class="empty-row"></div>     
       

<!-- footer.jsp를 이 위치에 가져다 놓겠다(동적) -->
<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>