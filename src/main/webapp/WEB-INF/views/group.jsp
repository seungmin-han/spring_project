<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<title>내 그룹</title>
</head>
<body>
	<c:if test="${empty sessionScope.memberNickName}">
		<script>
			alert("로그인 후 이용 가능한 서비스입니다.");
			history.back();
		</script>
	</c:if>
	<c:if test="${!empty msg}">
		<script type="text/javascript">alert("${msg}");</script>
	</c:if>
	
	<c:import url="header.jsp"></c:import>
	<h1>내 그룹</h1>
	<a href="/group/create">그룹 만들기</a>
	<c:if test="${empty groupList}">
		<p>가입된 그룹이 없습니다.</p>
	</c:if>
	<c:if test="${!empty groupList}">
		<table border=1>
			<thead>
				<tr>
					<th>그룹이미지</th>
					<th>그룹이름</th>
					<th>그룹 텍스트</th>
					<th>태그</th>
					<th>회원 수</th>
					<th>등급</th>
					<th>가입 상태</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${groupList}" var="groupData">
				<tr data-groupSeq="${groupData.get('groupSeq') }"> 
					<td><img src="/resources/images/${groupData.get('groupImageName') }" height="100"> </td>
					<td><a href="/group/${groupData.get('groupSeq') }">${groupData.get('groupName')}</a></td>
					<td>${groupData.get('groupText')}</td>
					<td>${groupData.get('groupTags')}</td>
					<td>${groupData.get('memberCount')}명</td>
					<td>${groupData.get('groupAdminNyText')}</td>
					<td>${groupData.get('joinStatusCdText')}
					<c:if test="${groupData.get('joinStatusCd') eq 'W'}">
					
						<form action="/group/leave" method='post'>
							<input type="hidden" name="joinStatusSeq" value="${groupData.joinStatusSeq }">
							<button type="submit">가입취소</button>
						</form>
					</c:if>
					<c:if test="${groupData.get('joinStatusCd') eq 'Y' and groupData.get('groupAdminNy') ne 1}">
					
						<form action="/group/leave" method='post'>
							<input type="hidden" name="joinStatusSeq" value="${groupData.joinStatusSeq }">
							<button type="submit">탈퇴하기</button>
						</form>
					</c:if>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>
