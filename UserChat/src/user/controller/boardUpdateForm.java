package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;
import user.dao.BoarderDAO;
import user.dto.BoarderDTO;

public class boardUpdateForm implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoarderDTO dto = new BoarderDTO();
		BoarderDAO dao = new BoarderDAO();
		int key =Integer.parseInt(request.getParameter("key"));
		
		dto = dao.select(key,"update");	//update 는 조회수를 증가하지 않는다.	
		request.setAttribute("list", dto);

		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/boardUpdate.jsp");
		dispatcher.forward(request, response);
	}

}
