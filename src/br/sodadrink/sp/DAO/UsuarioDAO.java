package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.Usuario;

public class UsuarioDAO {

	public static boolean cadastrarUsuarioDAO(Usuario usuario) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "INSERT INTO tblusuario(id_estado,id_nivel,nome,cpf,dtNascimento,sexo,login,senha,telefone,cep,cidade,logradouro,bairro,numero,email) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, usuario.getId_estado());
			preparedStatement.setInt(2, usuario.getId_nivel());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setString(4, usuario.getCpf());
			preparedStatement.setString(5, usuario.getDtNascimento());
			preparedStatement.setString(6, usuario.getSexo());
			preparedStatement.setString(7, usuario.getLogin());
			preparedStatement.setString(8, usuario.getSenha());
			preparedStatement.setString(9, usuario.getTelefone());
			preparedStatement.setString(10, usuario.getCep());
			preparedStatement.setString(11, usuario.getCidade());
			preparedStatement.setString(12, usuario.getLogradouro());
			preparedStatement.setString(13, usuario.getBairro());
			preparedStatement.setInt(14, usuario.getNumero());
			preparedStatement.setString(15, usuario.getEmail());

			preparedStatement.execute();

		} catch (SQLException e) {

			e.printStackTrace();
			return false;

		}

		return true;
	}

	public static boolean excluirUsuarioDAO(int id_usuario) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "DELETE FROM tblusuario WHERE id_usuario = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_usuario);

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static boolean editarUsuarioDAO(Usuario usuario) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "UPDATE tblusuario SET id_estado = ?, id_nivel = ?, nome = ?, cpf = ?, dtNascimento = ?,sexo = ?,login = ?,senha = ?,telefone = ?,cep = ?,cidade = ?,logradouro = ?,bairro = ?,numero = ?,email = ? WHERE id_usuario = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, usuario.getId_estado());
			preparedStatement.setInt(2, usuario.getId_nivel());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setString(4, usuario.getCpf());
			preparedStatement.setString(5, usuario.getDtNascimento());
			preparedStatement.setString(6, usuario.getSexo());
			preparedStatement.setString(7, usuario.getLogin());
			preparedStatement.setString(8, usuario.getSenha());
			preparedStatement.setString(9, usuario.getTelefone());
			preparedStatement.setString(10, usuario.getCep());
			preparedStatement.setString(11, usuario.getCidade());
			preparedStatement.setString(12, usuario.getLogradouro());
			preparedStatement.setString(13, usuario.getBairro());
			preparedStatement.setInt(14, usuario.getNumero());
			preparedStatement.setString(15, usuario.getEmail());

			preparedStatement.setInt(16, usuario.getId_usuario());

			preparedStatement.executeUpdate();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static List<Usuario> listarUsuarioDAO() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Usuario> listUsuario = new ArrayList<>();

		String sql = "SELECT u.*,n.descricao as nomeNivel FROM " + "tblusuario as u INNER JOIN tblnivel as n WHERE "
				+ "u.id_nivel = n.id_nivel";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Usuario usuario = new Usuario();
				usuario.setId_usuario(resultSet.getInt("id_usuario"));
				usuario.setId_estado(resultSet.getInt("id_estado"));
				usuario.setId_nivel(resultSet.getInt("id_nivel"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setCpf(resultSet.getString("cpf"));
				usuario.setDtNascimento(resultSet.getString("dtNascimento"));
				usuario.setSexo(resultSet.getString("sexo"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setTelefone(resultSet.getString("telefone"));
				usuario.setCep(resultSet.getString("cep"));
				usuario.setCidade(resultSet.getString("cidade"));
				usuario.setLogradouro(resultSet.getString("logradouro"));
				usuario.setBairro(resultSet.getString("bairro"));
				usuario.setNumero(resultSet.getString("numero"));
				usuario.setEmail(resultSet.getString("email"));

				usuario.setNomeNivel(resultSet.getString("nomeNivel"));

				listUsuario.add(usuario);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listUsuario;

	}

	public static List<Usuario> listarUsuarioPorIdDAO(int id_usuario) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Usuario> listUsuario = new ArrayList<>();

		String sql = "SELECT * FROM tblusuario WHERE id_usuario = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_usuario);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Usuario usuario = new Usuario();
				usuario.setId_usuario(resultSet.getInt("id_usuario"));
				usuario.setId_estado(resultSet.getInt("id_estado"));
				usuario.setId_nivel(resultSet.getInt("id_nivel"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setCpf(resultSet.getString("cpf"));
				usuario.setDtNascimento(resultSet.getString("dtNascimento"));
				usuario.setSexo(resultSet.getString("sexo"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setTelefone(resultSet.getString("telefone"));
				usuario.setCep(resultSet.getString("cep"));
				usuario.setCidade(resultSet.getString("cidade"));
				usuario.setLogradouro(resultSet.getString("logradouro"));
				usuario.setBairro(resultSet.getString("bairro"));
				usuario.setNumero(resultSet.getString("numero"));
				usuario.setEmail(resultSet.getString("email"));

				listUsuario.add(usuario);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listUsuario;

	}

	public static List<Usuario> listarUsuarioPorDadosDAO(String nomeCampo, String textoDigitadobusca) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Usuario> listUsuario = new ArrayList<>();

		String sql = "";

		sql = "SELECT u.*,n.descricao as nomeNivel FROM " + "tblusuario as u INNER JOIN tblnivel as n WHERE "
				+ "u.id_nivel = n.id_nivel and " + nomeCampo + " like ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, textoDigitadobusca + "%");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Usuario usuario = new Usuario();
				usuario.setId_usuario(resultSet.getInt("id_usuario"));
				usuario.setId_estado(resultSet.getInt("id_estado"));
				usuario.setId_nivel(resultSet.getInt("id_nivel"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setCpf(resultSet.getString("cpf"));
				usuario.setDtNascimento(resultSet.getString("dtNascimento"));
				usuario.setSexo(resultSet.getString("sexo"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setTelefone(resultSet.getString("telefone"));
				usuario.setCep(resultSet.getString("cep"));
				usuario.setCidade(resultSet.getString("cidade"));
				usuario.setLogradouro(resultSet.getString("logradouro"));
				usuario.setBairro(resultSet.getString("bairro"));
				usuario.setNumero(resultSet.getString("numero"));
				usuario.setEmail(resultSet.getString("email"));

				usuario.setNomeNivel(resultSet.getString("nomeNivel"));

				listUsuario.add(usuario);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listUsuario;

	}

	public static List<Usuario> listarUsuarioPorTodosDadosDAO(String nomeUsuario, String textoDigitadoNome,
			String loginUsuario, String textoDigitadoLogin, String cpfUsuario, String textoDigitadocpf,
			String emailUsuario, String textoDigitadoEmail, String nivelUsuario, String textoDigitadoNivel) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Usuario> listUsuario = new ArrayList<>();

		String sql = "SELECT u.*,n.descricao as nomeNivel FROM " + "tblusuario as u INNER JOIN tblnivel as n WHERE "
				+ "u.id_nivel = n.id_nivel and " + "" + nomeUsuario + " like ? and " + "" + loginUsuario
				+ " like ? and " + "" + cpfUsuario + " like ? and " + "" + emailUsuario + " like ? and " + ""
				+ nivelUsuario + " like ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, textoDigitadoNome + "%");
			preparedStatement.setString(2, textoDigitadoLogin + "%");
			preparedStatement.setString(3, textoDigitadocpf + "%");
			preparedStatement.setString(4, textoDigitadoEmail + "%");
			preparedStatement.setString(5, textoDigitadoNivel + "%");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Usuario usuario = new Usuario();
				usuario.setId_usuario(resultSet.getInt("id_usuario"));
				usuario.setId_estado(resultSet.getInt("id_estado"));
				usuario.setId_nivel(resultSet.getInt("id_nivel"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setCpf(resultSet.getString("cpf"));
				usuario.setDtNascimento(resultSet.getString("dtNascimento"));
				usuario.setSexo(resultSet.getString("sexo"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setTelefone(resultSet.getString("telefone"));
				usuario.setCep(resultSet.getString("cep"));
				usuario.setCidade(resultSet.getString("cidade"));
				usuario.setLogradouro(resultSet.getString("logradouro"));
				usuario.setBairro(resultSet.getString("bairro"));
				usuario.setNumero(resultSet.getString("numero"));
				usuario.setEmail(resultSet.getString("email"));

				usuario.setNomeNivel(resultSet.getString("nomeNivel"));

				listUsuario.add(usuario);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listUsuario;

	}

}
