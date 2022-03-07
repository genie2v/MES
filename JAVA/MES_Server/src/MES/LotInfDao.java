package MES;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DBConnection;

public class LotInfDao {
	private Connection conn;

	public Connection connection() {
		conn = DBConnection.getConnection();
		return conn;
	}

	// add
	public int add(LotInfDto dto) throws SQLException {
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format("select lot from lot_inf where lot = '%s'", dto.getLot());

		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			return result;
		} else {
			sql = String.format(
					"insert into lot_inf (fac, lot, last_timekey, oper, flow, prod,"
							+ "prod_qty, crt_tm, crt_user, chg_tm, chg_user) values "
							+ "('PKG', '%s', rpad(to_char(sysdate,'yyyymmddhh24mi'),20,'0'), '%s',"
							+ "'%s', '%s', %d, sysdate, 'USER1', sysdate, 'USER1' )",
					dto.getLot(), dto.getOper(), dto.getFlow(), dto.getProd(), dto.getProdQty());

			result = stmt.executeUpdate(sql);
			stmt.close();
			
			return result;
		}
	}
	
	//modify
	//remove

	
	public void close() {
		DBConnection.close();
	}
}
