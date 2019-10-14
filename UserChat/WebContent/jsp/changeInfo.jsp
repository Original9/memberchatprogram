<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script type="text/javascript">

//email 수정을 위해 해당 영역 클릭  후  focus out 되었을 때 전역변수로 userEmail을 미리 저장하여 이메일을 수정했는지 확인, 수정하지 않았으면 이메일 인증 필요없게 ..

window.onload = function(){
	let changePWBtn = document.getElementById("btnChangePW");
	changePWBtn.onclick=changePW;
	
	let userEmail = document.getElementById("userEmail");
	document.frm.Email.value = document.frm.userEmail.value;
	userEmail.onclick=clickToChangeEmail;
	userEmail.onblur=emailChangeCancel;
}

//비밀번호 변경 창 여는 function
function changePW() {
	
	//새 창의 크기
	let cw=500;
	let ch=400;
	
	//스크린의 크기
	let sw=screen.availWidth;
	let sh=screen.availHeight;
	
	//열 창의 포지션
	let px=(sw-cw)/2;
	let py=(sh-ch)/2;
	
	window.open("changePWForm.do","popup","left="+px+",top="+py+",width="+cw+",height="+ch+"\"");
}


//이메일 변경하려고 클릭했을 시 readOnly가 풀림.
function clickToChangeEmail(){
	
	document.frm.userEmail.readOnly=false;
}

//이메일 변경하려고 클릭했는데 변경 안했거나 이전 이메일과 똑같을 때.
function emailChangeCancel(){
	if(document.frm.Email.value==document.frm.userEmail.value){
		document.frm.userEmail.readOnly=true;
	}
}

function changeInfo(){
	if (form.userEmail.readOnly != true) {
		alert("이메일 인증을 통해 본인 인증을 해주세요.");
		form.id.focus();
		return false;
	}
	document.frm.submit();
}



/*---------------------------
이메일 발송
------------------------------*/
$(function() {
	$("#btnEmailCheck").click(function() {
		
		if(document.frm.userEmail.readOnly == true){
			alert("이미 인증된 이메일 입니다");
		}else{

		var param = {
			userEmail : document.frm.userEmail.value
		};

		var url = "emailCheck.do";

		$.ajax(url, {
			data : param,
			dataType : 'json',
			type : "POST"
		}).done(function(result) {
			if(result.result == true){
				//document.frm.userEmail.readOnly=true;
				document.getElementById("EmailCheckResult").style.color="blue";
				$("#ranNumInputTitle").css("display","");
				$("#ranNum").val(result.checkNum);
				//$("#checkRanNum").css("visibility","visible");
			}
			$("#EmailCheckResult").html(result.message);
		}).fail(function(xhr, status) {
			$("#EmailCheckResult").html(status);
		});
		}
	})

})


/*---------------------------
인증번호 확인
------------------------------*/
$(function() {
	$("#btnRanNumCheck").click(function() {
		
		var param = {
			myVal : document.frm.checkRanNum.value, ranNum : document.frm.ranNum.value
		};

		var url = "ranNumCheck.do";

		$.ajax(url, {
			data : param,
			dataType : 'json',
			type : "POST"
		}).done(function(result) {
			if(result.result == true){
				document.frm.userEmail.readOnly=true;
				
				//document.getElementById("EmailCheckResult").style.color="blue";
				//$("#ranNumInputTitle").css("visibility","visible");
				//$("#checkRanNum").css("visibility","visible");
			}
			$("#EmailCheckResult").html(result.message);
		}).fail(function(xhr, status) {
			$("#EmailCheckResult").html(status);
		});
	})

})

</script>

</head>

<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div><br /><br /><br /></div>
	<div class="container">
	<div class="col-md-2"></div>
	<div class="row">
	<div class="col-md-8">
		<form method="post" action="changeInfo.do" id="frm" name="frm">
				<input type="hidden" id="ranNum" name="ranNum">
				<input type="hidden" id="Email" name="Email">
			<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3"><h4>회원 정보 수정</h4></th>
					</tr>				
				</thead>
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>아이디</h5></td>
						<td colspan="2"><input class="form-control" type="text" id="userID" name="userID" maxlength="20" value="${list.userID }" readOnly="readOnly"></td>
						
					</tr>
					<tr>
						<td style="width: 130px;"><h5>비밀번호</h5></td>
						<td style="border-right:0px"><input class="form-control" type="password" id="userPassword1" name="userPassword1" maxlength="20" value="●●●●●●" readOnly="readOnly"></td>						
						<td style="width: 110px; border-left:0px"><button class="btn btn-primary" id="btnChangePW"
										type="button">비밀번호변경</button>&nbsp;
									</td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이름</h5></td>
						<td colspan="2"><input class="form-control" type="text" id="userName" name="userName" maxlength="20" value="${list.userName }"></td>						
					</tr>
					<tr>
						<td style="width: 110px;"><h5>나이</h5></td>
						<td colspan="2"><input class="form-control" type="number" id="userAge" name="userAge" maxlength="20" value="${list.userAge }"></td>						
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이메일</h5></td>
						<td style="border-right:0px"><input class="form-control" type="email" id="userEmail" name="userEmail" maxlength="20" value="${list.userEmail }" readOnly="readOnly"><br>
						<p style="display:none" align="left" id="ranNumInputTitle">인증번호 입력 : <input type="text" id="checkRanNum">&nbsp;<button class="btn btn-primary"
									id="btnRanNumCheck" type="button">확인</button></p><p align="right" id=EmailCheckResult style="color:red"></p></td>
								<td style="width: 110px; border-left:0px;"><button class="btn btn-primary"
									id="btnEmailCheck" type="button">이메일 인증</button>&nbsp;
						</td>						
						
					</tr>
				</tbody>
			</table>
			<p>&nbsp;&nbsp; *아이디는 수정할 수 없습니다.</p>
				<div align="center">
					<input type="button" class="btn btn-primary" onclick="changeInfo()" value="변경">&nbsp;&nbsp;&nbsp;
					<input type="reset" class="btn btn-primary" onclick="location.href='changeInfoForm.do'" value="취소">
				</div>
		</form>
		</div>
		</div>
	<div class="col-md-2"></div>
	</div>
</body>
</html>