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
<style>
@import
	url('https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap&subset=korean')
	;

@import
	url('https://fonts.googleapis.com/css?family=Sunflower:300&display=swap')
	;
</style>
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
	<div
		style="width: 100%; height: 100%; position: fixed; background: url('images/paperplane5.jpg') no-repeat; background-size: cover;"
		align="center">
		<jsp:include page="menu.jsp"></jsp:include>
		<div align="center">
			<div>
				<br /> <br /> <br /> <br />
			</div>
			<div>
				<h1 style="font-size: 50px; font-family: 'Nanum Brush Script', cursive;">자 유 게 시
					판</h1>
			</div>
			<div>
				<form id="frm" name="frm" class="form-inline" action="boardList.do">
					<input type="hidden" name="p" id="p" value="1">
					
					<table style="width:400px; height:100px; background:transparent">
						<td><select class="form-control inputstl" name="searchoption">
								<option value="all">글 제목</option>
								<option value="writer">작성자</option>
						</select></td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td><input type="text" id="search" name="search"
							class="form-control" value="${param.search }"></td>
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td><input type="submit" id="searchbtn" name="searchbtn"
							class="btn btn-default" value="검색"></td>
					</table>
				</form>
				<!-- 			<table border="1"> -->
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<table class="table table-striped" style="background-color:#a4eded;"
							style="text-align: center">   <!-- border: 1px solid #dddddd 스타일 없앰.-->
							<tr>
								<th class="text-center"
									style="font-family: 'Sunflower', sans-serif;">번호</th>
								<th style="width:500px;font-family: 'Sunflower', sans-serif;"
									class="text-center">제목</th>
								<th class="text-center"
									style="font-family: 'Sunflower', sans-serif;">작성자</th>
								<th class="text-center"
									style="font-family: 'Sunflower', sans-serif;">작성일</th>
								<th class="text-center"
									style="width:300px; font-family: 'Sunflower', sans-serif;">첨부파일</th>
								<th style="font-family: 'Sunflower', sans-serif;"
									class="text-center">조회수</th>
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
					<div class="col-md-2"></div>
				</div>
			</div>
			<br />
			<div>
				<c:if test="${userID != null }">
					<button class="btn btn-primary" type="button"
						onclick="location.href='borderWriteForm.do'">새글쓰기</button>
				</c:if>
			</div>



		</div>
		<br />
		<!-- 페이징 -->
		<div align="center">
		<ul class="pagination">
			<c:forEach begin="1" end="${pageCnt}" varStatus="st">
				<c:if test="${param.p != st.count}">
					<li><a href="#" onclick="searchpage(${st.count})">${st.count}</a></li>
				</c:if>
				<c:if test="${param.p == st.count}">
			<li><a>${st.count}</a></li>
		</c:if>
			</c:forEach>
		</ul>
		</div>

		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>