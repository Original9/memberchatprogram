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



<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
  <style>
    label, input { display:block; }
    input.text { margin-bottom:12px; width:95%; padding: .4em; }
    fieldset { padding:0; border:0; margin-top:25px; }
    h1 { font-size: 1.2em; margin: .6em 0; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
  </style>


  


</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  	
  	
  	<script>
  $( function() {
    var dialog, form,
 
      // From http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
      emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
      name = $( "#name" ),
      email = $( "#email" ),
      password = $( "#password" ),
      allFields = $( [] ).add( name ).add( email ).add( password ),
      tips = $( ".validateTips" );
 
    function updateTips( t ) {
      tips
        .text( t )
        .addClass( "ui-state-highlight" );
      setTimeout(function() {
        tips.removeClass( "ui-state-highlight", 1500 );
      }, 500 );
    }
 
    function checkLength( o, n, min, max ) {
      if ( o.val().length > max || o.val().length < min ) {
        o.addClass( "ui-state-error" );
        updateTips( "Length of " + n + " must be between " +
          min + " and " + max + "." );
        return false;
      } else {
        return true;
      }
    }
 
    function checkRegexp( o, regexp, n ) {
      if ( !( regexp.test( o.val() ) ) ) {
        o.addClass( "ui-state-error" );
        updateTips( n );
        return false;
      } else {
        return true;
      }
    }
 
    function addUser() {
      var valid = true;
      allFields.removeClass( "ui-state-error" );
 
      /* valid = valid && checkLength( name, "username", 3, 16 );
      valid = valid && checkLength( email, "email", 6, 80 );
      valid = valid && checkLength( password, "password", 5, 16 );
 
      valid = valid && checkRegexp( name, /^[a-z]([0-9a-z_\s])+$/i, "Username may consist of a-z, 0-9, underscores, spaces and must begin with a letter." );
      valid = valid && checkRegexp( email, emailRegex, "eg. ui@jquery.com" );
      valid = valid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" ); */
 
      if ( valid ) { //.do 호출
    	document.frm.submit();  
        /* $( "#users tbody" ).append( "<tr>" +
          "<td>" + name.val() + "</td>" +
          "<td>" + email.val() + "</td>" +
          "<td>" + password.val() + "</td>" +
        "</tr>" ); */
        dialog.dialog( "close" );
      }
      return valid;
    }
 
    dialog = $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 800,
      width: 700,
      modal: true,
      buttons: {
        "수정": addUser,
        "취소": function() {
          dialog.dialog( "close" );
        }
      },
      close: function() {
        form[ 0 ].reset();
        allFields.removeClass( "ui-state-error" );
      }
    });
 
    form = dialog.find( "form" ).on( "submit", function( event ) {
      event.preventDefault();
      addUser();
    });
 
    $( "body" ).on( "click", "[id^=update-user]",function() {
      dialog.dialog( "open" );
      var td = $(this).parent().parent(); //this = 선택된 버튼.
      //console.log($(td).children().eq(0));
      var tdLen = td[0].cells.length;
      
      console.log(td.children().eq(0).text());
      
      var updateForm = document.frm;
      
      for(i=1;i<tdLen-1;i++){
    	  updateForm[i].value = td.children().eq(i-1).text();
      }
      
    });
    
    $( "body" ).on( "click", "[id^=delete-user]",function() {
    	
    	var confirmStatus = confirm("정말로 회원을 삭제 하시겠습니까?");
    	
    	if(confirmStatus){
        
        var td = $(this).parent().parent(); //this = 선택된 버튼.
        //console.log($(td).children().eq(0));
        var tdLen = td[0].cells.length;
        
        console.log(td.children().eq(0).text());
        
        var updateForm = document.frm;
        
        for(i=1;i<tdLen-1;i++){
      	  updateForm[i].value = td.children().eq(i-1).text();
        }
        location.href="adminDeleteUser.do?key="+$('#id').val();
        
    	}
        
      });
  } );
  </script>
  
  
  
	
	<div align="center">
	<table id="userList" class="table table-striped table-bordered" style="width:80%">

		<thead>

			<tr>

				<th>아이디</th>

				<th>비밀번호</th>

				<th>이름</th>

				<th>나이</th>
				
				<th>성별</th>
				
				<th>이메일</th>
				
				<th>등급</th>
				
				<th>-</th>

			</tr>

		</thead>

		<tbody>

			<c:if test="${list.isEmpty()}">
				<tr>
					<td colspan="4">등록된 회원이 존재하지 않습니다</td>
				</tr>
			</c:if>
			<c:forEach items="${list }" var="dto">
				<tr>  <%-- onclick="location.href='#.do?key=${dto.userID}'" 클릭 시 #.do로 키값 넘기는 옵션--%>
					<td id="userID" align="center">${dto.userID}</td>
					<td id="userPassword" align="center">${dto.userPassword}</td>
					<td id="userName" align="center">${dto.userName}</td>
					<td id="userAge" align="center">${dto.userAge}</td>
					<td id="userGender" align="center">${dto.userGender}</td>
					<td id="userEmail" align="center">${dto.userEmail}</td>
					<td id="userGrant" align="center">${dto.userGrant}</td>
					<td align="center"><button id="update-user${status.count}" class="btn btn-primary">수정</button>&nbsp;&nbsp;&nbsp;&nbsp;<button id="delete-user${status.count}" class="btn btn-primary">삭제</button></td>
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

<div id="dialog-form" title="회원정보수정" >
  <p class="validateTips">All form fields are required.</p>
 
  <form name="frm" action="adminUpdateUser.do">
    <fieldset>
      <label for="id">ID</label>
      <input type="text" name="userID" id="id" value="" class="text ui-widget-content ui-corner-all" readOnly="readOnly">
      <label for="password">Password</label>
      <input type="password" name="userPassword" id="password" value="xxxxxxx" class="text ui-widget-content ui-corner-all">
      <label for="name">Name</label><!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="gender">Gender</label> -->
      <input type="text" name="userName" id="name" value="Jane Smith" class="text ui-widget-content ui-corner-all">
      <label for="age">Age</label>
      <input type="text" name="userAge" id="age" value="100" class="text ui-widget-content ui-corner-all">
      <label for="gender">Gender</label>
      <input type="text" name="userGender" id="gender" value="F" class="text ui-widget-content ui-corner-all">
      <label for="email">Email</label>
      <input type="text" name="userEmail" id="email" value="jane@smith.com" class="text ui-widget-content ui-corner-all">
      <label for="grade">Grade</label>
      <input type="text" name="userGrant" id="grade" value="U" class="text ui-widget-content ui-corner-all">
 
      <!-- Allow form submission with keyboard without duplicating the dialog button -->
      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
    </fieldset>
  </form>
</div>

</body>
</html>