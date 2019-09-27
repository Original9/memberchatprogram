<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
	<%
		String userID = null;
		session.setAttribute("userID", "jey");
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
		String toID = null;
		if(request.getParameter("toID") != null){
			toID = (String) request.getParameter("toID");
		}
	%>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<meta name = "viewport" content="width=device-width" initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		function autoClosingAlert(selector, delay){
			var alert = $(selector).alert();
			alert.show();
			window.setTimeout(function{ alert.hide() }, delay);
		}
		function submintFunction(){
			var fromID = '<%= userID%>';
			var toID = '<%= toID%>';
			var chatContent = $('#chatContent').val();
			$.ajax({ // ajax의 비동기적 통신
					type: "POST",
					url: "./chatSubmitservlet",
					data: {
						fromID: encodeURIComponent(fromID),
						toID: encodeURIComponent(toID),
						chatContent: : encodeURIComponent(chatContent),
					},
					success: function(result){
						if(result == 1){
							autoClosingAlert('#successMessage', 2000);
						} else if(result == 0){
							autoClosingAlert('#dangerMessage', 2000);
						} else {
							autoClosingAlert('#warningMessage', 2000);
						}
					}					
			});
			$('#chatContent').val('');
		}
	</script>
</head>
<body>	
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="container bootstrap snippet">
		<div class="row">
			<div class="col-xs-12">
				<div class ="portlet portlet-default">
					<div class="portlet-heading">
						<div class="portlet-title">
							<h4><i class="fa fa-circle text-white">실시간 채팅창</i></h4>
						</div>					
						<div class="clearfix"></div>	
					</div>
					<div id="chat" class="panel-collapse collapse in">
						<div id="chatList" class="parlet-body chat-widget" style="overflow-y:auto; height: 600px">
						</div>
						<div class="portlet-footer">							
							<div class="row" style="height: 90px;">
								<div class="form-group col-xs-10">
									<textarea style="height: 80px;" id="chatContent" class="form-control" placeholder="메세지를 입력하세요," maxlength="100"></textarea>
								</div>
								<div class="form-group col-xs-2">
									<button type="button" class="btn btn-default pull-right" onclick="submitFunction()">전송</button>
									<div class="clearfix"></div>								
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="alert alert-success" id="successMessage" style="display: none;">
		<strong>메세지 전송에 성공했습니다.</strong>
	</div>
	<div class="alert alert-danger" id="dangerMessage" style="display: none;">
		<strong>이름과 내용을 모두 입력해주세요.</strong>
	</div>
	<div class="alert alert-warning" id="databaseMessage" style="display: none;">
		<strong>데이터베이스 오류가 발생했습니다..</strong>
	</div>
</body>
</html>