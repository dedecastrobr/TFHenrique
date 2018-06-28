package natura;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Cliente{
	
	//Atributos
	private String nomeCliente = "";
	private String enderecoCliente = "";
	private String emailCliente = "";
	private long telefoneCliente = 0;
	private String newNomeCliente = "";
	private String newEnderecoCliente = "";
	private String newEmailCliente = "";
	private long newTelefoneCliente = 0;
	private int idCliente = 0;
	
	//Cliente - Pedido
	private ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
	
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
	
	public String getNewNomeCliente(){
		return newNomeCliente;
	}
	
	public String getNewEnderecoCliente(){
		return newEnderecoCliente;
	}
		
	public String getNewEmailCliente(){
		return newEmailCliente;
	}
		
	public long getNewTelefoneCliente(){
		return newTelefoneCliente;
	}
	
	//Get Cliente - Pedido
	public List<Pedido> getListaPedidos() {
		return listaPedidos;
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
	
	public void setNewNomeCliente(String newNomeCliente){
		this.newNomeCliente = newNomeCliente;
	}
		
	public void setNewEnderecoCliente(String newEnderecoCliente){
		this.newEnderecoCliente = newEnderecoCliente;
	}
		
	public void setNewEmailCliente(String newEmailCliente){
		this.newEmailCliente = newEmailCliente;
	}
		
	public void setNewTelefoneCliente(long newTelefoneCliente){
		this.newTelefoneCliente = newTelefoneCliente;
	}
	
	public void setIdCliente(int idCliente){
		this.idCliente = idCliente;
	}
	
	//Set Cliente - Pedido
	public void setListaPedidos(ArrayList<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
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
			System.out.println("ERRO: Digite somente números!");
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
	
	public void updateCliente(){
		System.out.println("Novo Nome: ");
		this.newNomeCliente = scan.nextLine();
		
		System.out.println("Novo Endereço: ");
		this.newEnderecoCliente = scan.nextLine();
		
		System.out.println("Novo Email: ");
		this.newEmailCliente = scan.nextLine();
		
		try{
			System.out.println("Novo Telefone: ");
			this.newTelefoneCliente = scan.nextLong();
			scan.nextLine();
		}catch(InputMismatchException e){
			System.out.println("ERRO: Digite somente números!");
			scan.nextLine();
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