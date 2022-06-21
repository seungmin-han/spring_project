<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<title>그룹 페이지</title>
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<h1>${result.get('groupName') }</h1>
	
	<table border=1>
		<tr>
			<td colspan="2"><img src="/resources/images/${result.get('groupImageName') }" height="100"></td>
		</tr>
		<tr>
			<th>그룹 텍스트</th>
			<td>${result.get('groupText') }</td>
		</tr>
		<tr>
			<th>그룹 태그</th>
			<td><span>${result.get('groupTags')}</span></td>			
		</tr>
		<tr>
			<th>인원</th>
			<td><span>${result.get('memberCount')}명</span></td>		
		</tr>
		<tr>
			<th>관리자여부</th>
			<td><span>${result.get('groupAdminNyText')}</span></td>		
		</tr>
	</table>
	
	<c:if test="${result.get('memberSeq') eq sessionScope.memberSeq }">
		<a href="/group/update/${result.get('groupSeq') }">수정하기</a>
	</c:if>
	<form action="/group/${result.get('groupSeq')} " method="get">
		<input type="text" name="q" placeholder="검색할 제목을 입력">
		<input type="hidden" name="page" value="${pageInfo.currentPage }">
		<button type="submit">검색</button>
	</form>
	<c:if test="${empty postList}">
		<p>게시글이 존재하지 않습니다.</p>
	</c:if>
	
	<c:if test="${!empty postList}">
		<table border=1>
			<thead>
				<tr>
					<th>게시판</th>
					<th>말머리</th>
					<th>제목</th>
					<th>작성일</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>좋아요</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${postList}" var="post">
				<tr data-groupSeq="${post.postSeq }"> 
					<td>${post.boardName} </td>
					<td>${post.subjectName}</td>
					<td><a href="/post/${post.postSeq }?page=${pageInfo.currentPage}">${post.postTitle}</a></td>
					<td>${post.postDate}</td>
					<td>${post.memberNickName }</td>
					<td>${post.postView}</td>
					<td>${post.postLike}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="pager">
			<c:if test="${pageInfo.currentBlock ne 1 }">
				<a href="/group/${result.get('groupSeq') }?page=${pageInfo.startPage-1} ">&lt;</a>
			</c:if>
			<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
				<a href="/group/${result.get('groupSeq') }?page=${i }">${i }</a>
			</c:forEach>
			<c:if test="${pageInfo.currentBlock ne pageInfo.totalBlock }">
				<a href="/group/${result.get('groupSeq') }?page=${pageInfo.endPage+1} ">&gt;</a>
			</c:if>
		</div>
	</c:if>
	<a href="/post/create">글쓰기</a>
</body>
</html>
