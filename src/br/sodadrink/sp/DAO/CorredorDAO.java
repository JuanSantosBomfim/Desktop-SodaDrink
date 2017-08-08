package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.Corredor;

public class CorredorDAO {

	public static boolean cadastrarCorredorDAO(Corredor corredor) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "INSERT INTO tblcorredor(nome) values(?)";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, corredor.getNome());

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public static boolean editarCorredorDAO(Corredor corredor) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "UPDATE tblcorredor SET nome = ? WHERE id_corredor = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, corredor.getNome());
			preparedStatement.setInt(2, corredor.getId_corredor());

			preparedStatement.executeUpdate();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static boolean excluirCorredorDAO(int id_corredor) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "DELETE from tblcorredor WHERE id_corredor = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_corredor);

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public static List<Corredor> listarCorredorDAO() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Corredor> listCorredor = new ArrayList<>();

		String sql = "SELECT * FROM tblcorredor";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Corredor corredor = new Corredor();
				corredor.setId_corredor(resultSet.getInt("id_corredor"));
				corredor.setNome(resultSet.getString("nome"));

				listCorredor.add(corredor);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listCorredor;

	}

	public static List<Corredor> listarCorredorPorIdDAO(int id_corredor) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Corredor> listCorredor = new ArrayList<>();

		String sql = "SELECT * FROM tblcorredor WHERE id_corredor = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_corredor);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Corredor corredor = new Corredor();
				corredor.setId_corredor(resultSet.getInt("id_corredor"));
				corredor.setNome(resultSet.getString("nome"));

				listCorredor.add(corredor);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listCorredor;

	}

}
