package user.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.AdminChangeInfoFormCommand;
import user.command.AdminUpdateUserCommand;
import user.command.BoardDeleteCommand;
import user.command.BoardListCommand;
import user.command.BoardUpdateCommand;
import user.command.BorderReadCommand;
import user.command.BorderWriteCommand;
import user.command.BorderWriteForm;
import user.command.ChangeInfoCommand;
import user.command.ChangeInfoFormCommand;
import user.command.ChangePWCommand;
import user.command.ChangePWFormCommand;
import user.command.Command;
import user.command.EmailCheckCommand;
import user.command.FileDownloadAction;
import user.command.FindIDCommand;
import user.command.FindIDFormCommand;
import user.command.FindPWCommand;
import user.command.FindPWFormCommand;
import user.command.IdCheckCommand;
import user.command.IndexCommand;
import user.command.InsertMemberCommand;
import user.command.LoginCheckCommand;
import user.command.LoginFormCommand;
import user.command.LogoutCommand;
import user.command.MainBoardDeleteCommand;
import user.command.MainBoardListCommand;
import user.command.MainBoardUpdateCommand;
import user.command.MainBorderReadCommand;
import user.command.MainBorderWriteCommand;
import user.command.MainBorderWriteForm;
import user.command.MainboardUpdateForm;
import user.command.RanNumCheckCommand;
import user.command.ValidCheckAndSendForFindIDCommand;
import user.command.boardUpdateForm;
import user.command.deleteFriendCommand;
import user.command.friendInsertCommand;
import user.command.friendListCommand;
import user.command.inBoxCommand;
import user.command.inBoxListDeleteCommand;
import user.command.joinCommand;
import user.command.memberSearchCommand;
import user.command.messageBoxCommand;

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
		map.put("/findIDForm.do", new FindIDFormCommand());
		map.put("/validCheckAndSendForFindID.do", new ValidCheckAndSendForFindIDCommand());
		map.put("/findID.do", new FindIDCommand());
		map.put("/findPWForm.do", new FindPWFormCommand());
		map.put("/findPW.do", new FindPWCommand());
		map.put("/logout.do", new LogoutCommand());
		map.put("/changeInfoForm.do", new ChangeInfoFormCommand());
		map.put("/changeInfo.do", new ChangeInfoCommand());
		map.put("/changePWForm.do", new ChangePWFormCommand());
		map.put("/adminChangeInfoForm.do", new AdminChangeInfoFormCommand());//delete페이지와 하나로 통합,이름바꾸기
		map.put("/adminUpdateUser.do", new AdminUpdateUserCommand());
		//map.put("/adminDeleteUser.do", new AdminDeleteUserCommand());
		map.put("/changePW.do", new ChangePWCommand());
		map.put("/emailCheck.do", new EmailCheckCommand());
		map.put("/ranNumCheck.do", new RanNumCheckCommand());
		
		
		
		
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
		map.put("/main.do", new MainBoardListCommand());
//		map.put("/MainBorderWriteCommand.do", new MainBorderWriteCommand());
//		map.put("/MainBorderWriteForm.do", new MainBorderWriteForm());
		map.put("/MainBorderWriteForm.do", new MainBorderWriteForm());
		map.put("/mainboardWrite.do", new MainBorderWriteCommand());
		map.put("/mainboardRead.do", new MainBorderReadCommand());
		map.put("/MainboardUpdateForm.do", new MainboardUpdateForm());
		map.put("/mainboardUpdate.do", new MainBoardUpdateCommand());
		map.put("/mainboardDelete.do", new MainBoardDeleteCommand());
//		map.put("/search.do", new BoardSearchCommand());
				
		
		//원조's
		map.put("/memberSearch.do", new memberSearchCommand());
		map.put("/friendList.do", new friendListCommand());
		map.put("/friendInsert.do", new friendInsertCommand());
		map.put("/inBox.do", new inBoxCommand());
		map.put("/deleteFriend.do", new deleteFriendCommand());
		map.put("/inBoxListDelete.do", new inBoxListDeleteCommand());
		
		
        
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
