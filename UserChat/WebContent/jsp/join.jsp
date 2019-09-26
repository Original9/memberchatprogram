<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkForm() {
		var form = document.frm;

		if (form.userID.value == "") {
			alert("아이디를 입력하세요.");
			form.id.focus();
			return false;
		}

		if (form.userID.disabled != true) {
			alert("중복체크를 해주세요.");
			form.id.focus();
			return false;
		}

		if (form.userPassword1.value == "") {
			alert("비밀번호를 입력하세요.");
			form.pw.focus();
			return false;
		}

		if (form.name.value == "") {
			alert("이름을 입력하세요.");
			form.name.focus();
			return false;
		}
		
		if (form.userPassword2.value == "") {
			alert("비밀번호 확인을 입력해 주세요.");
			form.name.focus();
			return false;
		}
		
		//비밀번호1,2 일치하는지 확인하는 메서드 만들기.

		form.submit();
	}

	function idCheck() {
		var chkId = document.frm.userID;
		if (chkId.value == "") {
			alert("아이디를 입력하세요.");
			chkId.focus();
			return false;
		}

		window
				.open("idCheck.do?id=" + chkId.value, "",
						"width=600, height=400");

	}
</script>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="container">
		<form method="post" action="insertMember.do" id="frm" name="frm">
			<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3"><h4>회원 등록 양식</h4></th>
					</tr>				
				</thead>
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>아이디</h5></td>
						<td><input class="form-control" type="text" id="userID" name="userID" maxlength="20" placeholder="아이디를 입력하세요."></td>
						<td style="width: 110px"><button class="btn btn-primary" onclick="idCheck()" type="button">중복체크</button></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>비밀번호</h5></td>
						<td colspan="2"><input onkeyup="passwordCheckFunction();" class="form-control" type="password" id="userPassword1" name="userPassword1" maxlength="20" placeholder="비밀번호를 입력하세요."></td>						
					</tr>
					<tr>
						<td style="width: 110px;"><h5>비밀번호 확인</h5></td>
						<td colspan="2"><input onkeyup="passwordCheckFunction();" class="form-control" type="password" id="userPassword2" name="userPassword2" maxlength="20" placeholder="비밀번호 확인을 입력하세요."></td>						
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이름</h5></td>
						<td colspan="2"><input class="form-control" type="text" id="userName" name="userName" maxlength="20" placeholder="이름을 입력하세요."></td>						
					</tr>
					<tr>
						<td style="width: 110px;"><h5>나이</h5></td>
						<td colspan="2"><input class="form-control" type="number" id="userAge" name="userAge" maxlength="20" placeholder="나이를 입력하세요."></td>						
					</tr>
					<tr>
						<td style="width: 110px;"><h5>성별</h5></td>
						<td colspan="2">
							<div class="form-group" style="text-align: center; margin: 0 auto;">
								<div class="btn-group" data-toggle="buttons">
									<label class="btn btn-primary active">
										<input type="radio" name="userGender" autocomplete="off" value="남자" checked>남자
									</label>
									<label class="btn btn-primary ">
										<input type="radio" name="userGender" autocomplete="off" value="여자" >여자
									</label>
								</div>
							</div> 
						</td>							
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이메일</h5></td>
						<td colspan="2"><input class="form-control" type="email" id="userEmail" name="userEamil" maxlength="20" placeholder="이메일을 입력하세요."></td>						
					</tr>
				</tbody>
			</table>
			<p>
				<div align="center">
					<input type="button" class="btn btn-primary" onclick="checkForm()" value="회원가입">&nbsp;&nbsp;&nbsp;
					<input type="reset" class="btn btn-primary" onclick="location.href='join.do'" value="취소">
				</div>
			</p>
		</form>
	</div>
</body>
</html>