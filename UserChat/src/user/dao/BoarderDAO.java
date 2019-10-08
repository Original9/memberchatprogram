package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import user.dto.BoarderDTO;

public class BoarderDAO {
	Connection conn = null;
	PreparedStatement pstmt;
	ResultSet rs;
 
	
	public BoarderDAO() {
		super();
	}
	
	public ArrayList<BoarderDTO> select() { //전체리스트라서 arraylist
		ArrayList<BoarderDTO> list = new ArrayList<BoarderDTO>();
		BoarderDTO dto;
		String sql="select a.USERID userid, a.BOARDID BOARDID, a.BTITLE BTITLE , a.BCONTENT BCONTENT, a.BDATE BDATE, a.BOARDFILE BOARDFILE, a.HIT HIT, \r\n" + 
				"b.username username from c_board a, C_user b\r\n" + 
				"where a.userid = b.userid\r\n";
		//"select USERID, BOARDID, BTITLE, BCONTENT, BDATE, BOARDFILE, HIT from c_board";
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				dto = new BoarderDTO();
				dto.setbId(rs.getString("USERID"));
				dto.setbNum(rs.getInt("BOARDID"));
				dto.setbTitle(rs.getString("BTITLE"));
				dto.setbContent(rs.getString("BCONTENT"));
				dto.setbWriteDate(rs.getDate("BDATE"));
				dto.setBfileName(rs.getString("BOARDFILE"));
				dto.setbHit(rs.getInt("HIT"));
				dto.setbName(rs.getString("username"));
				list.add(dto);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public BoarderDTO select (int key, String str) {
		BoarderDTO dto = new BoarderDTO();
		String sql = "select * from c_board where BOARDID= ?"; //
		
		try {
			conn = JDBCutil.connect();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, key);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setbId(rs.getString("USERID"));
				dto.setbNum(rs.getInt("BOARDID"));
				dto.setbTitle(rs.getString("BTITLE"));
				dto.setbContent(rs.getString("BCONTENT"));
				dto.setbWriteDate(rs.getDate("BDATE"));
				dto.setBfileName(rs.getString("BOARDFILE"));
				
				
//				dto.setbNum(rs.getInt("bnum"));
//				dto.setbName(rs.getString("bname"));
//				dto.setbContent(rs.getString("bcontent"));
//				dto.setbHit(rs.getInt("bhit"));
//				dto.setbTitle(rs.getString("btitle"));
//				dto.setbWriteDate(rs.getDate("bwritedate"));
//				dto.setbId(rs.getString("bid"));
//				dto.setBfileName(rs.getString("filename"));
				if(str.equals("read"))
					readCount(key); //조회수 1씩 올라가게 수정
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		close();
		return dto;
	}
	
	
	public int insert(BoarderDTO dto) { //글추가
		int n = 0;
		String sql = "insert into c_board(USERID,BOARDID,BTITLE,BCONTENT,BDATE,BOARDFILE) values(?, BOARD_SEQ.nextval, ?, ?,sysdate, ?)";
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getbId());
			pstmt.setString(2, dto.getbTitle());
			pstmt.setString(3, dto.getbContent());
			//pstmt.setString(4, dto.getbId());
			pstmt.setString(4, dto.getBfileName());
			n = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		close();
		return n;
	}
	
	public int update(BoarderDTO dto) { //글수정
		int n = 0;
		String sql = "update c_board set btitle=?, bcontent=?, boardfile=? where BOARDID = ?";
		
		if(dto.getBfileName() == null) {
			 sql = "update c_board set btitle=?, bcontent=? where BOARDID = ?";			
		}
		
		try {
			int cnt = 1;
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(cnt++, dto.getbTitle());
			pstmt.setString(cnt++, dto.getbContent());
			if(dto.getBfileName() != null) {
				pstmt.setString(cnt++, dto.getBfileName());
			}
			pstmt.setInt(cnt++, dto.getbNum());
			n = pstmt.executeUpdate();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return n;
	}
	
	
	public void delete(BoarderDTO dto) { //글삭제
		String sql = "delete from c_board where BOARDID = ? ";
		
				try {
					conn = JDBCutil.connect();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, dto.getbNum());
					pstmt.executeUpdate();
		
				} catch (SQLException e) {
					e.printStackTrace();
				}
		close();
		
	}
	

	
	private void readCount(int key) {
		String sql = "update c_board set hit = nvl(hit,0) + 1 where BOARDID= ?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, key);
			pstmt.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		close();
	}
	
	
	
	private void close() {
		try  {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
