package natura;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Produto{
	
	private int paginaProd = 0;
	private long codigoProd = 0;
	private String descricaoProd = "";
	private Double precoProd = 0.00;
	private int idProduto = 0;

	//Pedido - Produto
	private Pedido pedidoProduto = null;
	
	private Scanner scan = Natura.scan;
	
	//Gets
	public int getPaginaProd(){
		return paginaProd;
	}
	
	public long getCodigoProd(){
		return codigoProd;
	}
	
	public String getDescricaoProd(){
		return descricaoProd;
	}
	
	public Double getPrecoProd(){
		return precoProd;
	}
	
	public int getIdProduto(){
		return idProduto;
	}
	
	//Get Pedidos - Produtos
	public Pedido getpedidoProduto() {
		return pedidoProduto;
	}
	
	//Sets
	public void setPaginaProd(int paginaProd){
		this.paginaProd = paginaProd;
	}
	
	public void setCodigoProd(long codigoProd){
		this.codigoProd = codigoProd;
	}

	public void setDescricaoProd(String descricaoProd){
		this.descricaoProd = descricaoProd;
	}
	
	public void setPrecoProd(Double precoProd){
		this.precoProd = precoProd;
	}
	
	public void setIdProduto(int idProduto){
		this.idProduto = idProduto;
	}
	
	//Set Pedido - Produto
	public void setPedidoProduto(Pedido pedidoProduto) {
		this.pedidoProduto = pedidoProduto;
	}
	
	public Produto(int paginaProd, long codigoProd, String descricaoProd, Double precoProd, Pedido pedido){
		this.paginaProd = paginaProd;
		this.codigoProd = codigoProd;
		this.descricaoProd = descricaoProd;
		this.precoProd = precoProd;
		this.pedidoProduto = pedido;
	}
	
	public Produto(int paginaProd, long codigoProd, String descricaoProd, Double precoProd){
		this.paginaProd = paginaProd;
		this.codigoProd = codigoProd;
		this.descricaoProd = descricaoProd;
		this.precoProd = precoProd;
	}
	
	public Produto(){
		try{
			System.out.println("Página do Produto: ");
			this.paginaProd = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente números!");
			scan.nextLine();
		}
		
		try{
			System.out.println("Código do Produto: ");
			this.codigoProd = scan.nextLong();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente números!");
			scan.nextLine();			
		}
		
		System.out.println("Descrição do Produto: ");
		this.descricaoProd = scan.nextLine();
		
		try{
			System.out.println("Preço do Produto: ");
			this.precoProd = scan.nextDouble();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente números!");
			scan.nextLine();
		}		
	}
	
	public Produto(Pedido pedido){
		try{
			System.out.println("Página do Produto: ");
			this.paginaProd = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente números!");
			scan.nextLine();
		}
		
		try{
			System.out.println("Código do Produto: ");
			this.codigoProd = scan.nextLong();
			scan.nextLine();
			pedidoProduto = pedido;
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente números!");
			scan.nextLine();			
		}
		
		System.out.println("Descrição do Produto: ");
		this.descricaoProd = scan.nextLine();
		
		try{
			System.out.println("Preço do Produto: ");
			this.precoProd = scan.nextDouble();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente números!");
			scan.nextLine();
		}		
	}
	
	public boolean save(){
		Natura.listaProdutos.add(this);
		return true;
	}
	
	public void updateProdutos(){
		try{
			System.out.println("Nova Página do Produto: ");
			this.paginaProd = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente números!");
			scan.nextLine();
		}
		
		try{
			System.out.println("Novo Código do Produto: ");
			this.codigoProd = scan.nextLong();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente números!");
			scan.nextLine();			
		}
		
		System.out.println("Nova Descrição do Produto: ");
		this.descricaoProd = scan.nextLine();
		
		try{
			System.out.println("Novo Preço do Produto: ");
			this.precoProd = scan.nextDouble();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente números!");
			scan.nextLine();
		}
	}
	
	public boolean update(){
		Natura.listaProdutos.set(this.getIdProduto(), this);
		return true;
	}
	
	public void mostraProduto(){
		System.out.println("Produto:");
		System.out.println("Página: " + this.paginaProd);
		System.out.println("Código: " + this.codigoProd);
		System.out.println("Descrição: " + this.descricaoProd);
		System.out.println("Preço: " + this.precoProd);
	}
}