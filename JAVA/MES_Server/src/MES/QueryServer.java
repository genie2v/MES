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

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		String msg = "";
		String action = "";

		try {
			serverSocket = new ServerSocket(8000);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (true) {
			try {
				socket = serverSocket.accept();
				System.out.println("Client Connected");

				conn = DBConnection.getConnection();

				inputStreamReader = new InputStreamReader(socket.getInputStream());
				outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

				bufferedReader = new BufferedReader(inputStreamReader);
				bufferedWriter = new BufferedWriter(outputStreamWriter);

				action = bufferedReader.readLine();
				if (action.equals("get_combo")) {
					msg = bufferedReader.readLine();
					if (msg.equals("oper"))
						select_oper();
					msg = bufferedReader.readLine();
					if (msg.equals("flow"))
						select_flow();
					msg = bufferedReader.readLine();
					if (msg.equals("prod"))
						select_prod();
				} else if (action.equals("create_lot")) {
					create_lot();
				} else if (action.equals("get_his")) {
					get_his();
				} else if (action.equals("get_qty")) {
					get_qty();
				} else if (action.equals("get_lotlist")) {
					get_lotlist();
				}


				String strReadPara = bufferedReader.readLine();

				//ex) action=get_combo;para1=oper;para2=flow;para3=prod
				String[] strParaList = strReadPara.split(";");
				String strAction = strParaList[0];
				// 맨 앞 para 가 action
				if (strAction.equals("get_oper")) {
					select_oper();
				} else if (strAction.equals("get_testSample")) {

					String strPara1 = "";
					String strPara2 = "";
					String strPara3 = "";

					//ex) action=get_combo;para1=oper;para2=flow;para3=prod
					for (int i = 1; i < strParaList.length; i++) {

						String strParaValue = strParaList[i].split("=");
						if (strParaList[i].equal("para1")) {
							strPara1 = strParaValue[1];
						} else if (strParaList[i].equal("para2")) {
							strPara2 = strParaValue[1];
						} else if (strParaList[i].equal("para3")) {
							strPara3 = strParaValue[1];
						}
					}

					// do something
					// select_sample(strPara1, strPara2, strPara3);
					// or select_sample(strParaList);
				}


				// 요청을 한 줄로 받아왔을시 split
//				msg = bufferedReader.readLine();
//				System.out.println(msg);
//
//				String[] splitMsg = msg.split(";");
//				for (int i = 0; i < splitMsg.length; i++) {
//					System.out.println(splitMsg[i]);
//				}
//				
//				for (int i = 0; i < splitMsg.length; i++) {
//					String[] temp = splitMsg[i].split("=");
//					System.out.println(temp[0] + " " + temp[1]);
//				}

				// String[] split = splitMsg[1].split("=");
				// for (int i = 0; i < split.length; i++)
				// System.out.println(split[i]);

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

	public static void create_lot() throws IOException, SQLException {
		String insertData = bufferedReader.readLine();
		System.out.println(insertData);

		String insertLot = "insert into lot_inf (lot,oper,flow,prod,prod_qty) values (" + insertData + ")";
		String insertHis = "insert into lot_his (lot,oper,flow,prod,prod_qty) values (" + insertData + ")";
		pstm = conn.prepareStatement(insertLot);
		pstm.execute();
		pstm = conn.prepareStatement(insertHis);
		pstm.execute();
		System.out.println("Success Create");
		bufferedWriter.write("LOT생성");
		bufferedWriter.newLine();
		bufferedWriter.flush();
	}

	public static void get_his() throws IOException, SQLException {
		String searchId = bufferedReader.readLine();
		System.out.println(searchId);

		String countQuery = "Select count(lot) from lot_his where lot='" + searchId + "'";
		pstm = conn.prepareStatement(countQuery);
		rs = pstm.executeQuery();
		String count = "";
		while (rs.next())
			count = rs.getString(1);
		bufferedWriter.write(count);
		bufferedWriter.newLine();
		bufferedWriter.flush();
		System.out.println(count);

		String searchHis = "Select lot,oper,flow,prod,prod_qty from lot_his where lot = '" + searchId + "'";
		pstm = conn.prepareStatement(searchHis);
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

	public static void get_qty() throws SQLException, IOException {
		
	}

	public static void get_lotlist() throws IOException, SQLException {
		
	}
}
