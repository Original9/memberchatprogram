package user.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;
import user.dao.UserDAO;

public class FindIDCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("userName");
		String email = request.getParameter("userEmail");
		String sc = null;
		String path = null;
		String id = null;

		UserDAO dao = new UserDAO();
		boolean chkValidName = false;
		boolean chkValidEmail = false;

		chkValidName = dao.checkNameForFindID(name);
		chkValidEmail = dao.checkEmailForFindID(name, email);

		if (chkValidName == false) {
			sc = "<script>" + "alert('등록된 회원이 아닙니다!');" + "location='findIDForm.do';" + "</script>";
			;
			return "script:" + sc;

		} else {
			
			if(chkValidEmail == false) {
				sc = "<script>" + "alert('이메일 주소를 정확히 입력해 주세요!');" + "location='findIDForm.do';" + "</script>";
				;
				return "script:" + sc;
			}else {
				id = dao.findID(email);
				path = "{\"result\":true, \"message\":\"id는 \"+id+\"입니다.\"}";
				return "ajax:" + path;
			}


		}
	}
}