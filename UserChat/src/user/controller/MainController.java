package user.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.BoardDeleteCommand;
import user.controller.BoardListCommand;
import user.controller.BoardUpdateCommand;
import user.controller.BorderReadCommand;
import user.controller.BorderWriteCommand;
import user.controller.BorderWriteForm;
import user.controller.FileDownloadAction;
import user.controller.boardUpdateForm;
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
		map.put("/changeInfo.do", new ChangeInfoCommand());
		
		
		
		//승찬's
//		map.put("/boardView.do", new BoardListCommand());
//		map.put("/boardWrite.do", new BoardWriteCommand());
//		map.put("/BoardWriteFormCommand.do", new BoardWriteFormCommand());
		map.put("/boardList.do", new BoardListCommand());
		map.put("/borderWriteForm.do", new BorderWriteForm());
		map.put("/borderWrite.do", new BorderWriteCommand());
		map.put("/boardRead.do", new BorderReadCommand());		
		map.put("/boardUpdate.do", new BoardUpdateCommand());
		map.put("/boardUpdateForm.do", new boardUpdateForm());
		map.put("/boardDelete.do", new BoardDeleteCommand());
	    map.put("/FileDownloadAction.do", new FileDownloadAction());
		
		//원조's
		map.put("/memberSearch.do", new memberSearchCommand());
		
		
        
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
				
			}else if(page.startsWith("script:")) {
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(page.substring(7));
				
			}else {
				RequestDispatcher dispatcher=request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
			}
				
		}
	}

}
