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
//		String msg = "";
//		String action = "";

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

				/*
				 * 기존코드 action = bufferedReader.readLine(); if (action.equals("get_combo")) {
				 * msg = bufferedReader.readLine(); if (msg.equals("oper")) select_oper(); msg =
				 * bufferedReader.readLine(); if (msg.equals("flow")) select_flow(); msg =
				 * bufferedReader.readLine(); if (msg.equals("prod")) select_prod(); } else if
				 * (action.equals("create_lot")) { create_lot(); } else if
				 * (action.equals("get_his")) { get_his(); } else if (action.equals("get_qty"))
				 * { get_qty(); } else if (action.equals("get_lotlist")) { get_lotlist(); }
				 */

				String strReadPara = bufferedReader.readLine();
				//System.out.println(strReadPara);

				//ex) action=get_combo;para1=oper;para2=flow;para3=prod
				// 이건 예시...
				// action=get_oper;lot=lot1;flow=flow1 이런식으로 받은걸 가정한 코드
				
				String[] strParaList = strReadPara.split(";");
				String strAction = strParaList[0]; // action=get_combo (?)
				// 맨 앞 para 가 action

				check
				// 여기에 코드를 추가한 것은 예시를 든 것입니다
				// 그리고 get_combo 가 아니고 get_oper 로 받으라고 클라이언트와 서버 예시를 들어준 것인데
				// 클라이언트에서 String request = "action=get_combo;para1=oper;para2=flow;para3=prod"; 이거를 그대로 쓴 이유가 있나요?

				// 클라이언트에서 서버에 특정 공정을 조회해야한다고 했을 때 key 값이 필요한 경우가 있을겁니다
				// String request = "action=get_oper_detail;oper=1100;fac=PKG"
				// 그런 경우에 이런 식으로 서버에 조회할 수 있도록 예시를 든 것입니다

				// get_combo 하위에 여러 조회항목을 추가해버리면 get_combo 하위의 oper, flow, prod 는 재사용할 수가 없습니다
				// 나중에 추가할 화면에서 flow 나 prod 는 사용하지 않고 oper 만 사용한다고 했을 때 아래 작성한 코드는 사용할 수가 없겠지요
				// 아래 작성된 코드는 oper 코드를 받고 바로 뒤에 flow 를 주고받아야 하니까요
				// 마찬가지로 oper는 필요 없이 flow 나 prod 만 필요한 경우에는 또 어떻게 해야할까요? 또 코드 수정을 해야하는 거죠

				// 클라이언트에서 서버로 요청한 것은 서버가 처리 하여 클라이언트로 보낸 후
				// 소스 상단의 while (true) { 
				// 로 돌아가야 합니다 
				// 그래야 공용으로 사용할 함수를 만들었을 때 재사용할 수 있게 됩니다


				if (strAction.contains("get_combo")) { // action=get_combo, equals보단 contains (?)
					String strPara1 = "";
					String strPara2 = "";
					String strPara3 = "";

					// ex) action=get_combo;para1=oper;para2=flow;para3=prod
					for (int i = 1; i < strParaList.length; i++) {

						// String[] strParaValue = strParaList[i].split("=")
						String[] strParaValue = strParaList[i].split("="); // [0]para1,[1]oper
						for (int j = 0; j < strParaValue.length; j++) {
							if (strParaValue[j].equals("para1")) {
								strPara1 = strParaValue[1];
								break;
							} else if (strParaValue[j].equals("para2")) {
								strPara2 = strParaValue[1];
								break;
							} else if (strParaValue[j].equals("para3")) {
								strPara3 = strParaValue[1];
								break;
							}
						}
					}
					select_oper(strPara1);
					select_flow(strPara2);
					select_prod(strPara3);

					// do something
					// select_sample(strPara1, strPara2, strPara3);
					// or select_sample(strParaList);
				} else if (strAction.contains("create_lot")) {
					String insertData = bufferedReader.readLine();
					//System.out.println(insertData);
					create_lot(insertData);
				} else if (strAction.contains("get_his")) {
					String strPara1 = "";
					for (int i = 1; i < strParaList.length; i++) {
						String[] strParaValue = strParaList[i].split("=");
						for (int j = 0; j < strParaValue.length; j++) {
							if (strParaValue[j].equals("para1")) {
								strPara1 = strParaValue[1];
								//System.out.println(strPara1);
								break;
							}
						}
					}
					get_his(strPara1);
				} else if (strAction.contains("get_qty")) {
					get_qty();
				} else if (strAction.contains("get_lotlist")) {
					String strPara1 = "";
					for (int i = 1; i < strParaList.length; i++) {
						String[] strParaValue = strParaList[i].split("=");
						for (int j = 0; j < strParaValue.length; j++) {
							if (strParaValue[j].equals("para1")) {
								strPara1 = strParaValue[1];
								//System.out.println(strPara1);
								break;
							}
						}
					}
					get_lotlist(strPara1);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void select_oper(String oper) throws SQLException, IOException {
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

	public static void select_flow(String flow) throws SQLException, IOException {
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

	public static void select_prod(String prod) throws SQLException, IOException {
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

	public static void create_lot(String insertData) throws IOException, SQLException {
		// String insertData = bufferedReader.readLine();
		// System.out.println(insertData);

		check
		// 데이터 등록 시 mes_db 엑셀에 표시된 것 처럼 컬럼을 모두 채우기 바랍니다
		// 등록화면에 보이지 않았던 TIMEKEY 등등의 값도 전부 입력되어야 합니다
		// 그리고 이미 등록된 랏을 또 등록하려고 할 경우 현재 소스 상태에서는 exception 이 발생할 것입니다
		// exception이 발생하여 오류처리 되지 않고 클라이언트로 메시지를 보내 오류내용을 전달해야 합니다
		// 따라서 등록하기 전에 이미 등록된 key 데이터 인지 select 를 하고 insert를 해야 합니다
		// 클라이언트에서 서버로 보낼 때 파라미터 형식으로 보낸 것 처럼
		// 서버에서도 클라이언트로 보낼 때 파라미터 형식으로 보내어 처리하면 됩니다

		// 예시)
		// "return=0;para1=para1;para2=para2" -> 정상 처리된 경우 return=0
		// "return=Error-01" -> 오류가 발생한 경우 에러코드를 지정하여 클라이언트에서 해당 코드를 확인 후 오류 메시지 표시

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

	public static void get_his(String searchId) throws IOException, SQLException {
//		String searchId = bufferedReader.readLine();
//		System.out.println(searchId);

		String countQuery = "select count(lot) from lot_his where lot='" + searchId + "'";
		pstm = conn.prepareStatement(countQuery);
		rs = pstm.executeQuery();
		String count = "";
		while (rs.next())
			count = rs.getString(1);
		bufferedWriter.write(count);
		bufferedWriter.newLine();
		bufferedWriter.flush();
		System.out.println(count);

		String searchHis = "select lot,oper,flow,prod,prod_qty from lot_his where lot = '" + searchId + "'";
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
		// System.out.println("getQty");
		String countQuery = "select count(distinct o.oper) from oper_inf o, lot_his l " + "where o.oper=l.oper";
		pstm = conn.prepareStatement(countQuery);
		rs = pstm.executeQuery();
		String count = "";
		while (rs.next())
			count = rs.getString(1);
		bufferedWriter.write(count);
		bufferedWriter.newLine();
		bufferedWriter.flush();
		System.out.println(count);

		String groupOper = "select o.oper, count(lot), sum(prod_qty) from oper_inf o, lot_his l "
				+ "where o.oper = l.oper group by o.oper";
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

		String countQuery = "select count(lot) from lot_his where oper = '" + lotOper + "'";
		pstm = conn.prepareStatement(countQuery);
		rs = pstm.executeQuery();
		String count = "";
		while (rs.next())
			count = rs.getString(1);
		bufferedWriter.write(count);
		bufferedWriter.newLine();
		bufferedWriter.flush();
		// System.out.println(count);

		String getLotlist = "select lot,oper,flow,prod,prod_qty from lot_his where oper = '" + lotOper + "'";
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
