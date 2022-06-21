<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<title>그룹 탐색</title>
	<script
  	src="https://code.jquery.com/jquery-3.6.0.js"
  	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  	crossorigin="anonymous"></script>
  	<script type="text/javascript" src="/resources/script/group.js"></script>
</head>
<body>
	<c:if test="${empty sessionScope.memberNickName}">
		<script>
			alert("로그인 후 이용 가능한 서비스입니다.");
			history.back();
		</script>
	</c:if>
	
	<c:import url="header.jsp"></c:import>
	<h1>그룹 탐색</h1>
	<form action="/group/search" method="get">
		<input type="text" name="q" placeholder="키워드를 입력하세요">
		<button type="submit">검색</button>
	</form>
	<h2>추천 그룹</h2>
	<c:if test="${empty recommendGroupList}">
		<p>추천 그룹이 없습니다.</p>
	</c:if>
	<c:if test="${!empty recommendGroupList}">
		<table border=1>
			<thead>
				<tr>
					<th>그룹이미지</th>
					<th>그룹이름</th>
					<th>그룹 텍스트</th>
					<th>회원 수</th>
					<th>태그</th>
					<th>버튼</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${recommendGroupList }" var="recommendGroupData" >
				<tr data-groupSeq="${recommendGroupData.get('groupSeq') }"> 
					<td><img src="/resources/images/${recommendGroupData.get('groupImageName') }" height="100"> </td>
					<td><a href="/group/${recommendGroupData.get('groupSeq') }">${recommendGroupData.get('groupName')}</a></td>
					<td>${recommendGroupData.get('groupText')}</td>
					<td>${recommendGroupData.get('memberCount')}명</td>
					<td>${recommendGroupData.get('groupTags')}</td>
					<c:choose>
						<c:when test="${empty recommendGroupData.get('joinStatusCd') 
							or recommendGroupData.get('joinStatusCd') eq 'N'}">
							<td><button onclick="joinGroup(${recommendGroupData.get('groupSeq')})">가입하기</button></td>		
						</c:when>
						<c:otherwise>
							<td>${recommendGroupData.get('joinStatusCdText') }</td>
						</c:otherwise>
					</c:choose>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<h2>전체 그룹</h2>
	<c:if test="${empty groupList}">
		<p>검색된 그룹이 없습니다.</p>
	</c:if>
	<c:if test="${!empty groupList}">
		<table border=1>
			<thead>
				<tr>
					<th>그룹이미지</th>
					<th>그룹이름</th>
					<th>그룹 텍스트</th>
					<th>회원 수</th>
					<th>태그</th>
					<th>버튼</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${groupList}" var="groupData">
				<tr data-groupSeq="${groupData.get('groupSeq') }"> 
					<td><img src="/resources/images/${groupData.get('groupImageName') }" height="100"> </td>
					<td><a href="/group/${groupData.get('groupSeq') }">${groupData.get('groupName')}</a></td>
					
					<td>${groupData.get('groupText')}</td><td>${groupData.get('memberCount')}명</td>
					<td>${groupData.get('groupTags')}</td>
					<c:choose>
						<c:when test="${empty groupData.get('joinStatusCd') 
							or groupData.get('joinStatusCd') eq 'N'}">
							<td><button onclick="joinGroup(${groupData.get('groupSeq')})">가입하기</button></td>		
						</c:when>
						<c:otherwise>
							<td>${groupData.get('joinStatusCdText') }</td>
						</c:otherwise>
					</c:choose>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	
</body>
</html>
