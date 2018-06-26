package natura;

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
	
	//Cliente - Pedido
	private Cliente clientePedido = null;
	
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
	
	//Get Cliente - Pedido
	public Cliente getClientePedido() {
		return clientePedido;
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
	
	//Set Cliente - Pedido
	public void setClientePedido(Cliente clientePedido) {
		this.clientePedido = clientePedido;
	}
	
	//Set Pedido - Produto
	public void setListaProdutos(ArrayList<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	public Pedido(Double precoTotal, String dataPedido, String status, String dataVenda, Cliente cli, Produto prod){
		this.precoTotal = precoTotal;
		this.dataPedido = dataPedido;
		this.clientePedido = cli;
		this.dataPedido = dataPedido;
		this.status = status;
		this.dataVenda = dataVenda;
	}
	
	public Pedido(Cliente cli, Produto prod){
		try{
			System.out.println("Preço Total do Pedido: ");
			this.precoTotal = scan.nextDouble();
			scan.nextLine();
			clientePedido = cli;
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente números!");
			scan.nextLine();
		}
		
		System.out.println("Data do Pedido: ");
		this.dataPedido = scan.nextLine();
	}
	
	public void updatePedidos(){
		try{
			System.out.println("Novo Preço Total do Pedido: ");
			this.precoTotal = scan.nextDouble();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente números!");
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