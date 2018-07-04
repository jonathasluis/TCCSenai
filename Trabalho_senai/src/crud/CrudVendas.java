package crud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.Vendas;
import banco.Conexao;

public class CrudVendas {
	
	public boolean addvendas(Vendas venda) {
		String sql = "INSERT INTO vendas(produto, idanimal, preco, cliente, qtd, idfazenda, datavenda)"
				+ " VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, venda.getProduto());
			stmt.setInt(2, venda.getIdanimal());
			stmt.setDouble(3, venda.getPreco());
			stmt.setString(4, venda.getCliente());
			stmt.setInt(5, venda.getQuantidade());
			stmt.setInt(6, venda.getIdFazenda());
			stmt.setString(7, venda.getDataVenda());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updVendas(Vendas venda) {
		String sql = "update  vendas set preco, cliente where idvendas=?";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			 stmt.setDouble(1, venda.getPreco());
			 stmt.setString(2, venda.getCliente());
			 stmt.setInt(3, venda.getId());
			 stmt.execute();
			 stmt.close();
			 return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static ResultSet selecionaVendas(Vendas venda) {
		ResultSet tabela = null;
		String sql = "SELECT*FROM vendas where idfazenda=? order by datavenda";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setInt(1, venda.getIdFazenda());
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
	
	public static ResultSet procuraVendas(String letra,Vendas venda) {
		ResultSet tabela = null;
		String sql = "SELECT * FROM vendas WHERE produto LIKE ? and idfazenda=? order by datavenda";
		try {
			PreparedStatement stmt = Conexao.conexao.prepareStatement(sql);
			stmt.setString(1, "%"+letra+"%");
			stmt.setInt(2, venda.getIdFazenda());
			tabela=stmt.executeQuery();
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return tabela =null;
		}
		return tabela;
	}
	
}
