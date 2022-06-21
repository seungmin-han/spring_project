<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<script
  src="https://code.jquery.com/jquery-3.6.0.js"
  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  crossorigin="anonymous"></script>
<script type="text/javascript" src="/resources/externalLib/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/resources/script/subject.js"></script>
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<form action="/post/update" method="post" enctype="multipart/form-data" style="width:70%">
		<div>
			<label>게시판/말머리</label>
			<select name="boardSeq" required>
				<option value="">게시판 선택</option>
				<c:forEach items="${boardList }" var="boardItem">
					<c:if test="${boardItem.boardSeq eq postData.boardSeq }">
						<option value="${boardItem.boardSeq} " selected>${boardItem.boardName }</option>
					</c:if>
					<c:if test="${boardItem.boardSeq ne postData.boardSeq }">
						<option value="${boardItem.boardSeq} ">${boardItem.boardName }</option>
					</c:if>
				</c:forEach>
			</select>
			<select name="subjectSeq">
				<option value="0">말머리 없음</option>
				
			</select>
		</div>
		<div>
			<label>공개 여부</label><br>
			그룹 공개<input type="radio" name="postPublicNy" value="0">
			전체 공개<input type="radio" name="postPublicNy" value="1">	
		</div>
		<div id="group">
			<label>그룹 선택</label>
			<select name="groupSeq">
				<option value="0">그룹 선택</option>
				<c:forEach items="${groupList }" var="groupItem">
					<c:if test="${groupItem.groupSeq eq postData.groupSeq }">
						<option value="${groupItem.groupSeq} " selected>${groupItem.groupName }</option>
					</c:if>
					<c:if test="${groupItem.groupSeq ne postData.groupSeq }">
						<option value="${groupItem.groupSeq} ">${groupItem.groupName }</option>
					</c:if>
					
				</c:forEach>
			</select>
		</div>
		<div>
			<label>제목</label>
			<input id="postTitle" name="postTitle" type="text" value="${postData.postTitle }" required>
		</div>
		<div>
			<label>작성자</label>
			<input name="memberNickName" type="text" value="${memberNickName }" readonly >
		</div>
		<div>
			<label>내용</label>
			<textarea id="postContent" name="postContent" required></textarea>
		</div>
		<input type="hidden" name="memberSeq" value="${memberSeq}">
		<input type="hidden" name="postSeq" value="${postData.postSeq}">
		<input type="hidden" name="page" value="${page }">
		<input type="hidden" name="q" value="${q }">
		<button type='submit'>수정하기</button>
	</form>
	
	<script type="text/javascript">
		let editor = CKEDITOR.replace( 
            'postContent'
            ,{ 
                width : "100%"
                ,height : "500px"
                ,enterMode : 2
                , extraPlugins: 'image2,uploadimage'
                , filebrowserImageUploadUrl: '/post/image/upload'
                , uploadUrl: "/post/image/upload"
               	//,extraPlugins: 'easyimage'
            }
        );
		
		let sdata = `${postData.postContent}`;
		editor.setData(sdata);
		
		$("select[name=boardSeq]").on("change",function()
		{			
			$(this).parent().find("select[name=subjectSeq]").children("option:gt(0)").remove();
			let boardSeq = $(this).find("option:selected").val();	
			
			if(subjectSeq == "게시판 선택") return;
			
			createSubjectList(boardSeq);	
		})
		
		function createSubjectList(boardSeq) {
			let subjectList = getSubjectList(boardSeq);
			for(let subject of subjectList) {
				$("select[name=boardSeq]").parent().find("select[name=subjectSeq]")
				.append("<option value="+subject['subjectSeq']+">"+subject['subjectName']+"</option>");	
			}
		}
		
		$("input[name=postPublicNy]").on("change", function() 
		{
			toggleBox($(this).val());	
		});
		
		let postPublicNy = ${postData.postPublicNy};
		if(postPublicNy == 1) {
			toggleBox(postPublicNy);
			$("input[name=postPublicNy][value=1]").attr("checked", "ckecked");
		} else {
			$("input[name=postPublicNy][value=0]").attr("checked", "ckecked");	
			
		}
		
		function toggleBox(value) {
			if(value == 1)
			{
				$("#group").hide();
				$("select[name=groupSeq]").children().eq(0).attr("selected", "selected");
			} 
			else 
			{
				$("#group").show();
				$("select[name=groupSeq]").children().removeAttr("selected");	
			}
		}
		
		$(document).ready(function() {
			let boardSeq = $("select[name=boardSeq]").find("option:selected").val();	
			console.log(boardSeq);
			createSubjectList(boardSeq);
			
			let subjectSeq = ("${postData.subjectSeq }" == "") ? 0 : "${postData.subjectSeq }";
			$("select[name=subjectSeq]").children("[value="+subjectSeq+"]").eq(0).attr("selected", "selected");	
		})
		
		
	</script>
</body>
</html>