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
					System.out.println("idCliente: " + rs.getInt(1) + " \nTelefone: " + rs.getInt(2) + "\nNome: " + rs.getString(3) + "\nEndereço: " + rs.getString(4) + "\nEmail: " + rs.getString(5));
					System.out.println("------------------------------------------------");
					System.out.println("0 - Remover Cliente" + "\n1 - Alterar Cliente");
					System.out.println("99 - Sair");
					int opRemAlt = Menu.scan.nextInt();
					Menu.scan.nextLine();
					switch(opRemAlt){
					case 0:
						System.out.println("------------------------------------------------");
						System.out.println("idCliente: " + rs.getInt(1) + " \nTelefone: " + rs.getInt(2) + "\nNome: " + rs.getString(3) + "\nEndereço: " + rs.getString(4) + "\nEmail: " + rs.getString(5));
						System.out.println("------------------------------------------------");
						Natura.deletarCliente();
					case 1:
						System.out.println("");
						break;
					case 99:
						break;
					default:
						System.out.println("---------------------------------");
						System.out.println("Opção inválida!");
						System.out.println("---------------------------------");
						break;
					}
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
	
	public void deletarCliente(){
		long busca = 0;
		busca = Menu.scan.nextLong();
		Menu.scan.nextLine();
		DBConnection conn = new DBConnection();				
		conn.executeSQLCliente("delete from clientes where idCliente = '"+busca+"'");
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