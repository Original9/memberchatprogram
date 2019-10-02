<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<div align="center">
   <div><h1>�� ���� ����</h1></div>
   <div>
      <table border="1">
         <tr>
            <th width="100">�ۼ���</th>
            <td width="100" align="center">${list.bName }</td>
            <th width="100">�ۼ���</th>
            <td width="100" align="center">${list.bWriteDate }</td>
            <th width="100">��ȸ��</th>
            <td width="100" align="center">${list.bHit }</td>
         </tr>
         <tr>
            <th width="100">����</th>
            <td colspan="5" align="center">${list.bTitle }</td>
         </tr>
         <tr>
            <th width="100" height="300">����</th>
            <td colspan="5" align="center">${list.bContent }</td>
         </tr>
         <tr>
            <th width="100">÷������</th>               
             <td colspan="5" align="center"> <a href='FileDownloadAction.do?file_name=${list.bFileName}'>${list.bFileName}</a><button input type="button" onclick="location.href='FileDownloadAction.do?file_name=${list.bFileName}'">���ϴٿ�</button> </td>  
         </tr>
      </table>
   </div><br />
   <div>
   <!-- ���� �־ ���ǰ��� ������ ���̰� �ƴϸ� �Ⱥ��̰� �ϸ�ȴ�. -->
   <% String id = (String) session.getAttribute("id"); %>
      <c:if test = "${empty id }">
      <button type="button" onclick="location.href='boarderList.do'"> �� ��Ϻ���</button> &nbsp;&nbsp;      
      </c:if>
      <c:if test = "${not empty id }">
      <button type="button" onclick="location.href='boardUpdateForm.do?key=${list.bNum}'">�ۼ���</button> &nbsp;&nbsp;      
      <button type="button" onclick="location.href='.do'"> �� ����</button> &nbsp;&nbsp;
      <button type="button" onclick="location.href='boarderList.do'"> �� ��Ϻ���</button> &nbsp;&nbsp;
      </c:if>
   </div><br />
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>