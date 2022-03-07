package MES;

public class LotInfProcess {
	private LotInfDao dao;

	public LotInfProcess() {
		dao = new LotInfDao();
	}

	public String createLot(String insertData) {
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

			LotInfDto dto = new LotInfDto();
			dto.setLot(lot);
			dto.setOper(oper);
			dto.setFlow(flow);
			dto.setProd(prod);
			dto.setProdQty(prodQty);
			int result = dao.add(dto);

			if (result > 0) {
				response = "LOT 생성";
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

}
