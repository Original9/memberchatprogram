package user.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import user.dao.MainBoardDAO;
import user.dto.MainBoardDTO;

public class MainBorderWriteCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MainBoardDTO dto = new MainBoardDTO();
		MainBoardDAO dao = new MainBoardDAO();
        //첨부파일 업로드 루틴
		String file1 = null;
		String upfilepath = request.getSession().getServletContext().getRealPath("Uploadfile");
		int filesize = 10 * 1024;
		
		
		MultipartRequest mul = new MultipartRequest(request, upfilepath, filesize, "utf-8", new DefaultFileRenamePolicy());
		
		
		
		
		
		dto.setMbId(mul.getParameter("uid"));
		dto.setMbName(mul.getParameter("writer"));
//		dto.setbWriteDate(Date.valueOf(request.getParameter("wdate"))); ///date형으로 바꿔줘야함
		dto.setMbTitle(mul.getParameter("title"));
		dto.setMbContent(mul.getParameter("content"));
		dto.setMbfileName(mul.getOriginalFileName("filename"));
		int n = dao.insert(dto);
		String path;
		if(n != 0) 
			path = "main.do"; //성공시 목록보여줌
		else  
			path ="MainBorderWriteForm.do"; // 실패시 입력폼
		return path;
		
		
//		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
//		dispatcher.forward(request, response);

	}

}
