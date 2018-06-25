package natura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DBConnection {

	public static List<String> opsMenuRemovAlter = Arrays.asList("Remover", "Alterar");
	
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

	public void executeSQLCliente(String sqlCli) {
		Statement stmtCli = null;
		ResultSet rsCli = null;
		try {
			stmtCli = conn.createStatement();
			if (stmtCli.execute(sqlCli)) {
				rsCli = stmtCli.getResultSet();
				while (rsCli.next()) {
					System.out.println("------------------------------------------------");
					System.out.println("Cliente encontrado!");
					System.out.println("------------------------------------------------");
					System.out.println("idCliente: " + rsCli.getInt(1) + "\nNome: " + rsCli.getString(3) + " \nTelefone: " + rsCli.getInt(2) + "\nEndereço: " + rsCli.getString(4) + "\nEmail: " + rsCli.getString(5));
					System.out.println("------------------------------------------------");
					Menu menuRemovAlter = new Menu("Remover ou Alterar", opsMenuRemovAlter);
					menuRemovAlter.show();					
					int opRemAlt = menuRemovAlter.getOption();
					do{
						switch(opRemAlt){
						case 0:
							System.out.println("------------------------------------------------");
							System.out.println("idCliente: " + rsCli.getInt(1) + "\nNome: " + rsCli.getString(3) + " \nTelefone: " + rsCli.getInt(2) + "\nEndereço: " + rsCli.getString(4) + "\nEmail: " + rsCli.getString(5));
							System.out.println("------------------------------------------------");
							System.out.println("Tem certeza de que deseja remover o cliente? (S/N):");
							String opSimNao = scan.nextLine();
							switch(opSimNao.charAt(0)){
							case 'S': case 's':
								try (Connection conn = DriverManager.getConnection(url, user, password)) {
									String sqlCli2 = "DELETE FROM Clientes WHERE idCliente = ?";									
									PreparedStatement statement = conn.prepareStatement(sqlCli2);
									statement.setInt(1, rsCli.getInt(1));									
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
							case 99:
								break;
							default:
								System.out.println("---------------------------------");
								System.out.println("Opção inválida!");
								System.out.println("---------------------------------");
								break;
							}
							break;
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
						menuRemovAlter.show();
						opRemAlt = menuRemovAlter.getOption();
					}while(opRemAlt != 99);
					break;
				}
			} else {
				int count = stmtCli.getUpdateCount();
				if (count >= 1) {
					System.out.println(rsCli);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void executeSQLProd(String sqlProd) {
		Statement stmtProd = null;
		ResultSet rsProd = null;
		try {
			stmtProd = conn.createStatement();
			if (stmtProd.execute(sqlProd)) {
				rsProd = stmtProd.getResultSet();
				while (rsProd.next()) {
					System.out.println("------------------------------------------------");
					System.out.println("Produto encontrado!");
					System.out.println("------------------------------------------------");
					System.out.println("idProduto: " + rsProd.getInt(1) + " \nCodProduto: " + rsProd.getInt(2) + "\nDescricao: " + rsProd.getString(3) + "\nPreco: " + rsProd.getDouble(4) + "\nPagina: " + rsProd.getInt(5) + "\nQtdEstoque: " + rsProd.getInt(6));
					System.out.println("------------------------------------------------");
					Menu menuRemovAlter = new Menu("Remover ou Alterar", opsMenuRemovAlter);
					menuRemovAlter.show();					
					int opRemAlt = menuRemovAlter.getOption();
					do{
						switch(opRemAlt){
						case 0:
							System.out.println("------------------------------------------------");
							System.out.println("idProduto: " + rsProd.getInt(1) + " \nCodProduto: " + rsProd.getInt(2) + "\nDescricao: " + rsProd.getString(3) + "\nPreco: " + rsProd.getDouble(4) + "\nPagina: " + rsProd.getInt(5) + "\nQtdEstoque: " + rsProd.getInt(6));
							System.out.println("------------------------------------------------");
							System.out.println("Tem certeza de que deseja remover o produto? (S/N):");
							String opSimNao = scan.nextLine();
							switch(opSimNao.charAt(0)){
							case 'S': case 's':
								try (Connection conn = DriverManager.getConnection(url, user, password)) {
									String sqlProd2 = "DELETE FROM produtos WHERE CodProduto = ?";
									
									PreparedStatement stmtProd2 = conn.prepareStatement(sqlProd2);
									stmtProd2.setInt(1, rsProd.getInt(2));
									
									int rowsDeleted = stmtProd2.executeUpdate();
									if (rowsDeleted > 0) {
										System.out.println("----------------------------------");
										System.out.println("Produto removido com sucesso!");
										System.out.println("----------------------------------");
									}else{
										System.out.println("----------------------------------");
										System.out.println("ERRO: Falha na remoção do produto!");
										System.out.println("----------------------------------");
									}
									
								} catch (SQLException e) {
									e.printStackTrace();
								}
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
						menuRemovAlter.show();
						opRemAlt = menuRemovAlter.getOption();
					}while(opRemAlt != 99);
					break;
				}
			} else {
				int count = stmtProd.getUpdateCount();
				if (count >= 1) {
					System.out.println(rsProd);
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