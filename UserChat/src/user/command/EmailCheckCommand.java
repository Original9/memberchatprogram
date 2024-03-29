package user.command;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javax.servlet.http.HttpSession;

public class EmailCheckCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Email = request.getParameter("userEmail");
		String Subject = "[ChatProgram]인증번호입니다.";
		String path = null;
		
		boolean chkReg = false; //정규표현식 체크 변수
		Pattern p = Pattern.compile("^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+");
		
		Matcher m = p.matcher(Email);
		
		if(m.find()) {
			chkReg = true;
		}
		
		try {
			int ranNum = MailSend.sendWithRanNum(Email, Subject);
			request.getSession().setAttribute("ranNum", ranNum);
			
		} catch (AddressException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/*} catch (AddressException e) {
			path = "{\"result\":false, \"message\":\"메일 주소를 공백 없이 입력해 주세요.\"}";
			e.printStackTrace();
		} catch (SendFailedException e) {
			path = "{\"result\":false, \"message\":\"'@' 를 포함한 메일 주소 형식으로 입력해 주세요.\"}";
			e.printStackTrace();
		} catch (MessagingException e) {
			path = "{\"result\":false, \"message\":\"인증할 수 없습니다..\"}";
			e.printStackTrace();
		}
*/
		if (chkReg == false) { //이메일 형식 체크
			path = "{\"result\":false, \"message\":\"이메일 형식에 맞게 입력해 주세요.\"}";
		}else {
			path = "{\"result\":true, \"message\":\"메일이 전송되었습니다.\"}";
		}
		/*
		 * }
		 */
		/*
		 * else { path = "{\"result\":false, \"message\":\"인증할 수 없습니다..\"}"; }
		 */

		return "ajax:" + path;
	}

}
