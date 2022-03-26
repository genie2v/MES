package MES;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DB.DBConnection;

public class FlowOperInfDao {
	private Connection conn;

	public Connection connection() {
		conn = DBConnection.getConnection();
		return conn;
	}

	// read
	public ArrayList<FlowOperInfDto> flowoperInf(String flow, String oper) throws SQLException {
		ArrayList<FlowOperInfDto> result = new ArrayList<FlowOperInfDto>();

		Statement stmt = conn.createStatement();
		String sql = String.format("select * from flow_oper_inf where flow = '%s' and oper = '%s'", flow, oper);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			FlowOperInfDto dto = new FlowOperInfDto();

			dto.setFlow(rs.getString("flow"));
			dto.setOper(rs.getString("oper"));
			dto.setOperSeq(rs.getInt("oper_seq"));

			result.add(dto);
		}

		return result;
	}

	// read
	public FlowOperInfDto read(String fac, String flow, String oper) throws SQLException {
		FlowOperInfDto dto = null;

		Statement stmt = conn.createStatement();
		String sql = String.format("select * from flow_oper_inf where fac = '%s' and flow = '%s' and oper = '%s'", fac, flow, oper);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dto = new FlowOperInfDto();

			dto.setFac(rs.getString("fac"));
			dto.setFlow(rs.getString("flow"));
			dto.setOper(rs.getString("oper"));
			dto.setOperSeq(rs.getInt("oper_seq"));
			dto.setCrtTm(rs.getDate("crt_tm"));
			dto.setCrtIser(rs.getString("crt_user"));
			dto.setChgTm(rs.getDate("chg_tm"));
			dto.setCHgIser(rs.getString("chg_user"));

			result.add(dto);
		}

		return dto;
	}


	public FlowOperInfDto readByOperSeq(String fac, String flow, String seq) throws SQLException {
		FlowOperInfDto dto = null;

		Statement stmt = conn.createStatement();
		String sql = String.format("select * from flow_oper_inf where fac = '%s' flow = '%s' and seq = '%s'", fac, flow, seq);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dto = new FlowOperInfDto();

			dto.setFac(rs.getString("fac"));
			dto.setFlow(rs.getString("flow"));
			dto.setOper(rs.getString("oper"));
			dto.setOperSeq(rs.getInt("oper_seq"));
			dto.setCrtTm(rs.getDate("crt_tm"));
			dto.setCrtIser(rs.getString("crt_user"));
			dto.setChgTm(rs.getDate("chg_tm"));
			dto.setCHgIser(rs.getString("chg_user"));

			result.add(dto);
		}

		return dto;
	}

	// 다음 oper read
	public String nextOper(FlowOperInfDto dto) throws SQLException {
		String result = "";

		Statement stmt = conn.createStatement();
		String sql = String.format("select oper from flow_oper_inf where flow = '%s' and oper_seq = %d", dto.getFlow(),
				dto.getOperSeq());
		
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			result = rs.getString("oper");
		}
		return result;
	}

	public void close() {
		DBConnection.close();
	}
}
