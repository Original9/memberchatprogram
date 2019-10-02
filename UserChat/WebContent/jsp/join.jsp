<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
<style type="text/css">
  .line{border-right:0px;}
</style>

<script type="text/javascript">
	function checkForm() {
		var form = document.frm;

		/*---------------------------
		입력 안한 항목 체크
		------------------------------*/
		if (form.userID.value == "") {
			alert("아이디를 입력하세요.");
			form.id.focus();
			return false;
		}

		if (form.userID.readOnly != true) {
			alert("중복체크를 해주세요.");
			form.id.focus();
			return false;
		}

		if (form.userPassword.value == "") {
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


		form.submit();
	}
	
	

	/*---------------------------
	비밀번호 일치여부 체크
	------------------------------*/
	function passwordCheckFunction() {
		var pw1 = document.frm.userPassword;
		var pw2 = document.frm.userPassword2;
		if (pw1.value != pw2.value) {
			alert("비밀번호가 일치하지 않습니다.");
			document.frm.userPassword.value = "";
			document.frm.userPassword2.value = "";
			pw1.focus();
			return false;
		}

	}

	/*---------------------------
	아이디 중복 체크
	------------------------------*/
	$(function() {
		$("#btnIdCheck").click(function() {

			var param = {
				userID : document.frm.userID.value
			};

			var url = "idCheck.do";

			$.ajax(url, {
				data : param,
				dataType : 'json'
			}).done(function(result) {
				if(result.result == true){
					document.frm.userID.readOnly=true;
					document.getElementById("checkResult").style.color="blue";
				}
				$("#checkResult").html(result.message);
			}).fail(function(xhr, status) {
				$("#checkResult").html(status);
			});

		})

	})
</script>


</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<form method="post" action="insertMember.do" id="frm" name="frm">
					<table class="table table-bordered table-hover"
						style="text-align: center; border: 1px solid #dddddd">
						<thead>
							<tr>
								<th colspan="3"><h4>회원 등록 양식</h4></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="width: 110px;"><h5>아이디</h5></td>
								<td style="border-right:0px"><input class="form-control" type="text" id="userID"
									name="userID" maxlength="20" placeholder="아이디를 입력하세요."><p align="right" id=checkResult style="color:red"></p></td>
								<td style="width: 110px;border-left:0px;"><button class="btn btn-primary"
										id="btnIdCheck" type="button">중복체크</button>
									</td>
							</tr>
							<tr>
								<td style="width: 130px;"><h5>비밀번호</h5></td>
								<td colspan="2"><input class="form-control" type="password"
									id="userPassword" name="userPassword" maxlength="20"
									placeholder="비밀번호를 입력하세요."></td>
							</tr>
							<tr>
								<td style="width: 110px;"><h5>비밀번호 확인</h5></td>
								<td colspan="2"><input onblur="passwordCheckFunction();"
									class="form-control" type="password" id="userPassword2"
									name="userPassword2" maxlength="20"
									placeholder="비밀번호 확인을 입력하세요."></td>
							</tr>
							<tr>
								<td style="width: 110px;"><h5>이름</h5></td>
								<td colspan="2"><input class="form-control" type="text"
									id="userName" name="userName" maxlength="20"
									placeholder="이름을 입력하세요."></td>
							</tr>
							<tr>
								<td style="width: 110px;"><h5>나이</h5></td>
								<td colspan="2"><input class="form-control" type="number"
									id="userAge" name="userAge" maxlength="20"
									placeholder="나이를 입력하세요."></td>
							</tr>
							<tr>
								<td style="width: 110px;"><h5>성별</h5></td>
								<td colspan="2">
									<div class="form-group"
										style="text-align: center; margin: 0 auto;">
										<div class="btn-group" data-toggle="buttons">
											<label class="btn btn-primary active"> <input
												type="radio" name="userGender" autocomplete="off" value="M"
												checked>남자
											</label> <label class="btn btn-primary "> <input type="radio"
												name="userGender" autocomplete="off" value="F">여자
											</label>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td style="width: 110px;"><h5>이메일</h5></td>
								<td style="border-right:0px"><input class="form-control" type="email"
									id="userEmail" name="userEmail" maxlength="20"
									placeholder="이메일을 입력하세요."></td>
								<td style="width: 110px; border-left:0px;"><button class="btn btn-primary"
									id="btnEmailCheck" type="button">이메일 인증</button>
								</td>
							</tr>
						</tbody>
					</table>
					<p />
					<div align="center">
						<input type="button" class="btn btn-primary" onclick="checkForm()"
							value="회원가입">&nbsp;&nbsp;&nbsp; <input type="reset"
							class="btn btn-primary" onclick="location.href='join.do'"
							value="취소">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>