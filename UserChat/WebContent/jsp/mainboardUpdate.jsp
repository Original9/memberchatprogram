<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
	<%@ page import="user.dao.UserDAO" %>
    <%@ page import="user.dto.UserDTO" %>
    <%@ page import="user.dao.BoarderDAO" %>
    <%@ page import="user.dto.BoarderDTO" %>
    <%@ page import="java.io.PrintWriter" %>

<!DOCTYPE html>
<html>
<head>
<% 
		String userID = null;
		//userID = "jey"; // 로그인 임시로 접속가능하도록 세션값을 박아둔다
		//session.setAttribute("userID", "jey");
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
// 		BoarderDAO dao = new BoarderDAO();
// 		BoarderDTO dto = new BoarderDTO();
// 		if (!userID.equals(dto.getbId())){
// 	 		out.print("<script>");
// 	 		out.print("alert('로그인을 먼저 해 주세요.')");
// 	 		out.print("</script>");
// 	 		out.print("<script>");
// 	 		out.print("document.location.href='./boardList.do'");
// 	 		out.print("</script>");
// 			return;
// 		}
		
		
		
		

%>

<script>

function reset1(){ 
	$('#content').empty();
	 document.getElementById('title').value='';
  }
  
function checkBoard() {
	var form = document.frm;
	if (form.content.value == "") {
		alert("내용을 입력 하세요.");
		form.content.focus();
		return false;
	}

	if (form.title.value == "") {
		alert("제목을 입력하세요.");
		form.title.focus();
		return false;
	}

	form.submit();
}

</script>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
@import url('https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap&subset=korean');
@import url('https://fonts.googleapis.com/css?family=Sunflower:300&display=swap');
</style>

<jsp:include page="menu.jsp"></jsp:include>
</head>
<body>
<c:choose>
<c:when test="${grant == 'M' }">
	<div align="center">
		<div><br /><br /><br /></div>
		<div>
			<h1 style="font-family: 'Nanum Brush Script', cursive;">글 수정</h1>
		</div>
		<br />
		<div>
			<form id="frm" name="frm" action="mainboardUpdate.do" method="post" enctype="multipart/form-data">
				<!-- .do걸어야됨 -->
				<input type="hidden" id="bnum" name="bnum" value="${list.mbNum }">
				<input type="hidden" id="filedelete" name="filedelete" value="no">
				<div>
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<table class="table table-striped">
						<tr>
							<th style="font-family: 'Sunflower', sans-serif;" class="text-center" width="100">작성자</th>
							<td><input style="text-align:left; border:none" id="writer" name="writer" value="${name }" readonly="readonly"></td>
							<th style="font-family: 'Sunflower', sans-serif;" class="text-center" width="100">작성일자</th>
							<td>${list.mbWriteDate }</td>
						</tr>
						<tr>
							<th style="font-family: 'Sunflower', sans-serif;" class="text-center" width="100">제목</th>
							<td colspan="3"><input class="form-control" type="text" id="title" name="title"
								size="100" value="${list.mbTitle }"></td>
						</tr>
						<tr>
							<th style="font-family: 'Sunflower', sans-serif;" class="text-center" width="100">내용</th>
							<td colspan="3"><textarea class="form-control" rows="30" id="content" name="content" style="width:99%;height:300px;resize:none" >${list.mbContent }</textarea>
						</td></tr>
						<tr>
							<th style="font-family: 'Sunflower', sans-serif;" class="text-center" width="100">첨부파일</th>
							<td colspan="2"><input style="max-width:400px;border:none" class="form-control" type="file" id="filename" name="filename" size="100"></td>
							<td>
							<c:if test = "${list.mbfileName != null }">
							<input class="btn btn-default" type="button" value="삭제" onclick="frm.filedelete.value='yes'">
							</c:if>
							</td>
							
						</tr>
						
						
					</table>
				</div>
				<div class="col-md-3"></div>	
				</div>
				<br />
				<div style="width:400px">
					<input class="btn btn-primary" type="button" value="수정" onclick="checkBoard()"> &nbsp;&nbsp; 
<!-- 					<input class="btn btn-primary" type="reset" value="초기화" onclick="resetAll()"> &nbsp;&nbsp;  -->
					<input class="btn btn-primary" type="button" value="초기화" onclick="reset1()"> &nbsp;&nbsp; 
					<input class="btn btn-primary" type="button" value="목록보기" onclick="location.href='main.do'">
				</div>
			</form>
			</div>
		<jsp:include page="footer.jsp"></jsp:include>
</div>
</c:when>
<%-- <c:otherwise> --%>
<%-- 	<jsp:forward page="noAccess.jsp"></jsp:forward> --%>
<%-- </c:otherwise> --%>
</c:choose>
</body>
</html>