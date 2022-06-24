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
<link rel="stylesheet" href="/resources/stylesheet/common.css">
<link rel="stylesheet" href="/resources/stylesheet/content.css">
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<section class="wrap postWrap">
		<form action="/post/create" method="post" enctype="multipart/form-data">
			<div class="formWrap">
				<div>
					<label>게시판/말머리</label>
					<select class="formControl" name="boardSeq" required>
						<option class="formControl" value="">게시판 선택</option>
						<c:forEach items="${boardList }" var="boardItem">
							<option value="${boardItem.boardSeq} ">${boardItem.boardName }</option>
						</c:forEach>
					</select>
					<select class="formControl" name="subjectSeq">
						<option value="0">말머리 없음</option>
					</select>
				</div>
				<div>
					<label>공개 여부</label>
					<div>그룹 공개 &nbsp;<input type="radio" name="postPublicNy" value="0" checked></div>
					<div>전체 공개 &nbsp;<input type="radio" name="postPublicNy" value="1"></div>	
				</div>
				<div id="group">
					<label>그룹 선택</label>
					<select class="formControl" name="groupSeq">
						<option value="0">그룹 선택</option>
						<c:forEach items="${groupList }" var="groupItem">
							<option value="${groupItem.groupSeq} ">${groupItem.groupName }</option>
						</c:forEach>
					</select>
				</div>
				<div>
					<label>작성자</label>
					<input class="formControl" name="memberNickName" type="text" value="${memberNickName }" readonly >
				</div>
				<div>
					<label>제목</label>
					<input class="formControl title" id="postTitle" name="postTitle" type="text" required>
				</div>
				<div>
					<textarea id="postContent" name="postContent" required></textarea>
				</div>

				<input type="hidden" name="memberSeq" value="${memberSeq }">
				<div class="buttonWrap">
					<button class="btn" type="button" onclick="history.back()">돌아가기</button>
					<button class="btn" type='submit'>게시글 작성</button>
				</div>
				
			</div>
		</form>
	</section>
	<script type="text/javascript">
		CKEDITOR.replace( 
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
		
		
		$("select[name=boardSeq]").on("change",function()
		{			
			$(this).parent().find("select[name=subjectSeq]").children("option:gt(0)").remove();
			let subjectSeq = $(this).find("option:selected").val();	
			
			if(subjectSeq == "게시판 선택") return;
			
			let subjectList = getSubjectList(subjectSeq);
			for(let subject of subjectList) {
				$(this).parent().find("select[name=subjectSeq]")
				.append("<option value="+subject['subjectSeq']+">"+subject['subjectName']+"</option>");	
			}
		})
		
		$("input[name=postPublicNy]").on("change", function() 
		{
			if($(this).val() == 1)
			{
				$("#group").hide();
				$("select[name=groupSeq]").children().eq(0).attr("selected", "selected");
			} 
			else 
			{
				$("#group").show();
				$("select[name=groupSeq]").children().removeAttr("selected");	
			}
		});
	</script>
</body>
</html>