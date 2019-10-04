package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import user.command.Command;
import user.dao.UserDAO;
import user.dto.UserDTO;

public class ChangePWCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("userID");
		String changePassword=request.getParameter("currentPW1"); //변경할 비밀번호
		String sc = null;
		
		String realPassword=UserDAO.getInstance().readPassword(id); //지금 비밀번호
		System.out.println(realPassword);
		
		UserDTO dto = new UserDTO();
		
		try {
			BeanUtils.copyProperties(dto, request.getParameterMap());
			System.out.println(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int n=UserDAO.getInstance().changeUserInfo(dto, id);
		
		if(n!=0) {
			
			System.out.println("비밀번호 변경 성공");
			//JOptionPane.showMessageDialog(null, "로그인 성공.");
			sc="<script>"
					+ "alert('비밀번호가 성공적으로 변경되었습니다');"
					+ "location='changeInfoForm.do';"
					+ "</script>";;
		}else {
			System.out.println("변경 실패");
			sc="<script>"
					+ "alert('비밀번호 변경 실패');"
					+ "location='changeInfoForm.do';"
					+ "</script>";;
		}
		
		return "script:"+sc;
	}

}
