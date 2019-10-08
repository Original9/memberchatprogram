<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
	<%@ page import="user.dao.UserDAO" %>
    <%@ page import="user.dto.UserDTO" %>
    <%@ page import="user.dao.BoarderDAO" %>
    <%@ page import="user.dto.BoarderDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<%
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
	%>
	
<div align="center">
	<div><h1>공 지 사 항</h1></div>
	<div>
		<table border="1">
			<tr>
				<th width="100">번호</th>
				<th width="350">제목</th>
				<th width="100">작성자</th>
				<th width="100">작성일</th>
				<th width="100">첨부파일</th>
				<th width="80">조회수</th>
			</tr>
			
			<!-- db의 글목록을 가져와서 뿌려주는 곳~ -->
			<c:if test="${list.isEmpty()}">
			<tr>
				<td colspan="5"> 등록된 글이 존재하지 않습니다</td>
			</tr>	
			</c:if>
			<c:forEach items="${list }" var="dto">
				<tr onclick="location.href='mainboardRead.do?key=${dto.mbNum }'">
					<td align="center">${dto.mbNum } </td>
					<td align="center">${dto.mbTitle } </td>
					<td align="center">${dto.mbId } </td>
					<td align="center">${dto.mbWriteDate } </td>
					<td align="center">${dto.mbfileName } </td>
					<td align="center">${dto.mbHit } </td>
				</tr>
			</c:forEach>
		</table>
	</div><br />
	<div>
	<c:if test ="${grant == 'M' }">
		<button type="button" onclick="location.href='MainBorderWriteForm.do'">새글쓰기</button>
	</c:if>
	</div>


</div>

<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>