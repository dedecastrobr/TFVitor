package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class DBConnection {

	public static List<String> opsMenuBusca = Arrays.asList("Alterar", "Excluir");
	private Connection conn = null;

	private String URL = "jdbc:mysql://localhost";
	private String USER = "root";
	private String PASSWD = "cazella1998";
	private String BANCO = "TFVitor";

	public DBConnection() {

		try {

			conn = DriverManager.getConnection(URL + "/" + BANCO + "?user=" + USER + "&password=" + PASSWD);

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public Connection getConn() {
		return conn;
	}

	public void executeSQL(String sql) {

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
	
	public void executeSQLBusca(String sql) {

		Statement stmt = null;
		ResultSet rs = null;

		try {

			stmt = conn.createStatement();
			if (stmt.execute(sql)) {
				rs = stmt.getResultSet();
				System.out.println("Cliente encontrado: ");
				while (rs.next()) {
					System.out.println("____________________________");
					System.out.println("- " + rs.getString(1) + "\n- " + rs.getString(2) + "\n- " + rs.getString(3) + "\n- " + rs.getLong(4));
				}
				System.out.println("____________________________");
				Menu menu = new Menu("", opsMenuBusca);
				menu.showOps();
				int Ops = Menu.scan.nextInt();
				switch(Ops) {
					case 0:
						//alterar
						break;
						//remover
						try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD)) {
							
							String sql2 = "DELETE FROM clientes WHERE idCliente = '"+rs.getInt(5)+"'";
							
							PreparedStatement statement = conn.prepareStatement(sql2);
							statement.setInt(rs.getInt(5));
							
							int rowsDeleted = statement.executeUpdate();
							if (rowsDeleted > 0) {
								System.out.println("A user was deleted successfully!");
							}
							
						} catch (SQLException ex) {
							ex.printStackTrace();
}
					case 1:
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
}
