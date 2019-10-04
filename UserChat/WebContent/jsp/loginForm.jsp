<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkForm() {
		var form = document.frm;
		if (form.id.value == "") {
			alert("아이디를 입력하세요.");
			form.id.focus();
			return false;
		}

		if (form.pw.value == "") {
			alert("패스워드를 입력하세요.");
			form.pw.focus();
			return false;
		}

		form.submit();
	}
</script>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
		<div><br /><br /><br /></div>
		<div class="container">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<form id="frm" name="frm" action="loginCheck.do" method="post">
				<table class="table table-bordered table-hover" style="text-align: center; border:1px solid #dddddd">
					<thead>
					<tr>
						<th colspan="2"><h4>로그인</h4></th>
					</tr>				
					</thead>
					<tr>
						<td style="width: 60px;">id</td>
						<td style="width: 100px;"><input class="form-control" type="text" id="id" name="id" size="20"></td>
					</tr>
					<tr>
						<td style="width: 60px;">password</td>
						<td style="width: 100px;"><input class="form-control" type="password" id="pw" name="pw" size="20"></td>
					</tr>
				</table>
				
				<div align="center">
					<input class="btn btn-primary" type="button" onclick="checkForm()" value="로그인">&nbsp;&nbsp;&nbsp;
					<input class="btn btn-primary" type="button" onclick="location.href='findID.do'" value="아이디 찾기">&nbsp;&nbsp;&nbsp;
					<input class="btn btn-primary" type="button" onclick="location.href='findPW.do'" value="비밀번호 찾기">&nbsp;&nbsp;&nbsp;
					<input class="btn btn-primary" type="reset" onclick="location.href='login.do'" value="취소">
				</div>
			</form>
		</div>
		<div class="col-md-2"></div>
	</div>
	
</body>
</html>