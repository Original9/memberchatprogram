package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import user.dto.UserDTO;

public class UserDAO {
	public static UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
		
	//로그인
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
	
	//회원가입
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
	
	//id중복체크
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
	
	//회원정보 수정을 위한 회원정보 불러오기(사용자)
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
	
	//회원정보수정(사용자)
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
	
	public int changePW(String changePW, String id) { //비밀번호변경
		int n = 0;
		String sql = "update c_user set userpassword=? where userid=?";
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, changePW);
			pstmt.setString(2, id);

			n=pstmt.executeUpdate();
			//System.out.println("int n = "+n);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		
		return n;
	}
	
	//회원정보관리를 위한 회원정보리스트 불러오기(관리자)
	public ArrayList<UserDTO> readUserList(){
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		UserDTO dto;
		
		String sql="select * from c_user";
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new UserDTO();
				dto.setUserID(rs.getString("userid"));
				dto.setUserPassword(rs.getString("userpassword"));
				dto.setUserName(rs.getString("username"));
				dto.setUserAge(rs.getInt("userage"));
				dto.setUserGender(rs.getString("usergender"));
				dto.setUserEmail(rs.getString("userEmail"));
				dto.setUserGrant(rs.getString("ugrant"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		
		return list;
		
	}
	
	//아이디 찾기 시 입력한 이름이 유효한 회원인지 확인.
	public boolean checkNameForFindID(String writtenName) {
		boolean chk = false;
		String sql="select * from c_user where username=?";
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writtenName);
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
	
	//이름 유효한지 체크 후 이메일이 맞는지 확인
	public boolean checkEmailForFindID(String name, String writtenEmail) {
		boolean chk = false;
		String sql="select * from c_user where username=? and useremail=?";
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, writtenEmail);
			
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
	
	//아이디 찾기 메소드
	public String findID(String name, String writtenEmail) {
		String id = null;
		String sql="select * from c_user where username=? and useremail=?";
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, writtenEmail);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				id=rs.getString("userid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		
		return id;
	}
	
	//비밀번호 찾기 시 id와 이메일 체크(둘다맞아야만 메일이 간다.)
	public boolean checkIdAndEmailForFindPW(String writtenId, String writtenEmail) {
		boolean chk=false;
		String sql="select * from c_user where userid=? and useremail=?";
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writtenId);
			pstmt.setString(2, writtenEmail);
			
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
	
	public int findPW(String id, String ranString) { //사실 find password가 아닌 임시비밀번호로 변경하는 작업.
		int n=0;
		String sql="update c_user set userpassword=? where userid=?";
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ranString);
			pstmt.setString(2, id);

			n=pstmt.executeUpdate();
			//System.out.println("int n = "+n);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		
		return n;
		
	}
	
	public int adminChangeInfo(UserDTO dto) {
		int n = 0;
		String sql = "update c_user set userpassword=?, username=?, userage=?, usergender=?, useremail=?, ugrant=? where userid=?";
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserPassword());
			pstmt.setString(2, dto.getUserName());
			pstmt.setInt(3, dto.getUserAge());
			pstmt.setString(4, dto.getUserGender());
			pstmt.setString(5, dto.getUserEmail());
			pstmt.setString(6, dto.getUserGrant());
			
			pstmt.setString(7, dto.getUserID());

			n=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		
		return n;
	}
	
	public int adminDeleteUser(String id) {
		int n = 0;
		String sql = "delete from c_user where userid=?";
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);

			n=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		
		return n;
	}
	
}
