package user.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import user.command.Command;
import user.dao.BoarderDAO;
import user.dao.MainBoardDAO;
import user.dto.BoarderDTO;
import user.dto.MainBoardDTO;

public class MainBoardUpdateCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MainBoardDAO dao = new MainBoardDAO();
		MainBoardDTO dto = new MainBoardDTO();
		// 업데이트 수정
		String file1 = null;
		String upfilepath = request.getSession().getServletContext().getRealPath("uploadfile");
		System.out.println("=========================="+upfilepath);
		int filesize = 10 * 1024;
		MultipartRequest mul = new MultipartRequest(request, upfilepath, filesize, "utf-8", new DefaultFileRenamePolicy());
		
		
		
		
//		dto.setbWriteDate(Date.valueOf(request.getParameter("wdate"))); ///date형으로 바꿔줘야함
		dto.setMbNum(Integer.parseInt(mul.getParameter("bnum")));
		dto.setMbTitle(mul.getParameter("title"));
		dto.setMbContent(mul.getParameter("content"));
		
		String filedelete = mul.getParameter("filedelete");
		
		if(mul.getFilesystemName("filename") != null) {
			dto.setMbfileName(mul.getFilesystemName("filename")); 
		}
		else if(filedelete.equals("yes")) {
			File file = new File("filename");

			if( file.exists() ){
				if(file.delete()){
					System.out.println("파일삭제 성공"); 
					}else{ System.out.println("파일삭제 실패"); }
			}
			dto.setMbfileName("");
		}
		


		int n = dao.update(dto);
		String path;
		if(n != 0) 
			path = "boardList.do"; //성공시 목록보여줌
		else  
			path ="boardUpdateForm.do?key="+dto.getMbNum(); // 실패시 입력폼
		return path;


//		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
//		dispatcher.forward(request, response);
	

		
	}

}
