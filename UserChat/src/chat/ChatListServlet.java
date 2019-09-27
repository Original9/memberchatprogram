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
 * Servlet implementation class ChatListServlet
 */
@WebServlet("/ChatListServlet")
public class ChatListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("HERE IS CHATLISTSERVLET PAGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String fromID= request.getParameter("fromID");
		String toID = request.getParameter("toID");
		String listType = request.getParameter("listType");
		
		//System.out.println("fromID:" + fromID + "  toID: " + toID + "  listType: " + listType);
		if(fromID == null || fromID.equals("") || toID == null || toID.equals("")
				|| listType == null || listType.equals("")) {
			response.getWriter().write("0");
		} else if (listType.equals("ten")) {
			response.getWriter().write(getTen(URLDecoder.decode(fromID,"UTF-8"),toID));
		} else {
			try {
				response.getWriter().write(getID(URLDecoder.decode(fromID,"UTF-8"),URLDecoder.decode(toID,"UTF-8"),listType));
			}catch(Exception e) {
				response.getWriter().write("");
			}
		}
		
	}
	
	public String getTen(String fromID,String toID) {
		StringBuffer result = new StringBuffer(""); // 문자열이 계속 변할때에는 append만해주면 되는 StringBuffer이 더 좋다.
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByRecent(fromID, toID, 10);
//		for(ChatDTO dto : chatList) {
//			System.out.println(dto);
//		}
		if(chatList.size() == 0) return "";
		for(int i=0; i<chatList.size(); i++) {
			result.append("[{\"value\": \"" +chatList.get(i).getFromID()+"\"},");
			result.append("{\"value\": \"" +chatList.get(i).getToID()+"\"},");
			result.append("{\"value\": \"" +chatList.get(i).getChatContent()+"\"},");
			result.append("{\"value\": \"" +chatList.get(i).getChatTime()+"\"}]");
			if(i != chatList.size() - 1) result.append(",");			
		}
		result.append("], \"last\":\""+chatList.get(chatList.size()-1).getChatID()+"\"}");		
		return result.toString();
	}
	
	public String getID(String fromID,String toID, String chatID) {
		StringBuffer result = new StringBuffer(""); // 문자열이 계속 변할때에는 append만해주면 되는 StringBuffer이 더 좋다.
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByID(fromID, toID, chatID);
		if(chatList.size() == 0) return "";
		for(int i=0; i<chatList.size(); i++) {
			result.append("[{\"value\": \"" +chatList.get(i).getFromID()+"\"},");
			result.append("{\"value\": \"" +chatList.get(i).getToID()+"\"},");
			result.append("{\"value\": \"" +chatList.get(i).getChatContent()+"\"},");
			result.append("{\"value\": \"" +chatList.get(i).getChatTime()+"\"}]");
			if(i != chatList.size() - 1) result.append(",");			
		}
		result.append("], \"last\":\""+chatList.get(chatList.size()-1).getChatID()+"\"}");
		return result.toString();
	}

}

