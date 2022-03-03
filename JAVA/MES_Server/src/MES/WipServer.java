package MES;

import java.io.*;
import java.net.*;
import java.sql.*;

public class WipServer {
	public static Connection conn = null;
	public static PreparedStatement pstm = null;
	public static ResultSet rs = null;

	public static ServerSocket serverSocket = null;
	public static Socket socket = null;
	public static InputStreamReader inputStreamReader = null;
	public static OutputStreamWriter outputStreamWriter = null;
	public static BufferedWriter bufferedWriter = null;
	public static BufferedReader bufferedReader = null;

	// create, update, delete...
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		while (true) {
			try {
				serverSocket = new ServerSocket(8010);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				socket = serverSocket.accept();
				System.out.println("Wip Server");
				System.out.println("Client Connected");

				conn = DBConnection.getConnection();

				inputStreamReader = new InputStreamReader(socket.getInputStream());
				outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

				bufferedReader = new BufferedReader(inputStreamReader);
				bufferedWriter = new BufferedWriter(outputStreamWriter);

				while (true) {
					String strReadPara = bufferedReader.readLine();
					System.out.println(strReadPara);

					String[] strParaList = strReadPara.split(";");
					String strAction = strParaList[0];

					if (strAction.contains("create_lot")) {
						String lotId = bufferedReader.readLine();
						String insertData = bufferedReader.readLine();
						System.out.println(lotId + insertData);

						create_lot(lotId, insertData);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public static void create_lot(String lotId, String insertData) throws SQLException, IOException {
		// TODO Auto-generated method stub
		String checkPK = "select lot from lot_inf where lot = '" + lotId + "'";
		pstm = conn.prepareStatement(checkPK);
		rs = pstm.executeQuery();
		if (rs.next()) {
			bufferedWriter.write("존재하는 LOT ID");
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} else {
			String insertLot = "insert into lot_inf (fac,lot,oper,flow,prod,prod_qty,last_timekey,crt_tm,crt_user,chg_tm,chg_user) values"
					+ "('PKG'," + insertData
					+ ",rpad(to_char(sysdate,'yyyymmddhh24mi'),20,'0'),sysdate,'USER1',sysdate,'USER1')";
			String insertHis = "insert into lot_his (fac,lot,oper,flow,prod,prod_qty,timekey,crt_tm,crt_user,chg_tm,chg_user) values"
					+ "('PKG'," + insertData
					+ ",rpad(to_char(sysdate,'yyyymmddhh24mi'),20,'0'),sysdate,'USER1',sysdate,'USER1')";
			pstm = conn.prepareStatement(insertLot);
			pstm.execute();
			pstm = conn.prepareStatement(insertHis);
			pstm.execute();
			System.out.println("Success Create");
			bufferedWriter.write("LOT생성");
			bufferedWriter.newLine();
			bufferedWriter.flush();
		}

	}

}
