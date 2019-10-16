package user.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chat.InBoxDAO;

public class inBoxListDeleteCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String chatNum = request.getParameter("chatNum");
		String userID = request.getParameter("userID");
	
		
		InBoxDAO dao = new InBoxDAO();
		dao.delete_chat_member(chatNum, userID);
		
		
		return "jsp/inBox.jsp";
	}

}
