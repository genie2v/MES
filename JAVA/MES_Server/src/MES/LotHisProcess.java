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

			dao.add(dto);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public ArrayList<String> getHis(String lot) {
		ArrayList<String> response = new ArrayList<String>();
		try {
			dao.connection();
			ArrayList<LotHisDto> arr = dao.lists(lot);

			for (LotHisDto obj : arr) {
				String his = obj.getLot() + "," + obj.getOper() + "," + obj.getFlow() + "," + obj.getProd() + ","
						+ String.valueOf(obj.getProdQty());
				response.add(his);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

}
