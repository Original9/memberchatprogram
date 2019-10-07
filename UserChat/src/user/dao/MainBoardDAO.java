package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import user.dto.BoarderDTO;
import user.dto.MainBoardDTO;

public class MainBoardDAO {

	Connection conn = null;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public ArrayList<MainBoardDTO> select() { //전체리스트라서 arraylist
		ArrayList<MainBoardDTO> list = new ArrayList<MainBoardDTO>();
		MainBoardDTO dto;
		String sql="select a.USERID userid, a.NOTICEID NOTICEID, a.NTITLE NTITLE , a.NCONTENT NCONTENT, a.NDATE NDATE, a.noticefile nOTICEFILE, a.HIT HIT,\r\n" + 
				"b.username username from notice a, C_user b\r\n" + 
				"where a.userid = b.userid";
		//"select USERID, BOARDID, BTITLE, BCONTENT, BDATE, BOARDFILE, HIT from c_board";
		
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				dto = new MainBoardDTO();
				System.out.println(dto);
				dto.setMbId(rs.getString("USERID"));
				dto.setMbNum(rs.getInt("NOTICEID"));
				dto.setMbTitle(rs.getString("NTITLE"));
				dto.setMbContent(rs.getString("NCONTENT"));
				dto.setMbWriteDate(rs.getDate("NDATE"));
				dto.setMbfileName(rs.getString("nOTICEFILE"));
				dto.setMbHit(rs.getInt("HIT"));
				dto.setMbName(rs.getString("username"));
				list.add(dto);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public MainBoardDTO select (int key, String str) {
		MainBoardDTO dto = new MainBoardDTO();
		String sql = "select * from c_board where BOARDID= ?"; //
		
		try {
			conn = JDBCutil.connect();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, key);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setMbId(rs.getString("USERID"));
				dto.setMbNum(rs.getInt("BOARDID"));
				dto.setMbTitle(rs.getString("BTITLE"));
				dto.setMbContent(rs.getString("BCONTENT"));
				dto.setMbWriteDate(rs.getDate("BDATE"));
				dto.setMbfileName(rs.getString("BOARDFILE"));
				
				
//				dto.setbNum(rs.getInt("bnum"));
//				dto.setbName(rs.getString("bname"));
//				dto.setbContent(rs.getString("bcontent"));
//				dto.setbHit(rs.getInt("bhit"));
//				dto.setbTitle(rs.getString("btitle"));
//				dto.setbWriteDate(rs.getDate("bwritedate"));
//				dto.setbId(rs.getString("bid"));
//				dto.setBfileName(rs.getString("filename"));
//				if(str.equals("read"))
//					readCount(key); //조회수 1씩 올라가게 수정
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		close();
		return dto;
	}
	
	
	public int insert(MainBoardDTO dto) { //글추가
		int n = 0;
		String sql = "insert into NOTICE(USERID, NOTICEID, NTITLE, NCONTENT, NDATE, HIT, NOTICEFILE)\r\n" + 
				"values(?, ?, ?, ?, sysdate, ?)";
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMbId());
			pstmt.setString(2, dto.getMbTitle());
			pstmt.setString(3, dto.getMbContent());
			//pstmt.setString(4, dto.getbId());
			pstmt.setString(4, dto.getMbfileName());
			n = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		close();
		return n;
	}
	
	public int update(MainBoardDTO dto) { //글수정
		int n = 0;
		String sql = "update c_board set btitle=?, bcontent=?, boardfile=? where BOARDID = ?";
		
		if(dto.getMbfileName() == null) {
			 sql = "update c_board set btitle=?, bcontent=? where BOARDID = ?";			
		}
		
		try {
			int cnt = 1;
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(cnt++, dto.getMbTitle());
			pstmt.setString(cnt++, dto.getMbContent());
			if(dto.getMbfileName() != null) {
				pstmt.setString(cnt++, dto.getMbfileName());
			}
			pstmt.setInt(cnt++, dto.getMbNum());
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
		String sql = "update c_board set hit = hit + 1 where BOARDID= ? ";
		
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
