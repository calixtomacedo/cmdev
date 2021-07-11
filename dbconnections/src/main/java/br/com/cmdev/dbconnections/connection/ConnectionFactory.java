package br.com.cmdev.dbconnections.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private Connection connection;
	
	public Connection getMySQLConnection() {
		
		// MySql
		String driverName = "com.mysql.cj.jdbc.Driver"; 
		String url = "jdbc:mysql://";
		String serverName = "localhost:3306/";
		String dataBase = "cmdev-db?";
		String timezone = "useTimezone=true&serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8&useSSL=false&";
		String autoCommit = "relaxAutoCommit=true";
		String userName = "cmdev";
		String password = "cmdev";
		
		try {
			
			Class.forName(driverName);
			connection = DriverManager.getConnection(url.concat(serverName).concat(dataBase).concat(timezone).concat(autoCommit), userName, password);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	
	public Connection getOracleConnection() {
		
		// Oracle
		String driverName = "oracle.jdbc.driver.OracleDriver"; 
		String url = "jdbc:oracle:thin:@//";
		String serverName = "localhost:1521/";
		String dataBase = "XEPDB1";
		String userName = "ALURA";
		String password = "Alura";
		
		try {
			
			Class.forName(driverName);
			connection = DriverManager.getConnection(url.concat(serverName).concat(dataBase), userName, password);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
