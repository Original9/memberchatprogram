package user.controller;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;
import user.dao.UserDAO;

public class RanNumCheckCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int myVal = Integer.parseInt(request.getParameter("myVal"));
		int ranNum = (Integer)request.getSession().getAttribute("ranNum");
		//System.out.println(myVal);
		//System.out.println(ranNum);
		
//		System.out.println(request.getParameter("userID"));
		String path = null;
		
		if(myVal==ranNum) { //조건 작성
			path="{\"result\":true, \"message\":\"인증번호가 일치합니다.\", \"color\":\"blue\"}";
			request.getSession().removeAttribute("ranNum");
			
		}else {
			path="{\"result\":false, \"message\":\"인증번호가 일치하지 않습니다.\", \"color\":\"red\"}";
		}
		
		return "ajax:"+path;
		
	}

}
