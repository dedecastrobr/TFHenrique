package natura;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cliente{
	
	//Atributos
	private String nomeCliente = "";
	private String enderecoCliente = "";
	private String emailCliente = "";
	private long telefoneCliente = 0;
	private int idCliente = 0;
	
	//Pedido - Cliente
	private Pedido pedidoCliente = null;
	
	private Scanner scan = Natura.scan;
	
	//Gets
	public String getNomeCliente(){
		return nomeCliente;
	}
	
	public String getEnderecoCliente(){
		return enderecoCliente;
	}
		
	public String getEmailCliente(){
		return emailCliente;
	}
		
	public long getTelefoneCliente(){
		return telefoneCliente;
	}
	
	public int getIdCliente(){
		return idCliente;
	}
	
	//Get Pedido - Cliente
	public Pedido getpedidoCliente() {
		return pedidoCliente;
	}
	
	//Sets
	public void setNomeCliente(String nomeCliente){
		this.nomeCliente = nomeCliente;
	}
		
	public void setEnderecoCliente(String enderecoCliente){
		this.enderecoCliente = enderecoCliente;
	}
		
	public void setEmailCliente(String emailCliente){
		this.emailCliente = emailCliente;
	}
		
	public void setTelefoneCliente(long telefoneCliente){
		this.telefoneCliente = telefoneCliente;
	}
	
	public void setIdCliente(int idCliente){
		this.idCliente = idCliente;
	}
	
	//Set Pedido - Cliente
	public void setPedidoCliente(Pedido pedidoCliente) {
		this.pedidoCliente = pedidoCliente;
	}
	
	public Cliente(String nomeCliente, String enderecoCliente, String emailCliente, long telefoneCliente, Pedido pedidoCliente){
		this.nomeCliente = nomeCliente;
		this.enderecoCliente = enderecoCliente;
		this.emailCliente = emailCliente;
		this.telefoneCliente = telefoneCliente;
		this.pedidoCliente = pedidoCliente;
	}
	
	public Cliente(String nomeCliente, String enderecoCliente, String emailCliente, long telefoneCliente){
		this.nomeCliente = nomeCliente;
		this.enderecoCliente = enderecoCliente;
		this.emailCliente = emailCliente;
		this.telefoneCliente = telefoneCliente;
	}
		
	public Cliente(){
		System.out.println("Nome: ");
		this.nomeCliente = scan.nextLine();
		
		System.out.println("Endereço: ");
		this.enderecoCliente = scan.nextLine();
		
		System.out.println("Email: ");
		this.emailCliente = scan.nextLine();
		
		try{
			System.out.println("Telefone: ");
			this.telefoneCliente = scan.nextLong();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("---------------------------------");
			System.out.println("ERRO: Digite somente números!");
			System.out.println("---------------------------------");
			scan.nextLine();
		}
	}
	
	public Cliente(Pedido pedido){
		System.out.println("Nome: ");
		this.nomeCliente = scan.nextLine();
		pedidoCliente = pedido;
		
		System.out.println("Endereço: ");
		this.enderecoCliente = scan.nextLine();
		
		System.out.println("Email: ");
		this.emailCliente = scan.nextLine();
		
		try{
			System.out.println("Telefone: ");
			this.telefoneCliente = scan.nextLong();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("---------------------------------");
			System.out.println("ERRO: Digite somente números!");
			System.out.println("---------------------------------");
			scan.nextLine();
		}
	}
	
	public void createCliente(){
		Connection conn = (new DBConnection()).getConn();
		Statement stmt = null;
		
		String sql = "INSERT INTO Clientes(Telefone, Nome, Endereco, Email) VALUES('" + this.telefoneCliente + "','" + this.nomeCliente + "','" + this.enderecoCliente + "','" + this.emailCliente + "')";
		System.out.println(sql);
		try {
			stmt = conn.createStatement();
			if(stmt.execute(sql)) {
				System.out.println("Não funcionou");
			}else {
				int count = stmt.getUpdateCount();
				if (count >= 1) {
					System.out.println("---------------------------------");
					System.out.println("Cliente cadastrado com sucesso!");
					System.out.println("---------------------------------");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean update(){
    	Natura.listaClientes.set(this.getIdCliente(), this);
    	return true;
    }
	
	public void mostraCliente(){
		System.out.println("Cliente:");
		System.out.println("Telefone: " + this.telefoneCliente);
		System.out.println("Nome: " + this.nomeCliente);
		System.out.println("Endereço: " + this.enderecoCliente);
		System.out.println("Email: " + this.emailCliente);
	}
}