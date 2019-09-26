package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;
import user.dao.UserDAO;

public class IdCheckCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("userID");
//		System.out.println(request.getParameter("userID"));
		boolean chk = UserDAO.getInstance().isIdCheck(id);
		String path = null;
		
		if(chk) {
			path="jsp/idOK.jsp";
			
		}else {
			path="jsp/idNO.jsp";
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

}
