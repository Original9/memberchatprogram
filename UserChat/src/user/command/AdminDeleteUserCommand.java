package user.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.UserDAO;

public class AdminDeleteUserCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("key");
		String sc;
		
		int n = UserDAO.getInstance().adminDeleteUser(id);
		
		if (n != 0) {

			System.out.println("회원 삭제가 완료되었습니다.");
			// JOptionPane.showMessageDialog(null, "로그인 성공.");
			sc = "<script>" + "alert('회원 삭제가 완료되었습니다');"
			+ "location='adminChangeInfoForm.do';"
					+ "</script>";
			;
		} else {
			System.out.println("삭제 실패");
			sc = "<script>" + "alert('삭제 실패');"
			+ "location='adminChangeInfoForm.do';"
					+ "</script>";
			;
		}

		return "script:" + sc;
	}

}
