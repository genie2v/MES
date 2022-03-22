package MES;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DB.DBConnection;

public class QueryServer {
	public static Connection conn = null;
	public static PreparedStatement pstm = null;
	public static ResultSet rs = null;

	public static ServerSocket serverSocket = null;
	public static Socket socket = null;
	public static InputStreamReader inputStreamReader = null;
	public static OutputStreamWriter outputStreamWriter = null;
	public static BufferedWriter bufferedWriter = null;
	public static BufferedReader bufferedReader = null;

	// read
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		LotInfProcess infProcess = new LotInfProcess();
		LotHisProcess hisProcess = new LotHisProcess();

		while (true) {
			try {
				serverSocket = new ServerSocket(8000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				socket = serverSocket.accept();
				System.out.println("Query Server");
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
					String strAction = strParaList[0]; // action=get_combo

					if (strAction.contains("get_oper")) {
						select_oper();
					} else if (strAction.contains("get_flow")) {
						select_flow();
					} else if (strAction.contains("get_prod")) {
						select_prod();
					}
					else if (strAction.contains("get_his")) {
						String lotId = "";
						for (int i = 1; i < strParaList.length; i++) {
							String[] strParaValue = strParaList[i].split("=");
							for (int j = 0; j < strParaValue.length; j++) {
								if (strParaValue[j].equals("lot_id")) {
									lotId = strParaValue[1];
									break;
								}
							}
						}
						ArrayList<String> arr = hisProcess.getHis(lotId);
						bufferedWriter.write(String.valueOf(arr.size()));
						bufferedWriter.newLine();
						bufferedWriter.flush();

						for (int i = 0; i < arr.size(); i++) {
							String response = arr.get(i);
							System.out.println(response);
							bufferedWriter.write(response);
							bufferedWriter.newLine();
							bufferedWriter.flush();
						}

					} else if (strAction.contains("get_qty")) {
						get_qty();
					} else if (strAction.contains("get_lotlist")) {
						String strPara1 = "";
						for (int i = 1; i < strParaList.length; i++) {
							String[] strParaValue = strParaList[i].split("=");
							for (int j = 0; j < strParaValue.length; j++) {
								if (strParaValue[j].equals("oper")) {
									strPara1 = strParaValue[1];
									break;
								}
							}
						}
						get_lotlist(strPara1);
					} else if (strAction.contains("get_lotinf")) {
						String lotId = "";
						for (int i = 1; i < strParaList.length; i++) {
							String[] strParaValue = strParaList[i].split("=");
							for (int j = 0; j < strParaValue.length; j++) {
								if (strParaValue[j].equals("lot_id")) {
									lotId = strParaValue[1];
									break;
								}
							}
						}
						String response = infProcess.getLotInf(lotId);
						System.out.println(response);
						bufferedWriter.write(response);
						bufferedWriter.newLine();
						bufferedWriter.flush();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void select_oper() throws SQLException, IOException {
		String selOper = "select oper from oper_inf";
		pstm = conn.prepareStatement(selOper);
		rs = pstm.executeQuery();

		String response = "";

		while (rs.next())
			response += rs.getString(1) + ",";
		response = response.substring(0, response.length() - 1);
		System.out.println(response);
		bufferedWriter.write(response);
		bufferedWriter.newLine();
		bufferedWriter.flush();
	}

	public static void select_flow() throws SQLException, IOException {
		String selFlow = "select flow from flow_inf";
		pstm = conn.prepareStatement(selFlow);
		rs = pstm.executeQuery();

		String response = "";

		while (rs.next())
			response += rs.getString(1) + ",";
		response = response.substring(0, response.length() - 1);
		System.out.println(response);
		bufferedWriter.write(response);
		bufferedWriter.newLine();
		bufferedWriter.flush();
	}

	public static void select_prod() throws SQLException, IOException {
		String selProd = "select prod from prod_inf";
		pstm = conn.prepareStatement(selProd);
		rs = pstm.executeQuery();

		String response = "";

		while (rs.next())
			response += rs.getString(1) + ",";
		response = response.substring(0, response.length() - 1);
		System.out.println(response);
		bufferedWriter.write(response);
		bufferedWriter.newLine();
		bufferedWriter.flush();
	}

	/*
	 * wip public static void create_lot(String lotId, String insertData) throws
	 * IOException, SQLException { // String insertData = bufferedReader.readLine();
	 * System.out.println(insertData);
	 * 
	 * // check // 데이터 등록 시 mes_db 엑셀에 표시된 것 처럼 컬럼을 모두 채우기 바랍니다 // 등록화면에 보이지 않았던
	 * TIMEKEY 등등의 값도 전부 입력되어야 합니다 // 그리고 이미 등록된 랏을 또 등록하려고 할 경우 현재 소스 상태에서는
	 * exception 이 발생할 것입니다 // exception이 발생하여 오류처리 되지 않고 클라이언트로 메시지를 보내 오류내용을 전달해야
	 * 합니다 // 따라서 등록하기 전에 이미 등록된 key 데이터 인지 select 를 하고 insert를 해야 합니다 // 클라이언트에서
	 * 서버로 보낼 때 파라미터 형식으로 보낸 것 처럼 // 서버에서도 클라이언트로 보낼 때 파라미터 형식으로 보내어 처리하면 됩니다
	 * 
	 * // 예시) // "return=0;para1=para1;para2=para2" -> 정상 처리된 경우 return=0 //
	 * "return=Error-01" -> 오류가 발생한 경우 에러코드를 지정하여 클라이언트에서 해당 코드를 확인 후 오류 메시지 표시
	 * 
	 * // client > server; insertData 보내는 방법 변경 필요 String checkPk =
	 * "select lot from lot_inf where lot='" + lotId + "'"; pstm =
	 * conn.prepareStatement(checkPk); rs = pstm.executeQuery(); if (rs.next()) {
	 * bufferedWriter.write("이미 있는 LOT"); bufferedWriter.newLine();
	 * bufferedWriter.flush(); } else { String insertLot =
	 * "insert into lot_inf (fac,lot,oper,flow,prod,prod_qty,last_timekey,crt_tm,crt_user,chg_tm,chg_user) values"
	 * + "('PKG'," + insertData +
	 * ",rpad(to_char(sysdate,'yyyymmddhh24mi'),20,'0'),sysdate,'USER1',sysdate,'USER1')";
	 * String insertHis =
	 * "insert into lot_his (fac,lot,oper,flow,prod,prod_qty,timekey,crt_tm,crt_user,chg_tm,chg_user) values"
	 * + "('PKG'," + insertData +
	 * ",rpad(to_char(sysdate,'yyyymmddhh24mi'),20,'0'),sysdate,'USER1',sysdate,'USER1')";
	 * pstm = conn.prepareStatement(insertLot); pstm.execute(); pstm =
	 * conn.prepareStatement(insertHis); pstm.execute();
	 * System.out.println("Success Create"); bufferedWriter.write("LOT생성");
	 * bufferedWriter.newLine(); bufferedWriter.flush(); } }
	 */

	/*
	 * public static void get_his(String searchId) throws IOException, SQLException
	 * { // String searchId = bufferedReader.readLine(); //
	 * System.out.println(searchId);
	 * 
	 * String countQuery = "select count(*) from lot_his where lot='" + searchId +
	 * "'"; pstm = conn.prepareStatement(countQuery); rs = pstm.executeQuery();
	 * String count = ""; while (rs.next()) count = rs.getString(1);
	 * bufferedWriter.write(count); bufferedWriter.newLine();
	 * bufferedWriter.flush(); System.out.println(count);
	 * 
	 * String searchHis =
	 * "select lot,oper,flow,prod,prod_qty from lot_his where lot = '" + searchId +
	 * "'"; pstm = conn.prepareStatement(searchHis); rs = pstm.executeQuery();
	 * String response; while (rs.next()) { response = ""; response +=
	 * rs.getString("lot") + ","; response += rs.getString("oper") + ","; response
	 * += rs.getString("flow") + ","; response += rs.getString("prod") + ",";
	 * response += rs.getString("prod_qty");
	 * 
	 * System.out.println(response); bufferedWriter.write(response);
	 * bufferedWriter.newLine(); bufferedWriter.flush(); } }
	 */

	public static void get_qty() throws SQLException, IOException {
		// System.out.println("getQty");
		String countQuery = "select count(distinct oper) from lot_inf";
		pstm = conn.prepareStatement(countQuery);
		rs = pstm.executeQuery();
		String count = "";
		while (rs.next())
			count = rs.getString(1);
		bufferedWriter.write(count);
		bufferedWriter.newLine();
		bufferedWriter.flush();
		System.out.println(count);

		String groupOper = "select oper, count(lot), sum(prod_qty) from lot_inf " + "group by oper order by oper";
		pstm = conn.prepareStatement(groupOper);
		rs = pstm.executeQuery();
		String response;
		while (rs.next()) {
			response = "";
			response += rs.getString(1) + ",";
			response += rs.getString(2) + ",";
			response += rs.getString(3);

			System.out.println(response);
			bufferedWriter.write(response);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		}
	}

	public static void get_lotlist(String lotOper) throws IOException, SQLException {
		// System.out.println("getList");
		// String lotOper = bufferedReader.readLine();
		// System.out.println(lotOper);

		String countQuery = "select count(lot) from lot_inf where oper = '" + lotOper + "'";
		pstm = conn.prepareStatement(countQuery);
		rs = pstm.executeQuery();
		String count = "";
		while (rs.next())
			count = rs.getString(1);
		bufferedWriter.write(count);
		bufferedWriter.newLine();
		bufferedWriter.flush();
		// System.out.println(count);

		String getLotlist = "select lot,oper,flow,prod,prod_qty from lot_inf where oper = '" + lotOper + "'";
		pstm = conn.prepareStatement(getLotlist);
		rs = pstm.executeQuery();
		String response;
		while (rs.next()) {
			response = "";
			response += rs.getString("lot") + ",";
			response += rs.getString("oper") + ",";
			response += rs.getString("flow") + ",";
			response += rs.getString("prod") + ",";
			response += rs.getString("prod_qty");

			System.out.println(response);
			bufferedWriter.write(response);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		}
	}
}
