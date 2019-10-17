<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
<script>

	function pwChangeCancel(){
		self.close()
	}
	function pwEqualCheck() {
		var form=document.frm;
		var pw1 = document.frm.changePW1;
		var pw2 = document.frm.changePW2;
		var pwRegExp = /^[A-Za-z0-9]{6,12}$/;
		var check = pwRegExp.test(pw1.value);
		
		if (check == false){
			alert("비밀번호는 영문과 숫자를 포함하여 6~12자 이내로 작성하세요.");
			return false;
		}
		if (pw1.value != pw2.value) {
			alert("비밀번호가 일치하지 않습니다.");
			document.frm.changePW1.value = "";
			document.frm.changePW2.value = "";
			pw1.focus();
			return false;
		}
		
		
		form.submit();
	}

</script>
</head>
<body>
<div>
<br />
</div>
<div class="container">
		<br />
		<div>
			<form id="frm" name="frm" action="changePW.do" method="post">
				<table class="table table-bordered table-hover" style="text-align: center; border:1px solid #dddddd">
					<thead>
					<tr>
						<th colspan="2"><h4>비밀번호 변경</h4></th>
					</tr>				
					</thead>
					<tr>
						<td style="width: 60px;">현재 비밀번호</td>
						<td style="width: 100px;"><input class="form-control" type="text" id="currentPW" name="currentPW" size="20"></td>
					</tr>
					<tr>
						<td style="width: 60px;">변경할 비밀번호</td>
						<td style="width: 100px;"><input class="form-control" type="password" id="changePW1" name="changePW1" size="20"></td>
					</tr>
					<tr>
						<td style="width: 60px;">변경할 비밀번호 확인</td>
						<td style="width: 100px;"><input class="form-control" type="password" id="changePW2" name="changePW2" size="20"></td>
					</tr>
				</table>
				
				<div align="center">
					<input class="btn btn-primary" type="button" onclick="pwEqualCheck()" value="변경">&nbsp;&nbsp;&nbsp;
					<input class="btn btn-primary" type="reset" onclick="pwChangeCancel()" value="취소">
				</div>
			</form>
		</div>
	</div>
</body>
</html>