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

	// create
	public int create(FlowInfDto dto) throws SQLException {
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format(
				"insert into flow_inf (fac, flow, crt_tm, crt_user, chg_tm, chg_user) values ('PKG', '%s', sysdate, 'USER1', sysdate, 'USER1')",
				dto.getFlow());

		result = stmt.executeUpdate(sql);

		stmt.close();

		return result;
	}

	// read (Create Lot comboBox bind)
	public ArrayList<FlowInfDto> read() throws SQLException {
		ArrayList<FlowInfDto> result = new ArrayList<FlowInfDto>();
		FlowInfDto dto = null;

		Statement stmt = conn.createStatement();
		String sql = "select * from flow_inf";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dto = new FlowInfDto();

			dto.setFac(rs.getString("fac"));
			dto.setFlow(rs.getString("flow"));
			dto.setCrtTm(rs.getDate("crt_tm"));
			dto.setCrtUser(rs.getString("crt_user"));
			dto.setChgTm(rs.getDate("chg_tm"));
			dto.setChgUser(rs.getString("chg_user"));
			result.add(dto);
		}

		stmt.close();
		rs.close();

		return result;
	}

	// read
	public FlowInfDto read(String fac, String flow) throws SQLException {
		FlowInfDto dto = null;

		Statement stmt = conn.createStatement();
		String sql = String.format("select * from flow_inf where fac = '%s' and flow = '%s'", fac, flow);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dto = new FlowInfDto();

			dto.setFac(rs.getString("fac"));
			dto.setFlow(rs.getString("flow"));
			dto.setCrtTm(rs.getDate("crt_tm"));
			dto.setCrtUser(rs.getString("crt_user"));
			dto.setChgTm(rs.getDate("chg_tm"));
			dto.setChgUser(rs.getString("chg_user"));

			break;
		}

		stmt.close();
		rs.close();

		return dto;
	}

	// update
	public int update(FlowInfDto dto) throws SQLException {
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format(
				"update flow_inf set chg_tm = sysdate, chg_user = 'USER1' where fac = '%s' and flow = '%s'",
				dto.getFac(), dto.getFlow());

		result = stmt.executeUpdate(sql);

		stmt.close();

		return result;
	}

	// delete
	public int delete(String fac, String flow) throws SQLException {
		int result = 0;
		
		Statement stmt = conn.createStatement();
		String sql = String.format("delete from flow_inf where fac = '%s' and flow = '%s'", fac, flow);
		
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
	}

	public void close() {
		DBConnection.close();
	}

}
