package MES;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LotInfProcess {
	private LotInfDao dao;

	public LotInfProcess() {
		dao = new LotInfDao();
	}

	public String createLot(String insertData) {
		LotHisProcess hisProcess = new LotHisProcess();
		
		String response = "";
		String lot = "", oper = "", flow = "", prod = "";
		int prodQty = 0;

		try {
			dao.connection();

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
			String timeKey = format.format(date);

			LotInfDto dto = new LotInfDto();
			dto.setLot(lot);
			dto.setOper(oper);
			dto.setFlow(flow);
			dto.setProd(prod);
			dto.setProdQty(prodQty);
			dto.setLastTimekey(timeKey);

			int result = dao.add(dto);
			if (result > 0) {
				response = "LOT 생성";
				hisProcess.addHis("CREATE", insertData);
			} else {
				response = "이미 있는 LOT ID";
			}

			dao.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		return response;
	}

	public String getLotInf(String lotId) {
		String response = "";

		try {
			dao.connection();

			response = dao.list(lotId);

			dao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public String moveIn(String lotId) {
		LotHisProcess hisProcess = new LotHisProcess();
		
		String response = "";

		try {
			dao.connection();

			String moveYN = dao.moveInOut(lotId);
			if (moveYN.equals("N")) {
				response = "MOVE IN 실행불가";
			} else {
				int result = dao.updateMove(lotId, "LoggedIn");
				if (result > 0) {
					response = "LoggedIn 변경";
					hisProcess.addHis("MOVEIN", lotId);
				} else {
					response = "LoggedIn 변경불가";
				}
			}
			dao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public String moveOut(String lotId) {
		LotHisProcess hisProcess = new LotHisProcess();
		
		String response = "";

		dao.connection();

		try {
//			int result = dao.updateMove(lotId, "LoggedOut");
//			if (result > 0) {
//				response = "LoggedOut 변경";
//				String oper = dao.nextOper(lotId);
//				if (oper.equals("")) {
//					hisProcess.addHis("MOVEOUT", lotId);
//					//dao.rollBack();
//					//response = "진행 불가";
//					//return response;
//				} else {
//					dao.updateOper(oper, lotId);
//					hisProcess.addHis("MOVEOUT", lotId);
//				}
//			} else {
//				response = "LoggedOut 변경불가";
//			}
			String oper = dao.nextOper(lotId);
			if(oper.equals("")) {
				response = "진행불가";
			} else {
				int result = dao.updateMove(lotId, "LoggedOut");
				if(result > 0) {
					response = "LoggedOut 변경";
					dao.updateOper(oper, lotId);
					hisProcess.addHis("MOVEOUT", lotId);
				} else {
					response = "LoggedOut 변경불가";
				}
			}
			
			dao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

}
