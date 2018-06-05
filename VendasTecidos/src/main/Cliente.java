package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import tools.DBConnection;
import tools.Menu;

public class Cliente {
	
	private String nomeCliente = "";
	private String emailCliente = "";
	private String cpfCliente = "";
	private Scanner scan = Menu.scan;
	
	public Cliente() {
		System.out.println("Informe o nome do cliente: ");
		this.nomeCliente = scan.nextLine();

		System.out.println("Informe o e-mail do cliente: ");
		this.emailCliente = scan.nextLine();

		System.out.println("Informe a CPF do cliente: ");
		this.cpfCliente = scan.nextLine();
	}
	
	public void create() {
			
			Connection conn = (new DBConnection()).getConn();
			Statement stmt = null;
			
			String sql = "insert into Clientes(nome, email, cpf) values('" + this.nomeCliente + "','" + this.emailCliente + "'," + this.cpfCliente + ")";
			System.out.println(sql);
			try {
				stmt = conn.createStatement();
				if(stmt.execute(sql)) {
					System.out.println("Não funcionou");
				}else {
					int count = stmt.getUpdateCount();
					if (count >= 1) {
						System.out.println("Registro Inserido com sucesso!");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
}
