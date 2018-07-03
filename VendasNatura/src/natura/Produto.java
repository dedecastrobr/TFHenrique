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
	private String pontosProd = "";

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
	
	public String getPontosProd(){
		return pontosProd;
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
	
	public void setPontosProd(String pontosProd){
		this.pontosProd = pontosProd;
	}
	
	//Set Pedido - Produto
	public void setPedidoProduto(Pedido pedidoProduto) {
		this.pedidoProduto = pedidoProduto;
	}
	
	public Produto(int paginaProd, int codigoProd, String descricaoProd, Double precoProd, int qtdEstoque, String pontosProd, Pedido pedido){
		this.paginaProd = paginaProd;
		this.codigoProd = codigoProd;
		this.descricaoProd = descricaoProd;
		this.precoProd = precoProd;
		this.pedidoProduto = pedido;
		this.pontosProd = pontosProd;
	}
	
	public Produto(int paginaProd, int codigoProd, String descricaoProd, Double precoProd, int qtdEstoque, String pontosProd){
		this.paginaProd = paginaProd;
		this.codigoProd = codigoProd;
		this.descricaoProd = descricaoProd;
		this.precoProd = precoProd;
		this.pontosProd = pontosProd;
	}
	
	public Produto(){
		try{
			System.out.println("Página do Produto: ");
			this.paginaProd = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("---------------------------------");
			System.out.println("ERRO: Digite somente números!");
			System.out.println("---------------------------------");
			scan.nextLine();
		}
		
		try{
			System.out.println("Código do Produto: ");
			this.codigoProd = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("---------------------------------");
			System.out.println("ERRO: Digite somente números!");
			System.out.println("---------------------------------");
			scan.nextLine();			
		}
		
		System.out.println("Descrição do Produto: ");
		this.descricaoProd = scan.nextLine();
		
		try{
			System.out.println("Preço do Produto: ");
			this.precoProd = scan.nextDouble();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("---------------------------------");
			System.out.println("ERRO: Digite somente números!");
			System.out.println("---------------------------------");
			scan.nextLine();
		}		
		
		try{
			System.out.println("Quantidade em Estoque: ");
			this.qtdEstoque = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("---------------------------------");
			System.out.println("ERRO: Digite somente números!");
			System.out.println("---------------------------------");
			scan.nextLine();
		}
		
		System.out.println("Pontos do Produto: ");
		this.pontosProd = scan.nextLine();
	}
	
	public Produto(Pedido pedido){
		try{
			System.out.println("Página do Produto: ");
			this.paginaProd = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("---------------------------------");
			System.out.println("ERRO: Digite somente números!");
			System.out.println("---------------------------------");
			scan.nextLine();
		}
		
		try{
			System.out.println("Código do Produto: ");
			this.codigoProd = scan.nextInt();
			scan.nextLine();
			pedidoProduto = pedido;
		}catch(InputMismatchException e){
			System.out.println("---------------------------------");
			System.out.println("ERRO: Digite somente números!");
			System.out.println("---------------------------------");
			scan.nextLine();			
		}
		
		System.out.println("Descrição do Produto: ");
		this.descricaoProd = scan.nextLine();
		
		try{
			System.out.println("Preço do Produto: ");
			this.precoProd = scan.nextDouble();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("---------------------------------");
			System.out.println("ERRO: Digite somente números!");
			System.out.println("---------------------------------");
			scan.nextLine();
		}		
		
		try{
			System.out.println("Quantidade em Estoque: ");
			this.qtdEstoque = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("---------------------------------");
			System.out.println("ERRO: Digite somente números!");
			System.out.println("---------------------------------");
			scan.nextLine();
		}
		
		System.out.println("Pontos do Produto: ");
		this.pontosProd = scan.nextLine();
	}
	
	public void createProduto(){
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
	}
	
	public void updateProdutos(){
		try{
			System.out.println("Nova Página do Produto: ");
			this.paginaProd = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("---------------------------------");
			System.out.println("ERRO: Digite somente números!");
			System.out.println("---------------------------------");
			scan.nextLine();
		}
		
		try{
			System.out.println("Novo Código do Produto: ");
			this.codigoProd = scan.nextInt();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("---------------------------------");
			System.out.println("ERRO: Digite somente números!");
			System.out.println("---------------------------------");
			scan.nextLine();			
		}
		
		System.out.println("Nova Descrição do Produto: ");
		this.descricaoProd = scan.nextLine();
		
		try{
			System.out.println("Novo Preço do Produto: ");
			this.precoProd = scan.nextDouble();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("---------------------------------");
			System.out.println("ERRO: Digite somente números!");
			System.out.println("---------------------------------");
			scan.nextLine();
		}
		
		System.out.println("Novos Pontos do Produto: ");
		this.pontosProd = scan.nextLine();
	}
	
	public void mostraProduto(){
		System.out.println("Produto:");
		System.out.println("Página: " + this.paginaProd);
		System.out.println("Código: " + this.codigoProd);
		System.out.println("Descrição: " + this.descricaoProd);
		System.out.println("Preço: " + this.precoProd);
		System.out.println("Qtd. em Estoque: " + this.qtdEstoque);
		System.out.println("Pontos: " + this.pontosProd);
	}
}