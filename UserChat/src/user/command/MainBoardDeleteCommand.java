package user.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.MainBoardDAO;
import user.dto.MainBoardDTO;

public class MainBoardDeleteCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MainBoardDTO dto = new MainBoardDTO();
		MainBoardDAO dao = new MainBoardDAO();


		
		dto.setMbNum(Integer.parseInt(request.getParameter("key")));
		dao.delete(dto);
		//return "jsp/boardDelete.jsp";
		return "main.do";
	}

}
