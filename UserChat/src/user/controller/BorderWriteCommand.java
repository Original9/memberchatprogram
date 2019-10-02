package user.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import oracle.sql.DATE;
import user.command.Command;
import user.dao.BoarderDAO;
import user.dto.BoarderDTO;

public class BorderWriteCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 글추가 루틴 만들기
		
		BoarderDTO dto = new BoarderDTO();
		BoarderDAO dao = new BoarderDAO();
        //첨부파일 업로드 루틴
		String file1 = null;
		String upfilepath = request.getSession().getServletContext().getRealPath("uploadfile");
		int filesize = 10 * 1024;
		MultipartRequest mul = new MultipartRequest(request, upfilepath, filesize, "utf-8", new DefaultFileRenamePolicy());
		
		
		
		
		
		dto.setbId(mul.getParameter("uid"));
		dto.setbName(mul.getParameter("writer"));
//		dto.setbWriteDate(Date.valueOf(request.getParameter("wdate"))); ///date형으로 바꿔줘야함
		dto.setbTitle(mul.getParameter("title"));
		dto.setbContent(mul.getParameter("content"));
		dto.setBfileName(mul.getOriginalFileName("filename"));
		
		int n = dao.insert(dto);
		String path;
		if(n != 0) 
			path = "boardList.do"; //성공시 목록보여줌
		else  
			path ="borderWriteForm.do"; // 실패시 입력폼
		return path;
		
		
//		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
//		dispatcher.forward(request, response);
	}

}
