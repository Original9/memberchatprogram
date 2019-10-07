package user.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;

public class EmailCheckCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Email = request.getParameter("userEmail");
		String path = null;
		
		double randomVal1=Math.random();
		double randomVal2=Math.random();
		int ranNum = (int)(randomVal1 * 1000000) + (int)(randomVal2 * 100);

		String user = "javaMProject@gmail.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
		String password = "uqxcotgyqipejktc"; // 패스워드

		// SMTP 서버 정보를 설정한다.
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));

			// 수신자메일주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(Email));

			// Subject
			message.setSubject("본인인증 입니다."); // 메일 제목을 입력

			// Text
			message.setText("인증번호를 입력해 주세요 : "+ranNum); // 메일 내용을 입력

			// send the message
			Transport.send(message); //// 전송
			System.out.println("message sent successfully...");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (true) { //이메일이 발송되었는지 확인하는 조건 알아보기.
			path = "{\"result\":true, \"message\":\"메일이 전송되었습니다.\", \"checkNum\":"+ranNum+"}";

		} else {
			path = "{\"result\":false, \"message\":\"인증할 수 없습니다..\"}";
		}

		return "ajax:" + path;
	}

}
