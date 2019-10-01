package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import com.sun.webkit.ContextMenu.ShowContext;

import user.command.Command;

public class LogoutCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.invalidate();
		
		String sc = "<script>"
				+ "alert('정상적으로 로그아웃 되었습니다.');"
				+ "location='index.do';"
				+ "</script>";
			
		
		return "script:"+sc;
	}

}
