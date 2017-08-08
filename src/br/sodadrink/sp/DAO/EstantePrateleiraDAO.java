package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.EstantePrateleira;

public class EstantePrateleiraDAO {

	public static boolean cadastrarEstantePrateleiraDAO(EstantePrateleira estantePrateleira) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "INSERT INTO tblestanteprateleira(id_prateleira,id_estante) values(?,?)";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, estantePrateleira.getId_prateleira());
			preparedStatement.setInt(2, estantePrateleira.getId_estante());

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public static boolean editarPrateleiraDaEstanteDAO(EstantePrateleira estantePrateleira) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "UPDATE tblestanteprateleira SET id_estante = ? WHERE id_prateleira = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, estantePrateleira.getId_estante());
			preparedStatement.setInt(2, estantePrateleira.getId_prateleira());

			preparedStatement.executeUpdate();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static boolean excluirEstantePrateleiraDAO(int id_prateleira) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "DELETE FROM tblestanteprateleira WHERE id_prateleira = ?";

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

	public static List<EstantePrateleira> listarEstantePrateleiraDAO() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<EstantePrateleira> listEstantePrateleira = new ArrayList<>();

		String sql = "SELECT ep.id_estante, ep.id_prateleira, p.nome as nomePrateleira, e.nome as nomeEstante FROM tblestanteprateleira as ep INNER JOIN tblprateleira as p INNER JOIN tblestante as e "
				+ "WHERE ep.id_prateleira = p.id_prateleira and ep.id_estante = e.id_estante";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				EstantePrateleira estantePrateleira = new EstantePrateleira();

				estantePrateleira.setId_estante_prateleira(resultSet.getInt("id_prateleira"));
				estantePrateleira.setId_estante(resultSet.getInt("id_estante"));
				estantePrateleira.setId_prateleira(resultSet.getInt("id_prateleira"));

				estantePrateleira.setNomeEstante(resultSet.getString("nomeEstante"));
				estantePrateleira.setNomePrateleira(resultSet.getString("nomePrateleira"));

				listEstantePrateleira.add(estantePrateleira);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listEstantePrateleira;

	}

	public static List<EstantePrateleira> listarEstantePorIdPrateleiraDAO(int id_prateleira) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<EstantePrateleira> listEstantePrateleira = new ArrayList<>();

		String sql = "SELECT ep.*,p.id_prateleira, e.id_estante,e.nome as nomeEstante FROM tblestanteprateleira as ep INNER JOIN tblprateleira as p INNER JOIN tblestante as e "
				+ "WHERE ep.id_estante = e.id_estante and ep.id_prateleira = p.id_prateleira and ep.id_prateleira = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_prateleira);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				EstantePrateleira estantePrateleira = new EstantePrateleira();

				estantePrateleira.setId_estante_prateleira(resultSet.getInt("id_prateleira"));
				estantePrateleira.setId_estante(resultSet.getInt("id_estante"));
				estantePrateleira.setId_prateleira(resultSet.getInt("id_prateleira"));

				estantePrateleira.setNomeEstante(resultSet.getString("nomeEstante"));

				listEstantePrateleira.add(estantePrateleira);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listEstantePrateleira;

	}

	public static List<EstantePrateleira> listarPrateleiraPorEstanteDAO(int id_estante) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<EstantePrateleira> listEstantePrateleira = new ArrayList<>();

		String sql = "SELECT ep.*,p.id_prateleira, e.id_estante FROM tblestanteprateleira as ep INNER JOIN tblprateleira as p INNER JOIN tblestante as e "
				+ "WHERE ep.id_estante = e.id_estante and ep.id_prateleira = p.id_prateleira and ep.id_estante = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_estante);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				EstantePrateleira estantePrateleira = new EstantePrateleira();

				estantePrateleira.setId_estante_prateleira(resultSet.getInt("id_prateleira"));
				estantePrateleira.setId_estante(resultSet.getInt("id_estante"));
				estantePrateleira.setId_prateleira(resultSet.getInt("id_prateleira"));

				listEstantePrateleira.add(estantePrateleira);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listEstantePrateleira;

	}

}
