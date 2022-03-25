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
	public ArrayList<ProdInfDto> prodInf() throws SQLException {
		ArrayList<ProdInfDto> result = new ArrayList<ProdInfDto>();

		Statement stmt = conn.createStatement();
		String sql = "select * from prod_inf";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			ProdInfDto dto = new ProdInfDto();

			dto.setProd(rs.getString("prod"));
			result.add(dto);
		}

		return result;
	}

}
