package user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;
import user.dao.BoarderDAO;
import user.dto.BoarderDTO;

public class BoardListCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<BoarderDTO> list = new ArrayList<BoarderDTO>();
		BoarderDAO dao = new BoarderDAO();
		
		list = dao.select();
		request.setAttribute("list", list); //db에서 넘어온 값을  request객체 속성으로 삽입
		
		return "jsp/boardList.jsp";
	}

}
