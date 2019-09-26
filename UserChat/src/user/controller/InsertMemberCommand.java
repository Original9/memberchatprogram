package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;
import user.dao.UserDAO;
import user.dto.UserDTO;

public class InsertMemberCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDTO dto = new UserDTO();
		String path = null;
		
		dto.setUserID(request.getParameter("userID"));
		dto.setUserPasswrod(request.getParameter("userPassword1"));
		dto.setUserName(request.getParameter("userName"));
		dto.setUserAge(Integer.parseInt(request.getParameter("userAge")));
		dto.setUserGender(ConvertGender(request.getParameter("userGender")));
		dto.setUserEmail(request.getParameter("userEmail"));
		
		int r = UserDAO.getInstance().insertMember(dto);
		
		if(r != 0) {
			System.out.println("회원가입 성공");
		}else {
			System.out.println("회원가입 실패");
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
	
	public static String ConvertGender(String rq) {
		if(rq=="남자")
			rq="M";
		else
			rq="F";
		
		return rq;
		
	}

}
