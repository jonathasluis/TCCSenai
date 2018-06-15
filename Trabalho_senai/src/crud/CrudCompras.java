package crud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.Compras;
import banco.Conexao;

public class CrudCompras {
	
	public boolean addCompras(Compras compras) {
		String sql = "INSERT compras_insumos(fornecedor, cnpj,"
				+ " numero_nota, qtd, preco, produto, data_compra, id_fazenda) VALUES (?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
			stmt.setString(1, compras.getFornecedor());
			stmt.setString(2, compras.getCnpj());
			stmt.setString(3, compras.getNumeroNota());
			stmt.setInt(4, compras.getQuantidade());
			stmt.setDouble(5, compras.getPreco());
			stmt.setString(6, compras.getProduto());
			stmt.setString(7, compras.getDataCompra());
			stmt.setInt(4, compras.getIdFazenda());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updCompras(Compras compras) {
		String sql = "UPDATE compras_insumos SET fornecedor=?, cnpj=?,"
					+ "numero_nota=?, produto=? where id_fazenda=?";
		try {
			PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
			stmt.setString(1, compras.getFornecedor());
			stmt.setString(2, compras.getCnpj());
			stmt.setString(3, compras.getNumeroNota());
			stmt.setString(4, compras.getProduto());
			stmt.setInt(5, compras.getIdFazenda());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet selecionaCompras(Compras compras) {
		ResultSet tabela = null;
		String sql = "SELECT*FROM compras_insumos where id_fazenda=?";
		try {
			PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
			stmt.setInt(1, compras.getIdFazenda());
			tabela=stmt.executeQuery();
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return tabela=null;
		}
		return tabela;
	}
	
	public ResultSet procurarCompra(String letra,Compras compras) {
		ResultSet tabela = null;
		String sql = "SELECT*FROM compras_insumos WHERE produto LIKE ? and id_fazenda=?";
		try {
			PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
			stmt.setString(1,"%"+letra+"%");
			stmt.setInt(2, compras.getIdFazenda());
			tabela = stmt.executeQuery();
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erro selecionar");
		}
		return tabela;
	}
}


