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
//
function checkForm(){
	var form = document.frm;
	
	if (form.email.readOnly != true) {
		alert("이메일 인증을 통해 본인 인증을 해주세요.");
		form.name.focus();
		return false;
	}else{
		var param = {
				userName : document.frm.name.value,
				userEmail : document.frm.email.value
			};

			var url = "findID.do";

			$.ajax(url, {
				data : param,
				dataType : 'json'
			}).done(function(result) {
					$("#idFindResult").css("display","");
					$("#idFindResultValue").val(result.message);
				
			}).fail(function(xhr, status) {
				$("#idFindResult").html(status);
			});
		
	}

	
}

/*---------------------------
이름,이메일 유효성 체크 및 이메일 발송
------------------------------*/
$(function() {
	$("#btnEmailCheck").click(function() {

		var param = {
				userName : document.frm.name.value,
				userEmail : document.frm.email.value
		};
		

		var url = "validCheckAndSendForFindID.do";

		
		$.ajax(url, {
			data : param,
			dataType : 'json'
		}).done(function(result) {
			// if(result.result == true){
			//	document.getElementById("idFindResult").style.color="blue";
			//	$("#idFindResult").css("display","");
			//} 
			if(result.resultStatus==1){
				alert(result.message);
			}else if(result.resultStatus==2){
				alert(result.message);
			}else{
				$("#ranNumInputTitle").css("display","");
				alert(result.message);
				//document.frm.email.readOnly=true;
			}
		}).fail(function(xhr, status) {
			$("#idFindResult").html(status);
		});
		
		

	})




//인증번호 체크
/* $.ajax(url2, {
					data : param2,
					dataType : 'json'
				}).done(function(result) {
					if(result.result == true){
						$("#ranNumInputTitle").css("display","");
						$("#ranNum").val(result.checkNum);
					}
					alert(result.message);
				}).fail(function(xhr, status) {
					alert(result.message);
				}); */




 	/*---------------------------
	인증번호 확인
	------------------------------*/

		$("#btnRanNumCheck").click(function() {

			var param = {
				myVal : document.frm.checkRanNum.value	};

			var url = "ranNumCheck.do";

			$.ajax(url, {
				data : param,
				dataType : 'json'
			}).done(function(result) {
				if(result.result == true){
					document.frm.email.readOnly=true;
					
					checkForm();
					//document.getElementById("EmailCheckResult").style.color="blue";
					//$("#ranNumInputTitle").css("visibility","visible");
					//$("#checkRanNum").css("visibility","visible");
				}else{
					alert(result.message);
					
				}
			}).fail(function(xhr, status) {
				alert("인증번호를 공백없이 입력해 주세요!");
				//$("#EmailCheckResult").html(status);
			});

		})

	})


/*---------------------------
아이디 알려줌
------------------------------*/
/* $(function() {
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
			// if(result.result == true){
			//	document.getElementById("idFindResult").style.color="blue";
			//	$("#idFindResult").css("display","");
			//} 
			if(result.resultStatus==1){
				alert(result.message);
			}else if(result.resultStatus==2){
				alert(result.message);
			}else{
				//document.getElementById("idFindResult").style.color="black";
				$("#idFindResult").css("display","");
				$("#idFindResultValue").val(result.message);
			}
		}).fail(function(xhr, status) {
			$("#idFindResult").html(status);
		});

	})

}) */
</script>
</head>
<body>
	<div>
		<br /> <br /> <br />
	</div>
	<div class="container">

		<div>
			<form id="frm" name="frm" action="findID.do" method="post">
				<!-- <input type="hidden" id="ranNum" name="ranNum"> -->
				<table class="table table-bordered table-hover"
					style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="3"><h4>아이디 찾기</h4></th>
						</tr>
					</thead>
					<tr>
						<td valign="middle" style="width: 100px; height: 30px;">이름</td>
						<td align="left" valign="middle" style="width: 100px; border-right: 0px;"><input
							type="text" style="width: 150px;" id="name" name="name" size="20"></td>
						<td align="right" style="border-left: 0px"><button
								class="btn btn-primary" id="btnEmailCheck" type="button">이메일
								인증</button></td>
					</tr>
					<tr>
						<td style="width: 100px;">이메일 입력</td>
						<td colspan="2" align="left" style="width: 100px;"><input
							type="text" style="width: 100%;" id="email" name="email"
							size="20">

							<p style="display: none" align="left" id="ranNumInputTitle">
								<br />인증번호 입력 : <input type="text" id="checkRanNum">&nbsp;
								<button class="btn btn-primary" id="btnRanNumCheck"
									type="button">확인</button>
							</p>
							<p style="display: none" id="idFindResult">
								id는 &nbsp;<input type="text" style="width: 100px; text-align:center;"
									id="idFindResultValue" readOnly="readonly">&nbsp; 입니다.
							</p></td>

					</tr>
				</table>

				<div align="center">
					<input class="btn btn-primary" type="reset"
						onclick="location.href='findIDForm.do'" value="취소">
				</div>
			</form>
		</div>
	</div>

</body>
</html>