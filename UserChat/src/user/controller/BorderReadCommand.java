package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;
import user.dao.BoarderDAO;
import user.dto.BoarderDTO;

public class BorderReadCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoarderDTO dto = new BoarderDTO();
		BoarderDAO dao = new BoarderDAO();
		int key =Integer.parseInt(request.getParameter("key"));
		
		dto = dao.select(key,"read");  //read 는 조회수 증가
		
		request.setAttribute("list", dto);
		return ("jsp/boardRead.jsp");
	}

}
