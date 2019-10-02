package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import user.dto.UserDTO;

public class FriendDAO {
	public static FriendDAO FriendDAO_instance = new FriendDAO();
	
	public static FriendDAO getInstance() {
		return FriendDAO_instance;
	}

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void insertFriend(String userID, String friendID) {
		
		String sql = "insert into c_friends values(?,?)";
		conn = JDBCutil.connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, friendID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ArrayList<UserDTO> friendList(String userID) {
		ArrayList<UserDTO> list = new ArrayList<>();
		UserDTO dto = null;
		String sql = "select * from c_friends , c_user  where c_friends.friendid = c_user.userid and c_friends.userid = ?";
		conn = JDBCutil.connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new UserDTO();
				dto.setUserID(rs.getString("friendid"));
				dto.setUserName(rs.getString("USERNAME"));
				dto.setUserEmail(rs.getString("USEREMAIL"));
				list.add(dto);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	
	
		
}
