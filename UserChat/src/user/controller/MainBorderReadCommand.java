package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;
import user.dao.MainBoardDAO;
import user.dto.MainBoardDTO;

public class MainBorderReadCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MainBoardDTO dto = new MainBoardDTO();
		MainBoardDAO dao = new MainBoardDAO();
		int key =Integer.parseInt(request.getParameter("key"));
		String cnt = null;
		Cookie[] list = request.getCookies();
		for(int i=0; list!=null && i< list.length; i++) {
			if(list[i].getName().equals("mcount"+key)) {
				cnt = list[i].getValue();
			}
			
		}
		if(cnt == null) {
			Cookie c = new Cookie("mcount"+key, "yes");
			c.setMaxAge(5);
			response.addCookie(c);
			dao.readCount(key);
		}
		
		dto = dao.select(key,"read");  //read 는 조회수 증가
		
		request.setAttribute("list", dto);
		return ("jsp/mainboardRead.jsp");
	}

}
