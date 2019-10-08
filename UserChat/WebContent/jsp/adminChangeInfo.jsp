<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
  
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script> -->



<!-- datatables에서 가져온 부분. -->
<!-- <script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.3.1.js"></script> -->


<!-- 블로그에서 추가한 부분 -->
<%-- <link href="<c:url value='/etc/bootstrap-3.3.2-dist/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/etc/bootstrap-3.3.2-dist/css/bootstrap-theme.css' />" rel="stylesheet" type="text/css">
<link href="//cdn.datatables.net/plug-ins/f2c75b7247b/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="<c:url value='/js/jquery-1.11.2.min.js' />" ></script>
<script type="text/javascript" src="<c:url value='/etc/DataTables-1.10.5/media/js/jquery.dataTables.min.js' />" ></script>
<script type="text/javascript" src="//cdn.datatables.net/plug-ins/f2c75b7247b/integration/bootstrap/3/dataTables.bootstrap.js" ></script>
 --%>
<%-- <link href="<c:url value='/etc/bootstrap-3.3.2-dist/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/etc/bootstrap-3.3.2-dist/css/bootstrap-theme.css' />" rel="stylesheet" type="text/css">
<link href="//cdn.datatables.net/plug-ins/f2c75b7247b/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet" type="text/css">


<link rel="stylesheet" href="css/bootstrap.css">


<script type="text/javascript" src="<c:url value='/js/jquery-1.11.2.min.js' />" ></script>
<script type="text/javascript" src="<c:url value='/etc/DataTables-1.10.5/media/js/jquery.dataTables.min.js' />" ></script>
<script type="text/javascript" src="//cdn.datatables.net/plug-ins/f2c75b7247b/integration/bootstrap/3/dataTables.bootstrap.js" ></script> --%>





</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div align="center">
	<table id="userList" class="table table-striped table-bordered" style="width:80%">

		<thead>

			<tr>

				<th>아이디</th>

				<th>이름</th>

				<th>나이</th>

				<th>이메일</th>

			</tr>

		</thead>

		<tbody>

			<c:if test="${list.isEmpty()}">
				<tr>
					<td colspan="4">등록된 회원이 존재하지 않습니다</td>
				</tr>
			</c:if>
			<c:forEach items="${list }" var="dto">
				<tr onclick="location.href='#.do?key=${dto.userID}'">
					<td align="center">${dto.userID}</td>
					<td align="center">${dto.userName}</td>
					<td align="center">${dto.userAge}</td>
					<td align="center">${dto.userEmail}</td>
				</tr>
			</c:forEach>


			<%-- <tr th:each="data : ${list}">

      <td th:text="${list.userID}"></td>

      <td th:text="${list.userName}"></td>

      <td th:text="${list.userAge}"></td>

      <td th:text="${list.userEmail}"></td>

    </tr> --%>

		</tbody>

	</table>
	</div>

<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$('#userList').DataTable();
});
</script>
</body>
</html>