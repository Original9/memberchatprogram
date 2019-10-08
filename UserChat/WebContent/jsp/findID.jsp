<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
						<th colspan="2"><h4>아이디 찾기</h4></th>
					</tr>				
					</thead>
					<tr>
						<td style="width: 60px;">이메일 입력</td>
						<td style="width: 100px;"><input class="form-control" type="text" id="email" name="email" size="20"></td>
					</tr>
					<tr>
						<td style="width: 60px;">-</td>
						<td style="width: 100px;"><input class="form-control" type="password" id="changePW1" name="changePW1" size="20"></td>
					</tr>
					<tr>
						<td style="width: 60px;">-</td>
						<td style="width: 100px;"><input class="form-control" type="password" id="changePW2" name="changePW2" size="20"></td>
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