package chat;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.JDBCutil;

/**
 * Servlet implementation class ChatSubmitServlet
 */			  
@WebServlet("/ChatSubmitServlet")
public class ChatSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("HERE IS CHATSUBMITSERVLET PAGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String fromID= request.getParameter("fromID");
		String toID = request.getParameter("toID");
		String chatContent = request.getParameter("chatContent");
		String chatNum = request.getParameter("chatNum"); // 방번호를 넘겨 받음
		//////////////////////////////////////////////10/04일 이까지 ~~ 다음주에 방번호 받아온걸로 이어서하자 
		//int chat_num ="";
		//System.out.println("fromID:" + fromID + "  toID: " + toID + "  chatContent: " + chatContent);
		if(fromID == null || fromID.equals("") || toID == null || toID.equals("")
				|| chatContent == null || chatContent.equals("")) {
			response.getWriter().write("0");
		} else {
			fromID = URLDecoder.decode(fromID, "UTF-8");
			toID = URLDecoder.decode(toID, "UTF-8");
			chatContent = URLDecoder.decode(chatContent, "UTF-8");
			// chat_room 생성됬는지 확인  
			
			
			response.getWriter().write(new ChatDAO().submit(fromID, toID, chatContent,chatNum)+ "");
			
		}
		
	}

}
