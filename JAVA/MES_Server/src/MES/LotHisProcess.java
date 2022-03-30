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

	public LotHisDto deleteHis(String lotId, String timeKey) {
		LotHisDto dto = null;
		
		try {
			dao.connection();

			dto = dao.read(lotId, timeKey);
			
			if(dto.getTxnCd().equals("CREATE")) {
				// System.out.println("CREATE");
				dto = null;
				return dto;
			}
			
			int result = dao.delete(dto.getLot(), dto.getTimkey());			
			if(result == 0) {
				dto = null;
				return dto;
			}
			
			String lot = dto.getLot();
			dto = null;
			dto = dao.read(lot);
			
			return dto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
	}

	// View Lot History
	public ArrayList<String> getHis(String lot, String orderby) {
		ArrayList<String> response = new ArrayList<String>();
		try {
			dao.connection();
			ArrayList<LotHisDto> arr = dao.readForViewHis(lot, orderby);

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
