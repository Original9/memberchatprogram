package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import user.command.Command;
import user.dao.UserDAO;
import user.dto.UserDTO;

public class LoginCheckCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDTO dto = new UserDTO();
		UserDAO dao = UserDAO.getInstance();
		
		String path=null;
		HttpSession session = request.getSession(false);
		
		dto.setUserID(request.getParameter("id"));
		dto.setUserPassword(request.getParameter("pw"));
		
		dto=dao.loginCheck(dto);
		
		String sc=null;
		
		if(dto.getUserGrant()!=null) {
			System.out.println(dto.getUserGrant());
			session.setAttribute("userID", dto.getUserID());
			session.setAttribute("grant", dto.getUserGrant());
			session.setAttribute("name", dto.getUserName()); 
			System.out.println("로그인성공");
			//JOptionPane.showMessageDialog(null, "로그인 성공.");
			sc="<script>"
					+ "alert('로그인 성공');"
					+ "location='index.do';"
					+ "</script>";;
		}else {
			System.out.println("로그인실패");
			//JOptionPane.showMessageDialog(null, "로그인 실패!");
			sc="<script>"
					+ "alert('아이디와 비밀번호를 확인해 주세요!');"
					+ "location='login.do';"
					+ "</script>";;
		}
		
		return "script:"+sc;
		
	}

}
