<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<meta name="viewport" content="width=device-width" initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>JSP Ajax 실시간 회원제 서비스</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
</head>
<body>

   <%
      String userID = null;
      if (session.getAttribute("userID") != null) {
         userID = (String) session.getAttribute("userID");
      }
   %>
   <nav class="navbar navbar-default">
      <div class="navbar-header">
         <button type="button" class="navbar-toggle collapsed"
            data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
            aria-expanded="false">
            <span class="icon-bar"></span> <span class="icon-bar"></span> <span
               class="icon-bar"></span>
         </button>
         <a class="navbar-brand" href="index.jsp">실시간 회원 채팅서비스</a>
      </div>
      <div>
         <div class="collapse navbar-collapse"
            id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">

               <li><a href="index.jsp">메인</a>
               <li><a href="find.jsp">친구 찾기</a></li>
               <li><a href="box.jsp">메세지함<span id="unread"
                     class="label label-info"></span></a>
               <li><a href="boardView.jsp">자유 게시판</a></li>

            </ul>
            <%
               if (userID == null) {
            %>
            <ul class="nav navbar-nav navbar-right">
               <li class="dropdown"><a href="#" class="dropdown-toggle"
                  data-toggle="dropdown" role="button" aria-haspopup="true"
                  aria-expanded="false">접속하기<span class="caret"></span>
               </a>
                  <ul class="dropdown-menu">
                     <li><a href="login.jsp">로그인</a></li>
                     <li><a href="join.jsp">회원가입</a></li>
                  </ul></li>
            </ul>
            <%
               } else {
            %>
            <ul class="nav navbar-nav navbar-right">
               <li class="dropdown"><a href="#" class="dropdown-toggle"
                  data-toggle="dropdown" role="button" aria-haspopup="true"
                  aria-expanded="false">회원관리<span class="caret"></span>
               </a></li>
            </ul>
            <%
               }
            %>
         </div>
      </div>
   </nav>
   <div class="container">
      <table class="table table=bordered table-hover" style="text-align:center;
      border: 1px solid #dddddd">
      <thread>
         <tr>
            <th colspan="5"><h4>자유 게시판</h4></th>
         </tr>
         <tr>
            <th style="background-color:#fafafa; color: #000000; width: 70px;"><h5>번호</h5></th>
            <th style="background-color:#fafafa; color: #000000;"><h5>글제목</h5></th>
            <th style="background-color:#fafafa; color: #000000;"><h5>작성자</h5></th>
            <th style="background-color:#fafafa; color: #000000; width: 100px;" ><h5>작성 날짜</h5></th>
            <th style="background-color:#fafafa; color: #000000; width: 70px;"><h5>조회수</h5></th>
         </tr>
      </thread>
      <tbody>
         <tr>
            <td>1</td>
            <td>안녕 하세요</td>
            <td>홍길동</td>
            <td>2019-09-25</td>
            <td>1</td>
         </tr>
         
         <tr>
            <td colspan="5"><a href="boardwrite.jsp" class="btn btn=primary pull-right"
            type="submit">글쓰기</a></td>
         </tr>
      </tbody>   
      </table>
   </div>
   
   <div class="container">
      <form method="post" action="./userRegister">
         <table class="table table-bordered table-hover"
            style="text-align: center; border: 1px solid #dddddd">
            <thead>
               <tr>
                  <th colspan="3"><h4>회원 등록 양식</h4></th>
               </tr>
            </thead>
            <tbody>
               <tr>
                  <td style="width: 110px;"><h5>아이디</h5></td>
                  <td><input class="form-control" type="text" id="userID"
                     name="userID" maxlength="20" placeholder="아이디를 입력하세요."></td>
                  <td style="width: 110px"><button class="btn btn-primary"
                        onclick="registerCheckFunction(); type="button">중복체크</button></td>
               </tr>
               <tr>
                  <td style="width: 110px;"><h5>비밀번호</h5></td>
                  <td colspan="2"><input onkeyup="passwordCheckFunction();"
                     class="form-control" type="password" id="userPassword1"
                     name="userPassword1" maxlength="20" placeholder="비밀번호를 입력하세요."></td>
               </tr>
               <tr>
                  <td style="width: 110px;"><h5>비밀번호 확인</h5></td>
                  <td colspan="2"><input onkeyup="passwordCheckFunction();"
                     class="form-control" type="password" id="userPassword2"
                     name="userPassword2" maxlength="20" placeholder="비밀번호 확인을 입력하세요."></td>
               </tr>
               <tr>
                  <td style="width: 110px;"><h5>이름</h5></td>
                  <td colspan="2"><input class="form-control" type="text"
                     id="userName" name="userName" maxlength="20"
                     placeholder="이름을 입력하세요."></td>
               </tr>
            </tbody>
         </table>
      </form>
   </div>

</body>
</html>