package main;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import tools.Menu;

	public class menu {
		
		public static List<String> opsMenuPrincipal = Arrays.asList("Clientes", "Produtos", "Pedidos", "Estoque");
		public static List<String> opsMenuCliente = Arrays.asList("Cadastrar Novo Cliente", "Lista de Clientes", "Buscar Cliente");
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
							break;
						case 2:
							break;
						default:
							break;
						}
						menuC.show();
						op2 = menu.getOption();
					} while(op2 != 99);
					break;
				case 1:
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
		
		public static void limpaTela() {
			System.out.println("\033[2J");
	
		}
}

