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
	String userID = null;
	//userID = "jey"; // �α��� �ӽ÷� ���Ӱ����ϵ��� ���ǰ��� �ھƵд�
	//session.setAttribute("userID", "jey");
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	BoarderDAO dao = new BoarderDAO();
	BoarderDTO dto = new BoarderDTO();
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
</head>
<body>




	<jsp:include page="menu.jsp"></jsp:include>
	<div align="center">
		<div>
			<h1>�� ���� ����</h1>
		</div>
		<div>
			<table border="1">
				<tr>
					<th width="100">�ۼ���</th>
					<td width="100" align="center">${list.bId }</td>
					<th width="100">�ۼ���</th>
					<td width="100" align="center">${list.bWriteDate }</td>
					<th width="100">��ȸ��</th>
					<td width="100" align="center">${list.bHit }</td>
				</tr>
				<tr>
					<th width="100">����</th>
					<td colspan="5" align="center">${list.bTitle }</td>
				</tr>
				<tr>
					<th width="100" height="300">����</th>
					<td colspan="5" align="center">${list.bContent }</td>
				</tr>
				<tr>
					<th width="100">÷������</th>
					<td colspan="5" align="center"><a
						href='FileDownloadAction.do?file_name=${list.bfileName}'>${list.bfileName}</a>
					<button input type="button"
							onclick="location.href='FileDownloadAction.do?file_name=${list.bfileName}'">���ϴٿ�</button>
					</td>
				</tr>
			</table>
		</div>
		<br />
		<div>
			<!-- ���� �־ ���ǰ��� ������ ���̰� �ƴϸ� �Ⱥ��̰� �ϸ�ȴ�. -->
<%-- 			   <% String id = (String) session.getAttribute("id"); %> --%>
			<c:if test="${sessionScope.userID != list.bId }">
				<button type="button" onclick="location.href='boardList.do'">
					�� ��Ϻ���</button> &nbsp;&nbsp;      
      </c:if>
<%-- 			<c:if test="${not empty sessionScope.userID }"> --%>
				<c:if test = "${sessionScope.userID == list.bId}">
				<button type="button"
					onclick="location.href='boardUpdateForm.do?key=${list.bNum}'">�ۼ���</button> &nbsp;&nbsp;      
      <button type="button" onclick="location.href='boardDelete.do?key=${list.bNum}'">�� ����</button> &nbsp;&nbsp;
      <button type="button" onclick="location.href='boardList.do'">
					�� ��Ϻ���</button> &nbsp;&nbsp;
      </c:if>
		</div>
		<br />
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>