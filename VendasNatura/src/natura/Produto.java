package natura;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Produto{
	
	private int paginaProd = 0;
	private int codigoProd = 0;
	private String descricaoProd = "";
	private Double precoProd = 0.00;
	private int idProduto = 0;
	private int qtdEstoque = 0;

	//Pedido - Produto
	private Pedido pedidoProduto = null;
	
	private Scanner scan = Natura.scan;
	
	//Gets
	public int getPaginaProd(){
		return paginaProd;
	}
	
	public int getCodigoProd(){
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
	
	public int getQtdEstoque(){
		return qtdEstoque;
	}
	
	//Get Pedidos - Produtos
	public Pedido getpedidoProduto() {
		return pedidoProduto;
	}
	
	//Sets
	public void setPaginaProd(int paginaProd){
		this.paginaProd = paginaProd;
	}
	
	public void setCodigoProd(int codigoProd){
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
	
	public void setQtdEstoque(int qtdEstoque){
		this.qtdEstoque = qtdEstoque;
	}
	
	//Set Pedido - Produto
	public void setPedidoProduto(Pedido pedidoProduto) {
		this.pedidoProduto = pedidoProduto;
	}
	
	public Produto(int paginaProd, int codigoProd, String descricaoProd, Double precoProd, int qtdEstoque, Pedido pedido){
		this.paginaProd = paginaProd;
		this.codigoProd = codigoProd;
		this.descricaoProd = descricaoProd;
		this.precoProd = precoProd;
		this.pedidoProduto = pedido;
	}
	
	public Produto(int paginaProd, int codigoProd, String descricaoProd, Double precoProd, int qtdEstoque){
		this.paginaProd = paginaProd;
		this.codigoProd = codigoProd;
		this.descricaoProd = descricaoProd;
		this.precoProd = precoProd;
	}
	
	public Produto(){
		try{
			System.out.println("P�gina do Produto: ");
			this.paginaProd = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente n�meros!");
			scan.nextLine();
		}
		
		try{
			System.out.println("C�digo do Produto: ");
			this.codigoProd = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente n�meros!");
			scan.nextLine();			
		}
		
		System.out.println("Descri��o do Produto: ");
		this.descricaoProd = scan.nextLine();
		
		try{
			System.out.println("Pre�o do Produto: ");
			this.precoProd = scan.nextDouble();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente n�meros!");
			scan.nextLine();
		}		
		
		try{
			System.out.println("Quantidade em Estoque: ");
			this.qtdEstoque = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente n�meros!");
			scan.nextLine();
		}
	}
	
	public Produto(Pedido pedido){
		try{
			System.out.println("P�gina do Produto: ");
			this.paginaProd = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente n�meros!");
			scan.nextLine();
		}
		
		try{
			System.out.println("C�digo do Produto: ");
			this.codigoProd = scan.nextInt();
			scan.nextLine();
			pedidoProduto = pedido;
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente n�meros!");
			scan.nextLine();			
		}
		
		System.out.println("Descri��o do Produto: ");
		this.descricaoProd = scan.nextLine();
		
		try{
			System.out.println("Pre�o do Produto: ");
			this.precoProd = scan.nextDouble();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente n�meros!");
			scan.nextLine();
		}		
		
		try{
			System.out.println("Quantidade em Estoque: ");
			this.qtdEstoque = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente n�meros!");
			scan.nextLine();
		}
	}
	
	public void createProduto(){
		Connection conn = (new DBConnection()).getConn();
		Statement stmt = null;
		
		String sql = "insert into produtos(CodProduto, Descricao, Preco, Pagina, QtdEstoque) values('" + this.codigoProd + "','" + this.descricaoProd + "','" + this.precoProd + "','" + this.paginaProd +"','" + this.qtdEstoque + "')";
		System.out.println(sql);
		try {
			stmt = conn.createStatement();
			if(stmt.execute(sql)) {
				System.out.println("N�o funcionou");
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
	}
	
	public void updateProdutos(){
		try{
			System.out.println("Nova P�gina do Produto: ");
			this.paginaProd = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente n�meros!");
			scan.nextLine();
		}
		
		try{
			System.out.println("Novo C�digo do Produto: ");
			this.codigoProd = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente n�meros!");
			scan.nextLine();			
		}
		
		System.out.println("Nova Descri��o do Produto: ");
		this.descricaoProd = scan.nextLine();
		
		try{
			System.out.println("Novo Pre�o do Produto: ");
			this.precoProd = scan.nextDouble();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente n�meros!");
			scan.nextLine();
		}
	}
	
	public boolean update(){
		Natura.listaProdutos.set(this.getIdProduto(), this);
		return true;
	}
	
	public void mostraProduto(){
		System.out.println("Produto:");
		System.out.println("P�gina: " + this.paginaProd);
		System.out.println("C�digo: " + this.codigoProd);
		System.out.println("Descri��o: " + this.descricaoProd);
		System.out.println("Pre�o: " + this.precoProd);
	}
}