package user.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.UserDAO;
import user.dto.UserDTO;

public class AdminChangeInfoFormCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		//String id = (String)request.getSession().getAttribute("userID");
		
		list=UserDAO.getInstance().readUserList();
		
		request.setAttribute("list", list);
		
		return "jsp/adminChangeInfo.jsp";
	}

}
