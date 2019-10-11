package user.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;
import user.command.MailSend;
import user.dao.UserDAO;

public class ValidCheckAndSendForFindIDCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//request.setCharacterEncoding("UTF-8");
		//response.setContentType("text/html;charset=UTF-8");
		
		//String name =  URLDecoder.decode(request.getParameter("userName"),"UTF-8");
		//String email = URLDecoder.decode(request.getParameter("userEmail"),"UTF-8");
		
		String name =  request.getParameter("userName");
		String email = request.getParameter("userEmail");
		
		String Subject = "[ChatProgram]인증번호입니다.";
		String path = null;
		
		boolean chkValidName = false;
		boolean chkValidEmail = false;

		UserDAO dao = new UserDAO();
		chkValidName = dao.checkNameForFindID(name);
		chkValidEmail = dao.checkEmailForFindID(name, email);
		
		if(chkValidName == false) {
			path = "{\"resultStatus\":1, \"message\":\"존재하지 않는 회원입니다!\"}";
		}else if(chkValidName == true && chkValidEmail == false) {
			path = "{\"resultStatus\":2, \"message\":\"이메일을 정확히 입력해 주세요!\"}";
		}else {
			try {
				int ranNum = MailSend.sendWithRanNum(email, Subject);
				request.getSession().setAttribute("ranNum", ranNum);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			path = "{\"resultStatus\":3, \"message\":\"이메일이 발송되었습니다!\"}";
		}
		
		
		
		return "ajax:" + path;
	}

}
