package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import main.Cliente;
import main.Produtos;

public class DBConnection {

	public static List<String> opsMenuBusca = Arrays.asList("Alterar", "Excluir");
	private Connection conn = null;

	private String URL = "jdbc:mysql://localhost/";
	private String USER = "root";
	private String PASSWD = "cazella1998";
	private String BANCO = "TFVitor";
	private String URLBANCO = "jdbc:mysql://localhost/TFVitor";

	private int idCliente = 0;
	private int idProduto = 0;
	
	public DBConnection() {

		try {
			conn = DriverManager.getConnection(URL + BANCO + "?user=" + USER + "&password=" + PASSWD);
			
			if (conn != null) {
		        System.out.println("\n- Connected -\n");
		    }

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public Connection getConn() {
		return conn;
	}

	public void executeSQLCliente(String sql) {

		Statement stmt = null;
		ResultSet rs = null;

		try {

			stmt = conn.createStatement();
			if (stmt.execute(sql)) {
				rs = stmt.getResultSet();
				System.out.println("Clientes encontrados: ");
				while (rs.next()) {
					System.out.println("____________________________");
					System.out.println("- " + rs.getString(1) + "\n- " + rs.getString(2) + "\n- " + rs.getString(3) + "\n- " + rs.getLong(4));
				}
				System.out.println("____________________________");
			} else {
				int count = stmt.getUpdateCount();
				if (count >= 1) {
					System.out.println("Registro Inserido com sucesso!");
				}else {
					System.out.println("Registro Falhou!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void executeSQLBuscaC(String sql) {

		Statement stmt = null;
		ResultSet rs = null;

		try {

			stmt = conn.createStatement();
			if (stmt.execute(sql)) {
				rs = stmt.getResultSet();
				
				System.out.println("Clientes encontrados: ");
				while (rs.next()) {
					System.out.println("____________________________");
					System.out.println("Nome - " + rs.getString(1) + "\nEmail - " + rs.getString(2) + "\nEndereço - " + rs.getString(3) + "\nCPF - " + rs.getLong(4));					
					setIdCliente(rs.getInt(5));
				}
				
				System.out.println("____________________________");
				Menu menu = new Menu("Alterar ou excluir", opsMenuBusca);
				menu.showOps();
				int Ops = Menu.scan.nextInt();
				Menu.scan.nextLine();
				switch(Ops) {
					case 0:
						//alterar
						try (Connection conn = DriverManager.getConnection(URLBANCO, USER, PASSWD)) {
						
							Cliente at = new Cliente();
							String sql3 = "UPDATE Clientes SET nome=?, email=?, endereco=?, cpf=? WHERE idCliente = ?";
							
							PreparedStatement statementU = conn.prepareStatement(sql3);
							statementU.setString(1, at.getNome());
							statementU.setString(2, at.getEmail());
							statementU.setString(3, at.getEndereco());
							statementU.setString(4, at.getCpf());
							statementU.setInt(5, getIdCliente());
							
							//System.out.println("print--> " + statementU);
							int rowsUpdated = statementU.executeUpdate();
							if (rowsUpdated > 0) {
							    System.out.println("O cliente foi atualizado com sucesso!");
							}
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
						break;
						
					case 1:
						//remover
						try (Connection conn = DriverManager.getConnection(URLBANCO, USER, PASSWD)) {
							
							String sql2 = "DELETE FROM Clientes WHERE idCliente = ?";
							
							PreparedStatement statementD = conn.prepareStatement(sql2);
							statementD.setInt(1, getIdCliente());
							
							int rowsDeleted = statementD.executeUpdate();
							if (rowsDeleted > 0) {
								System.out.println("Cliente deletado com sucesso!");
							
							}
							
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
						break;
					default:
						break;
				}
			} else {
				int count = stmt.getUpdateCount();
				if (count >= 1) {
					System.out.println("Registro Inserido com sucesso!");
				}else {
					System.out.println("Registro Falhou!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void executeSQLProduto(String sql) {

		Statement stmt = null;
		ResultSet rs = null;

		try {
			
			stmt = conn.createStatement();
			if (stmt.execute(sql)) {
				rs = stmt.getResultSet();
				System.out.println("Produtos encontrados: ");
				while (rs.next()) {
					System.out.println("____________________________");
					System.out.println("Nome - " + rs.getString(1) + "\nPreço - " + rs.getString(2) + "\nQuantidade - " + rs.getString(3));
				}
				System.out.println("____________________________");
			} else {
				int count = stmt.getUpdateCount();
				if (count >= 1) {
					System.out.println("Registro Inserido com sucesso!");
				}else {
					System.out.println("Registro Falhou!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void executeSQLBuscaP(String sql) {

		Statement stmt = null;
		ResultSet rs = null;

		try {

			stmt = conn.createStatement();
			if (stmt.execute(sql)) {
				rs = stmt.getResultSet();
				
				System.out.println("Produto encontrado: ");
				while (rs.next()) {
					System.out.println("____________________________");
					System.out.println("- " + rs.getString(1) + "\n- " + rs.getString(2) + "\n- " + rs.getString(3));					
					setIdProduto(rs.getInt(4));
				}
				
				System.out.println("____________________________");
				Menu menu = new Menu("Alterar ou excluir", opsMenuBusca);
				menu.showOps();
				int Ops = Menu.scan.nextInt();
				Menu.scan.nextLine();
				switch(Ops) {
					case 0:
						//alterar
						try (Connection conn = DriverManager.getConnection(URLBANCO, USER, PASSWD)) {
						
							Produtos pro = new Produtos();
							String sql3 = "UPDATE Produto SET nome=?, preco=?, quantidade=? WHERE idProduto = ?";
							
							PreparedStatement statementU = conn.prepareStatement(sql3);
							statementU.setString(1, pro.getNome());
							statementU.setDouble(2, pro.getPreco());
							statementU.setInt(3, pro.getEstoque());
							statementU.setInt(4, getIdProduto());
							
							//System.out.println("print--> " + statementU);
							int rowsUpdated = statementU.executeUpdate();
							if (rowsUpdated > 0) {
							    System.out.println("O produto foi atualizado com sucesso!");
							}
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
						break;
						
					case 1:
						//remover
						try (Connection conn = DriverManager.getConnection(URLBANCO, USER, PASSWD)) {
							
							String sql2 = "DELETE FROM Produto WHERE idProduto = ?";
							
							PreparedStatement statementD = conn.prepareStatement(sql2);
							statementD.setInt(1, getIdProduto());
							
							int rowsDeleted = statementD.executeUpdate();
							if (rowsDeleted > 0) {
								System.out.println("Produto deletado com sucesso!");
							
							}
							
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
						break;
						
					default:
						break;
				}
			} else {
				int count = stmt.getUpdateCount();
				if (count >= 1) {
					System.out.println("Registro Inserido com sucesso!");
				}else {
					System.out.println("Registro Falhou!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void executeSQLEstoque(String sql) {

		Statement stmt = null;
		ResultSet rs = null;

		try {

			stmt = conn.createStatement();
			if (stmt.execute(sql)) {
				rs = stmt.getResultSet();
				System.out.println("Lista de Produtos em Estoque: ");
				System.out.println("_______________________________");
				System.out.println("Nome - Quantidade no estoque");
				while (rs.next()) {
					System.out.println(rs.getString(1) + " - " + rs.getString(2));
				}
				System.out.println("_______________________________");
			} else {
				System.out.println("Registro Falhou!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void executeSQLPedido(String sql) {

		Statement stmt = null;
		ResultSet rs = null;

		try {

			stmt = conn.createStatement();
			if (stmt.execute(sql)) {
				rs = stmt.getResultSet();
				
				System.out.println("Clientes encontrados: ");
				while (rs.next()) {
					System.out.println("____________________________");
					System.out.println("Nome - " + rs.getString(1) + "\nEmail - " + rs.getString(2) + "\nEndereço - " + rs.getString(3) + "\nCPF - " + rs.getLong(4));					
					setIdCliente(rs.getInt(5));
				}
				System.out.println("____________________________");
			} else {
				int count = stmt.getUpdateCount();
				if (count >= 1) {
					System.out.println("Registro Inserido com sucesso!");
				}else {
					System.out.println("Registro Falhou!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
}
