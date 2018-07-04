package natura;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Pedido{
	
	//Atributos
	private Double precoTotal = 0.00;
	private String dataPedido = "";
	private String status = "";
	private String dataVenda = "";
	private int idPedido = 0;
	
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
	
	public Pedido(){
		/*try{
			System.out.println("Preço Total do Pedido: ");
			this.precoTotal = scan.nextDouble();
			scan.nextLine();
			clientePedido = cli;
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente números!");
			scan.nextLine();
		}*/
		
		System.out.println("Data do Pedido: ");
		this.dataPedido = scan.nextLine();
	}
	
	public void createPedido(Cliente cli){
		Connection conn = (new DBConnection()).getConn();
		Statement stmt = null;
		
		String sql = "INSERT INTO Pedidos(Clientes_idCliente) VALUES('" + cli.getIdCliente() + "'";
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
	}

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
	}
}