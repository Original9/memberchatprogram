package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;

public class IndexCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//여기에 임의로 id 세션값을 주어 메세지 넘어가는거 테스트하자 
		request.getSession().setAttribute("", "");
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/main.jsp"); // 컨트롤 파일을 바로 호출 할수있다. 
		dispatcher.forward(request, response);	
		
	}

}
