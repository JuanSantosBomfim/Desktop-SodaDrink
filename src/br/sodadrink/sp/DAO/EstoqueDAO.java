package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.Estoque;

public class EstoqueDAO {

	public static boolean cadastrarEstoqueDAO(Estoque estoque) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "INSERT INTO tblestoque(id_produto,id_prateleira) values(?,?)";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, estoque.getId_produto());
			preparedStatement.setInt(2, estoque.getId_prateleira());

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public static List<Estoque> listarEstoqueDAO() {

		// cria uma conecxão com o banco
		Connection con = ConnectDataBase.openConection();
		// execulta a query
		PreparedStatement stm;
		// pega os valores que o sql retorna
		ResultSet rs;

		List<Estoque> listEstoque = new ArrayList<>();

		String sql = "SELECT estoque.*,produto.nome as nomeProduto,prateleira.nome as nomePrateleira FROM tblestoque as estoque "
				+ "INNER JOIN tblproduto as produto INNER JOIN tblprateleira as prateleira "
				+ "WHERE estoque.id_produto = produto.id_produto and estoque.id_prateleira = prateleira.id_prateleira";

		try {

			stm = con.prepareStatement(sql);
			rs = stm.executeQuery();

			while (rs.next()) {

				Estoque estoque = new Estoque();
				estoque.setId_estoque(rs.getInt("id_estoque"));
				estoque.setId_produto(rs.getInt("id_produto"));
				estoque.setId_prateleira(rs.getInt("id_prateleira"));

				estoque.setNomeProduto(rs.getString("nomeProduto"));
				estoque.setNomePrateleira(rs.getString("nomePrateleira"));
				/*
				 * estoque.setNomeEstante(rs.getString("nomeEstante"));
				 * estoque.setNomeCorredor(rs.getString("nomeCorredor"));
				 */

				listEstoque.add(estoque);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listEstoque;

	}

	public static List<Estoque> listarEstoquePorIdDAO(int id_estoque) {

		// cria uma conecxão com o banco
		Connection con = ConnectDataBase.openConection();
		// execulta a query
		PreparedStatement stm;
		// pega os valores que o sql retorna
		ResultSet rs;

		List<Estoque> listEstoque = new ArrayList<>();

		String sql = "SELECT estoque.*,produto.nome as nomeProduto,prateleira.nome as nomePrateleira FROM tblestoque as estoque "
				+ "INNER JOIN tblproduto as produto INNER JOIN tblprateleira as prateleira "
				+ "WHERE estoque.id_produto = produto.id_produto and estoque.id_prateleira = prateleira.id_prateleira and estoque.id_estoque = ?";

		try {

			stm = con.prepareStatement(sql);

			stm.setInt(1, id_estoque);

			rs = stm.executeQuery();

			while (rs.next()) {

				Estoque estoque = new Estoque();
				estoque.setId_estoque(rs.getInt("id_estoque"));
				estoque.setId_produto(rs.getInt("id_produto"));
				estoque.setId_prateleira(rs.getInt("id_prateleira"));

				estoque.setNomeProduto(rs.getString("nomeProduto"));
				estoque.setNomePrateleira(rs.getString("nomePrateleira"));
				/*
				 * estoque.setNomeEstante(rs.getString("nomeEstante"));
				 * estoque.setNomeCorredor(rs.getString("nomeCorredor"));
				 */

				listEstoque.add(estoque);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listEstoque;

	}

	public static boolean editarEstoqueDAO(Estoque estoque) {

		// cria uma Connection e instancia
		Connection con = ConnectDataBase.openConection();

		// cria um PreparedStatement para execultar a query
		PreparedStatement stm;

		// mudar inscicaoEstadual
		String sql = "UPDATE tblestoque SET id_produto = ?, id_prateleira = ? WHERE id_estoque = ?";

		try {

			stm = con.prepareStatement(sql);

			stm.setInt(1, estoque.getId_produto());
			stm.setInt(2, estoque.getId_prateleira());
			stm.setInt(3, estoque.getId_estoque());

			stm.executeUpdate();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static boolean excluirEstoqueDAO(int id_estoque) {

		Connection conexao = ConnectDataBase.openConection();

		PreparedStatement preparedStatement;

		String sql = "DELETE FROM tblestoque WHERE id_estoque = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_estoque);

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public static boolean excluirEstoquePeloProdutoDAO(int id_produto) {

		Connection conexao = ConnectDataBase.openConection();

		PreparedStatement preparedStatement;

		String sql = "DELETE FROM tblestoque WHERE id_produto = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_produto);

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}
	
	public static boolean excluirEstoquePelaPrateleiraDAO(int id_prateleira) {

		Connection conexao = ConnectDataBase.openConection();

		PreparedStatement preparedStatement;

		String sql = "DELETE FROM tblestoque WHERE id_prateleira = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_prateleira);

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

}
