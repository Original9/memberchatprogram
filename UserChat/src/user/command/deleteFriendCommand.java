package user.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspApplicationContext;

import user.dao.FriendDAO;
import user.dto.UserDTO;

public class deleteFriendCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false); // 세션을 없으면 굳이 만들지 말라 (false)

		String userID = (String)session.getAttribute("userID");		
		String friendID = (String) request.getParameter("toID");
		
		FriendDAO dao = new FriendDAO();
		dao.deleteFriend(userID, friendID);
		
			
		//FriendDAO dao = new FriendDAO();
		
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		list = dao.friendList(userID);		
		request.setAttribute("list", list);
		
		
		return "jsp/friendList.jsp";
	}

}
