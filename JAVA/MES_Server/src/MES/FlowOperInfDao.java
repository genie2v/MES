package MES;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DBConnection;

public class FlowOperInfDao {
	private Connection conn;

	public Connection connection() {
		conn = DBConnection.getConnection();
		return conn;
	}

	// create
	public int create(FlowOperInfDto dto) throws SQLException {
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format(
				"insert into flow_oper_inf (fac, flow, oper, oper_seq, crt_tm, crt_user, chg_tm, chg_user) values ('PKG', '%s', '%s', %d, sysdate, 'USER1', sysdate, 'USER1')",
				dto.getFlow(), dto.getOper(), dto.getOperSeq());

		result = stmt.executeUpdate(sql);

		stmt.close();

		return result;
	}

	// read
	public FlowOperInfDto read(String fac, String flow, String oper) throws SQLException {
		FlowOperInfDto dto = null;

		Statement stmt = conn.createStatement();
		String sql = String.format("select * from flow_oper_inf where fac = '%s' and flow = '%s' and oper = '%s'", fac,
				flow, oper);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dto = new FlowOperInfDto();

			dto.setFac(rs.getString("fac"));
			dto.setFlow(rs.getString("flow"));
			dto.setOper(rs.getString("oper"));
			dto.setOperSeq(rs.getInt("oper_seq"));
			dto.setCrtTm(rs.getDate("crt_tm"));
			dto.setCrtUser(rs.getString("crt_user"));
			dto.setChgTm(rs.getDate("chg_tm"));
			dto.setChgUser(rs.getString("chg_user"));
			break;
		}

		return dto;
	}

	// read
	public FlowOperInfDto readByOperSeq(String fac, String flow, int oper_seq) throws SQLException {
		FlowOperInfDto dto = null;

		Statement stmt = conn.createStatement();
		String sql = String.format("select * from flow_oper_inf where fac = '%s' and flow = '%s' and oper_seq = %d",
				fac, flow, oper_seq);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dto = new FlowOperInfDto();

			dto.setFac(rs.getString("fac"));
			dto.setFlow(rs.getString("flow"));
			dto.setOper(rs.getString("oper"));
			dto.setOperSeq(rs.getInt("oper_seq"));
			dto.setCrtTm(rs.getDate("crt_tm"));
			dto.setCrtUser(rs.getString("crt_user"));
			dto.setChgTm(rs.getDate("chg_tm"));
			dto.setChgUser(rs.getString("chg_user"));
			break;
		}

		return dto;
	}

	// update
	public int update(FlowOperInfDto dto) throws SQLException {
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format(
				"update flow_oper_inf set oper_seq = %d, chg_tm = sysdate, chg_user = 'USER1' where fac = '%s' and flow = '%s' and oper = '%s'",
				dto.getOperSeq(), dto.getFac(), dto.getFlow(), dto.getOper());

		result = stmt.executeUpdate(sql);

		stmt.close();

		return result;
	}

	// delete
	public int delete(String fac, String flow, String oper) throws SQLException {
		int result = 0;
		
		Statement stmt = conn.createStatement();
		String sql = String.format("delete from flow_oper_inf where fac = '%s', flow = '%s', oper = '%s'", fac, flow, oper);
		
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
	}
	public void close() {
		DBConnection.close();
	}
}
