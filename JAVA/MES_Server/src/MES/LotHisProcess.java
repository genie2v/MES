package MES;

import java.sql.SQLException;
import java.util.ArrayList;

public class LotHisProcess {
	private LotHisDao dao;

	public LotHisProcess() {
		dao = new LotHisDao();
	}

	public void addHis(LotInfDto dto, String txn_cd) throws SQLException {
		dao.connection();

		LotHisDto dtoHis = new LotHisDto();
		dtoHis.setLot(dto.getLot());
		dtoHis.setTimekey(dto.getLastTimkey());
		dtoHis.setOper(dto.getOper());
		dtoHis.setFlow(dto.getFlow());
		dtoHis.setProd(dto.getProd());
		dtoHis.setProdQty(dto.getProdQty());
		dtoHis.setProc(dto.getProc());
		// CREATE, MOVEIN, MOVEOUT
		dtoHis.setTxnCd(txn_cd);

		dao.create(dtoHis);
		dao.close();
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
