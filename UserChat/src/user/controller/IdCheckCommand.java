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
	public String excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("userID");
//		System.out.println(request.getParameter("userID"));
		boolean chk = UserDAO.getInstance().isIdCheck(id);
		String path = null;
		
		if(chk) {
			path="{\"result\":true, \"message\":\"사용가능한 아이디 입니다.\"}";
			
		}else {
			path="{\"result\":false, \"message\":\"사용 불가능한 아이디 입니다.\"}";
		}
		
		return "ajax:"+path;
	}
	
	

}
