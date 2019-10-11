<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>

function searchpage(p){
	document.frm.p.value=p;
	document.frm.submit();
}

</script>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div align="center">
		<div>
			<h1>자유 게시판</h1>
		</div>
		<div>
			<form id="frm" name="frm" action="boardList.do">
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
<!-- 			<table border="1"> -->
			<table class="table table-bordered table-hover" style="text-align: center; border:1px solid #dddddd">
				<tr>
					<th style="width:100">번호</th>
					<th style="width:350">제목</th>
					<th style="width:100">작성자</th>
					<th style="width:100">작성일</th>
					<th style="width:100">첨부파일</th>
					<th style="width:80">조회수</th>
				</tr>

				<!-- db의 글목록을 가져와서 뿌려주는 곳~ -->
				<c:if test="${list.isEmpty()}">
					<tr>
						<td colspan="5">등록된 글이 존재하지 않습니다</td>
					</tr>
				</c:if>
				<c:forEach items="${list }" var="dto">
					<tr onclick="location.href='boardRead.do?key=${dto.bNum }'">
						<td align="center">${dto.bNum }</td>
						<td align="center">${dto.bTitle }</td>
						<td align="center">${dto.bId }</td>
						<td align="center">${dto.bWriteDate }</td>
						<td align="center">${dto.bfileName }</td>
						<td align="center">${dto.bHit }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br />
		<div>
			<c:if test="${userID != null }">
				<button type="button" onclick="location.href='borderWriteForm.do'">새글쓰기</button>
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