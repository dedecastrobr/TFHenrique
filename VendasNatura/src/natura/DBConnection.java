package natura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

public class DBConnection {
	
	private Connection conn = null;
	
	public Connection getConn() {
		return conn;
	}

	public DBConnection() {
		
		try {
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost/natura?user=root&password=amazingday250193");
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	public void executeSQL(String sql) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			if(stmt.execute(sql)) {
				rs = stmt.getResultSet(); 
				while(rs.next()) {
					System.out.println(rs.getString(2));
				}
				System.out.println(rs);
			}else {
				int count = stmt.getUpdateCount();
				if(count == 1) {
					System.out.println("Cliente inserido com sucesso!");
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean CheckValue(String value){
		try{
			int x = Integer.parseInt(value);
			return true;
		}catch(InputMismatchException e){
			return false;
		}
	}
}
