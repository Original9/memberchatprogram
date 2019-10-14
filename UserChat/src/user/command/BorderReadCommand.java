package user.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.BoarderDAO;
import user.dto.BoarderDTO;

public class BorderReadCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoarderDTO dto = new BoarderDTO();
		BoarderDAO dao = new BoarderDAO();
		int key =Integer.parseInt(request.getParameter("key"));
		String cnt = null;
		Cookie[] list = request.getCookies();
		for(int i=0; list!=null && i< list.length; i++) {
			if(list[i].getName().equals("count"+key)) {
				cnt = list[i].getValue();
			}
			
		}
		if(cnt == null) {
			Cookie c = new Cookie("count"+key, "yes");
			c.setMaxAge(5);
			response.addCookie(c);
			dao.updateCount(key);
		}
	
		dto = dao.select(key,"read");  //read 는 조회수 증가

		
		request.setAttribute("list", dto);
		return ("jsp/boardRead.jsp");
	}

}
