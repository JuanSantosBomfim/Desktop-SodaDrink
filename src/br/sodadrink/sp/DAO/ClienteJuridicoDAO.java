package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.ClienteJuridico;

public class ClienteJuridicoDAO {

	public static List<ClienteJuridico> listarClientesDAO() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		String sql = "SELECT * FROM tblclientejuridico";

		List<ClienteJuridico> listClienteJuridico = new ArrayList<>();

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ClienteJuridico clienteJuridico = new ClienteJuridico();

				clienteJuridico.setId_cliente(resultSet.getInt("id_cliente"));
				clienteJuridico.setId_estado(resultSet.getInt("id_estado"));
				clienteJuridico.setRazaoSocial(resultSet.getString("razaoSocial"));
				clienteJuridico.setNomeFantasia(resultSet.getString("nomeFantasia"));
				clienteJuridico.setInscricaoEstadual(resultSet.getString("inscricaoEstadual"));
				clienteJuridico.setCnpj(resultSet.getString("cnpj"));
				clienteJuridico.setDtCadastro(resultSet.getString("dtCadastro"));
				clienteJuridico.setSenha(resultSet.getString("senha"));
				clienteJuridico.setEmail(resultSet.getString("email"));
				clienteJuridico.setTelefone(resultSet.getString("telefone"));
				clienteJuridico.setCep(resultSet.getString("cep"));
				clienteJuridico.setCidade(resultSet.getString("cidade"));
				clienteJuridico.setBairro(resultSet.getString("bairro"));
				clienteJuridico.setCep(resultSet.getString("cep"));
				clienteJuridico.setLogradouro(resultSet.getString("logradouro"));
				clienteJuridico.setNumero(resultSet.getInt("numero"));

				listClienteJuridico.add(clienteJuridico);

			}

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return listClienteJuridico;

	}

	public static List<ClienteJuridico> listarClientesPorIdDAO(int id_cliente) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		String sql = "SELECT * FROM tblclientejuridico WHERE id_cliente = ?";

		List<ClienteJuridico> listClienteJuridico = new ArrayList<>();

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_cliente);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ClienteJuridico clienteJuridico = new ClienteJuridico();

				clienteJuridico.setId_cliente(resultSet.getInt("id_cliente"));
				clienteJuridico.setId_estado(resultSet.getInt("id_estado"));
				clienteJuridico.setRazaoSocial(resultSet.getString("razaoSocial"));
				clienteJuridico.setNomeFantasia(resultSet.getString("nomeFantasia"));
				clienteJuridico.setInscricaoEstadual(resultSet.getString("inscricaoEstadual"));
				clienteJuridico.setCnpj(resultSet.getString("cnpj"));
				clienteJuridico.setDtCadastro(resultSet.getString("dtCadastro"));
				clienteJuridico.setSenha(resultSet.getString("senha"));
				clienteJuridico.setEmail(resultSet.getString("email"));
				clienteJuridico.setTelefone(resultSet.getString("telefone"));
				clienteJuridico.setCep(resultSet.getString("cep"));
				clienteJuridico.setCidade(resultSet.getString("cidade"));
				clienteJuridico.setBairro(resultSet.getString("bairro"));
				clienteJuridico.setCep(resultSet.getString("cep"));
				clienteJuridico.setLogradouro(resultSet.getString("logradouro"));
				clienteJuridico.setNumero(resultSet.getInt("numero"));

				listClienteJuridico.add(clienteJuridico);

			}

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return listClienteJuridico;

	}

	public static List<ClienteJuridico> listarClientesPorDadosDAO(String nomeCampo, String textoDigitadobusca) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		String sql = "SELECT * FROM tblclientejuridico WHERE " + nomeCampo + " like ?";

		List<ClienteJuridico> listClienteJuridico = new ArrayList<>();

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, textoDigitadobusca + "%");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ClienteJuridico clienteJuridico = new ClienteJuridico();

				clienteJuridico.setId_cliente(resultSet.getInt("id_cliente"));
				clienteJuridico.setId_estado(resultSet.getInt("id_estado"));
				clienteJuridico.setRazaoSocial(resultSet.getString("razaoSocial"));
				clienteJuridico.setNomeFantasia(resultSet.getString("nomeFantasia"));
				clienteJuridico.setInscricaoEstadual(resultSet.getString("inscricaoEstadual"));
				clienteJuridico.setCnpj(resultSet.getString("cnpj"));
				clienteJuridico.setDtCadastro(resultSet.getString("dtCadastro"));
				clienteJuridico.setSenha(resultSet.getString("senha"));
				clienteJuridico.setEmail(resultSet.getString("email"));
				clienteJuridico.setTelefone(resultSet.getString("telefone"));
				clienteJuridico.setCep(resultSet.getString("cep"));
				clienteJuridico.setCidade(resultSet.getString("cidade"));
				clienteJuridico.setBairro(resultSet.getString("bairro"));
				clienteJuridico.setCep(resultSet.getString("cep"));
				clienteJuridico.setLogradouro(resultSet.getString("logradouro"));
				clienteJuridico.setNumero(resultSet.getInt("numero"));

				listClienteJuridico.add(clienteJuridico);

			}

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return listClienteJuridico;

	}

	public static List<ClienteJuridico> listarClientesPorTodosDadosDAO(String razaoSocial,
			String textoDigitadorazaoSocial, String nomeFantasia, String textoDigitadonomeFantasia, String cnpj,
			String textoDigitadocnpj) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		String sql = "SELECT * FROM tblclientejuridico WHERE " + razaoSocial + " like ? and " + "" + nomeFantasia
				+ " like ? and " + "" + cnpj + " like ?";

		List<ClienteJuridico> listClienteJuridico = new ArrayList<>();

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, textoDigitadorazaoSocial + "%");
			preparedStatement.setString(2, textoDigitadonomeFantasia + "%");
			preparedStatement.setString(3, textoDigitadocnpj + "%");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ClienteJuridico clienteJuridico = new ClienteJuridico();

				clienteJuridico.setId_cliente(resultSet.getInt("id_cliente"));
				clienteJuridico.setId_estado(resultSet.getInt("id_estado"));
				clienteJuridico.setRazaoSocial(resultSet.getString("razaoSocial"));
				clienteJuridico.setNomeFantasia(resultSet.getString("nomeFantasia"));
				clienteJuridico.setInscricaoEstadual(resultSet.getString("inscricaoEstadual"));
				clienteJuridico.setCnpj(resultSet.getString("cnpj"));
				clienteJuridico.setDtCadastro(resultSet.getString("dtCadastro"));
				clienteJuridico.setSenha(resultSet.getString("senha"));
				clienteJuridico.setEmail(resultSet.getString("email"));
				clienteJuridico.setTelefone(resultSet.getString("telefone"));
				clienteJuridico.setCep(resultSet.getString("cep"));
				clienteJuridico.setCidade(resultSet.getString("cidade"));
				clienteJuridico.setBairro(resultSet.getString("bairro"));
				clienteJuridico.setCep(resultSet.getString("cep"));
				clienteJuridico.setLogradouro(resultSet.getString("logradouro"));
				clienteJuridico.setNumero(resultSet.getInt("numero"));

				listClienteJuridico.add(clienteJuridico);

			}

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return listClienteJuridico;

	}

}
