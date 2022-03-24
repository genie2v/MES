package MES;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LotInfProcess {
	private LotInfDao dao;
	private OperInfDao operDao;
	private FlowOperInfDao flowoperDao;

	public LotInfProcess() {
		dao = new LotInfDao();
		operDao = new OperInfDao();
		flowoperDao = new FlowOperInfDao();
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
			ArrayList<LotInfDto> arr = dao.lotInf(lotId);

			for (LotInfDto obj : arr) {
				response = "oper=" + obj.getOper() + ";flow=" + obj.getFlow() + ";prod=" + obj.getProd() + ";prod_qty="
						+ String.valueOf(obj.getProdQty());
			}
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
			ArrayList<LotInfDto> arr = dao.lotInf(lotId);

			for (LotInfDto obj : arr) {
				operDao.connection();
				ArrayList<OperInfDto> operArr = operDao.operInf(obj.getOper());

				String moveYN = "";
				for (OperInfDto operObj : operArr) {
					moveYN = operObj.getMoveInOutYn();
				}

				if (moveYN.equals("Y")) {
					if (obj.getProc().equals("LoggedIn")) {
						response = "LoggedIn 변경불가";
					} else {
						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
						String timeKey = format.format(date);

						LotInfDto dto = new LotInfDto();
						dto.setLot(obj.getLot());
						dto.setProc("LoggedIn");
						dto.setLastTimekey(timeKey);

						int result = dao.updateProc(dto);
						if (result > 0) {
							response = "LoggedIn 변경";
							hisProcess.addHis("MOVEIN", dto.getLot());
						}

					}
					dao.close();
					operDao.close();
				} else {
					response = "Move In 실행불가";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	public String moveOut(String lotId) {
		LotHisProcess hisProcess = new LotHisProcess();

		String response = "";
		try {
			dao.connection();
			ArrayList<LotInfDto> arr = dao.lotInf(lotId);

			for (LotInfDto obj : arr) {
				if (obj.getProc().equals("LoggedOut")) {
					response = "LoggedOut 변경불가";
				} else {
					flowoperDao.connection();
					ArrayList<FlowOperInfDto> flowoperArr = flowoperDao.flowoperInf(obj.getFlow(), obj.getOper());

					String operSeq = "";
					String flow = "";
					for (FlowOperInfDto flowoperObj : flowoperArr) {
						operSeq = String.valueOf(flowoperObj.getOperSeq());
						flow = flowoperObj.getFlow();
					}

					if (operSeq.equals("")) {
						response = "Move Out 진행불가";
					} else {
						FlowOperInfDto dto = new FlowOperInfDto();
						dto.setFlow(flow);
						dto.setOperSeq(Integer.parseInt(operSeq) + 1);
						String oper = flowoperDao.nextOper(dto);

						Date date = new Date();
						SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
						String timeKey = format.format(date);
						
						LotInfDto infDto = new LotInfDto();
						infDto.setLot(obj.getLot());
						infDto.setOper(oper);
						infDto.setLastTimekey(timeKey);
						infDto.setProc("LoggedOut");
						int result = dao.updateOper(infDto);
						if (result > 0) {
							response = "LoggedOut 변경";
							hisProcess.addHis("MOVEOUT", infDto.getLot());
						}
					}

				}
				
				dao.close();
				flowoperDao.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

}
