package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.Nivel;

public class NivelDAO {

	public static boolean cadastrarNivelDAO(Nivel nivel) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		String sql = "INSERT INTO tblnivel(descricao,categoria,estoque,fornecedor,marca,nivel,produto,usuario,relatorio) values(?,?,?,?,?,?,?,?,?)";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, nivel.getDescricao());
			preparedStatement.setInt(2, nivel.getCategoria());
			preparedStatement.setInt(3, nivel.getEstoque());
			preparedStatement.setInt(4, nivel.getFornecedor());
			preparedStatement.setInt(5, nivel.getMarca());
			preparedStatement.setInt(6, nivel.getNivel());
			preparedStatement.setInt(7, nivel.getProduto());
			preparedStatement.setInt(8, nivel.getUsuario());
			preparedStatement.setInt(9, nivel.getRelatorio());

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public static boolean excluirNivelDAO(int id_nivel) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		String sql = "DELETE FROM tblnivel WHERE id_nivel = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_nivel);

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static boolean editarNivelDAO(Nivel nivel) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		String sql = "UPDATE tblnivel SET descricao = ?,categoria = ?,estoque = ?,fornecedor = ?,marca = ?,nivel = ?,produto = ?,usuario = ?,relatorio = ? WHERE id_nivel = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, nivel.getDescricao());
			preparedStatement.setInt(2, nivel.getCategoria());
			preparedStatement.setInt(3, nivel.getEstoque());
			preparedStatement.setInt(4, nivel.getFornecedor());
			preparedStatement.setInt(5, nivel.getMarca());
			preparedStatement.setInt(6, nivel.getNivel());
			preparedStatement.setInt(7, nivel.getProduto());
			preparedStatement.setInt(8, nivel.getUsuario());
			preparedStatement.setInt(9, nivel.getRelatorio());
			preparedStatement.setInt(10, nivel.getId_nivel());

			preparedStatement.executeUpdate();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static List<Nivel> listarNivelDAO() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Nivel> listNivel = new ArrayList<>();

		String sql = "SELECT * FROM tblnivel";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Nivel nivel = new Nivel();
				nivel.setId_nivel(resultSet.getInt("id_nivel"));
				nivel.setDescricao(resultSet.getString("descricao"));

				nivel.setCategoria(resultSet.getInt("categoria"));
				nivel.setEstoque(resultSet.getInt("estoque"));
				nivel.setFornecedor(resultSet.getInt("fornecedor"));
				nivel.setMarca(resultSet.getInt("marca"));

				nivel.setNivel(resultSet.getInt("nivel"));
				nivel.setProduto(resultSet.getInt("produto"));
				nivel.setUsuario(resultSet.getInt("usuario"));
				nivel.setRelatorio(resultSet.getInt("relatorio"));

				listNivel.add(nivel);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listNivel;

	}

	public static List<Nivel> listarNivelPorIdDAO(int id_nivel) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Nivel> listNivel = new ArrayList<>();

		String sql = "SELECT * FROM tblnivel WHERE id_nivel = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			preparedStatement.setInt(1, id_nivel);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Nivel nivel = new Nivel();
				nivel.setId_nivel(resultSet.getInt("id_nivel"));
				nivel.setDescricao(resultSet.getString("descricao"));

				nivel.setCategoria(resultSet.getInt("categoria"));
				nivel.setEstoque(resultSet.getInt("estoque"));
				nivel.setFornecedor(resultSet.getInt("fornecedor"));
				nivel.setMarca(resultSet.getInt("marca"));

				nivel.setNivel(resultSet.getInt("nivel"));
				nivel.setProduto(resultSet.getInt("produto"));
				nivel.setUsuario(resultSet.getInt("usuario"));
				nivel.setRelatorio(resultSet.getInt("relatorio"));

				listNivel.add(nivel);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listNivel;

	}

}
