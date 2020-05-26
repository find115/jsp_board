<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>홈페이지</title>
        <meta name ="veiwport" content="width=device-width; init-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/all.min.css">
        <style>
        	.table th{
                text-align: center;
            }
            .empty-row{
                height:25px;
            }
        </style>
        <script src="${pageContext.request.contextPath}/res/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/res/js/bootstrap.min.js"></script>
    </head>
    <body>
        <!-- 상단(메뉴) 영역 -->
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <!-- 브랜드 영역 -->
                <div class="navbar-header">
                    <!-- 햄버거 버튼 -->
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#my-menu">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/index.do">홈페이지</a>
                </div>
                
				<c:choose>
					<c:when test="${empty power}">
		                <!--접었다 펼쳤다 할 영역(Collapse-Area) -->
		                <div class="collapse navbar-collapse" id="my-menu">
		                    <!-- 왼쪽 메뉴 영역(기본값) -->
		                    <ul class="nav navbar-nav">
		                        <li><a href="${pageContext.request.contextPath}/board/list.do">게시판</a></li>
		                        <li><a href="${pageContext.request.contextPath}/event/home.do">이벤트</a></li>
		                    </ul>
		
		                    <!-- 오른쪽 메뉴 영역 -->
		                    <ul class="nav navbar-nav navbar-right">
		                        <li><a href="${pageContext.request.contextPath}/member/login.do">로그인</a></li>
		                        <li><a href="${pageContext.request.contextPath}/member/regist.do">회원가입</a></li>
		                    </ul>
		                </div>
		        	</c:when>
		        	<c:otherwise>
		        		<!-- 왼쪽 메뉴 영역(기본값) -->
		                    <ul class="nav navbar-nav">
		                        <li><a href="${pageContext.request.contextPath}/board/list.do">게시판</a></li>
		                        <li><a href="${pageContext.request.contextPath}/event/home.do">이벤트</a></li>
		                        <c:if test="${power == '관리자'}"> 
		                        	<li>
										<a href="${pageContext.request.contextPath}/admin/home.do">[관리자메뉴]</a>
									</li>
								</c:if>
		                    </ul>
		
		                    <!-- 오른쪽 메뉴 영역 -->
		                    <ul class="nav navbar-nav navbar-right">
		                        <li><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
		                        <li><a href="${pageContext.request.contextPath}/member/information.do">내정보</a></li>
		                    </ul>
		        	</c:otherwise>
                </c:choose>
            </div>
        </nav>
        
        <div class="empty-row"></div>
        <div class="empty-row"></div>
        <div class="empty-row"></div>
        
        <div class="container-fluid">

						
