<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/stylesheet/common.css">
<link rel="stylesheet" href="/resources/stylesheet/content.css">
<title>로그인</title>
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<section class="wrap loginWrap">
		<h1>로그인</h1>
		<c:if test="${!empty msg}">
			<c:out value="<script>alert('${msg }');</script>" escapeXml="false"></c:out>
			<c:remove var="msg" scope="page" />
		</c:if>
		<div>
			<form class="formWrap loginFormWrap" action="/member/login" method="post">
				
				<div><label for="memberId">아이디</label></div>
				<div><input class="textField formControl" type="text" name="memberId" required/></div>
				<div><label for="password">비밀번호</label></div>
				<div><input class="textField formControl" type="password" name="memberPw" required/></div>
				<div><input class="btn formControl" type="submit" value="로그인"></div>
				<div><a class="btn formControl" href="/member/register">회원가입</a></div>
			</form>
			
		</div>
	</section>
</body>
</html>


