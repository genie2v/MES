package MES;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection dbConn;
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			String user = "person1";
			String pw = "person";
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,user,pw);
			
			System.out.println("DB CONNECT");
		} catch(ClassNotFoundException e) {
			System.out.println(e.toString());
		} catch(SQLException e) {
			System.out.println(e.toString());
		} catch(Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		
		return conn;
	}

}
