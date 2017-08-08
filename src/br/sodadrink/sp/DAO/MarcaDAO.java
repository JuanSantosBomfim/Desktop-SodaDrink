package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.Marca;

public class MarcaDAO {

	public static boolean cadastrarMarcaDAO(Marca marca) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "INSERT INTO tblmarca(marca,aprovado) values(?,?)";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, marca.getMarca());
			preparedStatement.setInt(2, 0);

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public static boolean excluirMarcaDAO(int id_marca) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "DELETE FROM tblmarca WHERE id_marca = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_marca);

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static boolean editarMarcaDAO(Marca marca) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "UPDATE tblmarca SET marca = ? WHERE id_marca = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, marca.getMarca());
			preparedStatement.setInt(2, marca.getId_marca());

			preparedStatement.executeUpdate();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static List<Marca> listarMarcaDAO() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Marca> listMarca = new ArrayList<>();

		String sql = "SELECT * FROM tblmarca";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Marca marca = new Marca();
				marca.setId_marca(resultSet.getInt("id_marca"));
				marca.setMarca(resultSet.getString("marca"));

				listMarca.add(marca);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listMarca;

	}

	public static List<Marca> listarMarcaPorIdDAO(int id_marca) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Marca> listMarca = new ArrayList<>();

		String sql = "SELECT * FROM tblmarca WHERE id_marca = ?;";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_marca);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Marca marca = new Marca();
				marca.setId_marca(resultSet.getInt("id_marca"));
				marca.setMarca(resultSet.getString("marca"));

				listMarca.add(marca);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listMarca;

	}

}
