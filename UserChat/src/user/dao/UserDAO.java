package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.dto.UserDTO;

public class UserDAO {
	public static UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public UserDTO loginCheck(UserDTO dto) {
		String sql="select * from c_user where userid=? and userpassword=?";
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserID());
			pstmt.setString(2, dto.getUserPassword());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto=new UserDTO();
				dto.setUserID(rs.getString("userid"));
				dto.setUserGrant(rs.getString("ugrant"));
				dto.setUserName(rs.getString("username"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		
		return dto;
	}
	

	public int insertMember(UserDTO dto) {
		String sql = "insert into c_user values(?,?,?,?,?,?,'U')";
		int r = 0;

		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserID());
			pstmt.setString(2, dto.getUserPassword());
			pstmt.setString(3, dto.getUserName());
			pstmt.setInt(4, dto.getUserAge());
			pstmt.setString(5, dto.getUserGender());
			pstmt.setString(6, dto.getUserEmail());

			r=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		
		return r;
	}
	
	public boolean isIdCheck(String id) {
		String sql="select * from c_user where userid=?";
		boolean chk = true;
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				chk=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		return chk;
	}
	
	public UserDTO readToChangeInfo(String id) {
		String sql="select * from c_user where userid=?";
		UserDTO dto = new UserDTO();
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto.setUserID(rs.getString("userid"));
				dto.setUserPassword(rs.getString("userpassword"));
				dto.setUserName(rs.getString("username"));
				dto.setUserAge(rs.getInt("userage"));
				dto.setUserGender(rs.getString("usergender"));
				dto.setUserEmail(rs.getString("userEmail"));
//				dto.setUserGrant(rs.getString("ugrant"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		
		return dto;
	}
}
