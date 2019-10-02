package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;
import user.dao.BoarderDAO;
import user.dto.BoarderDTO;

public class BoardDeleteCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoarderDTO dto = new BoarderDTO();
		BoarderDAO dao = new BoarderDAO();
		
		dto.setbNum(Integer.parseInt(request.getParameter("key")));
		dao.delete(dto);
		return "boardList.do";
	}

}
