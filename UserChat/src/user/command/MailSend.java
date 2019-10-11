package user.command;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSend {

	public static int sendWithRanNum(String Email, String Subject) throws AddressException, MessagingException {
		
		double randomVal1=Math.random();
		double randomVal2=Math.random();
		int ranNum = (int)(randomVal1 * 1000000) + (int)(randomVal2 * 1000);

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

		
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));

			// 수신자메일주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(Email));

			// Subject
			message.setSubject(Subject); // 메일 제목을 입력

			// Text
			message.setText("인증번호는 "+ranNum+" 입니다."); // 메일 내용을 입력

			// send the message
			Transport.send(message); //// 전송
			System.out.println("message sent successfully...");

			return ranNum;

	}
	
	public static String sendWithRanString(String Email, String Subject) throws AddressException, MessagingException {
		
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 20; i++) {
		    int rIndex = rnd.nextInt(3);
		    switch (rIndex) {
		    case 0:
		        // a-z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
		        break;
		    case 1:
		        // A-Z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
		        break;
		    case 2:
		        // 0-9
		        temp.append((rnd.nextInt(10)));
		        break;
		    }
		}

		String ranString = temp.toString();

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

		
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));

			// 수신자메일주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(Email));

			// Subject
			message.setSubject(Subject); // 메일 제목을 입력

			// Text
			message.setText("임시 비밀번호는 "+ranString+" 입니다."); // 메일 내용을 입력

			// send the message
			Transport.send(message); //// 전송
			System.out.println("message sent successfully...");
		
		return ranString;
	}
}
