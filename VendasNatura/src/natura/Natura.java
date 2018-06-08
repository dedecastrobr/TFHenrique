package natura;

import java.util.Arrays;
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
	
	public static List<String> opsMenuPesqClientes = Arrays.asList("Pesquisar por Telefone", "Pesquisar por Nome");
	
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String args[]){
		
		System.out.println("Bem-vindo à  Natura!");
		
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
						break;
					case 99:
						break;
					default:
						System.out.println("Opção inválida!");
						break;
					}
					menuClientes.show();
					opClientes = menuClientes.getOption();
				}while(opClientes != 99);
				break;
			case 1:
				/*Menu menuPedidos = new Menu("Pedidos", opsMenuPedidos);
				menuPedidos.show();
				
				int opPedidos = menuPedidos.getOption();
				do{
					switch(opPedidos){
					case 0:
						//registroPedidos(pesquisaClientes(), consultaProdutos());
						break;
					case 1:
						Cliente cli = new Cliente();
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
						}
						break;
					default:
						System.out.println("Opção inválida!");
						break;
					}
					menuPedidos.show();
					opPedidos = menuPedidos.getOption();
				}while(opPedidos != 99);*/
				break;
			case 2:
				/*Menu menuProdutos = new Menu("Produtos", opsMenuProdutos);
				menuProdutos.show();
				
				int opProdutos = menuProdutos.getOption();
				do{
					switch(opProdutos){
					case 0:
						cadastroProdutos();
						break;
					case 1:
						Produto prod = pesquisaProdutos();
						if(prod != null && prod.getCodigoProd() != 0){
							System.out.println("---------------------------------");
							System.out.println("Produto encontrado!");
							System.out.println("---------------------------------");
							prod.mostraProduto();
							System.out.println("---------------------------------");
							prod.updateProdutos();
							prod.update();
							System.out.println("---------------------------------");
							prod.mostraProduto();
							System.out.println("---------------------------------");
						}else{
							System.out.println("---------------------------------");
    						System.out.println("ERRO: Produto não encontrado!");
    						System.out.println("---------------------------------");
						}
						break;
					default:
						System.out.println("Opção inválida!");
						break;
					}
					menuProdutos.show();
					opProdutos = menuProdutos.getOption();
				}while(opProdutos != 99);*/
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
					default:
						System.out.println("Opção inválida!");
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
				System.out.println("Opção inválida!");
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
				clientes.save();
				System.out.println("---------------------------------");
				System.out.println("Cliente cadastrado com sucesso!");
				System.out.println("---------------------------------");
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
				produtos.save();
				System.out.println("---------------------------------");
				System.out.println("Produto cadastrado com sucesso!");
				System.out.println("---------------------------------");
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
		/*public static Cliente pesqCliCodigo(){
			long pesquisaCliCod = 0;
			try{
				System.out.println("Código do Cliente: ");
				pesquisaCliCod = scan.nextLong();
				scan.nextLine();
				DBConnection conn = new DBConnection();				
				conn.executeSQL("select * from clientes where idCliente = '"+pesquisaCliCod+"'");
				for (Cliente cli : listaClientes){
					if (cli.getTelefoneCliente() == pesquisaCliCod){               
						cli.setIdCliente(listaClientes.indexOf(cli));  
						return cli;
					}
				}   	
			}catch(InputMismatchException e){
				System.out.println("ERRO: Digite somente números!");
				scan.nextLine();
			}
				return null;
		}
		
		//Pesquisar Cliente por Nome
		public static Cliente pesqCliNome(){
			String pesquisaCliNome = "";
			try {
				System.out.println("Nome do Cliente: ");
				pesquisaCliNome = scan.nextLine();
				for(Cliente cliente: listaClientes){
					if(cliente.getNomeCliente() == pesquisaCliNome){
						cliente.setIdCliente(listaClientes.indexOf(cliente));
						return cliente;
					}
				}
			}catch(InputMismatchException e){
				System.out.println("ERRO: Digite somente números!");
				scan.nextLine();
			}
				return null;
		}*/
		
		public static Pedido pesquisaPedidos(){
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
		}
		
		public static Produto pesquisaProdutos(){
			long pesquisa = 0;
			try{
	    		System.out.println("Código do Produto: ");
	        	pesquisa = scan.nextLong();
	        	scan.nextLine();
	        	for (Produto prod : listaProdutos) {
	        		if (prod.getCodigoProd() == pesquisa){               
	        			prod.setIdProduto(listaProdutos.indexOf(prod));  
	        			return prod;
	        		}
	        	}
			}catch(InputMismatchException e){
				System.out.println("ERRO: Digite somente números!");
			}
        		return null;
		}
}
		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//Listas
		
		/*public static void listaClientes(){
    		System.out.println("Dados dos Cliente:");
    		System.out.println("---------------------------------");
    		int pos = 0;
    		for (Cliente clientes : listaClientes){
    			pos += 1;
    			System.out.println("Número: " + pos);
    			clientes.mostraCliente();
    			System.out.println("---------------------------------");    		
    		}
    	}*/