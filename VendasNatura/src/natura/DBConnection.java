package natura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBConnection {

	String bd = "Natura";
	String user = "root";
	String password = "amazingday250193";
	String url = "jdbc:mysql://localhost/"+bd+"?useTimezone=true&serverTimezone=UTC";
	Connection conn = null;
	
	public static Scanner scan = new Scanner(System.in);

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
					int opRemAlt = scan.nextInt();
					scan.nextLine();
					switch(opRemAlt){
					case 0:
						System.out.println("------------------------------------------------");
						System.out.println("idCliente: " + rs.getInt(1) + " \nTelefone: " + rs.getInt(2) + "\nNome: " + rs.getString(3) + "\nEndereço: " + rs.getString(4) + "\nEmail: " + rs.getString(5));
						System.out.println("------------------------------------------------");
						//Natura.deletarCliente(rs.getInt(1));
						System.out.println("Tem certeza de que deseja remover o cliente? (S/N):");
						String opSimNao = scan.nextLine();
						switch(opSimNao.charAt(0)){
						case 'S': case 's':
							try (Connection conn = DriverManager.getConnection(url, user, password)) {
								String sql2 = "DELETE FROM Clientes WHERE idCliente = ?";
								
								PreparedStatement statement = conn.prepareStatement(sql2);
								statement.setInt(1, rs.getInt(1));
								
								int rowsDeleted = statement.executeUpdate();
								if (rowsDeleted > 0) {
									System.out.println("----------------------------------");
									System.out.println("Cliente removido com sucesso!");
									System.out.println("----------------------------------");
								}else{
									System.out.println("----------------------------------");
									System.out.println("ERRO: Falha na remoção do cliente!");
									System.out.println("----------------------------------");
								}
								
							} catch (SQLException e) {
								e.printStackTrace();
							}
							break;
						case 'N': case 'n':
							break;
						default:
							System.out.println("---------------------------------");
							System.out.println("Opção inválida!");
							System.out.println("---------------------------------");
							break;
						}
					case 1:
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
	
	/*public void deleteCliente(String sql){
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			if (stmt.execute(sql)) {
				rs = stmt.getResultSet();
				while (rs.next()) {
					try{
						conn.prepareStatement("delete from Clientes where idCliente = '"+rs.getInt(1)+"'");
						stmt.execute(sql);
						stmt.close();
					}catch(SQLException e){
						throw new RuntimeException(e);
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
	}*/

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
					System.out.println("idProduto: " + rs.getInt(1) + " \nCodProduto: " + rs.getInt(2) + "\nDescricao: " + rs.getString(3) + "\nPreco: " + rs.getDouble(4) + "\nPagina: " + rs.getInt(5) + "\nQtdEstoque: " + rs.getInt(6));
					System.out.println("------------------------------------------------");
					System.out.println("0 - Remover Produto" + "\n1 - Alterar Produto");
					System.out.println("99 - Sair");
					int opRemAlt = scan.nextInt();
					scan.nextLine();
					switch(opRemAlt){
					case 0:
						System.out.println("------------------------------------------------");
						System.out.println("idProduto: " + rs.getInt(1) + " \nCodProduto: " + rs.getInt(2) + "\nDescricao: " + rs.getString(3) + "\nPreco: " + rs.getDouble(4) + "\nPagina: " + rs.getInt(5) + "\nQtdEstoque: " + rs.getInt(6));
						System.out.println("------------------------------------------------");
						System.out.println("Tem certeza de que deseja remover o produto? (S/N):");
						String opSimNao = scan.nextLine();
						switch(opSimNao.charAt(0)){
						case 'S': case 's':
							if(stmt.execute("delete from Clientes where idCliente = '"+rs.getInt(1)+"'")){
								System.out.println("Produto removido com sucesso!");
							}else{
								System.out.println("ERRO: Falha na remoção do produto!");
							}
							break;				
						case 'N': case 'n':
							break;
						default:
							System.out.println("---------------------------------");
							System.out.println("Opção inválida!");
							System.out.println("---------------------------------");
							break;
						}
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
	
	/*public boolean checkValue(String value){
		try{
			int x = Integer.parseInt(value);
			return true;
		}catch(Exception e){
			return false;
		}
	}*/
}