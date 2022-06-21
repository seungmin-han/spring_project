<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
	<ul>
		<li>
			<a href="/">메인</a>
		</li>
		<li>
			<a href="/group/">그룹</a>
			<ul>
				<li><a href="/group/">내 그룹</a></li>
				<li><a href="/group/search">그룹 탐색</a></li>
			</ul>
		</li>
		<li>
			<a href="/post/">커뮤니티</a>
		</li>
	</ul>
	<ul>
		<c:if test="${!empty sessionScope.memberNickName}">
			<li><a href="/member/update">${sessionScope.memberNickName}님</a></li>
			<li><a href="/member/logout">로그아웃</a></li>				
		</c:if>
		<c:if test="${empty sessionScope.memberNickName}">
			<li><a href="/member/login">로그인</a></li>				
		</c:if>
	</ul>
</header>

