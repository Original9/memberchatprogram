package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;
import user.dao.UserDAO;
import user.dto.UserDTO;

public class ChangeInfoFormCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserDTO dto = new UserDTO();
		String id = (String)request.getSession().getAttribute("userID");
		
		dto=UserDAO.getInstance().readToChangeInfo(id);//dao 에  changeInfo 만들어야함.
		System.out.println(dto.getUserEmail());
		
		request.setAttribute("list", dto);
		
		return "jsp/changeInfo.jsp";
	}

}
