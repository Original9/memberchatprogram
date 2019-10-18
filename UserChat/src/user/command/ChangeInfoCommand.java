package user.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import user.dao.UserDAO;
import user.dto.UserDTO;

public class ChangeInfoCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("userID");
		String sc = null;
		UserDTO dto = new UserDTO();
		
		try {
			BeanUtils.copyProperties(dto, request.getParameterMap());
			System.out.println(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int n=UserDAO.getInstance().changeUserInfo(dto, id);
		
		if(n!=0) {
			
			System.out.println("회원정보 변경 성공");
			sc="<script>"
					+ "alert('성공적으로 변경되었습니다');"
					+ "location='changeInfoForm.do';"
					+ "</script>";;
		}else {
			System.out.println("변경 실패");
			sc="<script>"
					+ "alert('정보 변경 실패');"
					+ "location='changeInfoForm.do';"
					+ "</script>";;
		}
		
		return "script:"+sc;
	}

}
