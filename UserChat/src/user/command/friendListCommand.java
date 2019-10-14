package user.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import user.dao.FriendDAO;
import user.dao.UserDAO;
import user.dto.UserDTO;

public class friendListCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false); // 세션을 없으면 굳이 만들지 말라 (false)
		
		String userID = (String)session.getAttribute("userID");		
		if(request.getParameter("userID") != null) {
			userID = (String)request.getParameter("userID");
		}
				
		FriendDAO dao = new FriendDAO();
		
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		list = dao.friendList(userID);		
		request.setAttribute("list", list);
			
		

		return "jsp/friendList.jsp";
	}

}
