package JanelasAnimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import banco.Conexao;



public class ComboBox extends CadastrarAnimais{
		
	public static void comboBoxEspecie() {
		ResultSet dados1=null;
		String sql = "SELECT (nome_es) FROM especie";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			dados1 = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			cbEspecie.removeAllItems();
			while(dados1.next()) {
					cbEspecie.addItem(dados1.getString("nome_es"));
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("foi nao");
		}
	}
	
	public static int pegaIdEspecie(String nomeEspecie) {
		ResultSet dados1=null;
		int id=0;
		String sql = "SELECT idespecie FROM especie where nome_es=?";
		try {
			PreparedStatement st = Conexao.conexao.prepareStatement(sql);
			st.setString(1, nomeEspecie);
			dados1 = st.executeQuery();
			st.execute();
			st.close();
			if(dados1.next()) {
				id = dados1.getInt("idespecie");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("foi nao");
		}
		return id;
	}
	
	public static String pegaNomeEspecie(int id) {
		ResultSet dados1=null;
		String valor=null;
		String sql = "SELECT idraca,nome_ra, id_especie, especie.nome_es FROM raca" + 
				"				INNER JOIN especie" + 
				"				ON id_especie = idespecie where idraca = ?";
		try {
			PreparedStatement st = Conexao.conexao.prepareStatement(sql);
			st.setInt(1, id);
			dados1 = st.executeQuery();
			st.execute();
			st.close();
			if(dados1.next()) {
				 valor = dados1.getString("nome_es");
				}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.out.println("foi nao");
		}
		return valor;
	}
	
	public static void comboBoxRaca(int idEspecie) {
		ResultSet dados1=null;
		String sql = "SELECT * FROM raca where id_especie = ?";
		try {
			PreparedStatement st = Conexao.conexao.prepareStatement(sql);
			st.setInt(1, idEspecie);
			dados1 = st.executeQuery();
			st.execute();
			st.close();
			while(dados1.next()) {
				  cbRaca.addItem(dados1.getString("nome_ra"));
				}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.out.println("foi nao");
		}
	}
	
	public static int pegaIdRaca(String raca) {
		ResultSet dados1=null;
		int id = 0;
		String sql = "SELECT (idraca) FROM raca where nome_ra = ?";
		try {
			PreparedStatement st = Conexao.conexao.prepareStatement(sql);
			st.setString(1, raca);
			dados1 = st.executeQuery();
			st.execute();
			st.close();
			if(dados1.next()) {
				 id = dados1.getInt("idraca");
				}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.out.println("foi nao");
		}
		return id;
	}
	
	public static String pegaNomeRaca(int id) {
		ResultSet dados1=null;
		String valor=null;
		String sql = "SELECT * FROM raca where idraca = ?";
		try {
			PreparedStatement st = Conexao.conexao.prepareStatement(sql);
			st.setInt(1, id);
			dados1 = st.executeQuery();
			st.execute();
			st.close();
			if(dados1.next()) {
				 valor = dados1.getString("nome_ra");
				}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.out.println("foi nao");
		}
		return valor;
	}
}
