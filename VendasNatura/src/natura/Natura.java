package natura;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Natura{
	
	public static List<String> opsMenuPrincipal = Arrays.asList("Clientes", "Pedidos", "Produtos", "Vendas");
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
						cadastroPedidos();
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
		
		public static void cadastroPedidos(){
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
			/*Pedido pedidos = new Pedido(cli, prod);
			if(pedidos != null && prod.getCodigoProd() != 0 && cli.getTelefoneCliente() != 0){
				System.out.println("---------------------------------");
				System.out.println("Pedido registrado com sucesso!");
				System.out.println("---------------------------------");
				pedidos.mostraPedido(cli, prod);
				System.out.println("---------------------------------");
			}else{
				System.out.println("---------------------------------");
				System.out.println("Falha no registro do pedido!");
				System.out.println("---------------------------------");
			}*/
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
		public static void pesqCliCodigo(){
			long pesquisa = 0;
			try{
				System.out.println("Código do Cliente: ");
				pesquisa = scan.nextLong();
				scan.nextLine();
				DBConnection conn = new DBConnection();				
				conn.executeSQLCliente("select * from Clientes where idCliente = '"+pesquisa+"'");
			}catch(InputMismatchException e){
				System.out.println("---------------------------------");
				System.out.println("ERRO: Digite somente números!");
				System.out.println("---------------------------------");
				scan.nextLine();
			}
		}
		
		//Pesquisar Cliente por Nome
		public static void pesqCliNome(){
			String pesquisa = "";
			System.out.println("Nome do Cliente: ");
			pesquisa = scan.nextLine();
			DBConnection conn = new DBConnection();
			conn.executeSQLCliente("select * from clientes where Nome = '"+pesquisa+"'");
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
		public static void pesqProdCodigo(){
			long pesquisaProd = 0;
			try{
	    		System.out.println("Código do Produto: ");
	        	pesquisaProd = scan.nextLong();
	        	scan.nextLine();
	        	DBConnection conn = new DBConnection();				
				conn.executeSQLProd("select * from produtos where CodProduto = '"+pesquisaProd+"'");
			}catch(InputMismatchException e){
				System.out.println("ERRO: Digite somente números!");
			}
		}
		
		//Pesquisar Produto por Nome
		public static void pesqProdNome(){
			String pesquisaProd = "";
			System.out.println("Nome do Produto: ");
			pesquisaProd = scan.nextLine();
			DBConnection conn = new DBConnection();
			conn.executeSQLProd("select * from produtos where Descricao = '"+pesquisaProd+"'");
		}
		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//Listas
		
		public static void listarClientes(){
			DBConnection conn = new DBConnection();
			conn.executeSQLCliente("select * from clientes");
		}
}