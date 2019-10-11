package user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.command.Command;
import user.dao.BoarderDAO;
import user.dto.BoarderDTO;

public class BoardListCommand implements Command {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<BoarderDTO> list = new ArrayList<BoarderDTO>();
		BoarderDAO dao = new BoarderDAO();
		//부서목록 조회
		//페이징 처리
		String title = request.getParameter("search");
		String p = request.getParameter("p");   //페이지번호
		int pageNo = 1;
		if( p != null && ! p.isEmpty()) {
			pageNo = Integer.parseInt(p);
		}
		int first, last;        // 조회할 시작과 끝 레코드 번호
		int recordTotal;        // 총레코드 갯수(DB조회)
		int pagePerRecord = 5;  // 한페이지에 출력할 레코드 건수
		int pageCnt;            // 페이지수
		first = (pageNo-1) * pagePerRecord + 1;          //해당페이지의 시작레코드 
		last = first + pagePerRecord - 1;                //해당페이지의 마지막레코드
		recordTotal = dao.recordTotal(title);   //전체레코드 수
		pageCnt =  recordTotal/pagePerRecord 
				 + (recordTotal%pagePerRecord>0 ? 1 : 0);//마지막페이지번호 
		
		
		list = dao.select(title, first, last);
		request.setAttribute("list", list); //db에서 넘어온 값을  request객체 속성으로 삽입
		request.setAttribute("pageCnt", pageCnt);
		return "jsp/boardList.jsp";
	}

}
