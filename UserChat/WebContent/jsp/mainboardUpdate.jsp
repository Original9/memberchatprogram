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
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="menu.jsp"></jsp:include>
</head>
<body>
<c:choose>
<c:when test="${userID == list.bId }">
	<div align="center">
		
		<div>
			<h1>글수정</h1>
		</div>
		<br />
		<div>
			<form id="frm" name="frm" action="boardUpdate.do" method="post" enctype="multipart/form-data">
				<!-- .do걸어야됨 -->
				<input type="hidden" id="bnum" name="bnum" value="${list.bNum }">
				<input type="hidden" id="filedelete" name="filedelete" value="no">
				<div>
					<table border="1">
						<tr>
							<th width="100">작성자</th>
							<td width="200"><input type="text" id="writer" name="writer" value="${name }" readonly="readonly"></td>
							<th width="100">작성일자</th>
							<td width="200">${list.bWriteDate }</td>
						</tr>
						<tr>
							<th width="100">제목</th>
							<td colspan="3"><input type="text" id="title" name="title"
								size="100" value="${list.bTitle }"></td>
						</tr>
						<tr>
							<th width="100">내용</th>
							<td colspan="3"><textarea rows="30" id="content" name="content" style="width:99%" >${list.bContent }</textarea>
						</td></tr>
						<tr>
							<th width="100">첨부파일</th>
							<td colspan="3"><input type="file" id="filename" name="filename" size="100"></td>
							<c:if test = "${list.bfileName != null }">
							<td><input type="button" value="삭제" onclick="frm.filedelete.value='yes'"></td>
							</c:if>
							
						</tr>
						
						
					</table>
				</div>
				<br />
				<div>
					<input type="submit" value="수정"> &nbsp;&nbsp; 
					<input type="reset" value="초기화"> &nbsp;&nbsp; 
					<input type="button" value="목록보기" onclick="location.href='boardList.do'">
				</div>
			</form>
			</div>
			<script>
				document.getElementById('wdate').value = new Date().toISOString().substring(0, 10);
			</script>
		<jsp:include page="footer.jsp"></jsp:include>
</div>
</c:when>
<%-- <c:otherwise> --%>
<%-- 	<jsp:forward page="noAccess.jsp"></jsp:forward> --%>
<%-- </c:otherwise> --%>
</c:choose>
</body>
</html>