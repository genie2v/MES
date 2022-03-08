package MES;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LotHisProcess {
	private LotHisDao dao;

	public LotHisProcess() {
		dao = new LotHisDao();
	}

	public void addHis(String addData) {
		String lot = "", oper = "", flow = "", prod = "";
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

			dao.add(dto);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
