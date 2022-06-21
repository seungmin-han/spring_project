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
  <script type="text/javascript" src="/resources/script/subject.js"></script>
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<div style="width:400px; border:1px solid #ccc; padding:10px; display:inline-block">
		<form action="/admin/board/create" method="post">
			<div>
				<label>게시판 추가</label><br>
				<input type="text" name="boardName" required="required">
			</div>
			<button type="submit">게시판 추가</button>
		</form>
		<hr>
		<form action="/admin/board/update" method="post">
			<div>
				<label>게시판 수정</label><br>
				<select name="boardSeq" required="required">
					<option value="">게시판 선택</option>
					
					<c:forEach items="${boardList }" var="boardItem">
						<option value="${boardItem.boardSeq} ">${boardItem.boardName}</option>
					</c:forEach>
				</select><br>
				<input type="text" name="boardName" required="required">
			</div>
			<button type="submit">게시판 수정</button>
		</form>
		<hr>
		<form action="/admin/board/delete" method="post">
			<div>
				<label>게시판 삭제</label><br>
				<select name="boardSeq" required="required">
					<option value="">게시판 선택</option>
					<c:forEach items="${boardList }" var="boardItem">
						<option value="${boardItem.boardSeq} ">${boardItem.boardName }</option>
					</c:forEach>
				</select>
			</div>
			<button type="submit">게시판 삭제</button>
		</form>
	</div>
	
	
	<div style="width:400px; border:1px solid #ccc; padding:10px; display:inline-block">
		<form action="/admin/subject/create" method="post">
			<div>
				<label>말머리 추가</label><br>
				<select name="boardSeq" required="required">
					<option value="">게시판 선택</option>
					
					<c:forEach items="${boardList }" var="boardItem">
						<option value="${boardItem.boardSeq} ">${boardItem.boardName}</option>
					</c:forEach>
				</select>
				<input type="text" name="subjectName" required="required">
			</div>
			<button type="submit">말머리 추가</button>
		</form>
		<hr>
		<form action="/admin/subject/update" method="post">
			<div>
				<label>말머리 수정</label><br>
				<select name="boardSeq" required="required">
					<option value="">게시판 선택</option>
					
					<c:forEach items="${boardList }" var="boardItem">
						<option value="${boardItem.boardSeq} ">${boardItem.boardName}</option>
					</c:forEach>
				</select>
				<select name="subjectSeq" required="required">
					<option value="">말머리 선택</option>
				</select>
				<br>
				<input type="text" name="subjectName" required="required">
			</div>
			<button type="submit">말머리 수정</button>
		</form>
		<hr>
		<form action="/admin/subject/delete" method="post">
			<div>
				<label>말머리 삭제</label><br>
				<select name="boardSeq" required="required">
					<option value="">게시판 선택</option>
					<c:forEach items="${boardList }" var="boardItem">
						<option value="${boardItem.boardSeq} ">${boardItem.boardName }</option>
					</c:forEach>
				</select>
				<select name="subjectSeq" required="required">
					<option value="">말머리 선택</option>
				</select>
			</div>
			<button type="submit">말머리 삭제</button>
		</form>
	</div>
	<script type="text/javascript">

		$("select[name=boardSeq]").on("change",function(){			
			$(this).parent().find("select[name=subjectSeq]").children("option:gt(0)").remove();
			let subjectSeq = $(this).find("option:selected").val();	
			
			if(subjectSeq == "게시판 선택") return;
			
			let subjectList = getSubjectList(subjectSeq);
			for(let subject of subjectList) {
				$(this).parent().find("select[name=subjectSeq]")
				.append("<option value="+subject['subjectSeq']+">"+subject['subjectName']+"</option>");	
			}
		})
		
	</script>
</body>
</html>