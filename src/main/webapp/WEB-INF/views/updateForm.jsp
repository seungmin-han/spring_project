<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 정보 수정</title>
	<script
  	src="https://code.jquery.com/jquery-3.6.0.js"
  	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  	crossorigin="anonymous"></script>
  	<script type="text/javascript" src="/resources/script/memberTag.js"></script>
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<form action="/member/update" method="post">
		<div>
			<label>아이디</label>
			<input type="text" name="memberId" value="${memberId}" disabled="disabled">
		</div>
		<div>
			<label>닉네임</label>
			<input type="text" name="memberNickName" value="${memberNickName }">
		</div>
		<div>
			<label>태그</label>
			<input type="text" id="memberTagName" name="memberTagName"><button id="createTagBtn" type="button">추가</button>
			<input type="hidden" id="memberSeq" name="memberSeq" value="${sessionScope.memberSeq }">
			<div id="tags">
				
			</div>
		</div>
		<div>
			<label>기존 비밀번호</label>
			<input type="password" name="oldMemberPw">
		</div>
		<div>
			<label>새 비밀번호</label>
			<input type="password" name="memberPw">
		</div>
		<div>
			<label>새 비밀번호확인</label>
			<input type="password" name="memberPwChk">
		</div>
		<input type="submit" value="정보 변경">
	</form>
	<script type="text/javascript">
		let memberSeq = ${memberSeq};
		getTags(memberSeq);
		
		$("#createTagBtn").on("click", function() {
			let memberSeq = document.querySelector("#memberSeq").value;
			let memberTagName = document.querySelector("#memberTagName").value;
			createTag(memberSeq,memberTagName);
		});
		
		$(document).on("click", ".deleteBtn", function() {
			let memberTagSeq = $(this).parent().data("membertagseq");
			deleteTag(memberSeq, memberTagSeq);
		});
	</script>
</body>
</html>