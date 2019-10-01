<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
		String userID = null;
		//userID = "jey"; // 로그인 임시로 접속가능하도록 세션값을 박아둔다
		//session.setAttribute("userID", "jey");
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID");
		}
		if(userID == null){
			session.setAttribute("messageType","오류메세지");
			session.setAttribute("messageContent" , "현재 로그인이 되어있지 않습니다.");
			response.sendRedirect("index.jsp"); // 다시 돌려보냄?
			return;
		}
	%>
<script type="text/javascript">
	function findFunction(){
		var userID = $('#findID').val();
		$.ajax({
			type: "POST",
			url: './userFindServlet',
			data: {userID: userID},
			success: function(result1){
				console.log(result1);		
				$('#checkMessage').html('친구찾기에 성공했습니다.')
				$('#checkType').attr('class', 'modal-content panel-success' );
				getFriend(userID);
				if(result1 == 0){
					$('#checkMessage').html('친구찾기에 성공했습니다.')
					$('#checkType').attr('class', 'modal-content panel-success' );
					getFriend(userID);
				}else{
					$('#checkMessage').html('친구를 찾을수 없습니다.');
					$('#checkType').attr('class','modal-content panel-warning');					
					failFriend();
				}
				$('#checkModal').modal("show");
			}
		})
	}
	function getFriend(FindID){
		$('#friendResult').html('<thead>' + 
				'<tr>' +
				'<th><h4>검색결과</h4></th>' +
				'</tr>' +
				'</thead>' +
				'<tbody>' + 
				'<tr>' +				
				'<td style="text-align: center;"><h3>'+FindID+'</h3><a href="messageBox.do?toID='+encodeURIComponent(FindID)+'" class="btn btn-primary pull-right">'+'메세지 보내기</a></td>'+		
				'</tr>' +
				'</tbody>');
		
	}
	function failFriend(){
		$('#checkMessage').html('');
	}
	

</script>
<body>
<jsp:include page="menu.jsp"></jsp:include>
	<div class="container">
		<table class="table table-bordered table-bover" style="text-align: center; border: 1px solid #dddddaa">
			<thead>
				<tr>
					<th colspan="2"><h4>검색으로 친구 찾기</h4></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td style="width: 100px"><h5>친구 아이디</h5></td>
					<td><input class="form-control" type="text" id="findID" name="findID" maxlength="20" placeholder="찾을 아이디를 입력하시오"></td>
				</tr>
				<tr>
					<td colspan="2"><button class="btn btn-primary pull-right" onclick="findFunction()">검색</button></td>
				</tr>
			</tbody>			
		</table>	
	</div>
	<div class="container">
		<table id="friendResult" name="friendResult" class="table table-bordered table-hover" style="text-align: center; border: 1px solid #ddddda" >
					
		</table>		
	</div>
	<%
		String messageContent = null;
		if(session.getAttribute("messageContent") != null){
			messageContent = (String) session.getAttribute("messageContent");
		}
		String messageType = null;
		if(session.getAttribute("messageType") != null){
			messageType = (String) session.getAttribute("messageType");			
		}
		if(messageContent !=null){
	%>
	<div class="modal fade" id="checkModal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div id="checkType" class="modal-content panel-info">
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal"></button>
							<span aria-hidden="true">&time</span>
							<span class="sr-only">Close</span>
							<h4 class="modal-title">확인메세지
							</h4>
					</div>
					<div id="checkMessage" class="modal-body">
					</div>
					<div id="modal-fotter">
						<button type="button" class="btn btn-primary-default" data-dismiss="modal"  >확인</button>						
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<%} %>

</body>
</html>