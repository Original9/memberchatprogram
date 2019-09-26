package user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

		public static DAO instance = new DAO();
		public static DAO getInstance() {
			return instance;
		}
	
	   private String driver="oracle.jdbc.driver.OracleDriver";
	   private String url="jdbc:oracle:thin:@localhost:1521:xe";
	   private String user="demo";
	   private String password="demo";
	   
	   Connection conn;
	   
	   public DAO()
	   {
		   try {
			Class.forName(driver);
			try {
				conn = DriverManager.getConnection(url,user,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	   }
}
