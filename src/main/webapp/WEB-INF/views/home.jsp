<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<title>메인페이지</title>
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<div>
	<h2>오늘의 주제</h2>
	<hr>
	<p align="right"><a href="/post/">더보기</a></p>
	<c:if test="${empty todayTopicList}">
		<p>게시글이 없습니다.</p>
	</c:if>
	
	<c:if test="${!empty todayTopicList}">
		<c:forEach items="${todayTopicList}" var="post">
		<div style="width:30%; border:1px solid #000;"> 
			<p><a href="/post/${post.postSeq }">${post.postTitle}</a></p>
			<hr>
			<p>${post.memberNickName } | ${post.postDate}</p>
		</div>
		</c:forEach>
	</c:if>
	</div>
	
	
	<div id="popular">
	<h2>인기글</h2>
	<hr>
	<p align="right"><a href="/post/">더보기</a></p>
	<c:if test="${empty popularPostList}">
		<p>게시글이 없습니다.</p>
	</c:if>
	
	<c:if test="${!empty popularPostList}">
		
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
				<c:forEach items="${popularPostList}" var="post">
				<tr data-groupSeq="${post.postSeq }"> 
					<td>${post.boardName} </td>
					<td>${post.subjectName}</td>
					<td><a href="/post/${post.postSeq }">${post.postTitle}</a></td>
					<td>${post.postDate}</td>
					<td>${post.memberNickName }</td>
					<td>${post.postView}</td>
					<td>${post.postLike}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	</div>
</body>
</html>
