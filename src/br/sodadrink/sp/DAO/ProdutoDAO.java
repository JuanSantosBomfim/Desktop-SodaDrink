package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.Produto;

public class ProdutoDAO {

	public static boolean cadastrarProdutoDAO(Produto produto) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "INSERT INTO tblproduto(id_categoria,id_fornecedor,id_marca,nome,valorCompra,imagem,peso,codBarra,quantidadeEstoque,porcDesconto,aprovado,valorVenda,quantidadeEngradado,qtdParaOSite) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, produto.getId_categoria());
			preparedStatement.setInt(2, produto.getId_fornecedor());
			preparedStatement.setInt(3, produto.getId_marca());
			preparedStatement.setString(4, produto.getNome());
			preparedStatement.setFloat(5, produto.getValorCompra());
			preparedStatement.setString(6, produto.getImagem());
			preparedStatement.setFloat(7, produto.getPeso());
			preparedStatement.setInt(8, produto.getCodBarra());
			preparedStatement.setInt(9, produto.getQuantidadeEstoque());
			preparedStatement.setInt(10, produto.getPorcDesconto());
			preparedStatement.setInt(11, produto.getAprovado());
			preparedStatement.setFloat(12, produto.getValorVenda());
			preparedStatement.setInt(13, produto.getQuantidadeEngradado());
			preparedStatement.setInt(14, produto.getQtdParaOSite());

			preparedStatement.executeUpdate();

			ConnectDataBase.CloseConnection();

		} catch (SQLException e) {

			e.printStackTrace();
			return false;

		}

		return true;

	}

	public static boolean excluirProdutoDAO(int id_produto) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "DELETE FROM tblproduto WHERE id_produto = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_produto);

			preparedStatement.execute();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static boolean editarProdutoDAO(Produto produto) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;

		String sql = "UPDATE tblproduto SET id_categoria = ?, id_fornecedor = ?, id_marca = ?, nome = ?, valorCompra = ?,peso = ?,codBarra = ?,quantidadeEstoque = ? WHERE id_produto = ?";

		try {

			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, produto.getId_categoria());
			preparedStatement.setInt(2, produto.getId_fornecedor());
			preparedStatement.setInt(3, produto.getId_marca());
			preparedStatement.setString(4, produto.getNome());
			preparedStatement.setFloat(5, produto.getValorCompra());
			preparedStatement.setFloat(6, produto.getPeso());
			preparedStatement.setInt(7, produto.getCodBarra());
			preparedStatement.setInt(8, produto.getQuantidadeEstoque());
			preparedStatement.setInt(9, produto.getId_produto());

			preparedStatement.executeUpdate();

			ConnectDataBase.CloseConnection();

		} catch (SQLException erroSql) {

			System.out.println(erroSql.getMessage());
			return false;

		}

		return true;

	}

	public static List<Produto> listarProdutoDAO() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Produto> listProduto = new ArrayList<>();

		String sql = "SELECT p.*, c.descricao as nomeCategoria,m.marca as nomeMarca,f.razaoSocial as nomeFornecedor FROM "
				+ "tblproduto as p INNER JOIN tblcategoria as c INNER JOIN tblmarca as m INNER JOIN tblfornecedor as f "
				+ "WHERE p.id_categoria = c.id_categoria and p.id_marca = m.id_marca and p.id_fornecedor = f.id_fornecedor";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Produto produto = new Produto();
				produto.setId_produto(resultSet.getInt("id_produto"));
				produto.setId_categoria(resultSet.getInt("id_categoria"));
				produto.setId_fornecedor(resultSet.getInt("id_fornecedor"));
				produto.setId_marca(resultSet.getInt("id_marca"));
				produto.setNome(resultSet.getString("nome"));
				produto.setValorCompra(resultSet.getString("valorCompra"));
				produto.setImagem(resultSet.getString("imagem"));
				produto.setPeso(resultSet.getString("peso"));
				produto.setCodBarra(resultSet.getString("codBarra"));
				produto.setQuantidadeEstoque(resultSet.getString("quantidadeEstoque"));
				produto.setPorcDesconto(resultSet.getInt("porcDesconto"));
				produto.setAprovado(resultSet.getInt("aprovado"));

				produto.setValorVenda(resultSet.getInt("valorVenda"));
				produto.setQuantidadeEngradado(resultSet.getInt("quantidadeEngradado"));
				produto.setQtdParaOSite(resultSet.getInt("qtdParaOSite"));

				produto.setNomeCategoria(resultSet.getString("nomeCategoria"));
				produto.setNomeMarca(resultSet.getString("nomeMarca"));
				produto.setNomeFornecedor(resultSet.getString("nomeFornecedor"));

				listProduto.add(produto);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listProduto;

	}

	public static List<Produto> listarProdutoPorIdDAO(int id_Produto) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Produto> listProduto = new ArrayList<>();

		String sql = "SELECT * FROM tblproduto WHERE id_produto = ?";

		try {
			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setInt(1, id_Produto);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Produto produto = new Produto();
				produto.setId_produto(resultSet.getInt("id_produto"));
				produto.setId_categoria(resultSet.getInt("id_categoria"));
				produto.setId_fornecedor(resultSet.getInt("id_fornecedor"));
				produto.setId_marca(resultSet.getInt("id_marca"));
				produto.setNome(resultSet.getString("nome"));
				produto.setValorCompra(resultSet.getString("valorCompra"));
				produto.setImagem(resultSet.getString("imagem"));
				produto.setPeso(resultSet.getString("peso"));
				produto.setCodBarra(resultSet.getString("codBarra"));
				produto.setQuantidadeEstoque(resultSet.getString("quantidadeEstoque"));
				produto.setPorcDesconto(resultSet.getInt("porcDesconto"));
				produto.setAprovado(resultSet.getInt("aprovado"));

				produto.setValorVenda(resultSet.getInt("valorVenda"));
				produto.setQuantidadeEngradado(resultSet.getInt("quantidadeEngradado"));
				produto.setQtdParaOSite(resultSet.getInt("qtdParaOSite"));

				listProduto.add(produto);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listProduto;

	}

	public static List<Produto> listarProdutoPorDadosDAO(String nomeCampo, String textoDigitadobusca) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Produto> listProduto = new ArrayList<>();

		String sql = "";

		sql = "SELECT p.*, c.descricao as nomeCategoria, m.marca as nomeMarca, f.razaoSocial as nomeFornecedor FROM "
				+ "tblproduto as p INNER JOIN tblcategoria as c INNER JOIN tblmarca as m INNER JOIN tblfornecedor as f "
				+ "WHERE p.id_categoria = c.id_categoria and p.id_marca = m.id_marca and p.id_fornecedor = f.id_fornecedor and "
				+ nomeCampo + " like ?";

		try {
			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, textoDigitadobusca + "%");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Produto produto = new Produto();
				produto.setId_produto(resultSet.getInt("id_produto"));
				produto.setId_categoria(resultSet.getInt("id_categoria"));
				produto.setId_fornecedor(resultSet.getInt("id_fornecedor"));
				produto.setId_marca(resultSet.getInt("id_marca"));
				produto.setNome(resultSet.getString("nome"));
				produto.setValorCompra(resultSet.getString("valorCompra"));
				produto.setImagem(resultSet.getString("imagem"));
				produto.setPeso(resultSet.getString("peso"));
				produto.setCodBarra(resultSet.getString("codBarra"));
				produto.setQuantidadeEstoque(resultSet.getString("quantidadeEstoque"));
				produto.setPorcDesconto(resultSet.getInt("porcDesconto"));
				produto.setAprovado(resultSet.getInt("aprovado"));

				produto.setValorVenda(resultSet.getInt("valorVenda"));
				produto.setQuantidadeEngradado(resultSet.getInt("quantidadeEngradado"));
				produto.setQtdParaOSite(resultSet.getInt("qtdParaOSite"));

				produto.setNomeCategoria(resultSet.getString("nomeCategoria"));
				produto.setNomeMarca(resultSet.getString("nomeMarca"));
				produto.setNomeFornecedor(resultSet.getString("nomeFornecedor"));

				listProduto.add(produto);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listProduto;

	}

	public static List<Produto> listarProdutoPorTodosDadosDAO(String nomeProduto, String textoDigitadonomeProduto,
			String codBarra, String textoDigitadocodBarra, String categoria, String textoDigitadocategoria,
			String marca, String textoDigitadomarca, String fornecedor, String textoDigitadofornecedor) {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Produto> listProduto = new ArrayList<>();

		String sql = "SELECT p.*, c.descricao as nomeCategoria, m.marca as nomeMarca, f.razaoSocial as nomeFornecedor "
				+ "FROM tblproduto as p INNER JOIN tblcategoria as c INNER JOIN "
				+ "tblmarca as m INNER JOIN tblfornecedor as f " + "WHERE p.id_categoria = c.id_categoria and "
				+ "p.id_marca = m.id_marca and " + "p.id_fornecedor = f.id_fornecedor and " + "p." + nomeProduto
				+ " like ? and " + "p." + codBarra + " like ? and " + "c." + categoria + " like ? and " + "m." + marca
				+ " like ? and " + "f." + fornecedor + " like ?";

		try {
			preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setString(1, textoDigitadonomeProduto + "%");
			preparedStatement.setString(2, textoDigitadocodBarra + "%");
			preparedStatement.setString(3, textoDigitadocategoria + "%");
			preparedStatement.setString(4, textoDigitadomarca + "%");
			preparedStatement.setString(5, textoDigitadofornecedor + "%");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Produto produto = new Produto();
				produto.setId_produto(resultSet.getInt("id_produto"));
				produto.setId_categoria(resultSet.getInt("id_categoria"));
				produto.setId_fornecedor(resultSet.getInt("id_fornecedor"));
				produto.setId_marca(resultSet.getInt("id_marca"));
				produto.setNome(resultSet.getString("nome"));
				produto.setValorCompra(resultSet.getString("valorCompra"));
				produto.setImagem(resultSet.getString("imagem"));
				produto.setPeso(resultSet.getString("peso"));
				produto.setCodBarra(resultSet.getString("codBarra"));
				produto.setQuantidadeEstoque(resultSet.getString("quantidadeEstoque"));
				produto.setPorcDesconto(resultSet.getInt("porcDesconto"));
				produto.setAprovado(resultSet.getInt("aprovado"));

				produto.setValorVenda(resultSet.getInt("valorVenda"));
				produto.setQuantidadeEngradado(resultSet.getInt("quantidadeEngradado"));
				produto.setQtdParaOSite(resultSet.getInt("qtdParaOSite"));

				produto.setNomeCategoria(resultSet.getString("nomeCategoria"));
				produto.setNomeMarca(resultSet.getString("nomeMarca"));
				produto.setNomeFornecedor(resultSet.getString("nomeFornecedor"));

				listProduto.add(produto);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listProduto;

	}

	public static List<Produto> listarProdutoForaDeEstoqueDAO() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		List<Produto> listProduto = new ArrayList<>();

		String sql = "SELECT distinct t.* FROM tblproduto as t WHERE t.id_produto not in(SELECT id_produto FROM tblestoque)";

		try {

			preparedStatement = conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Produto produto = new Produto();
				produto.setId_produto(resultSet.getInt("id_produto"));
				produto.setNome(resultSet.getString("nome"));

				listProduto.add(produto);

			}

			ConnectDataBase.CloseConnection();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return listProduto;

	}

}
