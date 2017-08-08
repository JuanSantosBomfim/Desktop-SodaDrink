package br.sodadrink.sp.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

/*DTO = objeto de transferência de dados

DAO = Data Access Object

DCO = objeto configurável dinamicamente */

public class ConnectDataBase {

	static Connection con = null;

	public static Connection openConection() {

		con = null;

		try {

			// Dizer para o java qual classe será
			// usada para connectar com o banco
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//String conexao = "jdbc:mysql://192.168.1.1:3306/dbsdrink?user=sdrink&password=SDrink@fijp2017";
		//String conexao = "jdbc:mysql://10.107.144.19:3306/dbsodadrink?user=root&password=bcd127";
		String conexao = "jdbc:mysql://localhost:3306/dbsodadrink?user=root&password=bcd127";

		try {

			// Dizer pro Connection usar o DriverManager para pegar a conexao
			con = DriverManager.getConnection(conexao);

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return con;

	}

	public static void CloseConnection() {

		if (con != null) {
			try {
				// Fecha a conexao
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
