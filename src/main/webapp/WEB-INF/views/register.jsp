<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="/resources/stylesheet/common.css">
<link rel="stylesheet" href="/resources/stylesheet/content.css">
</head>
<body>
	<section class="wrap loginWrap registerWrap">
		<form action="/member/register" method="post">
			<div class="formWrap">
				<div>
					<label>아이디</label>
					<input class="textField formControl" type="text" name="memberId" />
				</div>
				<div>
					<label>비밀번호</label>
					<input class="textField formControl" type="password" name="memberPw" />
				</div>
				<div>
					<label>비밀번호확인</label>
					<input class="textField formControl" type="password" name="memberPwChk" />
				</div>
				<div>
					<label>성</label>
					<input class="textField formControl" type="text" name="memberLastName" />
				</div>
				<div>
					<label>이름</label>
					<input class="textField formControl" type="text" name="memberFirstName" />
				</div>
				<div>
					<label>닉네임</label>
					<input class="textField formControl" type="text" name="MemberNickName" />
				</div>
				<div>
					<label>생년월일</label>
					<input class="textField formControl" type="date" name="memberDob" />
				</div>
				<div>
					<label>성별</label>
					<select class="formControl" name="memberGenderCd">
						<option value="M">남성</option>
						<option value="F">여성</option>
						<option value="O">기타</option>
					</select>
				</div>
				<div>
					<label>이메일</label>
					<input class="textField formControl" type="text" name="memberEmail" />
				</div>
				<input class="btn formControl" type="submit" value="회원가입">
			</div>
		</form>
	</section>
</body>
</html>