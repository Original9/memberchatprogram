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

	public ArrayList<InBoxDTO> messagesList(String userID) {
		ArrayList<InBoxDTO> list1 = new ArrayList<>();
		ArrayList<InBoxDTO> list2 = new ArrayList<>();
		InBoxDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
//		String sql1 = "select x.chat_num, x.fromid, x.username, y.mcount "
//				+ "from (select chat_num,fromid, username from (select chat_num,fromid from chat where toid=? group by fromid,chat_num) a, c_user b where a.fromid = b.userid) x left outer join (select a.fromid, a.mcount , b.username  from (select fromid,count(chatread) Mcount from chat where toId = ? group by fromid,chatread having chatread = 0) a natural join c_user b where a.fromid = b.userID) y on x.fromid = y.fromid";
		String sql1 = "SELECT\r\n" + 
				"            chat_num,\r\n" + 
				"            fromid,\r\n" + 
				"            username,\r\n" + 
				"            (SELECT   COUNT(chatread) mcount\r\n" + 
				"                FROM\r\n" + 
				"                    chat\r\n" + 
				"                WHERE\r\n" + 
				"                    toid = ? \r\n" + 
				"                    and  chatread=0\r\n" + 
				"                   and  fromid = a.fromid ) Mcount\r\n" + 
				"        FROM\r\n" + 
				"            (\r\n" + 
				"                SELECT\r\n" + 
				"                    chat_num,\r\n" + 
				"                    fromid\r\n" + 
				"                FROM\r\n" + 
				"                    chat\r\n" + 
				"                WHERE\r\n" + 
				"                    toid = ?\r\n" + 
				"                GROUP BY\r\n" + 
				"                    fromid,\r\n" + 
				"                    chat_num\r\n" + 
				"            ) a,\r\n" + 
				"            c_user b\r\n" + 
				"        WHERE\r\n" + 
				"            a.fromid = b.userid";
		String sql2 = "select * from chat_room ";
		
		try {
			conn = JDBCutil.connect();
			pstmt1 = conn.prepareStatement(sql1);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt1.setString(1, userID);
			pstmt1.setString(2,userID);
			
			rs1 = pstmt1.executeQuery();
			rs2 = pstmt2.executeQuery();
			while(rs2.next()) 
			{
				dto = new InBoxDTO();
				dto.setChat_num(rs2.getInt("chat_num"));
				dto.setChat_participants(rs2.getString("chat_member"));				
				list2.add(dto);
				System.out.println(list2); // 현재 참여자는 kwj 밖에 없다. 
				// list2 에 넣어준다. 메세지함에서 삭제했는지 안했는지 확인하기 위해서 
			}			
			
			while(rs1.next()) {
				dto = new InBoxDTO();	
				dto.setChat_num(rs1.getInt("chat_num"));
				dto.setFromID(rs1.getString("fromid"));
				dto.setName(rs1.getString("username"));				
				dto.setUnreadMeassgeCount(rs1.getInt("Mcount")); // Integer type의 초기값은 0 , null이 들어가 있다면
				
				if(is_member_not_deleted(list2, dto, userID)){
					System.out.println("is_member_not_deleted안하고 추가한다.");
					list1.add(dto);// 리스트에 추가하기 전에 방번호에 현재 유저가 DELETE 했는지 확인하고 리스트에 넣어준다.
				}else {
					System.out.println("삭제를 했으면 일로 들어와야하는데 !!!!");
					// 업으면 add안해준다. 
				}		
									
			}			
			return list1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				JDBCutil.disconnect(pstmt1, conn);
				JDBCutil.disconnect(pstmt2, conn);
			
		}
		
		return null;
	}
	public boolean is_member_not_deleted(ArrayList<InBoxDTO> list, InBoxDTO dto , String userID) { // 넘어 오는 dto에는 fromid가 넘어온다.
		for(int i=0; i < list.size() ; i++) {
			if(list.get(i).getChat_num() == dto.getChat_num())
			{
				String members[] = list.get(i).getChat_participants().split(",");
				for(int x = 0; x < members.length; x++ ) { 
					if(members[x].equals(userID)) { // userid 가있는지 없는지 비교해햐한다. 						
						return true;
					}
				}
			}
			
		}
		
		return false;
		
	}
	
	public void delete_chat_member(String chatNum, String userID) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs1 = null;
		String members[] = new String[1]; // 일단 채팅방에는 2명으로 제한한다. 
		String member = null;
		String sql1 = "select * from chat_room where chat_num = ?";
		String sql2 = "update chat_room set chat_member = ? where chat_num = ?";
		String sql3 = "update chat_room set user_count = user_count-1 where chat_num = ?";
		String sql4 = "delete from chat_room where chat_num = ?";
		
		int user_count;
		
		try {
			conn = JDBCutil.connect();
			pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, chatNum);
			rs1 = pstmt1.executeQuery();
			while(rs1.next()) {
				user_count = rs1.getInt("user_count"); // 처음 select 했을때 user_count값을 가져온다. 
				members = rs1.getString("chat_member").split(",");				
				for(int i = 0; i < members.length; i++) {
					if(members[i].equals(userID))
					{
						members[i]=""; // 아이디를 없애준다.
						
						pstmt3 = conn.prepareStatement(sql3); // user count 에서 하나 뺀다.
						if(user_count -1 == 0 )// 대화방에 모든 사람이 빠져나갔다면 
						{
							pstmt4 = conn.prepareStatement(sql4);
							pstmt4.setString(1, chatNum);
							pstmt4.executeUpdate();
						}
						pstmt3.setString(1, chatNum);
						pstmt3.executeUpdate();
					}
				}
				for(int i = 0; i < members.length; i++) {// 현재 2명이서 채팅한다는 가정
					if(!members[i].equals(""))
					{
						member = members[i]; 
					}
				}
			}
			
			pstmt2 = conn.prepareStatement(sql2);			
			pstmt2.setString(1, member);
			pstmt2.setString(2, chatNum);						
			pstmt2.executeUpdate();// 
			//마지막으로 user_count = 0 이면 chat 내용까지 다 삭제한다. 
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.disconnect(pstmt1, conn);
			JDBCutil.disconnect(pstmt2, conn);
			JDBCutil.disconnect(pstmt3, conn);
			JDBCutil.disconnect(pstmt4, conn);
		
	}
		
		
	}

}
