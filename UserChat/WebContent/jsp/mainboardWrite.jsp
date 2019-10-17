<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
@import url('https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap&subset=korean');
@import url('https://fonts.googleapis.com/css?family=Sunflower:300&display=swap');
</style>
<script>
	alert(new date).toTSOString();
	document.getElementByID('wdate').value = new Date().toISOString().substring(0, 10);

</script>

</head>
<body>
		<jsp:include page="menu.jsp"></jsp:include>
	<div align="center">
		<div><br /><br /><br /></div>
		<div>
			<h1 style="font-family: 'Nanum Brush Script', cursive;">글쓰기</h1>
		</div>
		<br />
		<div>
			<form id="frm" name="frm" action="mainboardWrite.do" method="post" enctype="multipart/form-data">
				<!-- .do걸어야됨 -->
				<input type="hidden" id="uid" name="uid" value="${userID }">
				<div>
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<table class="table table-striped">
						<tr>
							<th style="font-family: 'Sunflower', sans-serif;" class="text-center" width="100" width="100">작성자</th>
							<td width="200"><input style="text-align:left; border:none" type="text" id="writer" name="writer" value="${name }" readonly="readonly"></td>
							<th style="font-family: 'Sunflower', sans-serif;" class="text-center" width="100" width="100">작성일자</th>
							<td width="200"><input class="form-control" type="date" id="wdate" name="wdate"></td>
						</tr>
						<tr>
							<th style="font-family: 'Sunflower', sans-serif;" class="text-center" width="100" width="100">제목</th>
							<td colspan="3"><input class="form-control" type="text" id="title" name="title"
								size="100" placeholder="제목을 입력 하세요."></td>
						</tr>
						<tr>
							<th style="font-family: 'Sunflower', sans-serif;" class="text-center" width="100" width="100">내용</th>
							<td colspan="3"><textarea class="form-control" rows="30" id="content" name="content" style="width:99%;height:300px;resize:none" 
							placeholder="내용을 입력 하세요.">></textarea>
						</tr>
						<tr>
							<th style="font-family: 'Sunflower', sans-serif;" class="text-center" width="100">첨부파일</th>
							<td colspan="3"><input style="border:none" class="form-control" type="file" id="filename" name="filename" size="100"></td>
						</tr>
					
					</table>
				</div>
				<div class="col-md-3"></div>
				</div>
				<br />
				<div style="width:400px">
					<input class="btn btn-primary" type="submit" value="등록"> &nbsp;&nbsp; 
					<input class="btn btn-primary" type="reset" value="초기화"> &nbsp;&nbsp; 
					<input class="btn btn-primary" type="button" value="목록보기" onclick="location.href='main.do'">
				</div>
			</form>
			
			<script>
				document.getElementById('wdate').value = new Date().toISOString().substring(0, 10);
			</script>
			
			<jsp:include page="footer.jsp"></jsp:include>

		</div>
	</div>

</body>
</html>