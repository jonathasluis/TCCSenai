package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public static Connection conexao = null;
	
	public static Connection getConexao() {
		 String porta = "3307";
		 String ip = "127.0.0.1";
		 //String ip = "192.168.5.59";
		 String banco = "teste";
		 String usuario = "root";
	
		String sql = "jdbc:mysql://"+ip+":"+porta+"/"+banco;
		try {
			conexao = DriverManager.getConnection(sql, usuario, usuario);
			System.out.println("conectado ao banco");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("erro ao conectar ao banco");
		}
		return conexao;
	}

	/*public static void main(String[] args) {
		Conexao c = new  Conexao();
		System.out.println(c.getConexao());
	}*/
}
