package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.Categoria;

public class CategoriaDAO {

	public static boolean cadastrarCategoriaDAO(Categoria categoria) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "INSERT INTO tblcategoria(descricao,aprovado) values(?,?)";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, categoria.getDescricao());
			preparedStatement.setInt(2, 0);

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public static boolean excluirCategoriaDAO(int id_categoria) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "DELETE FROM tblcategoria WHERE id_categoria = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_categoria);

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static boolean editarCategoriaDAO(Categoria categoria) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "UPDATE tblcategoria SET descricao = ? WHERE id_categoria = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, categoria.getDescricao());
			preparedStatement.setInt(2, categoria.getId_categoria());

			preparedStatement.executeUpdate();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static List<Categoria> listarCategoriaDAO() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Categoria> listCategoria = new ArrayList<>();

		String sql = "SELECT * FROM tblcategoria;";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Categoria categoria = new Categoria();
				categoria.setId_categoria(resultSet.getInt("id_categoria"));
				categoria.setDescricao(resultSet.getString("descricao"));
				categoria.setAprovado(resultSet.getInt("aprovado"));

				listCategoria.add(categoria);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listCategoria;

	}

	public static List<Categoria> listarCategoriaPorIdDAO(int id_categoria) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Categoria> listCategoria = new ArrayList<>();

		String sql = "SELECT * FROM tblcategoria WHERE id_categoria = ?;";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_categoria);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Categoria categoria = new Categoria();
				categoria.setId_categoria(resultSet.getInt("id_categoria"));
				categoria.setDescricao(resultSet.getString("descricao"));
				categoria.setAprovado(resultSet.getInt("aprovado"));

				listCategoria.add(categoria);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listCategoria;

	}

}
