package chat;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChatBoxServlet
 */
@WebServlet("/ChatBoxServlet")
public class ChatBoxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String fromID = request.getParameter("id");
		
		if(fromID == null) {
			response.getWriter().write("0");
		} else {
			response.getWriter().write(getInBoxInfo(URLDecoder.decode(fromID,"UTF-8")));
		}
		
		
			
		
	}
	public String getInBoxInfo(String fromID) {
		StringBuffer result = new StringBuffer("");
		ArrayList<InBoxDTO> InBoxList = new ArrayList<>();
		InBoxDAO dao = new InBoxDAO();
		InBoxList = dao.messagesList(fromID);  // 값들이 return 되어 왔다.
		if(InBoxList.size() == 0 ) return "";
		result.append("{\"result\":[");
		for(int i=0; i<InBoxList.size(); i++) {
			result.append("[{\"fromid\": \"" +InBoxList.get(i).getFromID()+"\"},");
			result.append("{\"name\": \"" +InBoxList.get(i).getName()+"\"},");
			result.append("{\"unreadMeassageCount\": \"" +InBoxList.get(i).getUnreadMeassgeCount()+"\"}]");
			if(i != InBoxList.size() - 1) result.append(",");
		} 
		result.append("]}");
		
		
		return result.toString();
	}

}
