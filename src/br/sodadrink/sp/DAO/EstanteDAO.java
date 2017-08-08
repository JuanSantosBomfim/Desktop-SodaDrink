package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.Estante;

public class EstanteDAO {

	public static boolean cadastrarEstanteDAO(Estante estante) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "INSERT INTO tblestante(nome) values(?)";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, estante.getNome());

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public static boolean editarEstanteDAO(Estante estante) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "UPDATE tblestante SET nome = ? WHERE id_estante = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, estante.getNome());
			preparedStatement.setInt(2, estante.getId_estante());

			preparedStatement.executeUpdate();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static boolean excluirEstanteDAO(int id_estante) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "DELETE from tblestante WHERE id_estante = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_estante);

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public static List<Estante> listarEstanteDAO() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Estante> listEstante = new ArrayList<>();

		String sql = "SELECT * FROM tblestante";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Estante estante = new Estante();
				estante.setId_estante(resultSet.getInt("id_estante"));
				estante.setNome(resultSet.getString("nome"));

				listEstante.add(estante);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listEstante;

	}

	public static List<Estante> listarEstantePorIdDAO(int id_estante) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Estante> listEstante = new ArrayList<>();

		String sql = "SELECT * FROM tblestante WHERE id_estante = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_estante);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Estante estante = new Estante();
				estante.setId_estante(resultSet.getInt("id_estante"));
				estante.setNome(resultSet.getString("nome"));

				listEstante.add(estante);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listEstante;

	}

}
