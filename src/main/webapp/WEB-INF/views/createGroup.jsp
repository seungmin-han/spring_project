<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그룹 만들기</title>
</head>
<body>
	<form action="/group/create" method="post" enctype="multipart/form-data">
		<div>
			<label>그룹 이미지</label>
			<img id="image" alt="" src="" width="200" height="200"><br>
			<input id="groupImage" name="file" type="file" accept="image/*" >
		</div>
		<div>
			<label>그룹 이름</label>
			<input name="groupName" type="text">
		</div>
		<div>
			<label>그룹 소개</label>
			<textarea name="groupText" rows="3" cols="22" style="resize:none;"></textarea>
		</div>
		<div>
			<label>자동 승인 여부</label><br>
			수동 가입<input type="radio" name="groupJoinAcceptNy" value="0" checked>
			자동 가입<input type="radio" name="groupJoinAcceptNy" value="1">	
		</div>
		<input type="hidden" name="memberSeq" value="${memberSeq }">
		<button>만들기</button>
	</form>
	<script type="text/javascript">
		document.getElementById("groupImage").onchange = function () {
		    let reader = new FileReader();
	
		    reader.onload = function(e) {
		        document.getElementById("image").src = e.target.result;
			};
			reader.readAsDataURL(this.files[0]);
		};
	</script>
</body>
</html>