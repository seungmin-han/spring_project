<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>그룹 정보 수정</title>
	<script
  	src="https://code.jquery.com/jquery-3.6.0.js"
  	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  	crossorigin="anonymous"></script>
  	<script type="text/javascript" src="/resources/script/groupTag.js"></script>
  	<script type="text/javascript" src="/resources/script/group.js"></script>
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<form action="/group/update" method="post" name="form">
		<div>
			<label>그룹 이름</label>
			<input type="text" name="groupName" value="${groupData.get('groupName')}" disabled="disabled">
		</div>
		<div>
			<label>그룹 내용</label>
			<input type="text" name="groupText" value="${groupData.get('groupText')}">
		</div>
		<div>
			<label>자동 승인 여부</label><br>			
			수동 가입<input type="radio" name="groupJoinAcceptNy" value="0">
			자동 가입<input type="radio" name="groupJoinAcceptNy" value="1">	
		</div>
		<div>
			<label>태그</label>
			<input type="text" id="groupTagName" name="groupTagName"><button id="createTagBtn" type="button">추가</button>
			<input type="hidden" id="groupSeq" name="groupSeq" value="${groupData.get('groupSeq') }">
			<div id="tags">
					
			</div>
		</div>
		<input type="submit" value="정보 변경">
	</form>
	<table border=1>
		<tr>
			<th>닉네임</th>
			<th colspan="2">설정</th>
		</tr>
		<c:forEach items="${groupMemberList}" var="groupData">
			<tr data-joinStatusSeq="${groupData.get('joinStatusSeq')}">
				<td>${groupData.get("memberNickName")}</td>
				<c:choose>
					<c:when test="${groupData.get('joinStatusCd') eq 'W' }">
						<td><button class="acceptBtn">수락</button></td>
						<td><button class="kickBtn">거절</button></td>
					</c:when>
					<c:otherwise>
						<c:if test="${groupData.get('memberSeq') ne sessionScope.memberSeq}">
							<td data-memberSeq="${groupData.get('memberSeq') }">
								<button class="changeMasterBtn">관리자위임</button>
							</td>
							<td><button class="kickBtn">추방</button></td>
						</c:if>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
		let index = (${groupData.get('groupJoinAcceptNy')})?1:0;
		form.groupJoinAcceptNy[index].checked = true;
		let groupSeq = ${groupData.get('groupSeq')};
		getTags(groupSeq);
		
		$("#createTagBtn").on("click", function() {
			let groupSeq = document.querySelector("#groupSeq").value;
			let groupTagName = document.querySelector("#groupTagName").value;
			createTag(groupSeq ,groupTagName);
		});
		
		$(document).on("click", ".deleteBtn", function() {
			let groupTagSeq = $(this).parent().data("grouptagseq");
			deleteTag(groupSeq, groupTagSeq);	
		});
		
		$(document).on("click", ".kickBtn", function() {
			let joinStatusSeq = $(this).parent().parent().data("joinstatusseq");
			console.log(joinStatusSeq, groupSeq);
			kickMember(joinStatusSeq, groupSeq);
		});
		
		$(document).on("click", ".acceptBtn", function() {
			let joinStatusSeq = $(this).parent().parent().data("joinstatusseq");
			console.log(joinStatusSeq, groupSeq);
			acceptMember(joinStatusSeq, groupSeq);
		});
		
		$(document).on("click", ".changeMasterBtn", function() {
			let memberSeq = $(this).parent().data("memberseq");
			changeMaster(memberSeq, groupSeq);
		});
	</script>
</body>
</html>