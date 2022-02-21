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
		String msg = null;

		serverSocket = new ServerSocket(8010);

	}

}
