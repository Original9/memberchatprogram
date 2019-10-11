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

public class FindPWCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String Subject = "[ChatProgram]임시 비밀번호입니다.";
		String sc = null;
		
		UserDAO dao = new UserDAO();
		
		boolean chkValidIdAndEmail = false;
		

		chkValidIdAndEmail = dao.checkIdAndEmailForFindPW(id, email);
		
		if(chkValidIdAndEmail == false) {
			sc = "<script>"
					+ "alert('아이디와 이메일을 정확히 입력해 주세요!');"
					+ "location='findPWForm.do';"
					+ "</script>";;
					
					
		}else {
			try {
				String ranString = MailSend.sendWithRanString(email, Subject);
				int n = dao.findPW(id, ranString);//반환값(업데이트된 갯수 int) 있음.
				System.out.println(n);
				request.getSession().setAttribute("ranString", ranString);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sc = "<script>"
					+ "alert('\\n임시 비밀번호가 발송되었습니다.\\n \\n로그인 후 비밀번호를 변경해주세요!');"
					+ "window.close();"
					+ "</script>";;
					
			
		}
		
		
		
		return "script:" + sc;
	}

}
