package MES;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DB.DBConnection;

public class OperInfDao {
	private Connection conn;

	public Connection connection() {
		conn = DBConnection.getConnection();
		return conn;
	}

	// read
	public ArrayList<OperInfDto> operInf() throws SQLException {
		ArrayList<OperInfDto> result = new ArrayList<OperInfDto>();

		Statement stmt = conn.createStatement();
		String sql = "select * from oper_inf";
		
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			OperInfDto dto = new OperInfDto();
			
			dto.setOper(rs.getString("oper"));
			result.add(dto);
		}
		
		return result;
	}

	// read(oper값으로)
	public ArrayList<OperInfDto> operInf(String oper) throws SQLException {
		ArrayList<OperInfDto> result = new ArrayList<OperInfDto>();

		Statement stmt = conn.createStatement();
		String sql = String.format("select * from oper_inf where oper = '%s'", oper);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			OperInfDto dto = new OperInfDto();

			dto.setOper(rs.getString("oper"));
			dto.setMoveInOutYn(rs.getString("move_inout_yn"));
			result.add(dto);
		}

		return result;
	}

	public void close() {
		DBConnection.close();
	}

}
