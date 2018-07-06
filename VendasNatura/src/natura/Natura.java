package natura;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Natura{
	
	public static List<String> opsMenuPrincipal = Arrays.asList("Clientes", "Pedidos", "Produtos", "Estoque");
	public static List<String> opsMenuClientes = Arrays.asList("Cadastrar Cliente", "Pesquisar Cliente", "Listar Clientes");
	public static List<String> opsMenuPedidos = Arrays.asList("Cadastrar Pedido", "Pesquisar Pedido", "Listar Pedidos");
	public static List<String> opsMenuProdutos = Arrays.asList("Cadastrar Produto", "Pesquisar Produto", "Listar Produtos");
	public static List<String> opsMenuEstoque =  Arrays.asList("Atualizar Estoque", "Relatório de Estoque");
	
	public static List<String> opsMenuPesquisa = Arrays.asList("Pesquisar por Código", "Pesquisar por Nome");
	
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
						cadastroClientes();
						break;
					case 1:
						Menu menuPesquisa = new Menu("Pesquisar Cliente", opsMenuPesquisa);
						menuPesquisa.show();
						int opPesqClientes = menuPesquisa.getOption();
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
							menuPesquisa.show();
							opPesqClientes = menuPesquisa.getOption();
						}while(opPesqClientes != 99);
						break;
					case 2:
						listarClientes();
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
						//cadastroPedidos(Pedido(pedidos1);
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
    						System.out.println("ERRO: Pedido n�o encontrado!");
    						System.out.println("---------------------------------");
						}*/
						break;
					case 2:
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
						Menu menuPesquisa = new Menu("Pesquisar Produto", opsMenuPesquisa);
						menuPesquisa.show();
						int opPesqProdutos = menuPesquisa.getOption();
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
							menuPesquisa.show();
							opPesqProdutos = menuPesquisa.getOption();
						}while(opPesqProdutos != 99);
						break;
					case 2:
						listarProdutos();
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
						atualizaEstoque();
						break;
					case 1:
						relatorioEstoque();
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
	
		//Cadastrar Cliente
		public static void cadastroClientes(){
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
		
		//Cadastrar Cliente em um Pedido
		public static void cadastroCliPed(){
			Menu menuPesquisa = new Menu("Pesquisar Cliente", opsMenuPesquisa);
			menuPesquisa.show();
			int opPesqClientes = menuPesquisa.getOption();
			do{
				switch(opPesqClientes){
				case 0:
					pesqCliCodPedido();
					break;
				case 1:
					pesqCliNomePedido();
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
		}
		
		//Cadastrar Produto em um Pedido
		public static void cadastroProdPed(){
			Menu menuPesquisa = new Menu("Pesquisar Produto", opsMenuPesquisa);
			menuPesquisa.show();
			int opPesqProdutos = menuPesquisa.getOption();
			do{
				switch(opPesqProdutos){
				case 0:
					pesqProdCodPedido();
					break;
				case 1:
					pesqProdNomePedido();
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
				opPesqProdutos = menuPesquisa.getOption();
			}while(opPesqProdutos != 99);
		}
		
		/*public static void cadCliPedido(Cliente cli, Produto prod){
			Pedido ped = new Pedido(cli, prod);
			ped.createPedido(cli, prod);
		}*/
		
		//Cadastrar Produto
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
		
		/*public static Pedido cadastroPedidos(String sqlCliPed, String sqlCliPed3, String sqlProdPed, String sqlProdPed3, Cliente cli, Produto prod, Pedido pedidos){
			Pedido pedidos1 = new Pedido(sqlCliPed, sqlCliPed3, sqlProdPed, sqlProdPed3, cli, prod);
			if(pedidos1 != null){
				pedidos1.mostraPedido(cli, prod);
			}else{
				System.out.println("---------------------------------");
				System.out.println("Falha no cadastro do pedido!");
				System.out.println("---------------------------------");
			}
		}*/
		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//Pesquisas
		
		//Pesquisar Cliente por Código p/ Remoção ou Alteração de Dados
		public static void pesqCliCodigo(){
			long pesquisa = 0;
			try{
				System.out.println("Código do Cliente: ");
				pesquisa = scan.nextLong();
				scan.nextLine();
				DBConnection conn = new DBConnection();				
				conn.executeSQLCliente("SELECT * FROM Clientes WHERE idCliente LIKE '%"+pesquisa+"%'");
			}catch(InputMismatchException e){
				System.out.println("---------------------------------");
				System.out.println("ERRO: Digite somente números!");
				System.out.println("---------------------------------");
				scan.nextLine();
			}
		}
		
		//Pesquisar Cliente por Nome p/ Remoção ou Alteração de Dados
		public static void pesqCliNome(){
			String pesquisa = "";
			System.out.println("Nome do Cliente: ");
			pesquisa = scan.nextLine();
			DBConnection conn = new DBConnection();
			conn.executeSQLCliente("SELECT * FROM Clientes WHERE Nome LIKE '%"+pesquisa+"%'");
		}
		
		//Pesquisar Cliente por Código p/ Cadastrar Pedido
		public static Cliente pesqCliCodPedido(){
			long pesquisa = 0;
			try{
				System.out.println("Código do Cliente: ");
				pesquisa = scan.nextLong();
				scan.nextLine();
				DBConnection conn = new DBConnection();				
				conn.executeSQLCliPed("SELECT * FROM Clientes WHERE idCliente LIKE '%"+pesquisa+"%'");
			}catch(InputMismatchException e){
				System.out.println("---------------------------------");
				System.out.println("ERRO: Digite somente números!");
				System.out.println("---------------------------------");
				scan.nextLine();
			}
			return null;
		}
		
		//Pesquisar Cliente por Nome p/ Cadastrar Pedido
		public static Cliente pesqCliNomePedido(){
			String pesquisa = "";
			System.out.println("Nome do Cliente: ");
			pesquisa = scan.nextLine();
			DBConnection conn = new DBConnection();
			conn.executeSQLCliPed("SELECT * FROM Clientes WHERE Nome LIKE '%"+pesquisa+"%'");
			return null;
		}
		
		//Pesquisar Produto por Código
		public static void pesqProdCodigo(){
			long pesquisa = 0;
			try{
	    		System.out.println("Código do Produto: ");
	        	pesquisa = scan.nextLong();
	        	scan.nextLine();
	        	DBConnection conn = new DBConnection();				
				conn.executeSQLProd("SELECT * FROM Produtos WHERE CodProduto LIKE '%"+pesquisa+"%'");
			}catch(InputMismatchException e){
				System.out.println("---------------------------------");
				System.out.println("ERRO: Digite somente números!");
				System.out.println("---------------------------------");
				scan.nextLine();
			}
		}
		
		//Pesquisar Produto por Nome
		public static void pesqProdNome(){
			String pesquisa = "";
			System.out.println("Nome do Produto: ");
			pesquisa = scan.nextLine();
			DBConnection conn = new DBConnection();
			conn.executeSQLProd("SELECT * FROM Produtos WHERE Descricao LIKE '%"+pesquisa+"%'");
		}
		
		//Pesquisar Produto por Código p/ Cadastrar Pedido
		public static void pesqProdCodPedido(){
			long pesquisa = 0;
			try{
				System.out.println("Código do Produto: ");
				pesquisa = scan.nextLong();
				scan.nextLine();
				DBConnection conn = new DBConnection();				
				conn.executeSQLProdPed("SELECT * FROM Produtos WHERE CodProduto LIKE '%"+pesquisa+"%'");
			}catch(InputMismatchException e){
				System.out.println("---------------------------------");
				System.out.println("ERRO: Digite somente números!");
				System.out.println("---------------------------------");
				scan.nextLine();
			}
		}
		
		//Pesquisar Produto por Nome p/ Cadastrar Pedido
		public static void pesqProdNomePedido(){
			String pesquisa = "";
			System.out.println("Nome do Produto: ");
			pesquisa = scan.nextLine();
			DBConnection conn = new DBConnection();
			conn.executeSQLProdPed("SELECT * FROM Produtos WHERE Descricao LIKE '%"+pesquisa+"%'");
		}
		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//Listas
		
		public static void listarClientes(){
			DBConnection conn = new DBConnection();
			conn.executeSQLCliente("SELECT * FROM Clientes");
		}
		
		public static void listarProdutos(){
			DBConnection conn = new DBConnection();
			conn.executeSQLProd("SELECT * FROM Produtos");
		}
		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//Estoque
		
		//Atualizar Estoque
		public static void atualizaEstoque(){
			Menu menuPesquisa = new Menu("Pesquisar Produto", opsMenuPesquisa);
			menuPesquisa.show();
			int opPesqProdutos = menuPesquisa.getOption();
			do{
				switch(opPesqProdutos){
				case 0:
					long pesquisaProdCod = 0;
					try{
			    		System.out.println("Código do Produto: ");
			        	pesquisaProdCod = scan.nextLong();
			        	scan.nextLine();
			        	DBConnection conn = new DBConnection();				
						conn.executeSQLProdEst("SELECT * FROM Produtos WHERE CodProduto LIKE '%"+pesquisaProdCod+"%'");
					}catch(InputMismatchException e){
						System.out.println("---------------------------------");
						System.out.println("ERRO: Digite somente números!");
						System.out.println("---------------------------------");
						scan.nextLine();
					}					
					break;
				case 1:
					String pesquisaProdNome = "";
					System.out.println("Nome do Produto: ");
					pesquisaProdNome = scan.nextLine();
					DBConnection conn = new DBConnection();
					conn.executeSQLProdEst("SELECT * FROM Produtos WHERE Descricao LIKE '%"+pesquisaProdNome+"%'");
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
				opPesqProdutos = menuPesquisa.getOption();
			}while(opPesqProdutos != 99);
		}
		
		public static void relatorioEstoque(){
			
		}
}