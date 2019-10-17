package user.command;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.UserDAO;

public class RanNumCheckCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int myVal = Integer.parseInt(request.getParameter("myVal"));
		int ranNum = (Integer)request.getSession().getAttribute("ranNum");
		
		String path = null;
		
		if(myVal==ranNum) { //인증번호가 맞는지 확인
			path="{\"result\":true, \"message\":\"인증번호가 일치합니다.\", \"color\":\"blue\"}";
			request.getSession().removeAttribute("ranNum");
			//인증번호를 세션에 올림
			
		}else {
			path="{\"result\":false, \"message\":\"인증번호가 일치하지 않습니다.\", \"color\":\"red\"}";
		}
		
		return "ajax:"+path;
		
	}

}
