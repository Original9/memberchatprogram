<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>

/*---------------------------
아이디,이메일 유효성 체크 및 이메일 발송
------------------------------*/
$(function() {
	$("#btnEmailCheck").click(function() {

		var param = {
				userID : document.frm.id.value,
				userEmail : document.frm.email.value
		};
		

		var url = "validCheckAndSend.do";

		
		$.ajax(url, {
			data : param,
			dataType : 'json'
		}).done(function(result) {
			
		}).fail(function(xhr, status) {
			$("#idFindResult").html(status);
		});
		
		

	})

</script>
</head>
<body>
	<div>
		<br /> <br /> <br />
	</div>
	<div class="container">

		<div>
			<form id="frm" name="frm" action="findPW.do" method="post">
				<!-- <input type="hidden" id="ranNum" name="ranNum"> -->
				<table class="table table-bordered table-hover"
					style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="3"><h4>패스워드 찾기</h4></th>
						</tr>
					</thead>
					<tr>
						<td valign="middle" style="width: 100px; height: 30px;">아이디</td>
						<td align="left" style="width: 100px; border-right: 0px;"><input
							type="text" style="width: 150px;" id="id" name="name" size="20"></td>
						<td align="center" style="border-left: 0px"><button
								class="btn btn-primary" id="btnEmailSend" type="button">이메일
								전송</button></td>
					</tr>
					
				</table>

				<div align="center">
					<input class="btn btn-primary" type="reset"
						onclick="location.href='findPWForm.do'" value="취소"> <!-- 취소 시 창 닫히고 로그인페이지로 가게 바꾸기-->
				</div>
			</form>
		</div>
	</div>

</body>
</html>