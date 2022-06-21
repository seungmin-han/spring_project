<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그룹 만들기</title>
<script
  	src="https://code.jquery.com/jquery-3.6.0.js"
  	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  	crossorigin="anonymous"></script>
<script type="text/javascript" src="/resources/script/groupTag.js"></script>
</head>
<body>
	<form action="/group/create" method="post" enctype="multipart/form-data">
		<input name="groupImage" type="file" accept="image/*" >
		<input name="groupName" type="text">
		<input name="groupText" type="text">
		<input name="groupTagName" type="text"><button id="createTagBtn" type="button">추가</button>
		<input type="hidden" id="memberSeq" name="memberSeq" value="${sessionScope.memberSeq }">
		<div id="tags"></div>
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