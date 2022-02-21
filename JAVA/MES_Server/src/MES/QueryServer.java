package MES;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStreamReader inputStreamReader = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedWriter bufferedWriter = null;
		BufferedReader bufferedReader = null;
		String msg = "";
		String action = "";

		serverSocket = new ServerSocket(8000);

		while (true) {
			try {
				socket = serverSocket.accept();
				System.out.println("Client Connected");

				conn = DBConnection.getConnection();

				inputStreamReader = new InputStreamReader(socket.getInputStream());
				outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

				bufferedReader = new BufferedReader(inputStreamReader);
				bufferedWriter = new BufferedWriter(outputStreamWriter);

				//action = bufferedReader.readLine();
				//if (action.equals("search_combo")) {
					while (true) {
						String response = "";
						msg = bufferedReader.readLine();

						if (msg == null)
							break;

						System.out.println(msg);

						try {
							String selRec = "select " + msg + " from " + msg + "_inf";

							pstm = conn.prepareStatement(selRec);
							rs = pstm.executeQuery();

							while (rs.next()) {
								response += rs.getString(1) + ",";
							}

							response = response.substring(0, response.length() - 1);
							System.out.println(response);
							bufferedWriter.write(response);
							bufferedWriter.newLine();
							bufferedWriter.flush();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				//}


				try {
					if (pstm != null)
						pstm.close();
					if (conn != null)
						conn.close();
					if (rs != null)
						rs.close();
					System.out.println("DB 접속 해제");
				} catch (Exception e) {
					e.printStackTrace();
				}

				socket.close();
				inputStreamReader.close();
				outputStreamWriter.close();
				bufferedReader.close();
				bufferedWriter.close();
				System.out.println("Cient 접속 해제");
				break;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
