package user.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;

/**
 * Servlet implementation class MainController
 */

public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = null; // Servlet interface를 만들어야한다.   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
    	map = new HashMap<String, Command>();
		map.put("/index.do",new IndexCommand());
		map.put("/join.do", new joinCommand());
		map.put("/messageBox.do" , new messageBoxCommand());
		//은영's
		map.put("/insertMember.do", new InsertMemberCommand());
		map.put("/idCheck.do", new IdCheckCommand());
		map.put("/login.do", new LoginFormCommand());
		map.put("/loginCheck.do", new LoginCheckCommand());
		map.put("/logout.do", new LogoutCommand());
		
		
		
		//승찬's
		
		
		
		//원조's
		map.put("/memberSearch.do", new memberSearchCommand());
		map.put("/friendList.do", new friendListCommand());
		map.put("/friendInsert.do", new friendInsertCommand());
		
		
        
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 들어온 요청을 확인해서 실행시켜주는 부분
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String context = request.getContextPath();
		String path = uri.substring(context.length());
//		System.out.println("URi:" + uri);
//		System.out.println("cs:" + context);
//		System.out.println("path:" + path);
		
		Command comm = map.get(path);
		String page = comm.excute(request, response);
		
		if(page!=null) {
			if(page.startsWith("redirect:")) {
				response.sendRedirect(page.substring(9));
				
			}else if(page.startsWith("ajax:")) {
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(page.substring(5));
			}else {
				RequestDispatcher dispatcher=request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
			}
				
		}
	}

}
