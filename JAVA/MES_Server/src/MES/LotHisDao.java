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

	// lotId로 lot_his 검색
	public ArrayList<LotHisDto> lists(String lotId) throws SQLException {
		ArrayList<LotHisDto> result = new ArrayList<LotHisDto>();

		Statement stmt = conn.createStatement();
		String sql = String.format("select lot, oper, flow, prod, prod_qty from lot_his where lot = '%s'", lotId);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			LotHisDto dto = new LotHisDto();
			
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
	// modify
	// remove

	public void close() {
		DBConnection.close();
	}
}
