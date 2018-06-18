package natura;

import java.util.Arrays;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Natura{
	
	public static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	public static ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
	public static ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
	
	public static List<String> opsMenuPrincipal = Arrays.asList("Clientes", "Pedidos", "Produtos", "Vendas");
	public static List<String> opsMenuClientes = Arrays.asList("Cadastrar Cliente", "Pesquisar Cliente");
	public static List<String> opsMenuPedidos = Arrays.asList("Cadastrar Pedido", "Pesquisar Pedido");
	public static List<String> opsMenuProdutos = Arrays.asList("Cadastrar Produto", "Pesquisar Produto");
	public static List<String> opsMenuEstoque =  Arrays.asList("Atualizar Estoque", "Relatório de Estoque");
	
	public static List<String> opsMenuPesqCli = Arrays.asList("Pesquisar por Código", "Pesquisar por Nome");
	public static List<String> opsMenuPesqProd = Arrays.asList("Pesquisar por Código", "Pesquisar por Nome");
	//public static List<String> opsRemoverAlterar = Arrays.asList("Remover", "Alterar");
	
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String args[]){
		
		System.out.println("Bem-vindo à Natura!");
		
		Menu menu = new Menu("Menu Principal", opsMenuPrincipal);
		menu.show();
		
		int op = menu.getOption();
		do{
			switch(op){
			case 0:
				Menu menuClientes = new Menu("Clientes", opsMenuClientes);
				menuClientes.show();
				
				int opClientes = menuClientes.getOption();
				do{
					switch(opClientes){
					case 0:
						cadastroCliente();
						break;
					case 1:
						Menu menuPesqCli = new Menu("Pesquisar Cliente", opsMenuPesqCli);
						menuPesqCli.show();
						int opPesqClientes = menuPesqCli.getOption();
						do{
							switch(opPesqClientes){
							case 0:
								pesqCliCodigo();
								break;
							case 1:
								pesqCliNome();
								break;
							case 99:
								break;
							default:
								System.out.println("---------------------------------");
								System.out.println("Opção inválida!");
								System.out.println("---------------------------------");
								break;
							}
							menuPesqCli.show();
							opPesqClientes = menuPesqCli.getOption();
						}while(opPesqClientes != 99);
						break;
					case 99:
						break;
					default:
						System.out.println("---------------------------------");
						System.out.println("Opção inválida!");
						System.out.println("---------------------------------");
						break;
					}
					menuClientes.show();
					opClientes = menuClientes.getOption();
				}while(opClientes != 99);
				break;
			case 1:
				Menu menuPedidos = new Menu("Pedidos", opsMenuPedidos);
				menuPedidos.show();
				
				int opPedidos = menuPedidos.getOption();
				do{
					switch(opPedidos){
					case 0:
						//registroPedidos(pesquisaClientes(), consultaProdutos());
						break;
					case 1:
						/*Cliente cli = new Cliente();
						Produto prod = new Produto();
						Pedido ped = pesquisaPedidos();
						if(ped != null && ped.getIdPedido() != 0){
							System.out.println("---------------------------------");
							System.out.println("Pedido encontrado!");
							System.out.println("---------------------------------");
							ped.mostraPedido(cli, prod);
							System.out.println("---------------------------------");
							ped.updatePedidos();
							ped.update();
							System.out.println("---------------------------------");
							ped.mostraPedido(cli, prod);
							System.out.println("---------------------------------");
						}else{
							System.out.println("---------------------------------");
    						System.out.println("ERRO: Pedido não encontrado!");
    						System.out.println("---------------------------------");
						}*/
						break;
					case 99:
						break;
					default:
						System.out.println("---------------------------------");
						System.out.println("Opção inválida!");
						System.out.println("---------------------------------");
						break;
					}
					menuPedidos.show();
					opPedidos = menuPedidos.getOption();
				}while(opPedidos != 99);
				break;
			case 2:
				Menu menuProdutos = new Menu("Produtos", opsMenuProdutos);
				menuProdutos.show();
				
				int opProdutos = menuProdutos.getOption();
				do{
					switch(opProdutos){
					case 0:
						cadastroProdutos();
						break;
					case 1:
						Menu menuPesqProd = new Menu("Pesquisar Produto", opsMenuPesqProd);
						menuPesqProd.show();
						int opPesqProdutos = menuPesqProd.getOption();
						do{
							switch(opPesqProdutos){
							case 0:
								pesqProdCodigo();
								break;
							case 1:
								pesqProdNome();
								break;
							case 99:
								break;
							default:
								System.out.println("---------------------------------");
								System.out.println("Opção inválida!");
								System.out.println("---------------------------------");
								break;
							}
							menuPesqProd.show();
							opPesqProdutos = menuPesqProd.getOption();
						}while(opPesqProdutos != 99);
						break;
					case 99:
						break;
					default:
						System.out.println("---------------------------------");
						System.out.println("Opção inválida!");
						System.out.println("---------------------------------");
						break;
					}
					menuProdutos.show();
					opProdutos = menuProdutos.getOption();
				}while(opProdutos != 99);
				break;
			case 3:
				Menu menuEstoque = new Menu("Estoque", opsMenuEstoque);
				menuEstoque.show();
				
				int opEstoque = menuEstoque.getOption();
				do{
					switch(opEstoque){
					case 0:
						//atualizaEstoque();
						break;
					case 1:
						//relatorioEstoque();
						break;
					case 99:
						break;
					default:
						System.out.println("---------------------------------");
						System.out.println("Opção inválida!");
						System.out.println("---------------------------------");
						break;
					}
					menuEstoque.show();
					opEstoque = menuEstoque.getOption();
				}while(opEstoque != 99);
				break;
			case 99:
				System.out.println("Tchau!");
				System.exit(0);
				break;

			default:
				System.out.println("---------------------------------");
				System.out.println("Opção inválida!");
				System.out.println("---------------------------------");
				break;
			}
			menu.show();
			op = menu.getOption();
		}while (op != 99);
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//Cadastros
	
		public static void cadastroCliente(){
			Cliente clientes = new Cliente();
			if(clientes != null && clientes.getTelefoneCliente() != 0){
				clientes.createCliente();
				clientes.mostraCliente();
				System.out.println("---------------------------------");
			}else{
				System.out.println("---------------------------------");
				System.out.println("Falha no cadastro do cliente!");
				System.out.println("---------------------------------");
			}
		}
		
		public static void registroPedidos(Cliente cli, Produto prod){
			Pedido pedidos = new Pedido(cli, prod);
			if(pedidos != null && prod.getCodigoProd() != 0 && cli.getTelefoneCliente() != 0){
				pedidos.save();
				System.out.println("---------------------------------");
				System.out.println("Pedido registrado com sucesso!");
				System.out.println("---------------------------------");
				pedidos.mostraPedido(cli, prod);
				System.out.println("---------------------------------");
			}else{
				System.out.println("---------------------------------");
				System.out.println("Falha no registro do pedido!");
				System.out.println("---------------------------------");
			}
		}
		
		public static void cadastroProdutos(){
			Produto produtos = new Produto();
			if(produtos != null && produtos.getCodigoProd() != 0){
				produtos.createProduto();
				produtos.mostraProduto();
				System.out.println("---------------------------------");
			}else{
				System.out.println("---------------------------------");
				System.out.println("Falha no cadastro do produto!");
				System.out.println("---------------------------------");
			}
		}
		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//Pesquisas
		
		//Pesquisar Cliente por Código
		public static Cliente pesqCliCodigo(){
			long pesquisa = 0;
			try{
				System.out.println("Código do Cliente: ");
				pesquisa = scan.nextLong();
				scan.nextLine();
				DBConnection conn = new DBConnection();				
				conn.executeSQLCliente("select * from Clientes where idCliente = '"+pesquisa+"'");
				for (Cliente cli : listaClientes){
					if (cli.getIdCliente() == pesquisa){   
						return cli;
					}
				}   
			}catch(InputMismatchException e){
				System.out.println("---------------------------------");
				System.out.println("ERRO: Digite somente números!");
				System.out.println("---------------------------------");
				scan.nextLine();
			}
				return null;
		}
		
		//Pesquisar Cliente por Nome
		public static Cliente pesqCliNome(){
			String pesquisa = "";
			System.out.println("Nome do Cliente: ");
			pesquisa = scan.nextLine();
			DBConnection conn = new DBConnection();
			conn.executeSQLCliente("select * from clientes where Nome = '"+pesquisa+"'");
			for (Cliente cli : listaClientes){
				if (cli.getNomeCliente() == pesquisa){                
					return cli;						
				}
			}
			return null;
		}
		
		/*public static Pedido pesquisaPedidos(){
			long pesquisa = 0;
			try{
				System.out.println("Código do Pedido: ");
	        	pesquisa = scan.nextInt();
	        	scan.nextLine();
	        	for (Pedido ped : listaPedidos) {
	        		if (ped.getIdPedido() == pesquisa){               
	        			ped.setIdPedido(listaPedidos.indexOf(ped));  
	        			return ped;
	        		}
	        	}
			}catch(InputMismatchException e){
				System.out.println("ERRO: Digite somente números!");
			}
        		return null;
		}*/
		
		//Pesquisar Produto por Código
		public static Produto pesqProdCodigo(){
			long pesquisa = 0;
			try{
	    		System.out.println("Código do Produto: ");
	        	pesquisa = scan.nextLong();
	        	scan.nextLine();
	        	DBConnection conn = new DBConnection();				
				conn.executeSQLProd("select * from produtos where idProduto = '"+pesquisa+"'");
	        	for (Produto prod : listaProdutos) {
	        		if (prod.getCodigoProd() == pesquisa){ 
	        			return prod;
	        		}
	        	}
			}catch(InputMismatchException e){
				System.out.println("ERRO: Digite somente números!");
			}
        		return null;
		}
		
		//Pesquisar Produto por Nome
		public static Produto pesqProdNome(){
			String pesquisa = "";
			System.out.println("Nome do Cliente: ");
			pesquisa = scan.nextLine();
			DBConnection conn = new DBConnection();
			conn.executeSQLCliente("select * from clientes where Nome = '"+pesquisa+"'");
			for (Produto prod : listaProdutos){
				if (prod.getDescricaoProd() == pesquisa){                
					return prod;						
				}
			}
			return null;
		}

		
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//Deleções

		public static void deletarCliente(ResultSet rs){
			System.out.println("Tem certeza de que deseja remover o cliente? (S/N):");
			String opSimNao = scan.nextLine();
			switch(opSimNao.charAt(0)){
			case 'S': case 's':
				DBConnection conn = new DBConnection();				
				try {
					conn.executeSQLCliente("delete from Clientes where idCliente = '"+rs.getInt(1)+"'");
					System.out.println("Cliente removido com sucesso!");
				} catch (SQLException e) {
					System.out.println("ERRO: Falha na remoção do cliente!");
				}
			}
		}
		
		public static void alterarCliente(){
			
		}
		
		
}