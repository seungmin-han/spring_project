<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<c:if test="${!empty msg}">
		<c:out value="<script>alert('${msg }');</script>" escapeXml="false"></c:out>
		<c:remove var="msg" scope="page" />
	</c:if>
	
	<form action="/member/login" method="post">
		<input type="text" name="memberId" required/>
		<input type="password" name="memberPw" required/>
		<input type="submit" value="로그인">
	</form>
	<a href="/member/register">회원가입</a>
	
</body>
</html>


