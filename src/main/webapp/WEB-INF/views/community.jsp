<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<title>내 그룹</title>
	<link rel="stylesheet" href="/resources/stylesheet/common.css">
	<link rel="stylesheet" href="/resources/stylesheet/content.css">
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<section class="wrap communityWrap">
		<h1>전체글보기</h1>
		<form action="/post/" method="get">
			<input class="textField formControl" type="text" name="q" placeholder="검색할 제목을 입력">
			<input type="hidden" name="page" value="${pageInfo.currentPage }">
			<button class="btn" type="submit">검색</button>
		</form>
		<c:if test="${empty postList}">
			<p align="center">게시글이 존재하지 않습니다.</p>
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
					<a href="/post/?page=${pageInfo.startPage-1} ">&lt;</a>
				</c:if>
				<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
					<a href="/post/?page=${i }">${i }</a>
				</c:forEach>
				<c:if test="${pageInfo.currentBlock ne pageInfo.totalBlock }">
					<a href="/post/?page=${pageInfo.endPage+1} ">&gt;</a>
				</c:if>
			</div>
		</c:if>
		<a class="btn" href="/post/create">글쓰기</a>
	</section>
</body>
</html>
