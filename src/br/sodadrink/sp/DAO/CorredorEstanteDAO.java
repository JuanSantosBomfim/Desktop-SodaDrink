package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.EstanteCorredor;

public class CorredorEstanteDAO {

	public static boolean cadastrarCorredorEstanteDAO(EstanteCorredor estanteCorredor) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "INSERT INTO tblcorredorestante(id_estante,id_corredor) values(?,?)";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, estanteCorredor.getId_estante());
			preparedStatement.setInt(2, estanteCorredor.getId_corredor());

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public static boolean editarCorredorDaEstanteDAO(EstanteCorredor estanteCorredor) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "UPDATE tblcorredorestante SET id_corredor = ? WHERE id_estante = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, estanteCorredor.getId_corredor());
			preparedStatement.setInt(2, estanteCorredor.getId_estante());

			preparedStatement.executeUpdate();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static boolean excluirCorredorEstanteDAO(int id_estante) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "DELETE FROM tblcorredorestante WHERE id_estante = ?";

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

	public static List<EstanteCorredor> listarEstanteCorredorDAO() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<EstanteCorredor> listEstanteCorredor = new ArrayList<>();

		String sql = "SELECT ce.*,e.nome as nomeEstante,c.nome as nomeCorredor from tblcorredorestante as ce INNER JOIN tblestante as e INNER JOIN tblcorredor as c "
				+ "WHERE ce.id_estante = e.id_estante and ce.id_corredor = c.id_corredor";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				EstanteCorredor estanteCorredor = new EstanteCorredor();
				estanteCorredor.setId_estante_corredor(resultSet.getInt("id_corredor_estante"));
				estanteCorredor.setId_estante(resultSet.getInt("id_estante"));
				estanteCorredor.setId_corredor(resultSet.getInt("id_corredor"));

				estanteCorredor.setNomeEstante(resultSet.getString("nomeEstante"));
				estanteCorredor.setNomeCorredor(resultSet.getString("nomeCorredor"));

				listEstanteCorredor.add(estanteCorredor);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listEstanteCorredor;

	}

	public static List<EstanteCorredor> listarCorredorPorIdEstante(int id_estante) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<EstanteCorredor> listEstanteCorredor = new ArrayList<>();

		String sql = "SELECT ce.*,c.nome as nomeCorredor,e.nome as nomeEstante FROM tblcorredorestante as ce INNER JOIN tblcorredor as c INNER JOIN tblestante as e "
				+ "WHERE ce.id_corredor = c.id_corredor and ce.id_estante = e.id_estante and ce.id_estante = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_estante);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				EstanteCorredor estanteCorredor = new EstanteCorredor();
				estanteCorredor.setId_estante_corredor(resultSet.getInt("id_corredor_estante"));
				estanteCorredor.setId_estante(resultSet.getInt("id_estante"));
				estanteCorredor.setId_corredor(resultSet.getInt("id_corredor"));

				estanteCorredor.setNomeCorredor(resultSet.getString("nomeCorredor"));
				estanteCorredor.setNomeEstante(resultSet.getString("nomeEstante"));

				listEstanteCorredor.add(estanteCorredor);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listEstanteCorredor;

	}

	public static List<EstanteCorredor> listarEstantePorIdCorredorDAO(int id_corredor) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<EstanteCorredor> listEstanteCorredor = new ArrayList<>();

		String sql = "SELECT ce.*,c.nome as nomeCorredor,e.nome as nomeEstante FROM tblcorredorestante as ce INNER JOIN tblcorredor as c INNER JOIN tblestante as e "
				+ "WHERE ce.id_corredor = c.id_corredor and ce.id_estante = e.id_estante and ce.id_corredor = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_corredor);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				EstanteCorredor estanteCorredor = new EstanteCorredor();
				estanteCorredor.setId_estante_corredor(resultSet.getInt("id_corredor_estante"));
				estanteCorredor.setId_estante(resultSet.getInt("id_estante"));
				estanteCorredor.setId_corredor(resultSet.getInt("id_corredor"));

				estanteCorredor.setNomeCorredor(resultSet.getString("nomeCorredor"));
				estanteCorredor.setNomeEstante(resultSet.getString("nomeEstante"));

				listEstanteCorredor.add(estanteCorredor);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listEstanteCorredor;

	}

}
