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
		 String SQL = "SELECT * FROM CHAT WHERE((fromID = ? AND toID =?) OR (fromID =? AND toID=?)) and chatID > (SELECT MAX(chatID) - ? FROM CHAT) ORDER BY chatTime ";
		 //// DB 연결하는거 추가해야한다. 
		 try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, number);
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
	
	public int submit(String fromID,String toID, String chatContent){
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String SQL = "INSERT INTO CHAT VALUES(auto_increment.nextval,?,?,?,sysdate)";
		 //// DB 연결하는거 추가해야한다. 
		 try {
			 
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, chatContent);
			return pstmt.executeUpdate();
			
			
			
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
		return -1; // 오류 발생  
		 
		 
	}

}
