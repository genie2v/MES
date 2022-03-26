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
	private LotInfDao dao;
	private OperInfDao operDao;
	private FlowInfDao flowDao;
	private ProdInfDao prodDao;
	private FlowOperInfDao flowoperDao;

	public LotInfProcess() {
		dao = new LotInfDao();
		operDao = new OperInfDao();
		flowDao = new FlowInfDao();
		prodDao = new ProdInfDao();
		flowoperDao = new FlowOperInfDao();
	}

	public String getOper() throws SQLException {
		String response = "";

		operDao.connection();
		ArrayList<OperInfDto> arr = operDao.operInf();
		for (OperInfDto obj : arr) {
			response += obj.getOper() + ",";
		}
		response = response.substring(0, response.length() - 1);
		// System.out.println(response);
		return response;
	}

	public String getFlow() throws SQLException {
		String response = "";

		flowDao.connection();
		ArrayList<FlowInfDto> arr = flowDao.flowInf();
		for (FlowInfDto obj : arr) {
			response += obj.getFlow() + ",";
		}
		response = response.substring(0, response.length() - 1);
		// System.out.println(response);
		return response;
	}

	public String getProd() throws SQLException {
		String response = "";

		prodDao.connection();
		ArrayList<ProdInfDto> arr = prodDao.prodInf();
		for (ProdInfDto obj : arr) {
			response += obj.getProd() + ",";
		}
		response = response.substring(0, response.length() - 1);
		// System.out.println(response);
		return response;
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
			if (result == -1) {
				response = "중복되는 LOT";
			} else {
				response = "LOT 생성";
				hisProcess.addHis("CREATE", insertData);
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
			
			// LOT_INF 테이블의 키는 LOT_ID 인데 array로 받은 이유??
			// ArrayList<LotInfDto> arr = dao.lotInf(lotId);
			LotInfDto dtoLot = dao.read(lotId);

			if (dtoLot == null) {
				// 존재하지 않는 lot 으로 movein 시도하는 경우이다, 예외처리
				response = "존재하지 않는 lot 입니다";
				return response;
			}

			if (dtoLot.getProc().equals("LoggedIn")) {
				response = "LoggedIn 변경불가";
				return response;
			}
			// for (LotInfDto obj : arr) {
			operDao.connection();
			// 여기도 마찬가지로 리스트로 받을 이유가 없고, 테이블의 키 (fac, oper)로 한 row 만 조회하도록 한다, fac은 클라이언트에서 PKG로 넘기고
			ArrayList<OperInfDto> operArr = operDao.operInf(obj.getOper());

			String moveYN = "";
			for (OperInfDto operObj : operArr) {
				moveYN = operObj.getMoveInOutYn();
			}

			if (moveYN.equals("N")) {

				// {	코드의
					// {	뎁스를
						// {	최대한
							// {	덜 만드는
								// {	방향으로
									// {	처리한다 
								// }	이런식으로
							// } 	} 가 늘어나서
						// }	뎁스가 늘어지면
					// }	긴 코드의 if, for 등둥
				// }	가독성이 떨어진다

				// 코드 진행에 필요한 조건 		[	if (moveYN.equals("Y")) {	] 안에 코드를 넣어 뎁스를 키우기보다
				// 고드 진행에 필요없는 조건 	[	if (moveYN.equals("N")) {	] 으로 예외처리 하여 뎁스를 줄이는게 코드가 길어질 수록 가독성이 좋다
				// 부득이한 경우 해야할 수도 있지만 되도록 줄인다

				// move in 금지 oper 니까 예외처리
				response = "Move In 실행불가";
				return response;
			}
			// if (moveYN.equals("Y")) {

			// if (obj.getProc().equals("LoggedIn")) {
			// 	response = "LoggedIn 변경불가";
			// } else {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String timeKey = format.format(date);

			// move in 조건에 해당되면 위에서 조회한 dao 에 필요한 값을 넣어 update 한다

			// LotInfDto dto = new LotInfDto(); 
			dtoLot.setProc("LoggedIn");
			dtoLot.setLastTimekey(timeKey);
			// dtoLot.setChgUser(""); 아직 user 관리까지는 안하니 일단 제외

			int result = dao.update(dtoLot);
			if (result > 0) {
				response = "LoggedIn 변경";
				hisProcess.addHis("MOVEIN", dtoLot.getLot());
			}

			// }
			dao.close();
			operDao.close();
			// } else {
			// 	response = "Move In 실행불가";
			// }
			// }
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

			// 1.	수정된 move in 로직의 예시를 보고 move out 로직도 변경하도록 하자, connection close 하는 부분은 문제가 되는지 확인 필요

			// 2.	모든 dao 파일은 테이블의 "키" 값으로 하는 read / update 를 만들자, 수정된 LotinfDao, FLowOperInfDao 처럼
			//		read는 모든 컬럼이 들어간 dto를 반환하고 update는 dto를 받아 처리 한다
			//		이게 꼭 정답은 아니지만, 내가 맡은 업무의 코드는 다 이런 형식으로 사용하고 있고 편하다

			// 3.	혹시 기본 폰트를 사용하고 있다면 고정폭 폰트를 사용하는게 코드 가독성면에서 좋다, 개인적으로 D2Coding을 사용중

			// 4. 	메일에도 적었었지만 다시 풀어서 설명하면
			//		fac, flow, oper를 read 해서 FlowOperInfDto 에 담는다
			//		int iSeq = FlowOperInfDto.getSeq() + 1 로 변수에 넣고
			//		FlowOperInfDto 는 null 로 바꾸고
			//		fac, flow, iSeq를 readOperBySeq 로 다시 FlowOperInfDto 에 담는다
			//		FlowOperInfDto 에서 next oper를 받아 dtoLot 에 넣는다

			//		이 여러줄로 적은 과정을 한줄 두줄로 "내 자신의 실력에 감탄하며" 처리할 수도 있지만 코드는 이렇게 풀어서 표현하는게 좋다
			//		일을 해보면 알겠지만 몇 달만 지나가도 내가 작성한 코드를 까먹고 해석하는데 시간이 걸리는 경우가 생긴다
			//		내가 만든 코드를 내가 볼 때도 이런데 다른 사람이 내 코드를 볼 때는 더 심하다

			//		맨땅에서 새로 시작하지 않는 이상 처음 시작은 다른 사람의 코드를 보는 것 부터 시작하게 된다
			//		다른 사람의 코드가 "내 자신의 실력에 감탄하며" 쓴 코드라면 그걸 풀어서 해석에 시간이 걸리고 이는 곧 야근으로 연결된다
			//		혼자 개발할 것이 아니라면 최대한 코드는 풀어서 표현하고 로직이 복잡한 코드는 주석을 열심히 달아준다

			ArrayList<LotInfDto> arr = dao.lotInf(lotId);


			for (LotInfDto obj : arr) {
				if (dtoLot.getProc().equals("LoggedOut")) {
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

		return result;
	}

	public ArrayList<String> getLotList(String oper) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			dao.connection();
			ArrayList<LotInfDto> arr = dao.lists(oper);

			for (LotInfDto obj : arr) {
				String response = obj.getLot() + "," + obj.getOper() + "," + obj.getFlow() + "," + obj.getProd() + ","
						+ String.valueOf(obj.getProdQty());
				result.add(response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
