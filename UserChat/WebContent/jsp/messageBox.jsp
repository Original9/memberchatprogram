<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		String toID = null; // 보내는 상대를 임의로 정해둔다.
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
		
		var chat_num_temp = null;  // 방번호를 전역변수 설정해서 넘겨 받았다.  나중에 메시지창열고 2초나 3초뒤에 메세지를 보낼수 있게 하자. 처음에 전역변수는 null 값이다.
	
		function autoClosingAlert(selector, delay){
			var alert = $(selector).alert();
			alert.show();
			window.setTimeout(function(){ alert.hide() }, delay);
		}
		function submitFunction(){
			//window.alert("submitFUnciont");
			var fromID = '<%= userID%>';
			var toID = '<%= toID%>';
			var chatContent = $('#chatContent').val();
			var chat_num = chat_num_temp;
			//console.log(chat_num);
			 // 방번호를 전역변수 설정해서 넘겨 받았다.  나중에 메시지창열고 2초나 3초뒤에 메세지를 보낼수 있게 하자. 처음에 전역변수는 null 값이다.
			
			$.ajax({ // ajax의 비동기적 통신
					type: "POST",
					url: "./chatSubmitServlet",
					data: {
						fromID: encodeURIComponent(fromID),
						toID: encodeURIComponent(toID),
						chatContent: encodeURIComponent(chatContent),
						chatNum: encodeURIComponent(chat_num),
						
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
		var lastID = 0;
		function chatListFunction(type){
			var fromID = '<%= userID%>';
			var toID = '<%= toID%>';
			$.ajax({
				type: "POST",
				url: "./chatListServlet",
				data: {
					fromID: encodeURIComponent(fromID),
					toID: encodeURIComponent(toID),		
					listType: type
				},
				success: function(data){
					if(data=="")return;
					var parsed = JSON.parse(data);
					var result = parsed.result;
					chat_num_temp = result[0][4].value; // 채팅방 번호를 하나 따온다.
					
					
					
					for(var i=0 ; i<result.length; i++){						
						if(result[i][0].value == fromID) {
							result[i][0].value = 'ME';
						}
						addChat(result[i][0].value, result[i][2].value, result[i][3].value);	
					}
					lastID = Number(parsed.last);// 가장 마지막으로 전달 받은 채팅ID
					
					
				}
			});
		}
		function addChat(chatName, chatcontent1, chatTime){
			$('#chatList').append('<div class="row">' +
							'<div class="col-lg-12">' +
							'<div class="media">' +
							'<a class="pull-left" href="#">' +
							'<img class="media-object img-circle" style="width: 30px; height: 30px" src="images/icon.png" alt="">' +
							'</a>' +
							'<div class="media-body">'+
							'<h4 div class="media-heading">'+
							chatName +
							'<span class="small pull-right">' +
							chatTime +
							'</span>' +
							'</h4>' +
							'<p>' +
							chatcontent1 +  // 여기서 chatContent 및에 변수랑 이름이 같아서  에러 났었음 
							'</p>' +
							'</div>' +
							'</div>' +
							'</div>' +
							'</div>' +
							'<hr>'); 
							
		$('#chatList').scrollTop($('#chatList')[0].scrollHeight);//스크롤 밑으로 내려주는거
		}
		function getinfinitechat(){ //새로운 메세지가 있는지 계속해서 확인
			setInterval(function(){				
				chatListFunction(lastID);
			}, 3000);
		}
	</script>
</head>
<body>	
	<jsp:include page="menu.jsp"></jsp:include>
	현재 접속 ID: <%=userID %> 메세지전달 ID: <%=toID%> <div id=chat_num >방번호</div>
	
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
						<div id="chatList" class="portlet-body chat-widget" style="overflow-y:auto; height: 600px">
						</div>
						<div class="portlet-footer">							
							<div class="row" style="height: 90px;">
								<div class="form-group col-xs-10">
									<textarea style="height: 80px;" id="chatContent" name="chatConent" class="form-control" placeholder="메세지를 입력하세요," maxlength="100"></textarea>
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
	
	
	<script type="text/javascript">
		$('#messagelModal').modal("show");
		$(document).ready(function(){
			chatListFunction('noten');
			getinfinitechat();
		});
	</script>
</body>

</html>