package chat;

import java.io.IOException;
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
		String fromID = request.getParameter("id");
		ArrayList<InBoxDTO> InBoxList = new ArrayList<>();
		InBoxDAO dao = new InBoxDAO();
		InBoxList = dao.messagesList(fromID);
		if(fromID == null) {
			response.getWriter().write("0");
		}else {
			
		}
	}

}
