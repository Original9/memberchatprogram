<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="user.dao.UserDAO"%>
<%@ page import="user.dto.UserDTO"%>
<%@ page import="user.dao.BoarderDAO"%>
<%@ page import="user.dto.BoarderDTO"%>
<%@ page import="java.io.PrintWriter" %>

<!DOCTYPE html>
<html>
<head>
<%
// 	String userID = null;
	//userID = "jey"; // 로그인 임시로 접속가능하도록 세션값을 박아둔다
	//session.setAttribute("userID", "jey");
// 	if (session.getAttribute("userID") != null) {
// 		userID = (String) session.getAttribute("userID");
// 	}
// 	BoarderDAO dao = new BoarderDAO();
// 	BoarderDTO dto = new BoarderDTO();
// 	if (!userID.equals(dto.getbId())){
//  		out.print("<script>");
//  		out.print("alert('로그인을 먼저 해 주세요.')");
//  		out.print("</script>");
//  		out.print("<script>");
//  		out.print("document.location.href='./boardList.do'");
//  		out.print("</script>");
// 		return;
// 	}
	
	


%>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
@import url('https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap&subset=korean');
</style>
</head>
<body>




	<jsp:include page="menu.jsp"></jsp:include>
	<div><br /><br /><br /></div>
	<div align="center">
		<div>
			<h1 style="font-family: 'Nanum Brush Script', cursive;">글 내용</h1>
		</div>
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<table class="table table-striped">
				<tr>
					<th class="text-center" width="100">작성자</th>
					<td width="100" align="center">${list.mbId }</td>
					<th class="text-center" width="100">작성일</th>
					<td width="100" align="center">${list.mbWriteDate }</td>
					<th class="text-center" width="100">조회수</th>
					<td width="100" align="center">${list.mbHit }</td>
				</tr>
				<tr>
					<th class="text-center" width="100">제목</th>
					<td colspan="5" align="center">${list.mbTitle }</td>
				</tr>
				<tr>
					<th class="text-center" width="100" height="300">내용</th>
					<td colspan="5" align="center">${list.mbContent }</td>
				</tr>
				<tr>
					<th class="text-center" width="100">첨부파일</th>
					<td colspan="5" align="center"><a
						href='FileDownloadAction.do?file_name=${list.mbfileName}'>${list.mbfileName}</a>
					<button class="btn btn-primary" input type="button"
							onclick="location.href='FileDownloadAction.do?file_name=${list.mbfileName}'">파일다운</button>
					</td>
				</tr>
			</table>
		</div>
		<div class="col-md-3"></div>
		
		<div style="width:80%">
			<!-- 조건 넣어서 세션값이 있으면 보이고 아니면 안보이게 하면된다. -->
<%-- 			   <% String id = (String) session.getAttribute("id"); %> --%>
<%-- 			<c:if test="${sessionScope.userID != list.mbId }"> --%>
				<button class="btn btn-primary" type="button" onclick="location.href='main.do'">
					글 목록보기</button> &nbsp;&nbsp;      
<%--       </c:if> --%>
<%-- 			<c:if test="${not empty sessionScope.userID }"> --%>
<%-- 				<c:if test = "${sessionScope.userID == list.mbId}"> --%>
				<c:if test = "${grant == 'M'}">
				<button class="btn btn-primary" type="button"
					onclick="location.href='MainboardUpdateForm.do?key=${list.mbNum}'">글수정</button> &nbsp;&nbsp;      
      <button class="btn btn-primary" type="button" onclick="location.href='mainboardDelete.do?key=${list.mbNum}'">글 삭제</button> &nbsp;&nbsp;
<!--       <button type="button" onclick="location.href='main.do'"> -->
<!-- 					글 목록보기</button> &nbsp;&nbsp; -->
      </c:if>
		</div>
		<br />
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>