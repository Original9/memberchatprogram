<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	alert(new date).toTSOString();
	document.getElementByID('wdate').value = new Date().toISOString().substring(0, 10);

</script>

</head>
<body>
	<div align="center">
		<jsp:include page="menu.jsp"></jsp:include>
		
		<div>
			<h1>글쓰기</h1>
		</div>
		<br />
		<div>
			<form id="frm" name="frm" action="mainboardWrite.do" method="post" enctype="multipart/form-data">
				<!-- .do걸어야됨 -->
				<input type="hidden" id="uid" name="uid" value="${userID }">
				<div>
					<table border="1">
						<tr>
							<th width="100">작성자</th>
							<td width="200"><input type="text" id="writer" name="writer" value="${name }" readonly="readonly"></td>
							<th width="100">작성일자</th>
							<td width="200"><input type="date" id="wdate" name="wdate"></td>
						</tr>
						<tr>
							<th width="100">제목</th>
							<td colspan="3"><input type="text" id="title" name="title"
								size="100" placeholder="제목을 입력 하세요."></td>
						</tr>
						<tr>
							<th width="100">내용</th>
							<td colspan="3"><textarea rows="30" id="content" name="content" style="width:99%" 
							placeholder="내용을 입력 하세요.">></textarea>
						</tr>
						<tr>
							<th width="100">첨부파일</th>
							<td colspan="3"><input type="file" id="filename" name="filename" size="100"></td>
						</tr>
					
					</table>
				</div>
				<br />
				<div>
					<input type="submit" value="등록"> &nbsp;&nbsp; 
					<input type="reset" value="초기화"> &nbsp;&nbsp; 
					<input type="button" value="목록보기" onclick="location.href='main.do'">
				</div>
			</form>
			
			<script>
				document.getElementById('wdate').value = new Date().toISOString().substring(0, 10);
			</script>
			
			<jsp:include page="footer.jsp"></jsp:include>

		</div>
</body>
</html>