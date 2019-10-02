<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
window.onload = function(){
	let changePWBtn = document.getElementById("btnChangePW");
	changePWBtn.onclick=changePW;
}
		
function changePW() {
	window.open("changePWForm.do","popup","width=500,height=400");
}

function changeInfo(){
	document.frm.submit();
}

</script>

</head>
<body>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="container">
	<div class="row">
	<div class="col-md-10">
		<form method="post" action="changeInfo.do" id="frm" name="frm">
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
						<td><input class="form-control" type="password" id="userPassword1" name="userPassword1" maxlength="20" value="●●●●●●" readOnly="readOnly"></td>						
						<td style="width: 110px"><button class="btn btn-primary" id="btnChangePW"
										type="button">비밀번호변경</button>
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
						<td colspan="2"><input class="form-control" type="email" id="userEmail" name="userEmail" maxlength="20" value="${list.userEmail }"></td>						
					</tr>
				</tbody>
			</table>
			<p />
				<div align="center">
					<input type="button" class="btn btn-primary" onclick="changeInfo()" value="변경">&nbsp;&nbsp;&nbsp;
					<input type="reset" class="btn btn-primary" onclick="location.href='changeInfoForm.do'" value="취소">
				</div>
		</form>
		</div>
		</div>
	</div>
</body>
</html>