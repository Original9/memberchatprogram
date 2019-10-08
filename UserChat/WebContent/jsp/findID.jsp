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
아이디 알려줌
------------------------------*/
$(function() {
	$("#find").click(function() {

		var param = {
			userName : document.frm.name.value,
			userEmail : document.frm.email.value
		};

		var url = "findID.do";

		$.ajax(url, {
			data : param,
			dataType : 'json'
		}).done(function(result) {
			/* if(result.result == true){
				document.getElementById("idFindResult").style.color="blue";
				$("#idFindResult").css("display","");
			} */
			if(result.resultStatus==1){
				alert(result.message);
			}else if(result.resultStatus==2){
				alert(result.message);
			}else{
				document.getElementById("idFindResult").style.color="blue";
				$("#idFindResult").css("display","");
			}
			$("#idFindResult").html(result.message);
		}).fail(function(xhr, status) {
			$("#idFindResult").html(status);
		});

	})

})
</script>
</head>
<body>
<div>
<br />
<br />
<br />
</div>
<div class="container">
		
		<div>
			<form id="frm" name="frm" action="findID.do" method="post">
				<table class="table table-bordered table-hover" style="text-align: center; border:1px solid #dddddd">
					<thead>
					<tr>
						<th colspan="2"><h4>아이디 찾기</h4></th>
					</tr>				
					</thead>
					<tr>
						<td style="width: 100px;">이름</td>
						<td align="left" style="width: 100px;"><input type="text" style="width: 150px;" id="name" name="name" size="20"></td>
					</tr>
					<tr>
						<td style="width: 100px;">이메일 입력</td>
						<td align="left" style="width: 100px;"><input type="text" style="width: 100%;" id="email" name="email" size="20">
						<br /><br />
						<p style="display:none; color:red;" id="idFindResult"></p></td>
					</tr>
				</table>
				
				<div align="center">
					<input class="btn btn-primary" type="button" id="find" value="찾기">&nbsp;&nbsp;&nbsp;
					<input class="btn btn-primary" type="reset" onclick="location.href='findIDForm.do'" value="취소">
				</div>
			</form>
		</div>
	</div>

</body>
</html>