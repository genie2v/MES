package MES;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DB.DBConnection;

public class FlowInfDao {
	private Connection conn;
	
	public Connection connection() {
		conn = DBConnection.getConnection();
		return conn;
	}
	
	//read
	public ArrayList<FlowInfDto> flowInf() throws SQLException{
		ArrayList<FlowInfDto> result = new ArrayList<FlowInfDto>();
		
		Statement stmt = conn.createStatement();
		String sql = "select * from flow_inf";
		
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			FlowInfDto dto = new FlowInfDto();
			
			dto.setFlow(rs.getString("flow"));
			result.add(dto);
		}
		
		return result;
	}

}
