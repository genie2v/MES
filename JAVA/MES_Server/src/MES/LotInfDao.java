package MES;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

		try {
			Statement stmt = conn.createStatement();
			String sql = String.format("insert into lot_inf (fac, lot, last_timekey, oper, flow, prod,"
					+ "prod_qty, crt_tm, crt_user, chg_tm, chg_user, proc) values ('PKG', '%s', rpad('%s',20,'0'), '%s',"
					+ "'%s', '%s', %d, sysdate, 'USER1', sysdate, 'USER1', 'LoggedOut' )", dto.getLot(),
					dto.getLastTimkey(), dto.getOper(), dto.getFlow(), dto.getProd(), dto.getProdQty());
			result = stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return -1;
		}

		return result;
	}

	// read (lot값으로)
	public ArrayList<LotInfDto> lotInf(String lotId) throws SQLException {
		ArrayList<LotInfDto> result = new ArrayList<LotInfDto>();

		Statement stmt = conn.createStatement();
		String sql = String.format("select * from lot_inf where lot = '%s'", lotId);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			LotInfDto dto = new LotInfDto();

			dto.setLot(rs.getString("lot"));
			dto.setOper(rs.getString("oper"));
			dto.setFlow(rs.getString("flow"));
			dto.setProd(rs.getString("prod"));
			dto.setProdQty(rs.getInt("prod_qty"));
			dto.setProc(rs.getString("proc"));

			result.add(dto);
		}
		return result;
	}

	// read (oper값으로)
	public ArrayList<LotInfDto> lists(String oper) throws SQLException {
		ArrayList<LotInfDto> result = new ArrayList<LotInfDto>();

		Statement stmt = conn.createStatement();
		String sql = String.format("select lot, oper, flow, prod, prod_qty from lot_inf where oper = '%s' order by lot", oper);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			LotInfDto dto = new LotInfDto();

			dto.setLot(rs.getString("lot"));
			dto.setOper(rs.getString("oper"));
			dto.setFlow(rs.getString("flow"));
			dto.setProd(rs.getString("prod"));
			dto.setProdQty(rs.getInt("prod_qty"));

			result.add(dto);
		}
		rs.close();
		stmt.close();

		return result;
	}

	// update(proc)
	public int updateProc(LotInfDto dto) throws SQLException {
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format(
				"update lot_inf set last_timekey=rpad('%s',20,'0'), proc='%s', chg_tm = sysdate where lot = '%s'",
				dto.getLastTimkey(), dto.getProc(), dto.getLot());

		result = stmt.executeUpdate(sql);

		stmt.close();

		return result;
	}

	// update(oper)
	public int updateOper(LotInfDto dto) throws SQLException {
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format(
				"update lot_inf set last_timekey=rpad('%s',20,'0'), oper = '%s', proc = '%s', chg_tm = sysdate where lot = '%s'",
				dto.getLastTimkey(), dto.getOper(), dto.getProc(), dto.getLot());

		result = stmt.executeUpdate(sql);

		stmt.close();

		return result;
	}

	public void close() {
		DBConnection.close();
	}

}
