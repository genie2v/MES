package MES;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DB.DBConnection;

public class LotInfProcess {
	private LotInfDao lotDao;
	private OperInfDao operDao;
	private FlowInfDao flowDao;
	private ProdInfDao prodDao;
	private FlowOperInfDao flowoperDao;

	public LotInfProcess() {
		lotDao = new LotInfDao();
		operDao = new OperInfDao();
		flowDao = new FlowInfDao();
		prodDao = new ProdInfDao();
		flowoperDao = new FlowOperInfDao();
	}

	// Create Lot comboBox bind
	public String getOper() throws SQLException {
		String response = "";

		operDao.connection();
		ArrayList<OperInfDto> arr = operDao.read();
		for (OperInfDto obj : arr) {
			response += obj.getOper() + ",";
		}
		response = response.substring(0, response.length() - 1);
		operDao.close();
		return response;
	}

	public String getFlow() throws SQLException {
		String response = "";

		flowDao.connection();
		ArrayList<FlowInfDto> arr = flowDao.read();
		for (FlowInfDto obj : arr) {
			response += obj.getFlow() + ",";
		}
		response = response.substring(0, response.length() - 1);
		flowDao.close();
		return response;
	}

	public String getProd() throws SQLException {
		String response = "";

		prodDao.connection();
		ArrayList<ProdInfDto> arr = prodDao.read();
		for (ProdInfDto obj : arr) {
			response += obj.getProd() + ",";
		}
		response = response.substring(0, response.length() - 1);
		prodDao.close();
		return response;
	}

	public String createLot(String insertData) {
		LotHisProcess hisProcess = new LotHisProcess();
		String response = "";
		String lot = "", oper = "", flow = "", prod = "";
		int prodQty = 0;

		try {
			lotDao.connection();

			// LOT=LOT00001;OPER=1000;FLOW=FLOW-0001;PROD=PROD-XXXX-0001;PROD_QTY=1234
			String[] strParaList = insertData.split(";");
			for (int i = 0; i < strParaList.length; i++) {
				String[] strParaValue = strParaList[i].split("=");
				for (int j = 0; j < strParaValue.length; j++) {
					if (strParaValue[j].equals("lot")) {
						lot = strParaValue[1];
						break;
					} else if (strParaValue[j].equals("oper")) {
						oper = strParaValue[1];
						break;
					} else if (strParaValue[j].equals("flow")) {
						flow = strParaValue[1];
						break;
					} else if (strParaValue[j].equals("prod")) {
						prod = strParaValue[1];
						break;
					} else if (strParaValue[j].equals("prod_qty")) {
						prodQty = Integer.parseInt(strParaValue[1]);
						break;
					}
				}
			}

			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String timeKey = format.format(date);
//			java.sql.Date time = (java.sql.Date) format2.parse(timeKey);

			LotInfDto dto = new LotInfDto();
			dto.setLot(lot);
			dto.setOper(oper);
			dto.setFlow(flow);
			dto.setProd(prod);
			dto.setProdQty(prodQty);
			dto.setLastTimekey(timeKey);
			dto.setProc("LoggedOut");
//			dto.setCrtTm(time);

			int result = lotDao.create(dto);
			if (result == -1) {
				response = "???????????? LOT";
			} else {
				response = "LOT ??????";
				hisProcess.addHis(dto, "CREATE");
			}

			lotDao.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}

		return response;
	}

	// MoveIn, MoveOut ?????? ??? ?????? bind
	public String getLotInf(String lotId) {
		String response = "";
		try {
			lotDao.connection();

			LotInfDto dtoLot = lotDao.read(lotId);
			response = "oper=" + dtoLot.getOper() + ";flow=" + dtoLot.getFlow() + ";prod=" + dtoLot.getProd()
					+ ";prod_qty=" + String.valueOf(dtoLot.getProdQty());

			lotDao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}

		return response;
	}

	public String moveIn(String lotId) {
		LotHisProcess hisProcess = new LotHisProcess();
		String response = "";
		try {
			lotDao.connection();
			operDao.connection();

			LotInfDto dtoLot = lotDao.read(lotId);

			if (dtoLot == null) {
				// ???????????? ?????? lot?????? movein ??????, ????????????
				response = "???????????? ?????? lot ?????????";
				return response;
			}

			// ?????? LoggedIn??? ?????? ??????X
			if (dtoLot.getProc().equals("LoggedIn")) {
				response = "LoggedIn ????????????";
				return response;
			}

			// ???????????? ??? (fac, oper)??? ??? row ??? ??????
			OperInfDto dtoOper = operDao.read("PKG", dtoLot.getOper());

			String moveYN = dtoOper.getMoveInOutYn();

			if (moveYN.equals("N")) {
				// move in ?????? oper ?????? ????????????
				response = "Move In ????????????";
				return response;
			}

			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String timeKey = format.format(date);

			// move in ????????? ???????????? ????????? ????????? dao ??? ????????? ?????? ?????? update ??????
			dtoLot.setProc("LoggedIn");
			dtoLot.setLastTimekey(timeKey);
			// dtoLot.setChgUser(""); ?????? user ??????????????? ????????? ?????? ??????

			int result = lotDao.update(dtoLot);
			if (result > 0) {
				response = "LoggedIn ??????";
				hisProcess.addHis(dtoLot, "MOVEIN");
			}

			lotDao.close();
			operDao.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}

		return response;
	}

	public String moveOut(String lotId) {
		LotHisProcess hisProcess = new LotHisProcess();

		String response = "";
		try {
			lotDao.connection();
			flowoperDao.connection();

			LotInfDto dtoLot = lotDao.read(lotId);

			if (dtoLot == null) {
				// ???????????? ?????? lot?????? moveout ???????????? ??????, ????????????
				response = "???????????? ?????? lot ?????????";
				return response;
			}

			// ?????? LoggedOut??? ?????? ??????X
			if (dtoLot.getProc().equals("LoggedOut")) {
				response = "LoggedOut ????????????";
				return response;
			}

			// ???????????? ???(fac, flow, oper)??? ??? row ??? ??????
			FlowOperInfDto dtoFlowOper = flowoperDao.read("PKG", dtoLot.getFlow(), dtoLot.getOper());
			if (dtoFlowOper == null) {
				// oper??? flow_oper_inf ???????????? ?????? ??????
				response = "?????? ????????? ????????????";
				return response;
			}
			int iSeq = dtoFlowOper.getOperSeq() + 1;
			dtoFlowOper = null;
			dtoFlowOper = flowoperDao.readByOperSeq("PKG", dtoLot.getFlow(), iSeq);

			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String timeKey = format.format(date);

			dtoLot.setOper(dtoFlowOper.getOper());
			dtoLot.setProc("LoggedOut");
			dtoLot.setLastTimekey(timeKey);

			int result = lotDao.update(dtoLot);
			if (result > 0) {
				response = "LoggedOut ??????";
				hisProcess.addHis(dtoLot, "MOVEOUT");
			}

			lotDao.close();
			flowoperDao.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}

		return response;
	}

	// View Lot List
	public ArrayList<String> getQty() throws SQLException {
		ArrayList<String> result = new ArrayList<String>();

		Connection conn = DBConnection.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "select oper, count(lot), sum(prod_qty) from lot_inf group by oper order by oper";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			String response = "";
			response += rs.getString(1) + ",";
			response += rs.getString(2) + ",";
			response += rs.getString(3);

			result.add(response);
		}

		DBConnection.close();

		return result;
	}

	public ArrayList<String> getLotList(String oper) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			lotDao.connection();
			ArrayList<LotInfDto> arr = lotDao.readByOper(oper);

			for (LotInfDto obj : arr) {
				String response = obj.getLot() + "," + obj.getOper() + "," + obj.getFlow() + "," + obj.getProd() + ","
						+ String.valueOf(obj.getProdQty());
				result.add(response);
			}

			lotDao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}

		return result;
	}

	public String undoLot(String lotId) {
		LotHisProcess hisProcess = new LotHisProcess();
		String response = "";

		try {
			lotDao.connection();
			LotInfDto dtoLot = lotDao.read(lotId);

			if (dtoLot == null) {
				// ???????????? ?????? lot?????? undo ???????????? ??????, ????????????
				response = "???????????? ?????? lot ?????????";
				return response;
			}

			// ?????? ??? ?????? ?????? ????????? ?????????
			// LOT_HIS?????? ??????
//			 LotHisDto dtoHis = hisProcess.deleteHis(dtoLot.getLot(), dtoLot.getLastTimkey());
			// LOT_HIS, CANCEL_TYPE ??????
			LotHisDto dtoHis = hisProcess.updateCancel(dtoLot.getLot(), dtoLot.getLastTimkey());

			if (dtoHis == null) {
				// txn_cd = CREATE ?????? delete ?????? ????????? ???
				response = "???????????? ??????";
				return response;
			}

			dtoLot.setLastTimekey(dtoHis.getTimkey());
			dtoLot.setOper(dtoHis.getOper());
			dtoLot.setChgTm(dtoHis.getChgTm());
			dtoLot.setChgUser(dtoHis.getChgUser());
			dtoLot.setProc(dtoHis.getProc());

			int result = lotDao.updateUndo(dtoLot);
			if (result == 0) {
				response = "?????? ??????";
				return response;
			}

			response = "?????? ??????";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		return response;
	}

}
