package natura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	static String bd = "natura";
	static String user = "root";
	static String password = "amazingday250193";
	static String url = "jdbc:mysql://localhost/"+bd+"?useTimezone=true&serverTimezone=UTC";
	Connection conn = null;

	public DBConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
			if(conn != null){
				System.out.println("Connecting database ["+conn+"] OK!");
			}

		} catch (SQLException e) {

			System.out.println("Exception conexion: " + e.getMessage());

		}catch(ClassNotFoundException e){
			System.out.println("Exception driver: " + e.getMessage());
		}

	}

	public Connection getConn() {
		return conn;
	}

	public void executeSQLCliente(String sql) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			if (stmt.execute(sql)) {
				rs = stmt.getResultSet();
				while (rs.next()) {
					System.out.println("------------------------------------------------");
					System.out.println("Cliente encontrado!");
					System.out.println("------------------------------------------------");
					System.out.println("idCliente: " + rs.getInt(1));
					System.out.println("Telefone: " + rs.getInt(2));
					System.out.println("Nome: " + rs.getString(3));
					System.out.println("Endereço: " + rs.getString(4));
					System.out.println("Email: " + rs.getString(5));
					System.out.println("------------------------------------------------");
				}
			} else {
				int count = stmt.getUpdateCount();
				if (count >= 1) {
					System.out.println(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void executeSQLProd(String sql) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			if (stmt.execute(sql)) {
				rs = stmt.getResultSet();
				while (rs.next()) {
					System.out.println("------------------------------------------------");
					System.out.println("Produto encontrado!");
					System.out.println("------------------------------------------------");
					System.out.println(rs.getInt(1));
					System.out.println(rs.getInt(2));
					System.out.println(rs.getString(3));
					System.out.println(rs.getDouble(4));
					System.out.println(rs.getInt(5));
					System.out.println(rs.getInt(6));
					System.out.println("------------------------------------------------");
				}
			} else {
				int count = stmt.getUpdateCount();
				if (count >= 1) {
					System.out.println(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*public boolean checkValue(String value){
		try{
			int x = Integer.parseInt(value);
			return true;
		}catch(Exception e){
			return false;
		}
	}*/
}