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

	// create
	public int create(OperInfDto dto) throws SQLException {
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format(
				"insert into oper_inf (fac, oper, move_inout_yn, crt_tm, crt_user, chg_tm, chg_user) values ('PKG', '%s', '%s', sysdate, 'USER1', sysdate, 'USER1')",
				dto.getOper(), dto.getMoveInOutYn());

		result = stmt.executeUpdate(sql);

		stmt.close();

		return result;
	}

	// read (Create Lot comboBox bind)
	public ArrayList<OperInfDto> read() throws SQLException {
		ArrayList<OperInfDto> result = new ArrayList<OperInfDto>();
		OperInfDto dto = null;

		Statement stmt = conn.createStatement();
		String sql = "select * from oper_inf";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dto = new OperInfDto();

			dto.setFac(rs.getString("fac"));
			dto.setOper(rs.getString("oper"));
			dto.setMoveInOutYn(rs.getString("move_inout_yn"));
			dto.setCrtTm(rs.getDate("crt_tm"));
			dto.setCrtUser(rs.getString("crt_user"));
			dto.setChgTm(rs.getDate("chg_tm"));
			dto.setChgUser(rs.getString("chg_user"));
			result.add(dto);
		}

		return result;
	}

	// read
	public OperInfDto read(String fac, String oper) throws SQLException {
		OperInfDto dto = null;

		Statement stmt = conn.createStatement();
		String sql = String.format("select * from oper_inf where fac = '%s' and oper = '%s'", fac, oper);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dto = new OperInfDto();

			dto.setFac(rs.getString("fac"));
			dto.setOper(rs.getString("oper"));
			dto.setMoveInOutYn(rs.getString("move_inout_yn"));
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
	public int update(OperInfDto dto) throws SQLException {
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format(
				"update oper_inf set move_inout_yn = '%s', chg_tm = sysdate, chg_user = 'USER1' where fac = '%s' and oper = '%s'",
				dto.getMoveInOutYn(), dto.getFac(), dto.getOper());

		result = stmt.executeUpdate(sql);

		stmt.close();

		return result;
	}

	// delete
	public int delete(String fac, String oper) throws SQLException {
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format("delete from oper_inf where fac = '%s' and oper = '%s'", fac, oper);

		result = stmt.executeUpdate(sql);

		stmt.close();

		return result;
	}

	public void close() {
		DBConnection.close();
	}

}
