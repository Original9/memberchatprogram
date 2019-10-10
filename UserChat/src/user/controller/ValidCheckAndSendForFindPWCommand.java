package user.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;
import user.command.MailSend;
import user.dao.UserDAO;

public class ValidCheckAndSendForFindPWCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("userId");
		String email = request.getParameter("userEmail");
		String Subject = "[ChatProgram]임시 비밀번호입니다.";
		String path = null;
		
		boolean chkValidIdAndEmail = false;
		

		chkValidIdAndEmail = UserDAO.getInstance().checkIdAndEmailForFindPW(id, email);
		
		if(chkValidIdAndEmail == false) {
			path = "{\"resultStatus\":1, \"message\":\"아이디와 이메일을 정확히 입력해 주세요!\"}";
		}else {
			try {
				String ranString = MailSend.sendWithRanString(email, Subject);
				request.getSession().setAttribute("ranString", ranString);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			path = "{\"resultStatus\":2, \"message\":\"임시 비밀번호가 발송되었습니다. 로그인 후 비밀번호를 변경해 주세요!\"}";
			//JSON 데이터 중 message 항목에 개행문자 추가 알아보기.
		}
		
		
		
		return "ajax:" + path;
	}

}
