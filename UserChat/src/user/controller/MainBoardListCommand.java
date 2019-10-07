package user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;
import user.dao.MainBoardDAO;
import user.dto.MainBoardDTO;

public class MainBoardListCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<MainBoardDTO> list = new ArrayList<MainBoardDTO>();
		MainBoardDAO dao = new MainBoardDAO();
		
		list = dao.select();
		request.setAttribute("list", list); //db에서 넘어온 값을  request객체 속성으로 삽입
		
		return "jsp/main.jsp";
	}

}
