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
//버튼클릭이벤트

window.onload = function(){
	var sendEmailBtn = document.getElementById('btnEmailSend');
	var cancelBtn = document.getElementById('btnClose');
	var form = document.frm;
	
	sendEmailBtn.onclick = function(){
		form.submit();
	};
	
	cancelBtn.onclick = function(){
		self.close();
	};
	
}


//function 

/*---------------------------
아이디,이메일 유효성 체크 및 이메일 발송
------------------------------*/

/* $(function() {
	$("#btnEmailSend").click(function() {

		var param = {
				userID : document.frm.id.value,
				userEmail : document.frm.email.value
		};
		

		var url = "findPW.do";

		
		$.ajax(url, {
			data : param,
			dataType : 'json'
		}).done(function(result) {
			
			
		}).fail(function(xhr, status) {
			alert(status);
		});
		
		

	}) */

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
						<td align="left" style="width: 100px"><input
							type="text" style="width: 150px" id="id" name="id" size="20"></td>
						
					</tr>
					<tr>
						<td valign="middle" style="width: 100px; height: 30px;">이메일</td>
						<td align="left" style="width: 100px"><input
							type="text" style="width: 350px" id="email" name="email" size="20"></td>
					</tr>
					
				</table>

				<div align="center">
					<button	class="btn btn-primary" id="btnEmailSend" type="button">이메일 전송</button>
					<button class="btn btn-primary" id="btnClose" type="button">취소</button>
				</div>
			</form>
		</div>
	</div>

</body>
</html>