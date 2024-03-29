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

	public int recordTotal(String title) {
		int count = 0;
		String where = "";
		if (title != null) {
			where += "where btitle like '%' || ? || '%' ";
		}
		String sql = "select count(*) from c_board "
				+ where;//

		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			if (title != null) {
				pstmt.setString(1, title);
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
	

	public BoarderDAO() {
		super();
	}

	public ArrayList<BoarderDTO> select_writer(String title, int first, int last) { // 전체리스트라서 arraylist
		ArrayList<BoarderDTO> list = new ArrayList<BoarderDTO>();
		BoarderDTO dto;
		String where = "";
		if (title != null) {
			where += "and a.userid like '%' || ? || '%' ";
		}
		String sql = "select * from ( select a.*, rownum  rnum from ( "
				+ "select a.USERID userid, a.BOARDID BOARDID, a.BTITLE BTITLE , a.BCONTENT BCONTENT, a.BDATE BDATE, a.BOARDFILE BOARDFILE, a.HIT HIT,"
				+ "b.username username from c_board a, C_user b  where a.userid = b.userid  " + where
				+ " order by BOARDID desc" 
				+ ")a )b where rnum between ? and ?";
		// "select USERID, BOARDID, BTITLE, BCONTENT, BDATE, BOARDFILE, HIT from
		// c_board";

		try {
			int i = 0;
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);

			if (title != null) {
				pstmt.setString(++i, title);
			}
			pstmt.setInt(++i, first);
			pstmt.setInt(++i, last);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		return list;
	}
	
	public ArrayList<BoarderDTO> select(String title, int first, int last) { // 전체리스트라서 arraylist
		ArrayList<BoarderDTO> list = new ArrayList<BoarderDTO>();
		BoarderDTO dto;
		String where = "";
		if (title != null) {
			where += "and btitle like '%' || ? || '%' ";
		}
		String sql = "select * from ( select a.*, rownum  rnum from ( "
				+ "select a.USERID userid, a.BOARDID BOARDID, a.BTITLE BTITLE , a.BCONTENT BCONTENT, a.BDATE BDATE, a.BOARDFILE BOARDFILE, a.HIT HIT,"
				+ "b.username username from c_board a, C_user b  where a.userid = b.userid  " + where
				+ " order by BOARDID desc" 
				+ ")a )b where rnum between ? and ?";
		// "select USERID, BOARDID, BTITLE, BCONTENT, BDATE, BOARDFILE, HIT from
		// c_board";

		try {
			int i = 0;
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);

			if (title != null) {
				pstmt.setString(++i, title);
			}
			pstmt.setInt(++i, first);
			pstmt.setInt(++i, last);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		return list;
	}


	public BoarderDTO select(int key, String str) {
		BoarderDTO dto = new BoarderDTO();
		String sql = "select * from c_board where BOARDID= ?"; //

		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, key);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setbId(rs.getString("USERID"));
				dto.setbNum(rs.getInt("BOARDID"));
				dto.setbTitle(rs.getString("BTITLE"));
				dto.setbContent(rs.getString("BCONTENT"));
				dto.setbWriteDate(rs.getDate("BDATE"));
				dto.setBfileName(rs.getString("BOARDFILE"));
				dto.setbHit(rs.getInt("HIT"));

//				dto.setbNum(rs.getInt("bnum"));
//				dto.setbName(rs.getString("bname"));
//				dto.setbContent(rs.getString("bcontent"));
//				dto.setbHit(rs.getInt("bhit"));
//				dto.setbTitle(rs.getString("btitle"));
//				dto.setbWriteDate(rs.getDate("bwritedate"));
//				dto.setbId(rs.getString("bid"));
//				dto.setBfileName(rs.getString("filename"));
//				if(str.equals("read"))
//					updateCount(key); //조회수 1씩 올라가게 수정
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		return dto;
	}

	public int insert(BoarderDTO dto) { // 글추가
		int n = 0;
		String sql = "insert into c_board(USERID,BOARDID,BTITLE,BCONTENT,BDATE,BOARDFILE) values(?, BOARD_SEQ.nextval, ?, ?,sysdate, ?)";
		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getbId());
			pstmt.setString(2, dto.getbTitle());
			pstmt.setString(3, dto.getbContent());
			// pstmt.setString(4, dto.getbId());
			pstmt.setString(4, dto.getBfileName());
			n = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}

		return n;
	}

	public int update(BoarderDTO dto) { // 글수정
		int n = 0;
		String sql = "update c_board set btitle=?, bcontent=?, boardfile=? where BOARDID = ?";

		if (dto.getBfileName() == null) {
			sql = "update c_board set btitle=?, bcontent=? where BOARDID = ?";
		}

		try {
			int cnt = 1;
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(cnt++, dto.getbTitle());
			pstmt.setString(cnt++, dto.getbContent());
			if (dto.getBfileName() != null) {
				pstmt.setString(cnt++, dto.getBfileName());
			}
			pstmt.setInt(cnt++, dto.getbNum());
			n = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
		return n;
	}

	public void delete(BoarderDTO dto) { // 글삭제
		String sql = "delete from c_board where BOARDID = ? ";

		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getbNum());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}

	}

//	public ArrayList<BoarderDTO> search(String title) { // 전체리스트라서 arraylist
//		ArrayList<BoarderDTO> list = new ArrayList<BoarderDTO>();
//		BoarderDTO dto;
//		String sql = "select * from c_board where BTITLE like '%?%'";
//
//		try {
//			conn = JDBCutil.connect();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, title);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				dto = new BoarderDTO();
//				dto.setbId(rs.getString("USERID"));
//				dto.setbNum(rs.getInt("BOARDID"));
//				dto.setbTitle(rs.getString("BTITLE"));
//				dto.setbContent(rs.getString("BCONTENT"));
//				dto.setbWriteDate(rs.getDate("BDATE"));
//				dto.setBfileName(rs.getString("BOARDFILE"));
//				dto.setbHit(rs.getInt("HIT"));
//				dto.setbName(rs.getString("username"));
//				list.add(dto);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCutil.disconnect(pstmt, conn);
//		}
//		return list;
//	}

	public void updateCount(int key) {
		String sql = "update c_board set hit = nvl(hit,0) + 1 where BOARDID= ?";

		try {
			conn = JDBCutil.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, key);
			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.disconnect(pstmt, conn);
		}
	}

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
