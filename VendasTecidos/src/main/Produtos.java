package main;

import java.util.Scanner;

import tools.Menu;

public class Produtos {

	private int idProduto = 0;
	private String nome = "";
	private double preco = 0.00;
	private int idEstoque = 0;
	private Scanner scan = Menu.scan;
	
	public Produto() {
		System.out.println("Nome do produto:");
		this.nome = scan.nextLine();
		
		
	}
	
}
