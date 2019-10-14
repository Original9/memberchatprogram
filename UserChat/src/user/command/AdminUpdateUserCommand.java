package user.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import user.dto.UserDTO;

public class AdminUpdateUserCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDTO dto = new UserDTO();
		String path = null;
		
		try {
			BeanUtils.copyProperties(dto, request.getParameterMap());
			System.out.println(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "adminChangeInfoForm.do";
	}

}
