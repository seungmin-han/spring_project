<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
<script
  src="https://code.jquery.com/jquery-3.6.0.js"
  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  crossorigin="anonymous"></script>
<script type="text/javascript" src="/resources/externalLib/ckeditor/ckeditor.js"></script>
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<form action="/post/create" method="post" enctype="multipart/form-data" style="width:70%">
		<div>
			<label>게시판/말머리</label>
			<select name="boardSeq" disabled>
				<option value="${postData.boardSeq }" selected>${postData.boardName }</option>
			</select>
			<select name="subjectSeq" disabled> 
				<c:if test="${empty postData.subjectName }">
					<option value="0" selected>말머리 없음</option>
				</c:if>
				<c:if test="${not empty postData.subjectName }">
					<option value="${postData.subjectSeq }" selected>${postData.subjectName }</option>
				</c:if>
			</select>
		</div>
		<div>
			<label>공개 여부:</label>
			<c:if test="${postData.postPublicNy eq 1 }">
				<span>전체 공개</span>
			</c:if>
			<c:if test="${postData.postPublicNy eq 0 }">
				<span>그룹 공개(${postData.groupName })</span>
			</c:if>
						
		</div>
		<div>
			<label>제목</label>
			<input id="postTitle" name="postTitle" type="text" value="${postData.postTitle }" readOnly>
		</div>
		<div>
			<label>작성자</label>
			<input name="memberNickName" type="text" value="${postData.memberNickName }" readonly >
		</div>
		<div>
			<label>내용</label>
			<textarea id="postContent" name="postContent"></textarea>
		</div>
		
		<input type="hidden" name="memberSeq" value="${memberSeq }">
		<button type="button" onclick="history.back()">돌아가기</button>
	</form>
		<c:if test="${not empty sessionScope.memberSeq }">
			<c:if test="${sessionScope.memberSeq eq postData.memberSeq }">
				<button type="button" onclick="location.href='/post/update/${postData.postSeq }?page=${page}&q=${q }'">수정하기</button>
				<form method="post" action="/post/delete">
					<input type="hidden" name="page" value="${page }">
					<input type="hidden" name="postSeq" value="${postData.postSeq}">
					<input type="hidden" name="q" value="${q}">
					<button type="submit">삭제하기</button>
				</form>
				
			</c:if>
		</c:if>
	
	
	<script type="text/javascript">
		let editor = CKEDITOR.replace( 
            'postContent'
            
            ,{ 
                width : "100%"
                ,height : "500px"
                , readOnly : true
                ,enterMode : 2
                , extraPlugins: 'image2,uploadimage'
                , filebrowserImageUploadUrl: '/post/image/upload'
                , uploadUrl: "/post/image/upload"
               	//,extraPlugins: 'easyimage'
            }
        );
		let sdata = `${postData.postContent}`;
		editor.setData(sdata);
	</script>
</body>
</html>