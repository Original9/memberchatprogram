package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chat.InBoxDAO;
import user.command.Command;

public class inBoxListDeleteCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String chatNum = request.getParameter("chatNum");
		String userID = request.getParameter("userID");
		//System.out.println(userID);
		
		InBoxDAO dao = new InBoxDAO();
		dao.delete_chat_member(chatNum, userID);
		
		
		
		return "jsp/inBox.jsp";
	}

}
