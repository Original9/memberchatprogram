<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
%>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
<form id="frm" name="frm" action="mainboardDelete.do?key=${list.bNum}" method="post">
</form>
</div>
</body>
</html>
