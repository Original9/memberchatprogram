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
	//userID = "jey"; // 로그인 임시로 접속가능하도록 세션값을 박아둔다
	//session.setAttribute("userID", "jey");
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	BoarderDAO dao = new BoarderDAO();
	BoarderDTO dto = new BoarderDTO();
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
</head>
<body>




	<jsp:include page="menu.jsp"></jsp:include>
	<div align="center">
		<div>
			<h1>글 내용 보기</h1>
		</div>
		<div>
			<table border="1">
				<tr>
					<th width="100">작성자</th>
					<td width="100" align="center">${list.bId }</td>
					<th width="100">작성일</th>
					<td width="100" align="center">${list.bWriteDate }</td>
					<th width="100">조회수</th>
					<td width="100" align="center">${list.bHit }</td>
				</tr>
				<tr>
					<th width="100">제목</th>
					<td colspan="5" align="center">${list.bTitle }</td>
				</tr>
				<tr>
					<th width="100" height="300">내용</th>
					<td colspan="5" align="center">${list.bContent }</td>
				</tr>
				<tr>
					<th width="100">첨부파일</th>
					<td colspan="5" align="center"><a
						href='FileDownloadAction.do?file_name=${list.bfileName}'>${list.bfileName}</a>
					<button input type="button"
							onclick="location.href='FileDownloadAction.do?file_name=${list.bfileName}'">파일다운</button>
					</td>
				</tr>
			</table>
		</div>
		<br />
		<div>
			<!-- 조건 넣어서 세션값이 있으면 보이고 아니면 안보이게 하면된다. -->
<%-- 			   <% String id = (String) session.getAttribute("id"); %> --%>
			<c:if test="${sessionScope.userID != list.bId }">
				<button type="button" onclick="location.href='boardList.do'">
					글 목록보기</button> &nbsp;&nbsp;      
      </c:if>
<%-- 			<c:if test="${not empty sessionScope.userID }"> --%>
				<c:if test = "${sessionScope.userID == list.bId}">
				<button type="button"
					onclick="location.href='boardUpdateForm.do?key=${list.bNum}'">글수정</button> &nbsp;&nbsp;      
      <button type="button" onclick="location.href='boardDelete.do?key=${list.bNum}'">글 삭제</button> &nbsp;&nbsp;
      <button type="button" onclick="location.href='boardList.do'">
					글 목록보기</button> &nbsp;&nbsp;
      </c:if>
		</div>
		<br />
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>