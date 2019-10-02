package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;
import user.dao.FriendDAO;

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
		

		return "jsp/redirect:friendList.jsp";
	}

}
