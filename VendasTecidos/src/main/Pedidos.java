package main;

import java.util.Scanner;

import tools.Menu;

public class Pedidos {
	
	private int idCliente = 0;
	private int idProduto = 0;
	private String produto = "";
	private int quant = 0;
	private String cpf = "";
	private Scanner scan = Menu.scan;
	
	public Pedidos() {
		System.out.println("Produto comprado: ");
		this.setProduto(scan.nextLine());
		
		System.out.println("Quantidade adquirida: ");
		this.setQuant(scan.nextInt());
		scan.nextLine();
		
		System.out.println("CPF do cliente: ");
		this.setCpf(scan.nextLine());
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
