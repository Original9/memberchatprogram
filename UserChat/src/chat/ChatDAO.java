package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import user.dao.JDBCutil;


public class ChatDAO {
	
	// 접속 함수 만들어야한다. 
	DataSource dataSource;
	
	public ArrayList<ChatDTO> getChatListByID(String fromID,String toID, String chatID){
		 ArrayList<ChatDTO> chatList = null;
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String SQL = "SELECT * FROM CHAT WHERE((fromID = ? AND toID =?) OR (fromID =? AND toID=?)) and chatID > ? ORDER BY chatTime ";
		 //// DB 연결하는거 추가해야한다. 
		 try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, Integer.parseInt(chatID));
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatDTO>();
			while(rs.next())
			{
				ChatDTO chat = new ChatDTO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
				chat.setToID(rs.getString("toID").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				chat.setChat_num(rs.getInt("chat_num"));
				String timeType = "오전";
				if(chatTime >= 12) {
					timeType = "오후";
					chatTime -=12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0,11)+ timeType + " " + chatTime + " " + ":"+rs.getString("chatTime").substring(14,16));
				chatList.add(chat);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				JDBCutil.disconnect(pstmt, conn);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return chatList; 
		 
		 
	}
	
	public ArrayList<ChatDTO> getChatListByRecent(String fromID,String toID, int number){
		 ArrayList<ChatDTO> chatList = null;
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String SQL = "SELECT * FROM CHAT WHERE((fromID = ? AND toID =?) OR (fromID =? AND toID=?)) "
				 		+ "and chatID > (SELECT MAX(chatID) - ? FROM CHAT WHERE((fromID = ? AND toID =?) OR (fromID =? AND toID=?)) ) "
				 		+ "ORDER BY chatTime ";
		 //// DB 연결하는거 추가해야한다. 
		 try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, number);
			pstmt.setString(6, fromID);
			pstmt.setString(7, toID);
			pstmt.setString(8, toID);
			pstmt.setString(9, fromID);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatDTO>();
			while(rs.next())
			{
				ChatDTO chat = new ChatDTO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
				chat.setToID(rs.getString("toID").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				chat.setChat_num(rs.getInt("chat_num"));
				String timeType = "오전";
				if(chatTime >= 12) {
					timeType = "오후";
					chatTime -=12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0,11)+ timeType + " " + chatTime + " " + ":"+rs.getString("chatTime").substring(14,16));
				chatList.add(chat);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				JDBCutil.disconnect(pstmt, conn);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return chatList; 		 
	}
	
	public int submit(String fromID,String toID, String chatContent, String chatNum){
		 Connection conn = null;
		 PreparedStatement pstmt1 = null;
		 PreparedStatement pstmt2 = null;
		 ResultSet rs = null;
		 String SQL1 = null;
		 String SQL2 = null;
		 if(chatNum.equals("null") )
		 {
			 SQL1 = "INSERT INTO CHAT_ROOM VALUES(chat_table_increment.nextval,'2')";
			 SQL2 = "INSERT INTO CHAT VALUES(auto_increment.nextval,?,?,?,sysdate,chat_table_increment.currval,0)";// 이부분 DB에서 동시접속할때의 문제점이 있는지 생각해보기 
		 }else {
			 SQL2 = "INSERT INTO CHAT VALUES(auto_increment.nextval,?,?,?,sysdate,?,0)";			 
		 }
		 //// DB 연결하는거 추가해야한다. 
		 try {
			if(chatNum.equals("null")  )
			{
				conn = JDBCutil.connect();
				pstmt1 = conn.prepareStatement(SQL1);
				pstmt2 = conn.prepareStatement(SQL2);
				pstmt2.setString(1, fromID);
				pstmt2.setString(2, toID);
				pstmt2.setString(3, chatContent);
				pstmt1.executeUpdate();
				return pstmt2.executeUpdate();
				 
			}else {
				conn = JDBCutil.connect();
				pstmt1 = conn.prepareStatement(SQL2);
				pstmt1.setString(1, fromID);
				pstmt1.setString(2, toID);
				pstmt1.setString(3, chatContent);
				pstmt1.setString(4, chatNum); // 방번호				
				return pstmt1.executeUpdate();
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				JDBCutil.disconnect(pstmt1, conn);
				JDBCutil.disconnect(pstmt2, conn);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return -1; // 오류 발생  
	}
	
	public int readChat(String fromID, String toID) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String sql = "UPDATE chat set chatread = 1 where (fromID = ? AND toID =?)";
		
		 try {
			 conn = JDBCutil.connect();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, toID);
			 pstmt.setString(2, fromID);
			 return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		 return -1;
		
	}
	public int getAllUnread(String userID) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String sql = "select count(chatID) from chat where toID=? and chatRead=0"; 
		
		 try {
			 conn = JDBCutil.connect();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, userID);			 
			// pstmt.setString(2, fromID);
			 rs = pstmt.executeQuery();
			 if(rs.next()) {				 
				 return rs.getInt("count(chatID)");
			 }
			 return 0; // 받은 메세지가 없다. 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		 return -1;
		
	}
	
	public ArrayList<ChatDTO> getInBox(String userID){
		 ArrayList<ChatDTO> chatList = null;
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String SQL = "select * from chat where chatID in(select max(chatID) from chat where toID=? or fromID = ? )";
		 //// DB 연결하는거 추가해야한다. 
		 try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, userID);
		 
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatDTO>();
			while(rs.next())
			{
				ChatDTO chat = new ChatDTO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
				chat.setToID(rs.getString("toID").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11,13));
				String timeType = "오전";
				if(chatTime >= 12) {
					timeType = "오후";
					chatTime -=12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0,11)+ timeType + " " + chatTime + " " + ":"+rs.getString("chatTime").substring(14,16));
				chatList.add(chat);
			}
			for(int i=0; i< chatList.size(); i++) {
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				JDBCutil.disconnect(pstmt, conn);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return chatList; 		 
	}
	
}
