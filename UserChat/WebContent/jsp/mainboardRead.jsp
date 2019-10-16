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
	//userID = "jey"; // �α��� �ӽ÷� ���Ӱ����ϵ��� ���ǰ��� �ھƵд�
	//session.setAttribute("userID", "jey");
// 	if (session.getAttribute("userID") != null) {
// 		userID = (String) session.getAttribute("userID");
// 	}
// 	BoarderDAO dao = new BoarderDAO();
// 	BoarderDTO dto = new BoarderDTO();
// 	if (!userID.equals(dto.getbId())){
//  		out.print("<script>");
//  		out.print("alert('�α����� ���� �� �ּ���.')");
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
			<h1 style="font-family: 'Nanum Brush Script', cursive;">�� ����</h1>
		</div>
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<table class="table table-striped">
				<tr>
					<th class="text-center" width="100">�ۼ���</th>
					<td width="100" align="center">${list.mbId }</td>
					<th class="text-center" width="100">�ۼ���</th>
					<td width="100" align="center">${list.mbWriteDate }</td>
					<th class="text-center" width="100">��ȸ��</th>
					<td width="100" align="center">${list.mbHit }</td>
				</tr>
				<tr>
					<th class="text-center" width="100">����</th>
					<td colspan="5" align="center">${list.mbTitle }</td>
				</tr>
				<tr>
					<th class="text-center" width="100" height="300">����</th>
					<td colspan="5" align="center">${list.mbContent }</td>
				</tr>
				<tr>
					<th class="text-center" width="100">÷������</th>
					<td colspan="5" align="center"><a
						href='FileDownloadAction.do?file_name=${list.mbfileName}'>${list.mbfileName}</a>
					<button class="btn btn-primary" input type="button"
							onclick="location.href='FileDownloadAction.do?file_name=${list.mbfileName}'">���ϴٿ�</button>
					</td>
				</tr>
			</table>
		</div>
		<div class="col-md-3"></div>
		
		<div style="width:80%">
			<!-- ���� �־ ���ǰ��� ������ ���̰� �ƴϸ� �Ⱥ��̰� �ϸ�ȴ�. -->
<%-- 			   <% String id = (String) session.getAttribute("id"); %> --%>
<%-- 			<c:if test="${sessionScope.userID != list.mbId }"> --%>
				<button class="btn btn-primary" type="button" onclick="location.href='main.do'">
					�� ��Ϻ���</button> &nbsp;&nbsp;      
<%--       </c:if> --%>
<%-- 			<c:if test="${not empty sessionScope.userID }"> --%>
<%-- 				<c:if test = "${sessionScope.userID == list.mbId}"> --%>
				<c:if test = "${grant == 'M'}">
				<button class="btn btn-primary" type="button"
					onclick="location.href='MainboardUpdateForm.do?key=${list.mbNum}'">�ۼ���</button> &nbsp;&nbsp;      
      <button class="btn btn-primary" type="button" onclick="location.href='mainboardDelete.do?key=${list.mbNum}'">�� ����</button> &nbsp;&nbsp;
<!--       <button type="button" onclick="location.href='main.do'"> -->
<!-- 					�� ��Ϻ���</button> &nbsp;&nbsp; -->
      </c:if>
		</div>
		<br />
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>