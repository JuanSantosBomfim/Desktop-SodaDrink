package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.Venda;

public class PedidoDAO {

	public static List<Venda> listarPedidoDAO() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Venda> listVenda = new ArrayList<>();

		String sql = "SELECT v.*,f.formaPagamento as nomeFormaPagamento,cj.nomeFantasia as nomeCliente, s.status as nomeStatus FROM "
				+ "tblpedidovenda as v INNER JOIN tblformapagamento as f INNER JOIN "
				+ "tblclientejuridico as cj INNER JOIN tblstatus as s "
				+ "WHERE v.id_forma_pagamento = f.id_forma_pagamento and v.id_cliente = cj.id_cliente and v.id_status = s.id_status and v.id_status <> 3";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Venda venda = new Venda();
				venda.setId_pedido(resultSet.getInt("id_pedido_venda"));
				venda.setId_forma_pagamento(resultSet.getInt("id_forma_pagamento"));
				venda.setId_cliente(resultSet.getInt("id_cliente"));
				venda.setId_status(resultSet.getInt("id_status"));

				venda.setDtPedido(resultSet.getString("dtPedido"));
				venda.setDtEnvio(resultSet.getString("dtEnvio"));
				venda.setDtEntrega(resultSet.getString("dtEntrega"));
				venda.setValorTotal(resultSet.getString("valorTotal"));

				venda.setNomeCliente(resultSet.getString("nomeCliente"));
				venda.setNomeFormaPagamento(resultSet.getString("nomeFormaPagamento"));
				venda.setNomeStatus(resultSet.getString("nomeStatus"));

				listVenda.add(venda);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listVenda;

	}

	public static boolean editarStatusDataPedido(int id_status, String dtEnvio, int id_pedido) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "UPDATE tblpedidovenda SET id_status = ? , dtEnvio = ? WHERE id_pedido_venda = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_status);
			preparedStatement.setString(2, dtEnvio);
			preparedStatement.setInt(3, id_pedido);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}

		return true;

	}

	public static boolean editarStatusDataEntregaAtual(int id_status, String dtEnvio, String dateFormat,
			int id_pedido) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "UPDATE tblpedidovenda SET id_status = ? , dtEnvio = ?, dtEntrega = ? WHERE id_pedido_venda = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_status);
			preparedStatement.setString(2, dtEnvio);
			preparedStatement.setString(3, dateFormat);
			preparedStatement.setInt(4, id_pedido);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}

		return true;

	}

	public static List<Venda> listarPedidoPorBuscaDAO(String nomeCampo, String textoDigitadobusca) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Venda> listVenda = new ArrayList<>();

		String sql = "SELECT v.*,f.formaPagamento as nomeFormaPagamento,cj.nomeFantasia as nomeCliente, s.status as nomeStatus FROM "
				+ "tblpedidovenda as v INNER JOIN tblformapagamento as f INNER JOIN "
				+ "tblclientejuridico as cj INNER JOIN tblstatus as s "
				+ "WHERE v.id_forma_pagamento = f.id_forma_pagamento and v.id_cliente = cj.id_cliente and v.id_status = s.id_status and v.id_status <> 3 and "
				+ nomeCampo + " like ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, textoDigitadobusca + "%");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Venda venda = new Venda();
				venda.setId_pedido(resultSet.getInt("id_pedido_venda"));
				venda.setId_forma_pagamento(resultSet.getInt("id_forma_pagamento"));
				venda.setId_cliente(resultSet.getInt("id_cliente"));
				venda.setId_status(resultSet.getInt("id_status"));

				venda.setDtPedido(resultSet.getString("dtPedido"));
				venda.setDtEnvio(resultSet.getString("dtEnvio"));
				venda.setDtEntrega(resultSet.getString("dtEntrega"));
				venda.setValorTotal(resultSet.getString("valorTotal"));

				venda.setNomeCliente(resultSet.getString("nomeCliente"));
				venda.setNomeFormaPagamento(resultSet.getString("nomeFormaPagamento"));
				venda.setNomeStatus(resultSet.getString("nomeStatus"));

				listVenda.add(venda);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listVenda;

	}

	public static List<Venda> listarPedidoPorBuscaTodasDAO(String textoDigitadobusca, String nomeFormaPagamento,
			String dtInicial, String dtFim) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Venda> listVenda = new ArrayList<>();

		String sql = "SELECT v.*,f.formaPagamento as nomeFormaPagamento,cj.nomeFantasia as nomeCliente, s.status as nomeStatus FROM "
				+ "tblpedidovenda as v INNER JOIN tblformapagamento as f INNER JOIN "
				+ "tblclientejuridico as cj INNER JOIN tblstatus as s "
				+ "WHERE v.id_forma_pagamento = f.id_forma_pagamento and v.id_cliente = cj.id_cliente and v.id_status = s.id_status and v.id_status <> 3 and nomeFantasia like ? "
				+ "and f.formaPagamento like ? and v.dtPedido BETWEEN date(?) AND date(?)";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, textoDigitadobusca + "%");
			preparedStatement.setString(2, nomeFormaPagamento);
			preparedStatement.setString(3, dtInicial);
			preparedStatement.setString(4, dtFim);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Venda venda = new Venda();
				venda.setId_pedido(resultSet.getInt("id_pedido_venda"));
				venda.setId_forma_pagamento(resultSet.getInt("id_forma_pagamento"));
				venda.setId_cliente(resultSet.getInt("id_cliente"));
				venda.setId_status(resultSet.getInt("id_status"));

				venda.setDtPedido(resultSet.getString("dtPedido"));
				venda.setDtEnvio(resultSet.getString("dtEnvio"));
				venda.setDtEntrega(resultSet.getString("dtEntrega"));
				venda.setValorTotal(resultSet.getString("valorTotal"));

				venda.setNomeCliente(resultSet.getString("nomeCliente"));
				venda.setNomeFormaPagamento(resultSet.getString("nomeFormaPagamento"));
				venda.setNomeStatus(resultSet.getString("nomeStatus"));

				listVenda.add(venda);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listVenda;

	}

}
