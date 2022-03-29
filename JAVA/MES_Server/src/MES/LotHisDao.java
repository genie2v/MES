package MES;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DB.DBConnection;

public class LotHisDao {
	private Connection conn;

	public Connection connection() {
		conn = DBConnection.getConnection();
		return conn;
	}

	// create
	public void create(LotHisDto dto) throws SQLException {
		try {
			Statement stmt = conn.createStatement();
			String sql = String.format("insert into lot_his (fac, lot, timekey, oper, flow, prod,"
					+ "prod_qty, crt_tm, crt_user, chg_tm, chg_user, proc, txn_cd) values ('PKG', '%s', rpad('%s',20,'0'), '%s',"
					+ "'%s', '%s', %d, sysdate, 'USER1', sysdate, 'USER1', '%s', '%s' )", dto.getLot(), dto.getTimkey(),
					dto.getOper(), dto.getFlow(), dto.getProd(), dto.getProdQty(), dto.getProc(), dto.getTxnCd());

			stmt.executeQuery(sql);
			stmt.close();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return;
		}
	}

	// read
	public LotHisDto read(String lotId, String timekey) throws SQLException {
		LotHisDto dto = null;
		Statement stmt = conn.createStatement();
		String sql = String.format("select * from lot_his where lot = '%s' and timekey = '%s'", lotId, timekey);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dto = new LotHisDto();

			dto.setFac(rs.getString("fac"));
			dto.setLot(rs.getString("lot"));
			dto.setTimekey(rs.getString("timekey"));
			dto.setTxnCd(rs.getString("txn_cd"));
			dto.setOper(rs.getString("oper"));
			dto.setFlow(rs.getString("flow"));
			dto.setProc(rs.getString("proc"));
			dto.setProd(rs.getString("prod"));
			dto.setProdQty(rs.getInt("prod_qty"));
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

	// read(View Lot History)
	public ArrayList<LotHisDto> read(String lotId) throws SQLException {
		ArrayList<LotHisDto> result = new ArrayList<LotHisDto>();
		LotHisDto dto = null;

		Statement stmt = conn.createStatement();
		String sql = String.format("select * from lot_his where lot = '%s'", lotId);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dto = new LotHisDto();

			dto.setFac(rs.getString("fac"));
			dto.setLot(rs.getString("lot"));
			dto.setTimekey(rs.getString("timekey"));
			dto.setOper(rs.getString("oper"));
			dto.setFlow(rs.getString("flow"));
			dto.setProd(rs.getString("prod"));
			dto.setCrtTm(rs.getDate("crt_tm"));
			dto.setCrtUser(rs.getString("crt_user"));
			dto.setChgTm(rs.getDate("chg_tm"));
			dto.setChgUser(rs.getString("chg_user"));
			dto.setProdQty(rs.getInt("prod_qty"));
			dto.setProc(rs.getString("proc"));
			dto.setTxnCd(rs.getString("txn_cd"));

			result.add(dto);
		}

		rs.close();
		stmt.close();

		return result;
	}

	// update
	public int update(LotHisDto dto) throws SQLException {
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format(
				"update lot_his set oper = '%s', flow = '%s', prod = '%s', prod_qty = %d, proc ='%s', txn_cd = '%s' where lot = '%s' and timekey = '%s'",
				dto.getOper(), dto.getFlow(), dto.getProd(), dto.getProdQty(), dto.getProc(), dto.getTxnCd(),
				dto.getLot(), dto.getTimkey());

		result = stmt.executeUpdate(sql);
		
		stmt.close();

		return result;
	}
	
	// delete
	public int delete(String lotId, String timekey) throws SQLException {
		int result = 0;
		
		Statement stmt = conn.createStatement();
		String sql = String.format("delete from lot_his where lot = '%s' and timekey = '%s'", lotId, timekey);
		
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
	}
	
	public int delete(String lotId) throws SQLException {
		int result = 0;
		
		Statement stmt = conn.createStatement();
		String sql = String.format("delete from lot_his where lot = '%s'", lotId);
		
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
	}

	public void close() {
		DBConnection.close();
	}
}
