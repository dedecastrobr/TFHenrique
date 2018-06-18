package natura;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Pedido{
	
	//Atributos
	private Double precoTotal = 0.00;
	private String data = "";
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
	
	public String getData(){
		return data;
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

	public void setData(String data){
		this.data = data;
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
	
	public Pedido(Double precoTotal, String data, Cliente cli, Produto prod){
		this.precoTotal = precoTotal;
		this.data = data;
		this.clientePedido = cli;
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
		this.data = scan.nextLine();
	}
	
	public boolean save(){
		Natura.listaPedidos.add(this);
		return true;
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
		this.data = scan.nextLine();
	}
    
    public boolean update(){
    	Natura.listaPedidos.set(this.getIdPedido(), this);
    	return true;
    }
	
	public void mostraPedido(Cliente cli, Produto prod){
		System.out.println("Pedido:");
		System.out.println("Realizado por: " + cli.getNomeCliente());
		System.out.println("Página: " + prod.getPaginaProd());
		System.out.println("Código do Produto: " + prod.getCodigoProd());
		System.out.println("Descricão: " + prod.getDescricaoProd());
		System.out.println("Preço: " + prod.getPrecoProd());
		System.out.println("Preço Total: " + this.precoTotal);
		System.out.println("Data: " + this.data);
	}
}