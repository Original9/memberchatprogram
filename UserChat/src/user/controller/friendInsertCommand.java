package user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.command.Command;
import user.dao.FriendDAO;
import user.dto.UserDTO;

public class friendInsertCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String friendID = null; 
		if(request.getParameter("toID") != null){
			friendID = (String) request.getParameter("toID");			
		}
		String userID = request.getParameter("userID");
		if(request.getParameter("userID") != null) {
			userID = (String)request.getParameter("userID");
		}
		// 친구 추가할때 이미 추가되어있는지 확인하는거 넣어야함
		FriendDAO dao = new FriendDAO();
		dao.insertFriend(userID, friendID);	
		
		HttpSession session = request.getSession(false); // 세션을 없으면 굳이 만들지 말라 (false)
		
		String userID1 = (String)session.getAttribute("userID");		
		if(request.getParameter("userID") != null) {
			userID = (String)request.getParameter("userID");
		}
				
		//FriendDAO dao = new FriendDAO();
		
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		list = dao.friendList(userID1);		
		request.setAttribute("list", list);

		return "jsp/friendList.jsp";
	}

}
