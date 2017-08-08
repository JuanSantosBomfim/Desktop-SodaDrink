package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.Prateleira;

public class PrateleiraDAO {

	public static boolean cadastrarPrateleiraDAO(Prateleira prateleira) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "INSERT INTO tblprateleira(nome) values(?)";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, prateleira.getNome());

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public static boolean editarPrateleiraDAO(Prateleira prateleira) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "UPDATE tblprateleira SET nome = ? WHERE id_prateleira = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, prateleira.getNome());
			preparedStatement.setInt(2, prateleira.getId_prateleira());

			preparedStatement.executeUpdate();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static boolean excluirPrateleiraDAO(int id_prateleira) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "DELETE FROM tblprateleira WHERE id_prateleira = ?";

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

	public static List<Prateleira> listarPrateleiraDAO() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Prateleira> listPrateleira = new ArrayList<>();

		String sql = "SELECT * FROM tblprateleira";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Prateleira prateleira = new Prateleira();
				prateleira.setId_prateleira(resultSet.getInt("id_prateleira"));
				prateleira.setNome(resultSet.getString("nome"));

				listPrateleira.add(prateleira);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listPrateleira;

	}

	public static List<Prateleira> listarPrateleiraPorIdDAO(int id_prateleira) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Prateleira> listPrateleira = new ArrayList<>();

		String sql = "SELECT * FROM tblprateleira WHERE id_prateleira = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_prateleira);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Prateleira prateleira = new Prateleira();
				prateleira.setId_prateleira(resultSet.getInt("id_prateleira"));
				prateleira.setNome(resultSet.getString("nome"));

				listPrateleira.add(prateleira);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listPrateleira;

	}

}
