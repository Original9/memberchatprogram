package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;
import user.dao.MainBoardDAO;
import user.dto.MainBoardDTO;

public class MainboardUpdateForm implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MainBoardDTO dto = new MainBoardDTO();
		MainBoardDAO dao = new MainBoardDAO();

		
		int key =Integer.parseInt(request.getParameter("key"));
		
		dto = dao.select(key,"update");	//update 는 조회수를 증가하지 않는다.	
		request.setAttribute("list", dto);

		return "jsp/mainboardUpdate.jsp";
		
		
	}

}
