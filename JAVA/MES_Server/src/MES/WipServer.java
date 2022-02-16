package MES;

import java.io.*;
import java.net.*;
import java.sql.*;

public class WipServer {

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
		
		serverSocket = new ServerSocket(8000);
		
		socket = serverSocket.accept();
		System.out.println("Client Connected");
		
		conn = DBConnection.getConnection();
		
		inputStreamReader = new InputStreamReader(socket.getInputStream());
		outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
		
		bufferedReader = new BufferedReader(inputStreamReader);
		bufferedWriter = new BufferedWriter(outputStreamWriter);
		
		String msg = bufferedReader.readLine();
		System.out.println(msg);
		
		// 받은 oper로 combobox에 넣을 oper값 찾기
		String response = null;
		try {
			String selOper = "select" + msg + "from oper_inf";
			
			pstm = conn.prepareStatement(selOper);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
			}
			
			System.out.println(response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
