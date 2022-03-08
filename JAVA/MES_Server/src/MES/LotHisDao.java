package MES;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DBConnection;

public class LotHisDao {
	private Connection conn;

	public Connection connection() {
		conn = DBConnection.getConnection();
		return conn;
	}

	// add
	public void add(LotHisDto dto) throws SQLException {
		Statement stmt = conn.createStatement();

		String sql = String.format("select lot from lot_his where lot = '%s' and timekey = '%s'", dto.getLot(),
				dto.getTimkey());
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			stmt.close();
			return;
		} else {

			sql = String.format("insert into lot_his (fac, lot, timekey, oper, flow, prod,"
					+ "prod_qty, crt_tm, crt_user, chg_tm, chg_user) values ('PKG', '%s', rpad('%s',20,'0'), '%s',"
					+ "'%s', '%s', %d, sysdate, 'USER1', sysdate, 'USER1' )", dto.getLot(), dto.getTimkey(),
					dto.getOper(), dto.getFlow(), dto.getProd(), dto.getProdQty());

			stmt.executeQuery(sql);
			stmt.close();
		}
	}

	// modify
	// remove

	public void close() {
		DBConnection.close();
	}
}
