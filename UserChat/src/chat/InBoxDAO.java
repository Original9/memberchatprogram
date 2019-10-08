package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import user.dao.JDBCutil;

public class InBoxDAO {
	DataSource dataSource;

	public ArrayList<InBoxDTO> messagesList(String fromID) {
		ArrayList<InBoxDTO> list = new ArrayList<>();
		InBoxDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		String sql1 = "select a.fromid, a.mcount , b.username  from (select fromid,count(chatread) Mcount from chat where toId = ? group by fromid,chatread having chatread = 0) a natural join c_user b where a.fromid = b.userID";		
		try {
			conn = JDBCutil.connect();
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, fromID);			
			rs = pstmt1.executeQuery();			
			while(rs.next()) {
				dto = new InBoxDTO();
				dto.setFromID(rs.getString("fromid"));
				dto.setName(rs.getString("username"));
				dto.setUnreadMeassgeCount(rs.getInt("Mcount"));
				list.add(dto);				
				
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				JDBCutil.disconnect(pstmt1, conn);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	

}
