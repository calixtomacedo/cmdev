package br.com.cmdev.dbconnections.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Connection {
	
	private Context context;
	private DataSource[] dataSource;
	
	private static Connection instance = null;

	private Connection(String[] jndi) throws Exception {
		try {
			
			this.context = new InitialContext();
			this.dataSource = new DataSource[jndi.length];
			for (int i = 0; i < jndi.length; i++) {
				this.dataSource[i] = ((DataSource) this.context.lookup("java:jdbc/".concat(jndi[i])));
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public static Connection getInstance(String[] jndi) throws Exception {
		if(instance == null) {
			return new Connection(jndi);
		}
		return instance;
	}
	

}
