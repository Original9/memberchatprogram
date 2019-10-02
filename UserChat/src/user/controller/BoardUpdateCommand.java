package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;
import user.dao.BoarderDAO;
import user.dto.BoarderDTO;

public class BoardUpdateCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoarderDAO dao = new BoarderDAO();
		BoarderDTO dto = new BoarderDTO();
		
		dto.setbNum(Integer.parseInt(request.getParameter("bnum")));
		dto.setbTitle(request.getParameter("title"));
		dto.setbContent(request.getParameter("content"));
		
		int n = dao.update(dto);
		String path;
		if(n != 0) 
			path = "boardList.do"; //성공시 목록보여줌
		else  
			path ="boardUpdateForm.do"; // 실패시 입력폼
		return path;


//		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
//		dispatcher.forward(request, response);
	}

}
