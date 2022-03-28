package MES;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LotHisProcess {
	private LotHisDao dao;

	public LotHisProcess() {
		dao = new LotHisDao();
	}

	public void addHis(String type, String addData) {
		String lot = "", oper = "", flow = "", prod = "";
		String txn_cd = "";
		int prodQty = 0;

		try {
			dao.connection();

			String[] strParaList = addData.split(";");
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

			txn_cd = type;

			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String timeKey = format.format(date);

			LotHisDto dto = new LotHisDto();
			dto.setLot(lot);
			dto.setOper(oper);
			dto.setFlow(flow);
			dto.setProd(prod);
			dto.setProdQty(prodQty);
			dto.setTimekey(timeKey);
			dto.setTxnCd(txn_cd);

			if (txn_cd.equals("CREATE")) {
				dto.setProc("LoggedOut");
				dao.add(dto);
			} else if (txn_cd.equals("MOVEIN")) {
				dto.setProc("LoggedIn");
				dao.add(dto, addData);
			} else if (txn_cd.equals("MOVEOUT")) {
				dto.setProc("LoggedOut");
				dao.add(dto, addData);
			}
			dao.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
	}

	// View Lot History
	public ArrayList<String> getHis(String lot) {
		ArrayList<String> response = new ArrayList<String>();
		try {
			dao.connection();
			ArrayList<LotHisDto> arr = dao.read(lot);

			for (LotHisDto obj : arr) {
				String his = obj.getLot() + "," + obj.getOper() + "," + obj.getFlow() + "," + obj.getProd() + ","
						+ String.valueOf(obj.getProdQty() + "," + obj.getProc() + "," + obj.getTxnCd());
				response.add(his);
			}
			dao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		return response;
	}

}
