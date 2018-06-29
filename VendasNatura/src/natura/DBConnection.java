package natura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DBConnection {

	public static List<String> opsMenuRemovAlter = Arrays.asList("Remover", "Alterar");
	
	String bd = "Natura";
	String user = "root";
	String password = "amazingday250193";
	String url = "jdbc:mysql://localhost/"+bd+"?useTimezone=true&serverTimezone=UTC";
	Connection conn = null;
	
	private String newNomeCliente = "";
	private String newEnderecoCliente = "";
	private String newEmailCliente = "";
	private long newTelefoneCliente = 0;
	private int idCliente = 0;
	
	public String getNewNomeCliente(){
		return newNomeCliente;
	}
	
	public String getNewEnderecoCliente(){
		return newEnderecoCliente;
	}
		
	public String getNewEmailCliente(){
		return newEmailCliente;
	}
		
	public long getNewTelefoneCliente(){
		return newTelefoneCliente;
	}
	
	public int idCliente(){
		return idCliente;
	}
	
	public void setNewNomeCliente(String newNomeCliente){
		this.newNomeCliente = newNomeCliente;
	}
		
	public void setNewEnderecoCliente(String newEnderecoCliente){
		this.newEnderecoCliente = newEnderecoCliente;
	}
		
	public void setNewEmailCliente(String newEmailCliente){
		this.newEmailCliente = newEmailCliente;
	}
		
	public void setNewTelefoneCliente(long newTelefoneCliente){
		this.newTelefoneCliente = newTelefoneCliente;
	}
	
	public void setIdCliente(int idCliente){
		this.idCliente = idCliente;
	}
	
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

	//Pesquisa de Clientes p/ Remoção ou Alteração de Dados
	public void executeSQLCliente(String sqlCli) {
		Statement stmtCli = null;
		ResultSet rsCli = null;
		try {
			stmtCli = conn.createStatement();
			if (stmtCli.execute(sqlCli)) {
				rsCli = stmtCli.getResultSet();
				while (rsCli.next()) {
					System.out.println("------------------------------------------------");
					System.out.println("idCliente: " + rsCli.getInt(1) + "\nNome: " + rsCli.getString(3) + " \nTelefone: " + rsCli.getInt(2) + "\nEndereço: " + rsCli.getString(4) + "\nEmail: " + rsCli.getString(5));
					System.out.println("------------------------------------------------");
				}
					Menu menuRemovAlter = new Menu("Remover ou Alterar", opsMenuRemovAlter);
					menuRemovAlter.show();					
					int opRemAlt = menuRemovAlter.getOption();
					do{
						switch(opRemAlt){
						case 0:
							long pesquisa = 0;
							try{
					    		System.out.println("Código do cliente a ser removido: ");
					        	pesquisa = scan.nextLong();
					        	scan.nextLine();			
								((DBConnection) conn).executeSQLCliente("SELECT * FROM Clientes WHERE idCliente = '"+pesquisa+"'");
							}catch(InputMismatchException e){
								System.out.println("---------------------------------");
								System.out.println("ERRO: Digite somente números!");
								System.out.println("---------------------------------");
								scan.nextLine();
							}
							System.out.println("------------------------------------------------");
							System.out.println("idCliente: " + rsCli.getInt(1) + "\nNome: " + rsCli.getString(3) + " \nTelefone: " + rsCli.getInt(2) + "\nEndereço: " + rsCli.getString(4) + "\nEmail: " + rsCli.getString(5));
							System.out.println("------------------------------------------------");
							System.out.println("Tem certeza de que deseja remover o cliente? (S/N):");
							String opSimNao = scan.nextLine();
							switch(opSimNao.charAt(0)){
							case 'S': case 's':
								try (Connection conn = DriverManager.getConnection(url, user, password)) {
									String sqlCli2 = "DELETE FROM Clientes WHERE idCliente = ?";									
									PreparedStatement stmtDelete = conn.prepareStatement(sqlCli2);
									stmtDelete.setInt(1, rsCli.getInt(1));									
									int rowsDeleted = stmtDelete.executeUpdate();
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
							try (Connection conn = DriverManager.getConnection(url, user, password)) {
								String sqlCli3 = "UPDATE Clientes SET Telefone = '%', Nome = '%', Endereco = '%', Email = '%' WHERE idCliente = '%'";
								PreparedStatement stmtUpdate = conn.prepareStatement(sqlCli3);
								stmtUpdate.setInt(1, rsCli.getInt(1));
								stmtUpdate.setInt(1, rsCli.getInt(2));
								stmtUpdate.setString(1, rsCli.getString(3));
								stmtUpdate.setString(1, rsCli.getString(4));
								stmtUpdate.setString(1, rsCli.getString(5));
								int rowsUpdated = stmtUpdate.executeUpdate();
								if (rowsUpdated > 0) {
									System.out.println("----------------------------------");
									System.out.println("Dados do cliente atualizados com sucesso!");
									System.out.println("----------------------------------");
								}else {
									System.out.println("----------------------------------");
									System.out.println("ERRO: Falha na atualização dos dados do cliente!");
									System.out.println("----------------------------------");
								}
							} catch (SQLException ex) {
								ex.printStackTrace();
							}
							//update();
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
			} else {
				int count = stmtCli.getUpdateCount();
				if (count >= 1) {
					System.out.println(rsCli);
				}
			}
			System.out.println("----------------------------------");
			System.out.println("ERRO: Cliente não encontrado!");
			System.out.println("----------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateCliente(){
		System.out.println("Novo Nome: ");
		this.newNomeCliente = scan.nextLine();
		
		System.out.println("Novo Endereço: ");
		this.newEnderecoCliente = scan.nextLine();
		
		System.out.println("Novo Email: ");
		this.newEmailCliente = scan.nextLine();
		
		try{
			System.out.println("Novo Telefone: ");
			this.newTelefoneCliente = scan.nextLong();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente números!");
			scan.nextLine();
		}
	}
	
	/*public boolean save(){
    	Natura.listaClientes.add(this);
    	return true;
    }
	
	public boolean update(){
    	Natura.listaClientes.set(this.getIdCliente(), this);
    	return true;
    }*/
	
	//Pesquisa de Clientes p/ Cadastrar Pedidos
	public void executeSQLCliPed(String sqlCliPed) {
		Statement stmtCli = null;
		ResultSet rsCli = null;
		try {
			stmtCli = conn.createStatement();
			if (stmtCli.execute(sqlCliPed)) {
				rsCli = stmtCli.getResultSet();
				while (rsCli.next()) {
					System.out.println("------------------------------------------------");
					System.out.println("idCliente: " + rsCli.getInt(1) + "\nNome: " + rsCli.getString(3) + " \nTelefone: " + rsCli.getInt(2) + "\nEndereço: " + rsCli.getString(4) + "\nEmail: " + rsCli.getString(5));
					System.out.println("------------------------------------------------");
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		/*try (Connection conn = DriverManager.getConnection(url, user, password)) {
			String sqlCliPed2 = "SELECT * FROM clientes WHERE Nome LIKE 'Helga%'''";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sqlCliPed2);			
			int count = 0;			
			while (result.next()){
				String name = result.getString(2);
				String pass = result.getString(3);
				String fullname = result.getString("fullname");
				String email = result.getString("email");				
				String output = "User #%d: %s - %s - %s - %s";
				System.out.println(String.format(output, ++count, name, pass, fullname, email));
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}*/		
	}

	//Pesquisa de Produtos p/ Remoção ou Alteração de Dados
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
									String sqlProd2 = "DELETE FROM Produtos WHERE CodProduto = ?";									
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
					System.out.println("----------------------------------");
					System.out.println("ERRO: Produto não encontrado!");
					System.out.println("----------------------------------");
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
}