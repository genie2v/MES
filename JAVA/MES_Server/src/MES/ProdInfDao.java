package MES;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DB.DBConnection;

public class ProdInfDao {
	private Connection conn;

	public Connection connection() {
		conn = DBConnection.getConnection();
		return conn;
	}

	// read
	public ArrayList<ProdInfDto> read() throws SQLException {
		ArrayList<ProdInfDto> result = new ArrayList<ProdInfDto>();
		ProdInfDto dto = null;

		Statement stmt = conn.createStatement();
		String sql = "select * from prod_inf";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			dto = new ProdInfDto();

			dto.setFac(rs.getString("fac"));
			dto.setProd(rs.getString("prod"));
			dto.setCrtTm(rs.getDate("crt_tm"));
			dto.setCrtUser(rs.getString("crt_user"));
			dto.setChgTm(rs.getDate("chg_tm"));
			dto.setChgUser(rs.getString("chg_user"));
			result.add(dto);
		}

		return result;
	}
	
	// read
	public ProdInfDto read(String fac, String prod) throws SQLException {
		ProdInfDto dto = null;
		
		Statement stmt = conn.createStatement();
		String sql = String.format("select * from prod_inf where fac = '%s' and prod = '%s'", fac, prod);
		
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			dto = new ProdInfDto();

			dto.setFac(rs.getString("fac"));
			dto.setProd(rs.getString("prod"));
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
	public int read(ProdInfDto dto) throws SQLException {
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format(
				"update prod_inf set chg_tm = sysdate, chg_user = 'USER1' where fac = '%s' and prod = '%s'",
				dto.getFac(), dto.getProd());

		result = stmt.executeUpdate(sql);

		stmt.close();

		return result;
	}

	public void close() {
		DBConnection.close();
	}

}
