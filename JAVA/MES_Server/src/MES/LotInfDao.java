package MES;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import DB.DBConnection;

public class LotInfDao {
	private Connection conn;

	public Connection connection() {
		conn = DBConnection.getConnection();
		return conn;
	}

	// create
	public int create(LotInfDto dto) throws SQLException {
		int result = 0;

		try {
			Statement stmt = conn.createStatement();
			String sql = String.format("insert into lot_inf (fac, lot, last_timekey, oper, flow, prod,"
					+ "prod_qty, crt_tm, crt_user, chg_tm, chg_user, proc) values ('PKG', '%s', rpad('%s',20,'0'), '%s',"
					+ "'%s', '%s', %d, sysdate, 'USER1', sysdate, 'USER1', '%s' )", dto.getLot(), dto.getLastTimkey(),
					dto.getOper(), dto.getFlow(), dto.getProd(), dto.getProdQty(), dto.getProc());
			System.out.println(sql);
			result = stmt.executeUpdate(sql);

			stmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return -1;
		}

		return result;
	}

	// read
	public LotInfDto read(String lotId) throws SQLException {
		LotInfDto dto = null;
		Statement stmt = conn.createStatement();
		String sql = String.format("select * from lot_inf where lot = '%s'", lotId);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dto = new LotInfDto();

			dto.setFac(rs.getString("fac"));
			dto.setLot(rs.getString("lot"));
			dto.setLastTimekey(rs.getString("last_timekey"));
			dto.setOper(rs.getString("oper"));
			dto.setFlow(rs.getString("flow"));
			dto.setProc(rs.getString("proc"));
			dto.setProd(rs.getString("prod"));
			dto.setProdQty(rs.getInt("prod_qty"));
			dto.setCrtTm(rs.getDate("crt_tm"));
			dto.setCrtUser(rs.getString("crt_user"));
//			dto.setChgTm(rs.getDate("chg_tm"));
			dto.setChgTm(rs.getTimestamp("chg_tm"));
			dto.setChgUser(rs.getString("chg_user"));
			break;
		}

		stmt.close();
		rs.close();

		return dto;
	}

	// read (oper값으로), getLotList에서 사용(View Lot List)
	public ArrayList<LotInfDto> readByOper(String oper) throws SQLException {
		ArrayList<LotInfDto> result = new ArrayList<LotInfDto>();
		LotInfDto dto = null;

		Statement stmt = conn.createStatement();
		String sql = String.format("select * from lot_inf where oper = '%s' order by lot", oper);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dto = new LotInfDto();

			dto.setFac(rs.getString("fac"));
			dto.setLot(rs.getString("lot"));
			dto.setLastTimekey(rs.getString("last_timekey"));
			dto.setOper(rs.getString("oper"));
			dto.setFlow(rs.getString("flow"));
			dto.setProc(rs.getString("proc"));
			dto.setProd(rs.getString("prod"));
			dto.setProdQty(rs.getInt("prod_qty"));
			dto.setCrtTm(rs.getDate("crt_tm"));
			dto.setCrtUser(rs.getString("crt_user"));
//			dto.setChgTm(rs.getDate("chg_tm"));
			dto.setChgTm(rs.getTimestamp("chg_tm"));
			dto.setChgUser(rs.getString("chg_user"));

			result.add(dto);
		}

		rs.close();
		stmt.close();

		return result;
	}

	// update
	public int update(LotInfDto dto) throws SQLException {
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format(
				"update lot_inf set last_timekey = rpad('%s',20,'0'), oper = '%s', flow = '%s'"
						+ ", proc ='%s', prod = '%s', prod_qty = %d, chg_tm = sysdate"
						+ ", chg_user = 'USER1' where lot = '%s'",
				dto.getLastTimkey(), dto.getOper(), dto.getFlow(), dto.getProc(), dto.getProd(), dto.getProdQty(),
				dto.getLot());

		result = stmt.executeUpdate(sql);

		stmt.close();

		return result;
	}

	public int updateUndo(LotInfDto dto) throws SQLException {
		int result = 0;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String chg_tm = format.format(dto.getChgTm());
//		System.out.println(format.format(dto.getChgTm()));

		Statement stmt = conn.createStatement();
		String sql = String.format("update lot_inf set last_timekey=rpad('%s',20,'0'), oper = '%s', flow = '%s'"
				+ ", proc = '%s', prod = '%s', prod_qty = %d, chg_tm = to_date('%s','yy/MM/dd hh24:mi:ss'), chg_user = 'USER1' where lot = '%s'",
				dto.getLastTimkey(), dto.getOper(), dto.getFlow(), dto.getProc(), dto.getProd(), dto.getProdQty(),
				chg_tm, dto.getLot());
//		System.out.println(sql);

		result = stmt.executeUpdate(sql);

		stmt.close();

		return result;
	}

	// delete
	public int delete(String lotId) throws SQLException {
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format("delete from lot_inf where lot = '%s'", lotId);

		result = stmt.executeUpdate(sql);

		stmt.close();

		return result;
	}

	public void close() {
		DBConnection.close();
	}

}
