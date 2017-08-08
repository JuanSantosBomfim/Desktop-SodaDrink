package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.StatusPedido;

public class StatusPedidoDAO {

	public static List<StatusPedido> listarStatusDAO() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		String sql = "SELECT * FROM tblstatus";

		List<StatusPedido> listStatus = new ArrayList<>();

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				StatusPedido pedido = new StatusPedido();

				pedido.setId_status(resultSet.getInt("id_status"));
				pedido.setStatus(resultSet.getString("status"));

				listStatus.add(pedido);

			}

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listStatus;

	}

}
