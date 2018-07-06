package main;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import tools.DBConnection;
import tools.Menu;

	public class menu {
		
		public static List<String> opsMenuPrincipal = Arrays.asList("Clientes", "Produtos", "Pedidos", "Estoque");
		public static List<String> opsMenuCliente = Arrays.asList("Cadastrar Novo Cliente", "Lista de Clientes", "Buscar Cliente");
		public static List<String> opsMenuProdutos = Arrays.asList("Cadastrar Produto", "Lista de Produtos", "Buscar Produto");
		public static Scanner scan = new Scanner(System.in);
		public static int numMenu = 0;
		
		public static void main(String[] args) {
			
			Menu menu = new Menu("Menu de Loja", opsMenuPrincipal);
			menu.show();
			int op = menu.getOption();
			do{
				switch (op) {
				case 0:
					Menu menuC = new Menu("Menu do Cliente", opsMenuCliente);
					menuC.show();
					int op2 = menuC.getOption();
					do{
						switch (op2) {
						case 0:
							inserirCliente();
							break;
						case 1:
							listarClientes();
							break;
						case 2:
							buscaCliente();
							break;
						default:
							break;			
						}
						menuC.show();
						op2 = menu.getOption();
					} while(op2 != 99);
					break;
				case 1:
					Menu menuP = new Menu("Menu dos Produtos", opsMenuProdutos);
					menuP.show();
					int op3 = menuP.getOption();
					do{
						switch (op3) {
						case 0:
							inserirProduto();
							break;
						case 1:
							listarProdutos();
							break;
						case 2:
							
							break;
						default:
							break;			
						}
						menuP.show();
						op3 = menu.getOption();
					} while(op3 != 99);
					break;
				case 2:
					break;
				case 3:
					break;
				}
				menu.show();
				op = menu.getOption();
			} while(op != 99);
		}
	
		public static void inserirCliente() {
			Cliente al = new Cliente();
			if(al != null) {
				al.create();
			}
		}
		
		public static void listarClientes() {
			DBConnection conn = new DBConnection();
			conn.executeSQLCliente("select c.nome, c.email, c.endereco, c.cpf, c.idCliente from Clientes c");
		}
		
		public static void buscaCliente() {
			String buscacpf = "";
			System.out.println("Digite o CPF do cliente: ");
			buscacpf = scan.nextLine();
			DBConnection conn = new DBConnection();
			conn.executeSQLBuscaC("select c.nome, c.email, c.endereco, c.cpf, c.idCliente from Clientes c where cpf=" + buscacpf);
		}
		
		public static void listarProdutos() {
			DBConnection conn = new DBConnection();
			conn.executeSQLProduto("select p.nome, p.preco, e.Quantidade from Produto p, Estoque e where p.idEstoque = e.idEstoque");
		}
		
		public static void limpaTela() {
			System.out.println("\033[2J");
	
		}
		
		public static void inserirProduto() {
			
		}
}

