package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import tools.DBConnection;
import tools.Menu;

public class Produtos {

	private int idProduto = 0;
	private String nome = "";
	private double preco = 0.00;
	private int idEstoque = 0;
	private int Estoque = 0;
	private Scanner scan = Menu.scan;
	
	public Produtos() {
		System.out.println("Nome do produto:");
		this.setNome(scan.nextLine());
		
		System.out.println("Preço do produto: ");
		this.preco = scan.nextDouble();
		scan.nextLine();

		System.out.println("Quantidade em estoque: ");
		this.setEstoque(scan.nextInt());
		scan.nextLine();
	}
	
	public void create() {
		
		Connection conn = (new DBConnection()).getConn();
		Statement stmt = null;
	
		String sql = "insert into Produto(nome, preco, quantidade) values('" + this.nome + "','" + this.preco + "','" + this.Estoque + "')";
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
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getIdEstoque() {
		return idEstoque;
	}

	public void setIdEstoque(int idEstoque) {
		this.idEstoque = idEstoque;
	}

	public int getEstoque() {
		return Estoque;
	}

	public void setEstoque(int estoque) {
		Estoque = estoque;
	}

}