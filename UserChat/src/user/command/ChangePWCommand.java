package user.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import user.dao.UserDAO;
import user.dto.UserDTO;

public class ChangePWCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=(String)request.getSession().getAttribute("userID");
		String writtenPassword=request.getParameter("currentPW");
		String changePassword=request.getParameter("changePW1"); //변경할 비밀번호
		System.out.println("id, 변경할 비밀번호 : "+id+", "+changePassword);
		int n=0;
		boolean pwCheck = true;
		String sc = null;
		
		String realPassword=UserDAO.getInstance().readPassword(id); //지금 비밀번호
		System.out.println(realPassword);
		
		
		
		if(!(realPassword.equals(writtenPassword))) { //현재 비밀번호 입력 틀리면 비밀번호 변경 하지않고 다시 폼으로.
			System.out.println("비밀번호 틀림");
			pwCheck = false;
			sc="<script>"
					+ "alert('현재 비밀번호를 정확히 입력해 주세요');"
					+ "location='changePWForm.do';"
					+ "</script>";;
			return "script:"+sc;
		}
		
		if(realPassword.equals(changePassword)) { //현재 비밀번호와 변경할 비밀번호가 같으면 비번 변경 하지않고 다시 폼으로.
			System.out.println("현재 비밀번호와 변경할 비밀번호 같음");
			pwCheck = false;
			sc="<script>"
					+ "alert('변경할 비밀번호는 현재 비밀번호와 다르게 입력해 주세요');"
					+ "location='changePWForm.do';"
					+ "</script>";;
			return "script:"+sc;
		}
		
		
		
		n=UserDAO.getInstance().changePW(changePassword, id);
		
		
		
		
		if(n!=0 && realPassword.equals(writtenPassword)) {
			
			System.out.println("비밀번호 변경 성공");
			sc="<script>"
					+ "alert('비밀번호가 성공적으로 변경되었습니다');"
					+ "window.close();"
					//+ "location='changeInfoForm.do';"
					+ "</script>";;
		} else {
			System.out.println("변경 실패");
			sc="<script>"
					+ "alert('비밀번호 변경 실패, 관리자에게 문의하세요');"
					+ "window.close();"
					//+ "location='changeInfoForm.do';"
					+ "</script>";;
		}
		
		return "script:"+sc;
	}

}
