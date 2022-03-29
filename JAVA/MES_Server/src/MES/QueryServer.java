package MES;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryServer {
	public static ServerSocket serverSocket = null;
	public static Socket socket = null;
	public static InputStreamReader inputStreamReader = null;
	public static OutputStreamWriter outputStreamWriter = null;
	public static BufferedWriter bufferedWriter = null;
	public static BufferedReader bufferedReader = null;

	// read
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		LotInfProcess infProcess = new LotInfProcess();
		LotHisProcess hisProcess = new LotHisProcess();

		while (true) {
			try {
				serverSocket = new ServerSocket(8000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				socket = serverSocket.accept();
				System.out.println("Query Server");
				System.out.println("Client Connected");

				inputStreamReader = new InputStreamReader(socket.getInputStream());
				outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

				bufferedReader = new BufferedReader(inputStreamReader);
				bufferedWriter = new BufferedWriter(outputStreamWriter);

				while (true) {
					String strReadPara = bufferedReader.readLine();
					System.out.println(strReadPara);

					String[] strParaList = strReadPara.split(";");
					String strAction = strParaList[0]; // action=get_combo

					if (strAction.contains("get_oper")) {
						bufferedWriter.write(infProcess.getOper());
						bufferedWriter.newLine();
						bufferedWriter.flush();
					} else if (strAction.contains("get_flow")) {
						bufferedWriter.write(infProcess.getFlow());
						bufferedWriter.newLine();
						bufferedWriter.flush();
					} else if (strAction.contains("get_prod")) {
						bufferedWriter.write(infProcess.getProd());
						bufferedWriter.newLine();
						bufferedWriter.flush();
					}
					else if (strAction.contains("get_his")) {
						String lotId = "";
						for (int i = 1; i < strParaList.length; i++) {
							String[] strParaValue = strParaList[i].split("=");
							for (int j = 0; j < strParaValue.length; j++) {
								if (strParaValue[j].equals("lot_id")) {
									lotId = strParaValue[1];
									break;
								}
							}
						}
						ArrayList<String> arr = hisProcess.getHis(lotId);
						bufferedWriter.write(String.valueOf(arr.size()));
						bufferedWriter.newLine();
						bufferedWriter.flush();

						for (int i = 0; i < arr.size(); i++) {
							String response = arr.get(i);
							//System.out.println(response);
							bufferedWriter.write(response);
							bufferedWriter.newLine();
							bufferedWriter.flush();
						}
					} else if (strAction.contains("get_qty")) {
						ArrayList<String> arr = infProcess.getQty();
						bufferedWriter.write(String.valueOf(arr.size()));
						bufferedWriter.newLine();
						bufferedWriter.flush();
						
						for(int i=0;i<arr.size();i++) {
							String response = arr.get(i);
							//System.out.println(response);
							bufferedWriter.write(response);
							bufferedWriter.newLine();
							bufferedWriter.flush();
						}
					} else if (strAction.contains("get_lotlist")) {
						String oper = "";
						for (int i = 1; i < strParaList.length; i++) {
							String[] strParaValue = strParaList[i].split("=");
							for (int j = 0; j < strParaValue.length; j++) {
								if (strParaValue[j].equals("oper")) {
									oper = strParaValue[1];
									break;
								}
							}
						}
						ArrayList<String> arr = infProcess.getLotList(oper);
						bufferedWriter.write(String.valueOf(arr.size()));
						bufferedWriter.newLine();
						bufferedWriter.flush();
						
						for(int i=0;i<arr.size();i++) {
							String response = arr.get(i);
							//System.out.println(response);
							bufferedWriter.write(response);
							bufferedWriter.newLine();
							bufferedWriter.flush();
						}
					} else if (strAction.contains("get_lotinf")) {
						String lotId = "";
						for (int i = 1; i < strParaList.length; i++) {
							String[] strParaValue = strParaList[i].split("=");
							for (int j = 0; j < strParaValue.length; j++) {
								if (strParaValue[j].equals("lot_id")) {
									lotId = strParaValue[1];
									break;
								}
							}
						}
						String response = infProcess.getLotInf(lotId);
						//System.out.println(response);
						bufferedWriter.write(response);
						bufferedWriter.newLine();
						bufferedWriter.flush();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
