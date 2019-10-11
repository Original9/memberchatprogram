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
<script>

function searchpage(p){
	document.frm.p.value=p;
	document.frm.submit();
}

</script>
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
				<form id="frm" name="frm" action="main.do">
				<input type="hidden" name="p" id="p" value="1"> 
				<select name="searchoption">
					<option value="select">검색 방법</option>
					<option value="all">전체</option>
					<option value="title">제목</option>
					<option value="writer">작성자</option>
				</select>
				<input type="text" id="search" name="search"
					value="${param.search }"> <input type="submit"
					id="searchbtn" name="searchbtn" value="검색">
			</form>
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
	<!-- 페이징 -->
	<div align="center">
		<c:forEach begin="1" end="${pageCnt}" varStatus="st">
			<c:if test="${param.p != st.count}">
				<a href="#" onclick="searchpage(${st.count})">${st.count}</a>
			</c:if>
			<c:if test="${param.p == st.count}">
			${st.count}
		</c:if>
		</c:forEach>
	</div>

<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>