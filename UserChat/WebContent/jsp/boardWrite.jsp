<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	String userID = null;
	if(session.getAttribute("userID") != null){
		userID = (String) session.getAttribute("userID");
	}
	
	if(userID == null){
		session.setAttribute("messageType", "오류 메세지");
		session.setAttribute("messageContent", "현재 로그인이 되어 있지 않습니다.");
// 		response.sendRedirect("index.jsp");
		return;
	}

%>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
a
</body>
</html>