package natura;

import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.sql.ResultSet;

public class Pedido{
	
	public static List<String> opsMenuPesquisa = Arrays.asList("Pesquisar por Código", "Pesquisar por Nome");
	public static List<String> opsMenuPesquisaProd = Arrays.asList("Pesquisar por Código", "Pesquisar por Nome");
	
	//Atributos
	private Double precoTotal = 0.00;
	private String dataPedido = "";
	private String status = "";
	private String dataVenda = "";
	private int idPedido = 0;
	
	private int idItens = 0;
	
	//Pedido - Cliente
	private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	
	//Pedido - Produto
	private ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
	
	private Scanner scan = Natura.scan;
	
	//Gets
	public Double getPrecoTotal(){
		return precoTotal;
	}
	
	public String getDataPedido(){
		return dataPedido;
	}
	
	public String getStatus(){
		return status;
	}
	
	public String dataVenda(){
		return dataVenda;
	}
	
	public int getIdPedido(){
		return idPedido;
	}
	
	public int getIdItens(){
		return idItens;
	}
	
	//Get Pedido - Cliente
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}
	
	//Get Pedido - Produto
	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	
	//Sets	
	public void setPrecoTotal(Double precoTotal){
		this.precoTotal = precoTotal;
	}

	public void setDataPedido(String dataPedido){
		this.dataPedido = dataPedido;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public void setDataVenda(String dataVenda){
		this.dataVenda = dataVenda;
	}
	
	public void setIdPedido(int idPedido){
		this.idPedido = idPedido;
	}
	
	public void setIdItens(int idItens){
		this.idItens = idItens;
	}
	
	//Set Pedido - Cliente
	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	//Set Pedido - Produto
	public void setListaProdutos(ArrayList<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	public Pedido(Double precoTotal, String dataPedido, String status, String dataVenda, Cliente cli, Produto prod){
		this.precoTotal = precoTotal;
		this.dataPedido = dataPedido;
		this.status = status;
		this.dataVenda = dataVenda;
	}
	
	public Pedido(String sqlCliPed, String sqlCliPed3, String sqlProdPed, String sqlProdPed3, Cliente cli, Produto prod, Natura natura){
		Menu menuPesquisa = new Menu("Pesquisar Cliente", opsMenuPesquisa);
		menuPesquisa.show();
		int opPesqClientes = menuPesquisa.getOption();
		do{
			switch(opPesqClientes){
			case 0:
				int pesquisaCliCod = 0;
				try{
		    		System.out.println("Código do Cliente: ");
		        	pesquisaCliCod = Menu.scan.nextInt();
		        	Menu.scan.nextLine();
		        	Connection conn = (new DBConnection()).getConn();
		        	String sqlCliPed1 = "SELECT * Clientes WHERE idCliente LIKE '%"+pesquisaCliCod+"%'";
		        	Statement stmtCliPed = null;
			    	ResultSet rsCliPed = null;
			    	try {
			    		stmtCliPed = conn.createStatement();
			    		if (stmtCliPed.execute(sqlCliPed1)) {
			    			rsCliPed = stmtCliPed.getResultSet();
			    			while (rsCliPed.next()) {
			    				System.out.println("------------------------------------------------");
			    				System.out.println("idCliente: " + rsCliPed.getInt(1) + "\nNome: " + rsCliPed.getString(3) + " \nTelefone: " + rsCliPed.getInt(2) + "\nEndereço: " + rsCliPed.getString(4) + "\nEmail: " + rsCliPed.getString(5));
			    				System.out.println("------------------------------------------------");
			    			}
			    			int pesquisaCliCod2 = 0;
			    			try{
			    	    		System.out.println("Código do cliente que realizou o pedido: ");
			    	        	pesquisaCliCod2 = scan.nextInt();
			    	        	scan.nextLine();
			    	        	String sqlCliPed2 = "SELECT * FROM Clientes WHERE idCliente = '"+pesquisaCliCod2+"'";
			    	        	Statement stmtCliPed2 = conn.createStatement();
			    	        	ResultSet rsCliPed2 = stmtCliPed2.executeQuery(sqlCliPed2);
			    	        	while(rsCliPed2.next()){
			    	        		System.out.println("------------------------------------------------");
			    					System.out.println("idCliente: " + rsCliPed2.getInt(1) + "\nNome: " + rsCliPed2.getString(3) + " \nTelefone: " + rsCliPed2.getInt(2) + "\nEndereço: " + rsCliPed2.getString(4) + "\nEmail: " + rsCliPed2.getString(5));
			    					System.out.println("------------------------------------------------");
			    					
			    					//Pesquisar Produto p/ Cadastrar Pedido
		    						Menu menuPesquisaProd = new Menu("Pesquisar Produto", opsMenuPesquisaProd);
		    						menuPesquisaProd.show();
		    						int opPesqProdutos = menuPesquisa.getOption();
		    						do{
		    							switch(opPesqProdutos){
		    							case 0:
		    								int pesquisaProdCod = 0;
		    								try{
		    						    		System.out.println("Código do Produto: ");
		    						        	pesquisaProdCod = Menu.scan.nextInt();
		    						        	Menu.scan.nextLine();
		    						        	Connection conn1 = (new DBConnection()).getConn();
		    						        	String sqlProdPed1 = "SELECT * Produtos WHERE CodProduto LIKE '%"+pesquisaProdCod+"%'";
		    						        	Statement stmtProdPed = null;
		    							    	ResultSet rsProdPed = null;
		    							    	try {
		    							    		stmtProdPed = conn1.createStatement();
		    							    		if (stmtProdPed.execute(sqlProdPed1)) {
		    							    			rsProdPed = stmtProdPed.getResultSet();
		    							    			while (rsProdPed.next()) {
		    							    				System.out.println("------------------------------------------------");
		    							    				System.out.println("idProduto: " + rsProdPed.getInt(1) + "\nCodProduto: " + rsProdPed.getInt(2) + " \nDescrição: " + rsProdPed.getString(3) + "\nPreço: " + rsProdPed.getDouble(4) + "\nPágina: " + rsProdPed.getInt(5) + "\nPontos: " + rsProdPed.getString(7));
		    							    				System.out.println("------------------------------------------------");
		    							    			}
		    							    			int pesquisaProdCod2 = 0;
		    							    			try{
		    							    	    		System.out.println("Código do produto a ser inserido no pedido: ");
		    							    	        	pesquisaProdCod2 = scan.nextInt();
		    							    	        	scan.nextLine();
		    							    	        	String sqlProdPed2 = "SELECT * FROM Produtos WHERE CodProduto = '"+pesquisaProdCod2+"'";
		    							    	        	Statement stmtProdPed2 = conn1.createStatement();
		    							    	        	ResultSet rsProdPed2 = stmtProdPed2.executeQuery(sqlProdPed2);
		    							    	        	while(rsProdPed2.next()){
		    							    	        		System.out.println("------------------------------------------------");
		    							    					System.out.println("idProduto: " + rsProdPed2.getInt(1) + "\nCodProduto: " + rsProdPed2.getInt(2) + " \nDescrição: " + rsProdPed2.getString(3) + "\nPreço: " + rsProdPed2.getDouble(4) + "\nPágina: " + rsProdPed2.getInt(5) + "\nPontos: " + rsProdPed2.getString(7));
		    							    					System.out.println("------------------------------------------------");
		    							    					
		    							    	        	}
		    							    	        }catch(InputMismatchException e){
		    							    				System.out.println("---------------------------------");
		    							    				System.out.println("ERRO: Digite somente números!");
		    							    				System.out.println("---------------------------------");
		    							    				scan.nextLine();
		    							    			}
		    							    		}else {
		    							    			int count = stmtProdPed.getUpdateCount();
		    							    			if (count >= 1) {
		    							    				System.out.println(rsProdPed);
		    							    			}
		    							    		}
		    							    	}catch (SQLException e) {
		    							    		e.printStackTrace();
		    							    	}
		    									//conn.executeSQLCliPed("SELECT * FROM Clientes WHERE idCliente LIKE '%"+pesquisaCliCod+"%'");
		    								}catch(InputMismatchException e){
		    									System.out.println("---------------------------------");
		    									System.out.println("ERRO: Digite somente números!");
		    									System.out.println("---------------------------------");
		    									scan.nextLine();
		    								}					
		    								break;
		    							case 1:
		    								String pesquisaProdNome = "";
		    								try{
		    									System.out.println("Nome do Produto: ");
		    									pesquisaProdNome = Menu.scan.nextLine();
		    									if(pesquisaProdNome.equals(prod.getDescricaoProd())) {
		    										Connection conn2 = (new DBConnection()).getConn();
		    										Statement stmtProdPed3 = null;
		    							    		ResultSet rsProdPed3 = null;
		    							    		try {
		    							    			stmtProdPed3 = conn2.createStatement();
		    							    			if (stmtProdPed3.execute(sqlProdPed3)) {
		    							    				rsProdPed3 = stmtProdPed3.getResultSet();
		    							    				while (rsProdPed3.next()) {
		    							    					System.out.println("------------------------------------------------");
		    							    					System.out.println("idProduto: " + rsProdPed3.getInt(1) + "\nCodProduto: " + rsProdPed3.getInt(2) + " \nDescrição: " + rsProdPed3.getString(3) + "\nPreço: " + rsProdPed3.getDouble(4) + "\nPágina: " + rsProdPed3.getInt(5) + "\nPontos: " + rsProdPed3.getString(7));
		    							    					System.out.println("------------------------------------------------");
		    							    				}
		    							    				int pesquisaProdCod2 = 0;
		    							    				try{
		    							    		    		System.out.println("Código do produto a ser inserido no pedido: ");
		    							    		        	pesquisaProdCod2 = scan.nextInt();
		    							    		        	scan.nextLine();
		    							    		        	String sqlProdPed4 = "SELECT * FROM Produtos WHERE CodProduto = '"+pesquisaProdCod2+"'";
		    							    		        	Statement stmtProdPed4 = conn2.createStatement();
		    							    		        	ResultSet rsProdPed4 = stmtProdPed4.executeQuery(sqlProdPed4);
		    							    		        	while(rsProdPed4.next()){
		    							    		        		System.out.println("------------------------------------------------");
		    							    						System.out.println("idProduto: " + rsProdPed3.getInt(1) + "\nCodProduto: " + rsProdPed3.getInt(2) + " \nDescrição: " + rsProdPed3.getString(3) + "\nPreço: " + rsProdPed3.getDouble(4) + "\nPágina: " + rsProdPed3.getInt(5) + "\nPontos: " + rsProdPed3.getString(7));
		    							    						System.out.println("------------------------------------------------");
		    							    						
		    							    		        	}
		    							    		        }catch(InputMismatchException e){
		    								    				System.out.println("---------------------------------");
		    								    				System.out.println("ERRO: Digite somente números!");
		    								    				System.out.println("---------------------------------");
		    								    				scan.nextLine();
		    								    			}
		    								    		}else {
		    								    			int count = stmtProdPed3.getUpdateCount();
		    								    			if (count >= 1) {
		    								    				System.out.println(rsProdPed3);
		    								    			}
		    								    		}
		    								    	}catch (SQLException e) {
		    								    		e.printStackTrace();
		    								   		}
		    									}
		    								}catch (Exception e) {
		    							    	e.printStackTrace();
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
		    							menuPesquisaProd.show();
		    							opPesqProdutos = menuPesquisaProd.getOption();
		    						}while(opPesqProdutos != 99);
			    	        	}
			    	        }catch(InputMismatchException e){
			    				System.out.println("---------------------------------");
			    				System.out.println("ERRO: Digite somente números!");
			    				System.out.println("---------------------------------");
			    				scan.nextLine();
			    			}
			    		}else {
			    			int count = stmtCliPed.getUpdateCount();
			    			if (count >= 1) {
			    				System.out.println(rsCliPed);
			    			}
			    		}
			    	}catch (SQLException e) {
			    		e.printStackTrace();
			    	}
					//conn.executeSQLCliPed("SELECT * FROM Clientes WHERE idCliente LIKE '%"+pesquisaCliCod+"%'");
				}catch(InputMismatchException e){
					System.out.println("---------------------------------");
					System.out.println("ERRO: Digite somente números!");
					System.out.println("---------------------------------");
					scan.nextLine();
				}					
				break;
			case 1:
				String pesquisaCliNome = "";
				try{
					System.out.println("Nome do Cliente: ");
					pesquisaCliNome = Menu.scan.nextLine();
					if(pesquisaCliNome.equals(cli.getNomeCliente())) {
						Connection conn2 = (new DBConnection()).getConn();
						Statement stmtCliPed3 = null;
			    		ResultSet rsCliPed3 = null;
			    		try {
			    			stmtCliPed3 = conn2.createStatement();
			    			if (stmtCliPed3.execute(sqlCliPed3)) {
			    				rsCliPed3 = stmtCliPed3.getResultSet();
			    				while (rsCliPed3.next()) {
			    					System.out.println("------------------------------------------------");
			    					System.out.println("idCliente: " + rsCliPed3.getInt(1) + "\nNome: " + rsCliPed3.getString(3) + " \nTelefone: " + rsCliPed3.getInt(2) + "\nEndereço: " + rsCliPed3.getString(4) + "\nEmail: " + rsCliPed3.getString(5));
			    					System.out.println("------------------------------------------------");
			    				}
			    				int pesquisaCliCod2 = 0;
			    				try{
			    		    		System.out.println("Código do cliente que realizou o pedido: ");
			    		        	pesquisaCliCod2 = scan.nextInt();
			    		        	scan.nextLine();
			    		        	String sqlCliPed4 = "SELECT * FROM Clientes WHERE idCliente = '"+pesquisaCliCod2+"'";
			    		        	Statement stmtCliPed4 = conn2.createStatement();
			    		        	ResultSet rsCliPed4 = stmtCliPed4.executeQuery(sqlCliPed4);
			    		        	while(rsCliPed4.next()){
			    		        		System.out.println("------------------------------------------------");
			    						System.out.println("idCliente: " + rsCliPed4.getInt(1) + "\nNome: " + rsCliPed4.getString(3) + " \nTelefone: " + rsCliPed4.getInt(2) + "\nEndereço: " + rsCliPed4.getString(4) + "\nEmail: " + rsCliPed4.getString(5));
			    						System.out.println("------------------------------------------------");
			    						
			    						//Pesquisar Produto p/ Cadastrar Pedido
			    						Menu menuPesquisaProd = new Menu("Pesquisar Produto", opsMenuPesquisaProd);
			    						menuPesquisaProd.show();
			    						int opPesqProdutos = menuPesquisa.getOption();
			    						do{
			    							switch(opPesqProdutos){
			    							case 0:
			    								int pesquisaProdCod = 0;
			    								try{
			    						    		System.out.println("Código do Produto: ");
			    						        	pesquisaProdCod = Menu.scan.nextInt();
			    						        	Menu.scan.nextLine();
			    						        	Connection conn = (new DBConnection()).getConn();
			    						        	String sqlProdPed1 = "SELECT * Produtos WHERE CodProduto LIKE '%"+pesquisaProdCod+"%'";
			    						        	Statement stmtProdPed = null;
			    							    	ResultSet rsProdPed = null;
			    							    	try {
			    							    		stmtProdPed = conn.createStatement();
			    							    		if (stmtProdPed.execute(sqlProdPed1)) {
			    							    			rsProdPed = stmtProdPed.getResultSet();
			    							    			while (rsProdPed.next()) {
			    							    				System.out.println("------------------------------------------------");
			    							    				System.out.println("idProduto: " + rsProdPed.getInt(1) + "\nCodProduto: " + rsProdPed.getInt(2) + " \nDescrição: " + rsProdPed.getString(3) + "\nPreço: " + rsProdPed.getDouble(4) + "\nPágina: " + rsProdPed.getInt(5) + "\nPontos: " + rsProdPed.getString(7));
			    							    				System.out.println("------------------------------------------------");
			    							    			}
			    							    			int pesquisaProdCod2 = 0;
			    							    			try{
			    							    	    		System.out.println("Código do produto a ser inserido no pedido: ");
			    							    	        	pesquisaProdCod2 = scan.nextInt();
			    							    	        	scan.nextLine();
			    							    	        	String sqlProdPed2 = "SELECT * FROM Produtos WHERE CodProduto = '"+pesquisaProdCod2+"'";
			    							    	        	Statement stmtProdPed2 = conn.createStatement();
			    							    	        	ResultSet rsProdPed2 = stmtProdPed2.executeQuery(sqlProdPed2);
			    							    	        	while(rsProdPed2.next()){
			    							    	        		System.out.println("------------------------------------------------");
			    							    					System.out.println("idProduto: " + rsProdPed2.getInt(1) + "\nCodProduto: " + rsProdPed2.getInt(2) + " \nDescrição: " + rsProdPed2.getString(3) + "\nPreço: " + rsProdPed2.getDouble(4) + "\nPágina: " + rsProdPed2.getInt(5) + "\nPontos: " + rsProdPed2.getString(7));
			    							    					System.out.println("------------------------------------------------");
			    							    					
			    							    	        	}
			    							    	        }catch(InputMismatchException e){
			    							    				System.out.println("---------------------------------");
			    							    				System.out.println("ERRO: Digite somente números!");
			    							    				System.out.println("---------------------------------");
			    							    				scan.nextLine();
			    							    			}
			    							    		}else {
			    							    			int count = stmtProdPed.getUpdateCount();
			    							    			if (count >= 1) {
			    							    				System.out.println(rsProdPed);
			    							    			}
			    							    		}
			    							    	}catch (SQLException e) {
			    							    		e.printStackTrace();
			    							    	}
			    									//conn.executeSQLCliPed("SELECT * FROM Clientes WHERE idCliente LIKE '%"+pesquisaCliCod+"%'");
			    								}catch(InputMismatchException e){
			    									System.out.println("---------------------------------");
			    									System.out.println("ERRO: Digite somente números!");
			    									System.out.println("---------------------------------");
			    									scan.nextLine();
			    								}					
			    								break;
			    							case 1:
			    								String pesquisaProdNome = "";
			    								try{
			    									System.out.println("Nome do Produto: ");
			    									pesquisaProdNome = Menu.scan.nextLine();
			    									if(pesquisaProdNome.equals(prod.getDescricaoProd())) {
			    										Connection conn21 = (new DBConnection()).getConn();
			    										Statement stmtProdPed3 = null;
			    							    		ResultSet rsProdPed3 = null;
			    							    		try {
			    							    			stmtProdPed3 = conn21.createStatement();
			    							    			if (stmtProdPed3.execute(sqlProdPed3)) {
			    							    				rsProdPed3 = stmtProdPed3.getResultSet();
			    							    				while (rsProdPed3.next()) {
			    							    					System.out.println("------------------------------------------------");
			    							    					System.out.println("idProduto: " + rsProdPed3.getInt(1) + "\nCodProduto: " + rsProdPed3.getInt(2) + " \nDescrição: " + rsProdPed3.getString(3) + "\nPreço: " + rsProdPed3.getDouble(4) + "\nPágina: " + rsProdPed3.getInt(5) + "\nPontos: " + rsProdPed3.getString(7));
			    							    					System.out.println("------------------------------------------------");
			    							    				}
			    							    				int pesquisaProdCod2 = 0;
			    							    				try{
			    							    		    		System.out.println("Código do produto a ser inserido no pedido: ");
			    							    		        	pesquisaProdCod2 = scan.nextInt();
			    							    		        	scan.nextLine();
			    							    		        	String sqlProdPed4 = "SELECT * FROM Produtos WHERE CodProduto = '"+pesquisaProdCod2+"'";
			    							    		        	Statement stmtProdPed4 = conn21.createStatement();
			    							    		        	ResultSet rsProdPed4 = stmtProdPed4.executeQuery(sqlProdPed4);
			    							    		        	while(rsProdPed4.next()){
			    							    		        		System.out.println("------------------------------------------------");
			    							    						System.out.println("idProduto: " + rsProdPed3.getInt(1) + "\nCodProduto: " + rsProdPed3.getInt(2) + " \nDescrição: " + rsProdPed3.getString(3) + "\nPreço: " + rsProdPed3.getDouble(4) + "\nPágina: " + rsProdPed3.getInt(5) + "\nPontos: " + rsProdPed3.getString(7));
			    							    						System.out.println("------------------------------------------------");
			    							    						
			    							    						//Cadastrar Pedido
			    							    						System.out.println("Data do Pedido: ");
			    							    						this.dataPedido = scan.nextLine();
			    							    						
			    							    						System.out.println("Status: ");
			    							    						this.status = scan.nextLine();
			    							    						Connection conn = (new DBConnection()).getConn();
			    							    						Statement stmt = null;
			    							    						
			    							    						String sql = "INSERT INTO Pedidos(idPedido, DataPedido, Clientes_idCliente, Status) VALUES('" + this.idPedido + "','" + this.dataPedido + "','" + cli.getIdCliente() + "','" + this.status +"')";
			    							    						System.out.println(sql);
			    							    						try {
			    							    							stmt = conn.createStatement();
			    							    							if(stmt.execute(sql)) {
			    							    								System.out.println("Não funcionou");
			    							    							}else {
			    							    								int count = stmt.getUpdateCount();
			    							    								if (count >= 1) {
			    							    									System.out.println("---------------------------------");
			    							    									System.out.println("Pedido cadastrado com sucesso!");
			    							    									System.out.println("---------------------------------");
			    							    								}
			    							    							}
			    							    						} catch (SQLException e) {
			    							    							e.printStackTrace();
			    							    						}
			    							    						
			    							    		        	}
			    							    		        }catch(InputMismatchException e){
			    								    				System.out.println("---------------------------------");
			    								    				System.out.println("ERRO: Digite somente números!");
			    								    				System.out.println("---------------------------------");
			    								    				scan.nextLine();
			    								    			}
			    								    		}else {
			    								    			int count = stmtProdPed3.getUpdateCount();
			    								    			if (count >= 1) {
			    								    				System.out.println(rsProdPed3);
			    								    			}
			    								    		}
			    								    	}catch (SQLException e) {
			    								    		e.printStackTrace();
			    								   		}
			    									}
			    								}catch (Exception e) {
			    							    	e.printStackTrace();
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
			    							menuPesquisaProd.show();
			    							opPesqProdutos = menuPesquisaProd.getOption();
			    						}while(opPesqProdutos != 99);
			    		        	}
			    		        }catch(InputMismatchException e){
			    					System.out.println("---------------------------------");
			    					System.out.println("ERRO: Digite somente números!");
			    					System.out.println("---------------------------------");
			    					scan.nextLine();
			    				}
			    			}else {
			    				int count = stmtCliPed3.getUpdateCount();
			    				if (count >= 1) {
			    					System.out.println(rsCliPed3);
			    				}
			    			}
			    		}catch (SQLException e) {
			    			e.printStackTrace();
			    		}
					}
		        }catch (Exception e) {
		    		e.printStackTrace();
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
			menuPesquisa.show();
			opPesqClientes = menuPesquisa.getOption();
		}while(opPesqClientes != 99);
		/*try{
			System.out.println("Preço Total do Pedido: ");
			this.precoTotal = scan.nextDouble();
			scan.nextLine();
			clientePedido = cli;
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente números!");
			scan.nextLine();
		}*/
	}
	
	/*public void createPedido(Cliente cli, Produto prod){
		Connection conn = (new DBConnection()).getConn();
		Statement stmt = null;
		
		String sql = "INSERT INTO Produtos(CodProduto, Descricao, Preco, Pagina, QtdEstoque, Pontos) VALUES('" + this.codigoProd + "','" + this.descricaoProd + "','" + this.precoProd + "','" + this.paginaProd +"','" + this.qtdEstoque + "','" + this.pontosProd + "')";
		System.out.println(sql);
		try {
			stmt = conn.createStatement();
			if(stmt.execute(sql)) {
				System.out.println("Não funcionou");
			}else {
				int count = stmt.getUpdateCount();
				if (count >= 1) {
					System.out.println("---------------------------------");
					System.out.println("Produto cadastrado com sucesso!");
					System.out.println("---------------------------------");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Connection conn = (new DBConnection()).getConn();
		Statement stmt = null;
		int pesquisa = 0;
		try{
			System.out.println("Quantidade: ");
        	pesquisa = scan.nextInt();
        	scan.nextLine();
        	try{
        		String sqlPed = "INSERT INTO Itens_Pedido (idItens, idPedido, idProduto) VALUES ('" + this.idItens + "', '" + this.idPedido + "', '" + prod.getIdProduto() + "')";
        		PreparedStatement stmtInsertPed = conn.prepareStatement(sqlPed);
				int rowsUpdated = stmtInsertPed.executeUpdate();
				if (rowsUpdated > 0) {
					System.out.println("----------------------------------");
					System.out.println("Estoque atualizado com sucesso!");
					System.out.println("----------------------------------");
				}else{
					System.out.println("----------------------------------");
					System.out.println("ERRO: Falha na atualização do estoque!");
					System.out.println("----------------------------------");
				}       		
		String sql = "INSERT INTO Pedidos(idPedido, PrecoTotal, DataPedido, Clientes_idCliente, DataVenda, Status) VALUES('" + this.idPedido + "', '" + this.precoTotal + "', '" + this.dataPedido + "', '" + cli.getIdCliente() + "', '" + this.dataVenda + "', '" + this.status + "')";
		Connection conn = (new DBConnection()).getConn();
		Statement stmt = null;
		
		String sql = "INSERT INTO Pedidos(CodProduto, Descricao, Preco, Pagina, QtdEstoque, Pontos) VALUES('" + this.codigoProd + "','" + this.descricaoProd + "','" + this.precoProd + "','" + this.paginaProd +"','" + this.qtdEstoque + "','" + this.pontosProd + "')";
		System.out.println(sql);
		try {
			stmt = conn.createStatement();
			if(stmt.execute(sql)) {
				System.out.println("Não funcionou");
			}else {
				int count = stmt.getUpdateCount();
				if (count >= 1) {
					System.out.println("---------------------------------");
					System.out.println("Produto cadastrado com sucesso!");
					System.out.println("---------------------------------");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(sql);
		try {
			stmt = conn.createStatement();
			if(stmt.execute(sql)) {
				System.out.println("Não funcionou");
			}else {
				int count = stmt.getUpdateCount();
				if (count >= 1) {
					System.out.println("---------------------------------");
					System.out.println("Cliente inserido no pedido com sucesso!");
					System.out.println("---------------------------------");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

	public void updatePedidos(){
		try{
			System.out.println("Novo Preço Total do Pedido: ");
			this.precoTotal = scan.nextDouble();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("---------------------------------");
			System.out.println("ERRO: Digite somente números!");
			System.out.println("---------------------------------");
			scan.nextLine();
		}
		
		System.out.println("Nova Data do Pedido: ");
		this.dataPedido = scan.nextLine();
	}
    
	public void mostraPedido(Cliente cli, Produto prod){
		System.out.println("Pedido:");
		System.out.println("Realizado por: " + cli.getNomeCliente());
		System.out.println("Página: " + prod.getPaginaProd());
		System.out.println("Código do Produto: " + prod.getCodigoProd());
		System.out.println("Descrição: " + prod.getDescricaoProd());
		System.out.println("Preço: " + prod.getPrecoProd());
		System.out.println("Preço Total: " + this.precoTotal);
		System.out.println("Data: " + this.dataPedido);
		System.out.println("Status: " + this.status);
	}
}