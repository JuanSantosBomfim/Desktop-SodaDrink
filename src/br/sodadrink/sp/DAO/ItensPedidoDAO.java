package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.ItensPedido;

public class ItensPedidoDAO {

	public static List<ItensPedido> listarTotalVendasDoProdutoDAO(int id_produto) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<ItensPedido> listItensPedido = new ArrayList<>();

		String sql = "SELECT SUM(quantidade) as quantidadeTotalVenda,ip.*,p.nome FROM tblitenspedido as ip INNER JOIN tblproduto as p "
				+ "WHERE ip.id_produto = p.id_produto and ip.id_produto = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_produto);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ItensPedido itensPedido = new ItensPedido();
				itensPedido.setId_item_pedido(resultSet.getInt("id_item_pedido"));
				itensPedido.setId_produto(resultSet.getInt("id_produto"));
				itensPedido.setId_pedido(resultSet.getInt("id_pedido"));
				itensPedido.setQuantidade(resultSet.getInt("quantidade"));

				itensPedido.setQuantidadeTotalVenda(resultSet.getInt("quantidadeTotalVenda"));

				listItensPedido.add(itensPedido);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listItensPedido;

	}

}
