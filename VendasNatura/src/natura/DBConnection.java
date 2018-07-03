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
	
	//Atributos do Cliente Atualizado
	private String newNomeCliente = "";
	private String newEnderecoCliente = "";
	private String newEmailCliente = "";
	private long newTelefoneCliente = 0;
	
	//Atributos do Produto Atualizado
	private int newCodigoProd = 0;
	private String newDescricaoProd = ""; 
	private Double newPrecoProd = 0.00;
	private int newPaginaProd = 0;
	private String newPontosProd = ""; 
	
	//Gets do Cliente Atualizado
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
	
	//Gets do Produto Atualizado
	public int getNewCodigoProd(){
		return newCodigoProd;
	}
	
	public String getNewDescricaoProd(){
		return newDescricaoProd;
	}
	
	public Double getNewPrecoProd(){
		return newPrecoProd;
	}
	
	public int getNewPaginaProd(){
		return newPaginaProd;
	}
	
	public String getNewPontosProd(){
		return newPontosProd;
	}
	
	//Sets do Cliente Atualizado
	
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
	
	//Sets do Cliente Atualizado
	
	public void setNewCodigoProd(int newCodigoProd){
		this.newCodigoProd = newCodigoProd;
	}
	
	public void setNewDescricaoProd(String newDescricaoProd){
		this.newDescricaoProd = newDescricaoProd;
	}
	
	public void setNewPrecoProd(Double newPrecoProd){
		this.newPrecoProd = newPrecoProd;
	}
	
	public void setNewPaginaProd(int newPaginaProd){
		this.newPaginaProd = newPaginaProd;
	}
	
	public void setNewPontosProd(String newPontosProd){
		this.newPontosProd = newPontosProd;
	}
	
	public static Scanner scan = new Scanner(System.in);
	
	public DBConnection(long newTelefoneCliente, String newNomeCliente, String newEnderecoCliente, String newEmailCliente, int newCodigoProd, String newDescricaoProd, Double newPrecoProd, int newPaginaProd, String newPontosProd){
		this.newTelefoneCliente = newTelefoneCliente;
		this.newNomeCliente = newNomeCliente;
		this.newEnderecoCliente = newEnderecoCliente;
		this.newEmailCliente = newEmailCliente;
		this.newCodigoProd = newCodigoProd;
		this.newDescricaoProd = newDescricaoProd;
		this.newPrecoProd = newPrecoProd;
		this.newPaginaProd = newPaginaProd;
		this.newPontosProd = newPontosProd;
	}

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
							int pesquisa = 0;
							try{
					    		System.out.println("Código do cliente a ser removido: ");
					        	pesquisa = scan.nextInt();
					        	scan.nextLine();
					        	try(Connection conn = DriverManager.getConnection(url, user, password)){
					        		String sqlCliRem = "SELECT * FROM Clientes WHERE idCliente = '"+pesquisa+"'";
					        		Statement stmtRemoveCli = conn.createStatement();
					        		ResultSet rsCli2 = stmtRemoveCli.executeQuery(sqlCliRem);
					        		while(rsCli2.next()){
					        			System.out.println("------------------------------------------------");
										System.out.println("idCliente: " + rsCli2.getInt(1) + "\nNome: " + rsCli2.getString(3) + " \nTelefone: " + rsCli2.getInt(2) + "\nEndereço: " + rsCli2.getString(4) + "\nEmail: " + rsCli2.getString(5));
										System.out.println("------------------------------------------------");
										System.out.println("Tem certeza de que deseja remover o cliente? (S/N):");
										String opSimNao = scan.nextLine();
										switch(opSimNao.charAt(0)){
										case 'S': case 's':
											try (Connection conn2 = DriverManager.getConnection(url, user, password)) {
												String sqlCli2 = "DELETE FROM Clientes WHERE idCliente = ?";									
												PreparedStatement stmtDeleteCli = conn.prepareStatement(sqlCli2);
												stmtDeleteCli.setInt(1, rsCli2.getInt(1));									
												int rowsDeleted = stmtDeleteCli.executeUpdate();
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
					        		}
					        	}catch(InputMismatchException e){
					        		e.printStackTrace();
					        	}
							}catch(InputMismatchException e){
								System.out.println("---------------------------------");
								System.out.println("ERRO: Digite somente números!");
								System.out.println("---------------------------------");
								scan.nextLine();
							}
							break;
						case 1:
							int buscaCli = 0;
							try{
					    		System.out.println("Código do cliente a ser alterado: ");
					        	buscaCli = scan.nextInt();
					        	scan.nextLine();
					        	try(Connection conn = DriverManager.getConnection(url, user, password)){
					        		String sqlCliAlter = "SELECT * FROM Clientes WHERE idCliente = '"+buscaCli+"'";
					        		Statement stmtAltCli = conn.createStatement();
					        		ResultSet rsCli3 = stmtAltCli.executeQuery(sqlCliAlter);
					        		while(rsCli3.next()){
					        			System.out.println("------------------------------------------------");
										System.out.println("idCliente: " + rsCli3.getInt(1) + "\nNome: " + rsCli3.getString(3) + " \nTelefone: " + rsCli3.getInt(2) + "\nEndereço: " + rsCli3.getString(4) + "\nEmail: " + rsCli3.getString(5));
										System.out.println("------------------------------------------------");
										updateCliente();
										System.out.println("------------------------------------------------");
										System.out.println("idCliente: " + rsCli3.getInt(1) + "\nNome: " + getNewNomeCliente() + " \nTelefone: " + getNewTelefoneCliente() + "\nEndereço: " + getNewEnderecoCliente() + "\nEmail: " + getNewEmailCliente());
										System.out.println("------------------------------------------------");
										System.out.println("Confirma os novos dados do cliente? (S/N):");
										String opSimNao = scan.nextLine();
										switch(opSimNao.charAt(0)){
										case 'S': case 's':
											try (Connection conn2 = DriverManager.getConnection(url, user, password)) {
												String sqlCli3 = "UPDATE Clientes SET idCliente = '" + rsCli3.getInt(1) + "', Telefone = '" + getNewTelefoneCliente() + "', Nome = '" + getNewNomeCliente() + "', Endereco = '" + getNewEnderecoCliente() + "', Email = '" + getNewEmailCliente() + "' WHERE idCliente = '" + buscaCli + "'";                         
												PreparedStatement stmtUpdate = conn2.prepareStatement(sqlCli3);
												int rowsUpdatedCli = stmtUpdate.executeUpdate();
												if (rowsUpdatedCli > 0) {
													System.out.println("---------------------------------------");
													System.out.println("Dados do cliente alterados com sucesso!");
													System.out.println("---------------------------------------");
												}else{
													System.out.println("----------------------------------------------");
													System.out.println("ERRO: Falha na alteração dos dados do cliente!");
													System.out.println("----------------------------------------------");
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
					        		}
					        	}catch(InputMismatchException e){
					        		e.printStackTrace();
					        	}
							}catch(InputMismatchException e){
								System.out.println("---------------------------------");
								System.out.println("ERRO: Digite somente números!");
								System.out.println("---------------------------------");
								scan.nextLine();
							}
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
	
	//Pesquisa de Clientes p/ Cadastrar Pedidos
	public void executeSQLCliPed(String sqlCliPed) {
		Statement stmtCliPed = null;
		ResultSet rsCliPed = null;
		try {
			stmtCliPed = conn.createStatement();
			if (stmtCliPed.execute(sqlCliPed)) {
				rsCliPed = stmtCliPed.getResultSet();
				while (rsCliPed.next()) {
					System.out.println("------------------------------------------------");
					System.out.println("idCliente: " + rsCliPed.getInt(1) + "\nNome: " + rsCliPed.getString(3) + " \nTelefone: " + rsCliPed.getInt(2) + "\nEndereço: " + rsCliPed.getString(4) + "\nEmail: " + rsCliPed.getString(5));
					System.out.println("------------------------------------------------");
				}
				int pesquisa = 0;
				try{
		    		System.out.println("Código do cliente que realizou o pedido: ");
		        	pesquisa = scan.nextInt();
		        	scan.nextLine();
		        	try(Connection conn = DriverManager.getConnection(url, user, password)){
		        		String sqlCliPed2 = "SELECT * FROM Clientes WHERE idCliente = '"+pesquisa+"'";
		        		Statement stmtCliPed2 = conn.createStatement();
		        		ResultSet rsCliPed2 = stmtCliPed2.executeQuery(sqlCliPed2);
		        		while(rsCliPed2.next()){
		        			System.out.println("------------------------------------------------");
							System.out.println("idCliente: " + rsCliPed2.getInt(1) + "\nNome: " + rsCliPed2.getString(3) + " \nTelefone: " + rsCliPed2.getInt(2) + "\nEndereço: " + rsCliPed2.getString(4) + "\nEmail: " + rsCliPed2.getString(5));
							System.out.println("------------------------------------------------");
							try(Connection conn2 = DriverManager.getConnection(url, user, password)){
				        		String sqlCliPed3 = "INSERT INTO Pedidos(Clientes_idCliente) VALUES (?)";
				        		PreparedStatement stmtInsert = conn.prepareStatement(sqlCliPed3);
				        		stmtInsert.setInt(1, rsCliPed2.getInt(4));
				        		int rowsInserted = stmtInsert.executeUpdate();
				        		if(rowsInserted > 0){
				        			System.out.println("---------------------------------");
		        					System.out.println("Cliente inserido no pedido com sucesso!");
		        					System.out.println("---------------------------------");
				        		}else{
				        			System.out.println("---------------------------------");
				        			System.out.println("ERRO: Falha na inserção do cliente no pedido!");
				        			System.out.println("---------------------------------");
				        		}
							}catch(SQLException e){
								e.printStackTrace();
							}
		        		}
		        	}
				}catch(InputMismatchException e){
					System.out.println("---------------------------------");
					System.out.println("ERRO: Digite somente números!");
					System.out.println("---------------------------------");
					scan.nextLine();
				}
			
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
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
					System.out.println("idProduto: " + rsProd.getInt(1) + " \nCodProduto: " + rsProd.getInt(2) + "\nDescricao: " + rsProd.getString(3) + "\nPreco: " + rsProd.getDouble(4) + "\nPagina: " + rsProd.getInt(5) + "\nQtdEstoque: " + rsProd.getInt(6) + "\nPontos: " + rsProd.getString(7));
					System.out.println("------------------------------------------------");
				}
					Menu menuRemovAlter = new Menu("Remover ou Alterar", opsMenuRemovAlter);
					menuRemovAlter.show();					
					int opRemAlt = menuRemovAlter.getOption();
					do{
						switch(opRemAlt){
						case 0:
							int pesquisa = 0;
							try{
					    		System.out.println("Código do produto a ser removido: ");
					        	pesquisa = scan.nextInt();
					        	scan.nextLine();
					        	try(Connection conn = DriverManager.getConnection(url, user, password)){
					        		String sqlProdRem = "SELECT * FROM Produtos WHERE CodProduto = '"+pesquisa+"'";
					        		Statement stmtRemoveProd = conn.createStatement();
					        		ResultSet rsProd2 = stmtRemoveProd.executeQuery(sqlProdRem);
					        		while(rsProd2.next()){
										System.out.println("------------------------------------------------");
										System.out.println("idProduto: " + rsProd2.getInt(1) + " \nCodProduto: " + rsProd2.getInt(2) + "\nDescricao: " + rsProd2.getString(3) + "\nPreco: " + rsProd2.getDouble(4) + "\nPagina: " + rsProd2.getInt(5) + "\nQtdEstoque: " + rsProd2.getInt(6) + "\nPontos: " + rsProd2.getString(7));
										System.out.println("------------------------------------------------");
										System.out.println("Tem certeza de que deseja remover o produto? (S/N):");
										String opSimNao = scan.nextLine();
										switch(opSimNao.charAt(0)){
										case 'S': case 's':
											try (Connection conn2 = DriverManager.getConnection(url, user, password)) {
												String sqlProd2 = "DELETE FROM Produtos WHERE CodProduto = ?";									
												PreparedStatement stmtProd2 = conn2.prepareStatement(sqlProd2);
												stmtProd2.setInt(1, rsProd2.getInt(2));									
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
										case 99:
											break;
										default:
											System.out.println("---------------------------------");
											System.out.println("Opção inválida!");
											System.out.println("---------------------------------");
											break;
										}
					        		}
					        	}catch(InputMismatchException e){
					        		e.printStackTrace();
					        	}
							}catch(InputMismatchException e){
								System.out.println("---------------------------------");
								System.out.println("ERRO: Digite somente números!");
								System.out.println("---------------------------------");
								scan.nextLine();
							}
							break;
						case 1:
							int buscaProd = 0;
							try{
					    		System.out.println("Código do produto a ser alterado: ");
					        	buscaProd = scan.nextInt();
					        	scan.nextLine();
					        	try(Connection conn = DriverManager.getConnection(url, user, password)){
					        		String sqlProdAlter = "SELECT * FROM Produtos WHERE CodProduto = '"+buscaProd+"'";
					        		Statement stmtAltProd = conn.createStatement();
					        		ResultSet rsProd3 = stmtAltProd.executeQuery(sqlProdAlter);
					        		while(rsProd3.next()){
					        			System.out.println("------------------------------------------------");
										System.out.println("idProduto: " + rsProd3.getInt(1) + "\nCodProduto: " + rsProd3.getInt(2) + " \nDescrição: " + rsProd3.getString(3) + "\nPreço: " + rsProd3.getDouble(4) + "\nPágina: " + rsProd3.getInt(5) + "\nPontos: " + rsProd3.getString(7));
										System.out.println("------------------------------------------------");
										updateProduto();
										System.out.println("------------------------------------------------");
										System.out.println("idProduto: " + rsProd3.getInt(1) + "\nCodProduto: " + getNewCodigoProd() + " \nDescrição: " + getNewDescricaoProd() + "\nPreço: " + getNewPrecoProd() + "\nPágina: " + getNewPaginaProd() + "\nPontos: " + getNewPontosProd());
										System.out.println("------------------------------------------------");
										System.out.println("Confirma os novos dados do produto? (S/N):");
										String opSimNao = scan.nextLine();
										switch(opSimNao.charAt(0)){
										case 'S': case 's':
											try (Connection conn2 = DriverManager.getConnection(url, user, password)) {
												String sqlProd3 = "UPDATE Produtos SET idProd = '" + rsProd3.getInt(1) + "', CodProduto = '" + getNewCodigoProd() + "', Descricao = '" + getNewDescricaoProd() + "', Preco = '" + getNewPrecoProd() + "', Pagina = '" + getNewPaginaProd() + "', QtdEstoque = '?', Pontos = '" + getNewPontosProd() + "' WHERE idProd = '" + buscaProd + "'";                         
												PreparedStatement stmtUpdateProd = conn2.prepareStatement(sqlProd3);
												int rowsUpdatedProd = stmtUpdateProd.executeUpdate();
												if (rowsUpdatedProd > 0) {
													System.out.println("---------------------------------------");
													System.out.println("Dados do produto alterados com sucesso!");
													System.out.println("---------------------------------------");
												}else{
													System.out.println("----------------------------------------------");
													System.out.println("ERRO: Falha na alteração dos dados do produto!");
													System.out.println("----------------------------------------------");
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
					        		}
					        	}catch(InputMismatchException e){
					        		e.printStackTrace();
					        	}
							}catch(InputMismatchException e){
								System.out.println("---------------------------------");
								System.out.println("ERRO: Digite somente números!");
								System.out.println("---------------------------------");
								scan.nextLine();
							}
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
				int count = stmtProd.getUpdateCount();
				if (count >= 1) {
					System.out.println(rsProd);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateProduto(){
		try{
			System.out.println("Nova Página do Produto: ");
			this.newPaginaProd = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("---------------------------------");
			System.out.println("ERRO: Digite somente números!");
			System.out.println("---------------------------------");
		}
		
		try{
			System.out.println("Novo Código do Produto: ");
			this.newCodigoProd = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("---------------------------------");
			System.out.println("ERRO: Digite somente números!");
			System.out.println("---------------------------------");
		}
		
		System.out.println("Nova Descrição do Produto: ");
		this.newDescricaoProd = scan.nextLine();
		
		try{
			System.out.println("Novo Preço do Produto: ");
			this.newPrecoProd = scan.nextDouble();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("---------------------------------");
			System.out.println("ERRO: Digite somente números!");
			System.out.println("---------------------------------");
		}
		
		System.out.println("Novos Pontos do Produto: ");
		this.newPontosProd = scan.nextLine();
	}
	
	//Pesquisa de Produtos p/ Atualização de Estoque
	public void executeSQLProdEst(String sqlProdEst){
		Statement stmtProdEst = null;
		ResultSet rsProdEst = null;
		try {
			stmtProdEst = conn.createStatement();
			if (stmtProdEst.execute(sqlProdEst)) {
				rsProdEst = stmtProdEst.getResultSet();
				while (rsProdEst.next()) {
					System.out.println("------------------------------------------------");
					System.out.println("idProduto: " + rsProdEst.getInt(1) + " \nCodProduto: " + rsProdEst.getInt(2) + "\nDescrição: " + rsProdEst.getString(3) + "\nPreço: " + rsProdEst.getDouble(4) + "\nPágina: " + rsProdEst.getInt(5) + "\nQtdEstoque: " + rsProdEst.getInt(6) + "\nPontos: " + rsProdEst.getString(7));
					System.out.println("------------------------------------------------");
				}
				int pesquisa = 0;
				try{
		    		System.out.println("Código do produto a ter seu estoque atualizado: ");
		        	pesquisa = scan.nextInt();
		        	scan.nextLine();
		        	try(Connection conn = DriverManager.getConnection(url, user, password)){
		        		String sqlProdEst2 = "SELECT * FROM Produtos WHERE CodProduto = '"+pesquisa+"'";
		        		Statement stmtProdEst2 = conn.createStatement();
		        		ResultSet rsProdEst2 = stmtProdEst2.executeQuery(sqlProdEst2);
		        		while(rsProdEst2.next()){
		        			System.out.println("------------------------------------------------");
							System.out.println("idProduto: " + rsProdEst2.getInt(1) + "\nCodProduto: " + rsProdEst2.getInt(2) + " \nDescrição: " + rsProdEst2.getString(3) + "\nPreço: " + rsProdEst2.getDouble(4) + "\nPágina: " + rsProdEst2.getInt(5) + "\nQtdEstoque: " + rsProdEst2.getInt(6) + "\nPontos: " + rsProdEst2.getString(7));
							System.out.println("------------------------------------------------");
							int pesquisaQtd = 0;
							try{
								System.out.println("Nova Quantidade em Estoque: ");
								pesquisaQtd = scan.nextInt();
								scan.nextLine();
								try (Connection conn2 = DriverManager.getConnection(url, user, password)) {
									String sqlProdEst3 = "UPDATE Produtos SET QtdEstoque = '" + pesquisaQtd + "' WHERE CodProduto = '" + pesquisa + "'";									
									PreparedStatement stmtUpdateEst = conn2.prepareStatement(sqlProdEst3);
									int rowsUpdated = stmtUpdateEst.executeUpdate();
									if (rowsUpdated > 0) {
										System.out.println("----------------------------------");
										System.out.println("Estoque atualizado com sucesso!");
										System.out.println("----------------------------------");
									}else{
										System.out.println("----------------------------------");
										System.out.println("ERRO: Falha na atualização do estoque!");
										System.out.println("----------------------------------");
									}
								}catch(InputMismatchException e){
									System.out.println("---------------------------------");
									System.out.println("ERRO: Digite somente números!");
									System.out.println("---------------------------------");
									scan.nextLine();
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
		        		}
		        	}catch(InputMismatchException e){
		        		e.printStackTrace();
		        	}
				}catch(InputMismatchException e){
					System.out.println("---------------------------------");
					System.out.println("ERRO: Digite somente números!");
					System.out.println("---------------------------------");
					scan.nextLine();
				}
			}else {
				int count = stmtProdEst.getUpdateCount();
				if (count >= 1) {
					System.out.println(rsProdEst);
				}
			}
		}catch (SQLException e) {
			System.out.println("----------------------------------");
			System.out.println("ERRO: Produto não encontrado!");
			System.out.println("----------------------------------");
		}
	}
}