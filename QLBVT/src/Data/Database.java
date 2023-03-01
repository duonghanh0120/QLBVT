package Data;

import java.sql.*;
public class Database {
	public static Connection connectDB() {
		Connection conn = null;
		try {
			//Nạp
			Class.forName("com.mysql.jdbc.Driver");
			//Kết nối
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetau","root","");
			System.out.println("Connected!");
				
		} catch (Exception e) {
			System.out.println("Fail. Try again");
		}
		return conn;
	}
	public static void main(String args[]) {
		connectDB();
	}
}