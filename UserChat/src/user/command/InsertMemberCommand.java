package user.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import javafx.scene.control.Alert;
import user.dao.UserDAO;
import user.dto.UserDTO;

public class InsertMemberCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDTO dto = new UserDTO();
		String path = null;
		
		/*
		 * dto.setUserID(request.getParameter("userID"));
		 * dto.setUserPassword(request.getParameter("userPassword1"));
		 * dto.setUserName(request.getParameter("userName"));
		 * dto.setUserAge(Integer.parseInt(request.getParameter("userAge")));
		 * dto.setUserGender(ConvertGender(request.getParameter("userGender")));
		 * dto.setUserEmail(request.getParameter("userEmail"));
		 */
		
		try {
			BeanUtils.copyProperties(dto, request.getParameterMap());
			System.out.println(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*System.out.println(dto.getUserID());
		System.out.println(dto.getUserPassword());
		System.out.println(dto.getUserName());
		System.out.println(dto.getUserAge());
		System.out.println(dto.getUserGender());
		System.out.println(dto.getUserEmail());*/
		int r = UserDAO.getInstance().insertMember(dto);
		
		if(r != 0) {
			System.out.println("회원가입 성공");
			//로그인 창으로?
			
			//메인으로?
			path="index.jsp";
			
		}else {
			System.out.println("회원가입 실패");
			path="index.jsp";
		}
		
		return path;
	}
	
	/*
	 * public static String ConvertGender(String rq) { if(rq=="남자") rq="M"; else
	 * rq="F";
	 * 
	 * return rq;
	 * 
	 * }
	 */

}
