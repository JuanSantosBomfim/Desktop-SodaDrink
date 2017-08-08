package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.Fornecedor;

public class FornecedorDAO {

	// importe os java.sql
	public static boolean cadastrarFornecedorDAO(Fornecedor fornecedor) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "INSERT INTO tblfornecedor(id_estado,cep,cidade,logradouro,bairro,numero,email,telefone,razaoSocial,nomeFantasia,cnpj,inscricaoEstadual) values(?,?,?,?,?,?,?,?,?,?,?,?)";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, fornecedor.getIdEstado());
			preparedStatement.setString(2, fornecedor.getCep());
			preparedStatement.setString(3, fornecedor.getCidade());
			preparedStatement.setString(4, fornecedor.getLogradouro());
			preparedStatement.setString(5, fornecedor.getBairro());
			preparedStatement.setInt(6, fornecedor.getNumero());
			preparedStatement.setString(7, fornecedor.getEmail());
			preparedStatement.setString(8, fornecedor.getTelefone());
			preparedStatement.setString(9, fornecedor.getRazaoSocial());
			preparedStatement.setString(10, fornecedor.getNomeFantasia());
			preparedStatement.setString(11, fornecedor.getCnpj());
			preparedStatement.setString(12, fornecedor.getInscricaoEstadual());

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static boolean excluirFornecedorDAO(int id_fornecedor) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "DELETE FROM tblfornecedor WHERE id_fornecedor = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_fornecedor);

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static boolean editarFornecedorDAO(Fornecedor fornecedor) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "UPDATE tblfornecedor SET id_estado = ?, cep = ?, cidade = ?, logradouro = ?, bairro = ?,numero = ?,email = ?,telefone = ?,razaoSocial = ?,nomeFantasia = ?,cnpj = ?,inscricaoEstadual = ? WHERE id_fornecedor = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, fornecedor.getIdEstado());
			preparedStatement.setString(2, fornecedor.getCep());
			preparedStatement.setString(3, fornecedor.getCidade());
			preparedStatement.setString(4, fornecedor.getLogradouro());
			preparedStatement.setString(5, fornecedor.getBairro());
			preparedStatement.setInt(6, fornecedor.getNumero());
			preparedStatement.setString(7, fornecedor.getEmail());
			preparedStatement.setString(8, fornecedor.getTelefone());
			preparedStatement.setString(9, fornecedor.getRazaoSocial());
			preparedStatement.setString(10, fornecedor.getNomeFantasia());
			preparedStatement.setString(11, fornecedor.getCnpj());
			preparedStatement.setString(12, fornecedor.getInscricaoEstadual());
			preparedStatement.setInt(13, fornecedor.getIdFornecedor());

			preparedStatement.executeUpdate();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static List<Fornecedor> listarFornecedorDAO() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Fornecedor> listFornecedor = new ArrayList<>();

		String sql = "SELECT * FROM tblfornecedor;";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setIdFornecedor(resultSet.getInt("id_fornecedor"));
				fornecedor.setIdEstado(resultSet.getInt("id_estado"));
				fornecedor.setCep(resultSet.getString("cep"));
				fornecedor.setCidade(resultSet.getString("cidade"));
				fornecedor.setLogradouro(resultSet.getString("logradouro"));
				fornecedor.setBairro(resultSet.getString("bairro"));
				fornecedor.setNumero(resultSet.getInt("numero"));
				fornecedor.setEmail(resultSet.getString("email"));
				fornecedor.setTelefone(resultSet.getString("telefone"));
				fornecedor.setRazaoSocial(resultSet.getString("razaoSocial"));
				fornecedor.setNomeFantasia(resultSet.getString("nomeFantasia"));
				fornecedor.setCnpj(resultSet.getString("cnpj"));
				// mudar inscricaoEstadual
				fornecedor.setInscricaoEstadual(resultSet.getString("inscricaoEstadual"));

				listFornecedor.add(fornecedor);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listFornecedor;

	}

	public static List<Fornecedor> listarFornecedorPorId(int id_fornecedor) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Fornecedor> listFornecedor = new ArrayList<>();

		String sql = "SELECT * FROM tblfornecedor WHERE id_fornecedor = ?";

		try {
			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_fornecedor);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setIdFornecedor(resultSet.getInt("id_fornecedor"));
				fornecedor.setIdEstado(resultSet.getInt("id_estado"));
				fornecedor.setCep(resultSet.getString("cep"));
				fornecedor.setCidade(resultSet.getString("cidade"));
				fornecedor.setLogradouro(resultSet.getString("logradouro"));
				fornecedor.setBairro(resultSet.getString("bairro"));
				fornecedor.setNumero(resultSet.getInt("numero"));
				fornecedor.setEmail(resultSet.getString("email"));
				fornecedor.setTelefone(resultSet.getString("telefone"));
				fornecedor.setRazaoSocial(resultSet.getString("razaoSocial"));
				fornecedor.setNomeFantasia(resultSet.getString("nomeFantasia"));
				fornecedor.setCnpj(resultSet.getString("cnpj"));
				fornecedor.setInscricaoEstadual(resultSet.getString("inscricaoEstadual"));

				listFornecedor.add(fornecedor);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listFornecedor;

	}

	public static List<Fornecedor> listarFornecedorPorDadosDAO(String nomeCampo, String textoDigitadobusca) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Fornecedor> listFornecedor = new ArrayList<>();

		String sql = "SELECT * FROM tblfornecedor WHERE " + nomeCampo + " like ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			preparedStatement.setString(1, textoDigitadobusca + "%");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setIdFornecedor(resultSet.getInt("id_fornecedor"));
				fornecedor.setIdEstado(resultSet.getInt("id_estado"));
				fornecedor.setCep(resultSet.getString("cep"));
				fornecedor.setCidade(resultSet.getString("cidade"));
				fornecedor.setLogradouro(resultSet.getString("logradouro"));
				fornecedor.setBairro(resultSet.getString("bairro"));
				fornecedor.setNumero(resultSet.getInt("numero"));
				fornecedor.setEmail(resultSet.getString("email"));
				fornecedor.setTelefone(resultSet.getString("telefone"));
				fornecedor.setRazaoSocial(resultSet.getString("razaoSocial"));
				fornecedor.setNomeFantasia(resultSet.getString("nomeFantasia"));
				fornecedor.setCnpj(resultSet.getString("cnpj"));
				fornecedor.setInscricaoEstadual(resultSet.getString("inscricaoEstadual"));

				listFornecedor.add(fornecedor);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return listFornecedor;

	}

	public static List<Fornecedor> listarFornecedorPorTodosDadosDAO(String razaoSocial, String textoDigitadoRazaoSocial,
			String nomeFantasia, String textoDigitadoNomeFantasia, String cnpj, String textoDigitadoCnpj,
			String inscricaoEstadual, String textoDigitadoinscricaoEstadual) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Fornecedor> listFornecedor = new ArrayList<>();

		String sql = "SELECT * FROM tblfornecedor WHERE " + razaoSocial + " like ? and " + "" + nomeFantasia
				+ " like ? and " + "" + cnpj + " like ? and " + "" + inscricaoEstadual + " like ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			preparedStatement.setString(1, textoDigitadoRazaoSocial + "%");
			preparedStatement.setString(2, textoDigitadoNomeFantasia + "%");
			preparedStatement.setString(3, textoDigitadoCnpj + "%");
			preparedStatement.setString(4, textoDigitadoinscricaoEstadual + "%");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setIdFornecedor(resultSet.getInt("id_fornecedor"));
				fornecedor.setIdEstado(resultSet.getInt("id_estado"));
				fornecedor.setCep(resultSet.getString("cep"));
				fornecedor.setCidade(resultSet.getString("cidade"));
				fornecedor.setLogradouro(resultSet.getString("logradouro"));
				fornecedor.setBairro(resultSet.getString("bairro"));
				fornecedor.setNumero(resultSet.getInt("numero"));
				fornecedor.setEmail(resultSet.getString("email"));
				fornecedor.setTelefone(resultSet.getString("telefone"));
				fornecedor.setRazaoSocial(resultSet.getString("razaoSocial"));
				fornecedor.setNomeFantasia(resultSet.getString("nomeFantasia"));
				fornecedor.setCnpj(resultSet.getString("cnpj"));
				fornecedor.setInscricaoEstadual(resultSet.getString("inscricaoEstadual"));

				listFornecedor.add(fornecedor);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return listFornecedor;

	}

}
