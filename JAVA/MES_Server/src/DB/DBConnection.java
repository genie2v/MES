package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection dbConn;
	
	public static Connection getConnection() {
		if(dbConn == null) {
			try {
				String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
				String user = "person1";
				String pwd = "person";
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbConn = DriverManager.getConnection(url,user,pwd);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		return dbConn;
	}
	
	public static Connection getConnection(String url, String user, String pwd) {
		if(dbConn == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbConn = DriverManager.getConnection(url, user, pwd);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		return dbConn;
	}
	
	public static void close() {
		if(dbConn != null) {
			try {
				if(!dbConn.isClosed()) dbConn.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		dbConn = null;
	}

}
