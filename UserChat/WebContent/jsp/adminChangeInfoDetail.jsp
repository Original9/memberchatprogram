<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Dialog - Modal form</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <style>
    label, input { display:block; }
    input.text { margin-bottom:12px; width:95%; padding: .4em; }
    fieldset { padding:0; border:0; margin-top:25px; }
    h1 { font-size: 1.2em; margin: .6em 0; }
    div#users-contain { width: 350px; margin: 20px 0; }
    div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
    div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
  </style>
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
 
      valid = valid && checkLength( name, "username", 3, 16 );
      valid = valid && checkLength( email, "email", 6, 80 );
      valid = valid && checkLength( password, "password", 5, 16 );
 
      valid = valid && checkRegexp( name, /^[a-z]([0-9a-z_\s])+$/i, "Username may consist of a-z, 0-9, underscores, spaces and must begin with a letter." );
      valid = valid && checkRegexp( email, emailRegex, "eg. ui@jquery.com" );
      valid = valid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );
 
      if ( valid ) {
        $( "#users tbody" ).append( "<tr>" +
          "<td>" + name.val() + "</td>" +
          "<td>" + email.val() + "</td>" +
          "<td>" + password.val() + "</td>" +
        "</tr>" );
        dialog.dialog( "close" );
      }
      return valid;
    }
 
    dialog = $( "#dialog-form" ).dialog({
      autoOpen: false,
      height: 400,
      width: 350,
      modal: true,
      buttons: {
        "Create an account": addUser,
        Cancel: function() {
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
 
    $( "#create-user" ).button().on( "click", function() {
      dialog.dialog( "open" );
    });
  } );
  </script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div><br /><br /><br /></div>
	<div class="container">
	<div class="col-md-2"></div>
	<div class="row">
	<div class="col-md-8">
		<form method="post" action="changeInfo.do" id="frm" name="frm">
				<input type="hidden" id="ranNum" name="ranNum">
				<input type="hidden" id="Email" name="Email">
			<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3"><h4>회원 정보 수정</h4></th>
					</tr>				
				</thead>
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>아이디</h5></td>
						<td colspan="2"><input class="form-control" type="text" id="userID" name="userID" maxlength="20" value="${list.userID }" readOnly="readOnly"></td>
						
					</tr>
					<tr>
						<td style="width: 130px;"><h5>비밀번호</h5></td>
						<td style="border-right:0px"><input class="form-control" type="password" id="userPassword1" name="userPassword1" maxlength="20" value="●●●●●●" readOnly="readOnly"></td>						
						<td style="width: 110px; border-left:0px"><button class="btn btn-primary" id="btnChangePW"
										type="button">비밀번호변경</button>&nbsp;
									</td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이름</h5></td>
						<td colspan="2"><input class="form-control" type="text" id="userName" name="userName" maxlength="20" value="${list.userName }"></td>						
					</tr>
					<tr>
						<td style="width: 110px;"><h5>나이</h5></td>
						<td colspan="2"><input class="form-control" type="number" id="userAge" name="userAge" maxlength="20" value="${list.userAge }"></td>						
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이메일</h5></td>
						<td style="border-right:0px"><input class="form-control" type="email" id="userEmail" name="userEmail" maxlength="20" value="${list.userEmail }" readOnly="readOnly"><br>
						<p style="display:none" align="left" id="ranNumInputTitle">인증번호 입력 : <input type="text" id="checkRanNum">&nbsp;<button class="btn btn-primary"
									id="btnRanNumCheck" type="button">확인</button></p><p align="right" id=EmailCheckResult style="color:red"></p></td>
								<td style="width: 110px; border-left:0px;"><button class="btn btn-primary"
									id="btnEmailCheck" type="button">이메일 인증</button>&nbsp;
						</td>						
						
					</tr>
				</tbody>
			</table>
			<p>&nbsp;&nbsp; *아이디는 수정할 수 없습니다.</p>
				<div align="center">
					<input type="button" class="btn btn-primary" onclick="changeInfo()" value="변경">&nbsp;&nbsp;&nbsp;
					<input type="reset" class="btn btn-primary" onclick="location.href='changeInfoForm.do'" value="취소">
				</div>
		</form>
		</div>
		</div>
	<div class="col-md-2"></div>
	</div>
</body>
</html>