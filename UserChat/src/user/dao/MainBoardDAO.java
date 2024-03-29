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
	
	
	
	public int recordTotal(String NTITLE) {
		int count = 0;
		String where = "";
		if (NTITLE != null) {
			where += "where NTITLE like '%' || ? || '%' ";
		}
		String sql = "select count(*) from notice "
				+ where;

		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			if (NTITLE != null) {
				pstmt.setString(1, NTITLE);
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		return count;
	
	}
	
	public ArrayList<MainBoardDTO> select(String NTITLE, int first, int last) { //전체리스트라서 arraylist
		ArrayList<MainBoardDTO> list = new ArrayList<MainBoardDTO>();
		MainBoardDTO dto;
		String where = "";
		if (NTITLE != null) {
			where += "and NTITLE like '%' || ? || '%' ";
		}
		String sql = "select * from ( select a.*, rownum  rnum from ( "
					+ "select a.USERID userid, a.NOTICEID NOTICEID, a.NTITLE NTITLE , a.NCONTENT NCONTENT, a.NDATE NDATE, a.noticefile nOTICEFILE, a.HIT HIT,"
					+ "b.username username from notice a, C_user b where a.userid = b.userid  " + where
					+ " order by NOTICEID desc"
					+ ")a )b where rnum between ? and ?";
		
		try {
			int i = 0;
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			
			if (NTITLE != null) {
				pstmt.setString(++i, NTITLE);
			}
			pstmt.setInt(++i, first);
			pstmt.setInt(++i, last);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				dto = new MainBoardDTO();
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
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		
		return list;
	}
	
	public MainBoardDTO select (int key, String str) {
		MainBoardDTO dto = new MainBoardDTO();
		String sql = "select * from NOTICE where NOTICEID= ?"; //
		
		try {
			conn = JDBCutil.connect();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, key);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setMbId(rs.getString("USERID"));
				dto.setMbNum(rs.getInt("NOTICEID"));
				dto.setMbTitle(rs.getString("NTITLE"));
				dto.setMbContent(rs.getString("NCONTENT"));
				dto.setMbWriteDate(rs.getDate("NDATE"));
				dto.setMbfileName(rs.getString("NOTICEFILE"));
				dto.setMbHit(rs.getInt("HIT"));
				
				
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
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		return dto;
	}
	
	
	public int insert(MainBoardDTO dto) { //글추가
		int n = 0;
		String sql = "insert into NOTICE(USERID, NOTICEID, NTITLE, NCONTENT, NDATE, NOTICEFILE)\r\n" + 
				"values(?, MAINBOARD_SEQ.nextval, ?, ?, sysdate, ?)";
		
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
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		
		return n;
	}
	
	public int update(MainBoardDTO dto) { //글수정
		int n = 0;
		String sql = "update NOTICE set NTITLE=?, NCONTENT=?, NOTICEFILE=? where NOTICEID = ?";
		
		if(dto.getMbfileName() == null) {
			 sql = "update NOTICE set NTITLE=?, NCONTENT=? where NOTICEID = ?";			
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
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		return n;
	}
	
	
	public void delete(MainBoardDTO dto) { //글삭제
		String sql = "delete from NOTICE where NOTICEID = ? ";
		
				try {
					conn = JDBCutil.connect();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, dto.getMbNum());
					pstmt.executeUpdate();
		
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					JDBCutil.disconnect(pstmt, conn);
				}
		
	}
	

	
	public void readCount(int key) {
		String sql = "update NOTICE set hit = nvl(hit,0) + 1 where NOTICEID= ?";
		
		try {
			conn = JDBCutil.connect();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, key);
			pstmt.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
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
