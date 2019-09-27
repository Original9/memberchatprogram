package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.command.Command;
import user.dao.UserDAO;
import user.dto.UserDTO;

public class LoginCheckCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDTO dto = new UserDTO();
		UserDAO dao = UserDAO.getInstance();
		
		String path=null;
		HttpSession session = request.getSession(false);
		
		dto.setUserID(request.getParameter("id"));
		dto.setUserPassword(request.getParameter("pw"));
		
		dto=dao.loginCheck(dto);
		
		if(dto.getUserGrant()!=null) {
			System.out.println(dto.getUserGrant());
			session.setAttribute("userID", dto.getUserID());
			session.setAttribute("grant", dto.getUserGrant());
			session.setAttribute("name", dto.getUserName()); 
			System.out.println("로그인성공");
			path="jsp/main.jsp";
		}else {
			System.out.println("로그인실패");
			path="jsp/main.jsp";
		}
		
		return path;
		
	}

}
