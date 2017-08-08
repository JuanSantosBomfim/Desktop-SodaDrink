package br.sodadrink.sp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sodadrink.sp.dbutils.ConnectDataBase;
import br.sodadrink.sp.model.FormaPagamento;

public class FormaPagamentoDAO {

	public static List<FormaPagamento> listarFormaPagamentoDAO() {

		// cria uma conecxão com o banco
		Connection conexao = ConnectDataBase.openConection();
		// execulta a query mysql
		PreparedStatement preparedStatement;
		// pega os valores que o sql retorna
		ResultSet resultSet;

		String sql = "SELECT * FROM tblformapagamento";

		List<FormaPagamento> listaFormaPagamento = new ArrayList<>();

		try {

			preparedStatement = conexao.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				FormaPagamento formaPagamento = new FormaPagamento();

				formaPagamento.setId_forma_pagamento(resultSet.getInt("id_forma_pagamento"));
				formaPagamento.setFormaPagamento(resultSet.getString("formaPagamento"));

				listaFormaPagamento.add(formaPagamento);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaFormaPagamento;

	}

}
