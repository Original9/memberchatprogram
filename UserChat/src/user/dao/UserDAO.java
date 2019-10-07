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
		int r = 0;
		String sql = "insert into c_user values(?,?,?,?,?,?,'U','','')";

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
		boolean chk = false;
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				chk=true;
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
	
	
	public int changeUserInfo(UserDTO dto, String id) {
		int n = 0;
		String sql = "update c_user set username=?, userage=?, useremail=? where userid=?";
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserName());
			pstmt.setInt(2, dto.getUserAge());
			pstmt.setString(3, dto.getUserEmail());
			pstmt.setString(4, id);

			n=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		
		return n;
	}
	
	public String readPassword(String id) { //비밀번호 변경을 위한 현재 비밀번호 얻기
		String sql="select * from c_user where userid=?";
		String password= null;
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				password=rs.getString("userpassword");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		
		return password;
	}
	
	public int changePW(String changePW, String id) { //비밀번호변경 구현 덜함.
		int n = 0;
		String sql = "update c_user set userpassword=? where userid=?";
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, changePW);
			pstmt.setString(2, id);

			n=pstmt.executeUpdate();
			System.out.println("int n = "+n);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		
		return n;
	}
}
