<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%
		String userID = null;
		String grant = null;
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
			grant = (String) session.getAttribute("grant");
		}
	%>
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<meta name = "viewport" content="width=device-width" initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
	<title>JSP Ajax 실시간 회원제 서비스</title>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script type="text/javascript">
		function getUnread(){
			$.ajax({
				type:"POST",
				url:"./chatUnread",
				data:{
					userID: encodeURIComponent('<%=userID%>'),
				},
				success: function(result){
					if(result >= 1){						
						showUnread(result);
					}else{
						showUnread('');
					}
				}
			})
		}
		function getInfiniteUnread(){
			setInterval(function(){
				getUnread();
			},4000);			
		}
		function showUnread(result){	
			$('#unread').html(result);
		}
	</script>
</head>
<body>
	
	<nav class = "navbar navbar-default">
		<div class="navbar-header">
				<button type="button" class = "navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
					<a class = "navbar-brand" href="index.jsp">실시간 회원 채팅서비스</a>				
			</div>	
			<div>						
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" >
					<ul class="nav navbar-nav">						
						<li class="active"><a href="index.jsp">메인</a></li>
						<li><a href="memberSearch.do">친구 찾기</a></li>
						<li><a href="friendList.do">친구목록</a></li>
			            <li><a href="inBox.do">메세지함<span id="unread" class="label label-info"></span></a></li>
              			<li><a href="boardList.do">자유 게시판</a></li>              			 
					</ul>
					<%
						if(userID == null){
					%>
					<ul class="nav navbar-nav navbar-right" >
						<li class="dropdown">
							<a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">접속하기<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href = "login.do">로그인</a></li>
								<li><a href = "join.do">회원가입</a></li>							
							</ul>
						</li>
					</ul>
					<%
						} else if(grant.equals("U")) {
					%>
					<ul class="nav navbar-nav navbar-right" >
						<li class="dropdown">
							<a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">회원관리<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href = "changeInfoForm.do">회원정보수정</a></li>
								<li><a href = "logout.do">로그아웃</a></li>							
							</ul>
						</li>
					</ul>
					<%
						} else {
					%>
					<ul class="nav navbar-nav navbar-right" >
						<li class="dropdown">
							<a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">회원관리<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href = "adminChangeInfoForm.do">회원정보관리</a></li>
								<li><a href = "logout.do">로그아웃</a></li>							
							</ul>
						</li>
					</ul>
					<%
						}
					%>
				</div>
			</div>		
	</nav> 

</body>
<%
	if(userID != null){
%>
	<script type="text/javascript">
		$(document).ready(function(){
			getInfiniteUnread();
		});
	</script>
<%
	}
%>
</html>