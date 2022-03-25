package MES;

import java.io.*;
import java.net.*;
import java.sql.SQLException;

public class WipServer {
	private static ServerSocket serverSocket = null;
	private static Socket socket = null;
	public static InputStreamReader inputStreamReader = null;
	private static OutputStreamWriter outputStreamWriter = null;
	private static BufferedWriter bufferedWriter = null;
	private static BufferedReader bufferedReader = null;

	// create, update, delete...
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		LotInfProcess infProcess = new LotInfProcess();

		while (true) {
			try {
				serverSocket = new ServerSocket(8010);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				socket = serverSocket.accept();
				System.out.println("Wip Server");
				System.out.println("Client Connected");

				inputStreamReader = new InputStreamReader(socket.getInputStream());
				outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

				bufferedReader = new BufferedReader(inputStreamReader);
				bufferedWriter = new BufferedWriter(outputStreamWriter);

				while (true) {
					String strReadPara = bufferedReader.readLine();
					System.out.println(strReadPara);

					String[] strParaList = strReadPara.split(";");
					String strAction = strParaList[0];

					if (strAction.contains("create_lot")) {
						String insertData = bufferedReader.readLine();
						String response = infProcess.createLot(insertData);
						bufferedWriter.write(response);
						bufferedWriter.newLine();
						bufferedWriter.flush();
					} else if (strAction.contains("movein")) {
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
						String response = infProcess.moveIn(lotId);
						System.out.println(response);
						bufferedWriter.write(response);
						bufferedWriter.newLine();
						bufferedWriter.flush();
					} else if (strAction.contains("moveout")) {
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
						String response = infProcess.moveOut(lotId);
						System.out.println(response);
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
