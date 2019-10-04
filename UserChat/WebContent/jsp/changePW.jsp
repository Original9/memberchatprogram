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
//비번체크 함수
</script>
</head>
<body>
<div>
<br />
</div>
<div class="container">
		
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
						<td style="width: 100px;"><input class="form-control" type="text" id="id" name="id" size="20"></td>
					</tr>
					<tr>
						<td style="width: 60px;">변경할 비밀번호</td>
						<td style="width: 100px;"><input class="form-control" type="password" id="pw" name="pw" size="20"></td>
					</tr>
					<tr>
						<td style="width: 60px;">변경할 비밀번호 확인</td>
						<td style="width: 100px;"><input class="form-control" type="password" id="pw" name="pw" size="20"></td>
					</tr>
				</table>
				
				<div align="center">
					<input class="btn btn-primary" type="button" onclick="pwEqualCheck()" value="변경">&nbsp;&nbsp;&nbsp;
					<input class="btn btn-primary" type="reset" onclick="location.href='changePWForm.do'" value="취소">
				</div>
			</form>
		</div>
	</div>
</body>
</html>