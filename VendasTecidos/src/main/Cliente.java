package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import tools.DBConnection;
import tools.Menu;

public class Cliente {
	
	private int idCliente = 0;
	private String nomeCliente = "";
	private String emailCliente = "";
	private String cpfCliente = "";
	private String endereco = "";
	private Scanner scan = Menu.scan;
	
	public Cliente() {
		scan.nextLine();
		System.out.println("Informe o nome do cliente: ");
		this.nomeCliente = scan.nextLine();

		System.out.println("Informe o e-mail do cliente: ");
		this.emailCliente = scan.nextLine();
		
		System.out.println("Informe o endereço do cliente: ");
		this.endereco = scan.nextLine();

		System.out.println("Informe a CPF do cliente: ");
		this.cpfCliente = scan.nextLine();
	}
	
	public void create() {
			
			Connection conn = (new DBConnection()).getConn();
			Statement stmt = null;
			
			if (cpfCliente.length() == 11) {
				String sql = "insert into Clientes(nome, email, cpf, endereco) values('" + this.nomeCliente + "','"
						+ this.emailCliente + "','" + this.cpfCliente + "','" + this.endereco + "')";
				//System.out.println("print--> " + sql);
				try {
					stmt = conn.createStatement();
					if (stmt.execute(sql)) {
						System.out.println("Não funcionou");
					} else {
						int count = stmt.getUpdateCount();
						if (count >= 1) {
							System.out.println("Registro Inserido com sucesso!");
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}else {
				System.out.println("Dados Inválidos!");
			}
		}

	public int getIdCliente() {
		return idCliente;
	}
	
	public String getNome() {
		return nomeCliente;
	}
	
	public String getEmail() {
		return emailCliente;
	}
	
	public String getCpf() {
		return cpfCliente;
	}
	
	public String getEndereco() {
		return endereco;
	}
}