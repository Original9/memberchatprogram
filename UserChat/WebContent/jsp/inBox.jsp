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
		if(userID == null){
			session.setAttribute("messageType","오류메세지");
			session.setAttribute("messageContent" , "현재 로그인이 되어있지 않습니다.");
			response.sendRedirect("index.jsp"); // 다시 돌려보냄?
			return;
		}
%>
<meta charset="UTF-8">
<title>InBox</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	
	
	$(document).ready(function(){
		var id = '<%= userID%>';
		$.ajax({
			type: "POST",
			url:"./chatBox",
			data: {					 
				id :  encodeURIComponent(id),
			},			
			success: function(data){
				
				
				
			}
			
		})
	});
	
</script>
</head>
<jsp:include page="menu.jsp"></jsp:include>
<body>
<div class="container">
		<table class="table table-bordered table-bover" style="text-align: center; border: 1px solid #dddddaa">
			<thead>
				<tr>
					<th colspan="5"><h4>메세지 목록</h4></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td style="width: 100px"><h5> ID</h5></td>
					<td style="width: 100px"><h5> NAME</h5></td>
					<td style="width: 100px"><h5> 안읽은 메세지</h5></td>
					<td style="width: 20px"><h5>SEND MESSAGE</h5></td>
					<td style="width: 20px"><h5>DELETE</h5></td>						
				</tr>
				<c:if test="${list.isEmpty()}">
				<tr>
				<td colspan="5"> 추가된 친구가 없습니다. </td>
				</tr>
				</c:if>
							
				
				
			</tbody>			
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