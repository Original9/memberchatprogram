package user.command;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.UserDAO;

public class IdCheckCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("userID");
//		System.out.println(request.getParameter("userID"));
		boolean chk = UserDAO.getInstance().isIdCheck(id);
		boolean chkReg = false; //정규표현식 체크 변수
		String path = null;
		Pattern p = Pattern.compile("^[A-Za-z0-9]{5,16}$");
		
		Matcher m = p.matcher(id);
		
		if(m.find()) {
			chkReg = true;
		}
		
		if(chkReg == false){
			path="{\"result\":false, \"message\":\"사용 불가능한 아이디 입니다.\", \"chkReg\":false}";
		}else if(chk) {
			path="{\"result\":false, \"message\":\"사용 불가능한 아이디 입니다.\"}";
			
		}else {
			path="{\"result\":true, \"message\":\"사용 가능한 아이디 입니다.\"}";
		}
		
		return "ajax:"+path;
	}
	
	

}
