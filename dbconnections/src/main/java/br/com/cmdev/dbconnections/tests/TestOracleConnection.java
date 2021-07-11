package br.com.cmdev.dbconnections.tests;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.cmdev.dbconnections.connection.ConnectionFactory;

/**
 * @author Calixto Macedo
 */
public class TestOracleConnection {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory connection = new ConnectionFactory();

		// Creating a new connection
		Connection com = connection.getOracleConnection();

		System.out.println(com.getMetaData().getDatabaseProductName().concat(" ==> ".concat(com.getMetaData().getUserName())));
	}

	
}
