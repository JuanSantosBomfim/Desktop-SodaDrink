package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.Estado;

public class EstadoDAO {

	public static List<Estado> listarEstados() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Estado> estados = new ArrayList<>();

		String sql = "SELECT * FROM tblestado;";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Estado estado = new Estado();

				estado.setId_estado(resultSet.getInt("id_estado"));
				estado.setEstado(resultSet.getString("estado"));
				estado.setUf(resultSet.getString("uf"));

				estados.add(estado);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return estados;

	}

}
