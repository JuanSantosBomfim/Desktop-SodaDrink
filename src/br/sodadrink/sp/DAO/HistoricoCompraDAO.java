package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.Compra;

public class HistoricoCompraDAO {

	public static boolean cadastrarHistoricoCompraDAO(Compra compra) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "INSERT INTO tblpedidocompra(fornecedor,produto,usuario,dtPedido) values(?,?,?,NOW())";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, compra.getFornecedor());
			preparedStatement.setString(2, compra.getProduto());
			preparedStatement.setString(3, compra.getUsuario());

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {
			e.printStackTrace();

			return false;

		}

		return true;

	}

	public static List<Compra> listarHistoricoCompraDAO() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Compra> listHistoricoCompras = new ArrayList<>();

		String sql = "SELECT * FROM tblpedidocompra";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Compra compra = new Compra();
				compra.setId_pedido(resultSet.getInt("id_pedido"));
				compra.setFornecedor(resultSet.getString("fornecedor"));
				compra.setProduto(resultSet.getString("produto"));
				compra.setUsuario(resultSet.getString("usuario"));
				compra.setDtPedido(resultSet.getString("dtPedido"));

				listHistoricoCompras.add(compra);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listHistoricoCompras;

	}

}
