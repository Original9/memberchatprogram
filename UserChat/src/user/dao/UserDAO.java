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

	public int insertMember(UserDTO dto) {
		String sql = "insert into c_user values(?,?,?,?,?,?,'U')";
		int r = 0;

		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserID());
			pstmt.setString(2, dto.getUserPasswrod());
			pstmt.setString(3, dto.getUserName());
			pstmt.setInt(4, dto.getUserAge());
			pstmt.setString(5, dto.getUserGender());
			pstmt.setString(6, dto.getUserEmail());

			r=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		return chk;
	}
}
