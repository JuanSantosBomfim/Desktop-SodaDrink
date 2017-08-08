package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.Usuario;

public class LoginDAO {

	public static List<Usuario> fazerLogin(String txtlogin, String txtsenha) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		String dbLogin, dbSenha;

		String sql = "SELECT * FROM tblusuario";

		List<Usuario> listDadosUsuario = new ArrayList<>();

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery(sql);

			Usuario usuario = new Usuario();

			usuario.setConseguiuLogar(false);
			listDadosUsuario.add(usuario);

			while (resultSet.next()) {

				dbLogin = resultSet.getString("login");
				dbSenha = resultSet.getString("senha");

				int id_usuario = resultSet.getInt("id_usuario");

				if (dbLogin.equals(txtlogin) && dbSenha.equals(txtsenha)) {

					usuario.setId_usuario(id_usuario);
					usuario.setConseguiuLogar(true);
					listDadosUsuario.add(usuario);
				}

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listDadosUsuario;

	}

}
