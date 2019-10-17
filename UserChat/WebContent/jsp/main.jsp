<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="user.dao.UserDAO"%>
<%@ page import="user.dto.UserDTO"%>
<%@ page import="user.dao.BoarderDAO"%>
<%@ page import="user.dto.BoarderDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
@import
   url('https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap&subset=korean')
   ;
@import
	url('https://fonts.googleapis.com/css?family=Sunflower:300&display=swap')
	;
</style>
<script>

function searchpage(p){
   document.frm.p.value=p;
   document.frm.submit();
}

</script>
</head>
<body>
<div style="width:100%; height:100%; position:fixed; background:url('images/paperplane4.jpg') no-repeat;background-size: cover;" align="center">

   <jsp:include page="menu.jsp"></jsp:include>
   <%
      String userID = null;
      if (session.getAttribute("userID") != null) {
         userID = (String) session.getAttribute("userID");
      }
   %>
   <div>
      <br />
      <br />
      <br />
      <br />
   </div>
   <div>
      <div>
         <h1 style="font-size: 50px; font-family: 'Nanum Brush Script', cursive;">공 지 사 항</h1>
      </div>
      <div>
         <form id="frm" name="frm" action="main.do">
            <input type="hidden" name="p" id="p" value="1">

            <table width="400px" height="100px">

               <td>&nbsp;&nbsp;&nbsp;</td>
               <td><input class="form-control" type="text" id="search"
                  name="search" value="${param.search }"></td>
               <td>&nbsp;&nbsp;&nbsp;</td>
               <td><input type="submit" class="btn btn-default"
                  id="searchbtn" name="searchbtn" value="검색"></td>
            </table>

         </form>
         <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8" align="center">
               <table class="table table-striped" style="background-color:#a4eded;" align="center">
                  <tr>
                     <th style="font-family: 'Sunflower', sans-serif;" class="text-center">번호</th>
                     <th style="font-family: 'Sunflower', sans-serif; width:500px" class="text-center">제목</th>
                     <th style="font-family: 'Sunflower', sans-serif;" class="text-center">작성자</th>
                     <th style="font-family: 'Sunflower', sans-serif;" class="text-center">작성일</th>
                     <th style="font-family: 'Sunflower', sans-serif; width:300px" class="text-center">첨부파일</th>
                     <th style="font-family: 'Sunflower', sans-serif;" class="text-center">조회수</th>
                  </tr>

                  <!-- db의 글목록을 가져와서 뿌려주는 곳~ -->
                  <c:if test="${list.isEmpty()}">
                     <tr>
                        <td colspan="5">등록된 글이 존재하지 않습니다</td>
                     </tr>
                  </c:if>
                  <c:forEach items="${list }" var="dto">
                     <tr onclick="location.href='mainboardRead.do?key=${dto.mbNum }'">
                        <td align="center">${dto.mbNum }</td>
                        <td align="center">${dto.mbTitle }</td>
                        <td align="center">${dto.mbId }</td>
                        <td align="center">${dto.mbWriteDate }</td>
                        <td align="center">${dto.mbfileName }</td>
                        <td align="center">${dto.mbHit }</td>
                     </tr>
                  </c:forEach>
               </table>
            </div>
            <div class="col-md-2"></div>
         </div>
      </div>
      <br />
      <div>
         <c:if test="${grant == 'M' }">
            <button class="btn btn-info" type="button"
               onclick="location.href='MainBorderWriteForm.do'">새글쓰기</button>
         </c:if>
      </div>


   </div>
   <br />
   <!-- 페이징 -->
   <div align="center">
   <ul class="pagination">
      <c:forEach begin="1" end="${pageCnt}" varStatus="st">
         <c:if test="${param.p != st.count}">
            <li><a href="#" onclick="searchpage(${st.count})">${st.count}</a></li>
         </c:if>
         <c:if test="${param.p == st.count}">
         <li><a>${st.count}</a></li>
      </c:if>
      </c:forEach>
   </ul>
   </div>

   <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>