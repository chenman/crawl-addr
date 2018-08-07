package util.db;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionPool {
	private String driverClass = "com.mysql.cj.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://localhost:3306/addrdb?useSSL=false&serverTimezone=GMT";
	private String user = "chenman";
	private String password = "123456";
	
	private int maxIdleTime = 60;
	private int maxPoolSize = 20;
	private int minPoolSize = 10;
	private int InitialPoolSize = 10;
	private int maxStatements = 60;
	private ComboPooledDataSource cpd;

	private static ConnectionPool pool;

	private ConnectionPool() {
		try {
			cpd = new ComboPooledDataSource();
			cpd.setDriverClass(driverClass);
			cpd.setJdbcUrl(dbUrl);
			cpd.setUser(user);
			cpd.setPassword(password);
			cpd.setMaxIdleTime(maxIdleTime);
			cpd.setMaxPoolSize(maxPoolSize);
			cpd.setMinPoolSize(minPoolSize);
			cpd.setInitialPoolSize(InitialPoolSize);
			cpd.setMaxStatements(maxStatements);
		} catch (PropertyVetoException e) {

		}
	}

	private ConnectionPool(String driverClass, String dbUrl, String user, String password)
			throws PropertyVetoException {
		cpd = new ComboPooledDataSource();
		cpd.setDriverClass(driverClass);
		cpd.setJdbcUrl(dbUrl);
		cpd.setUser(user);
		cpd.setPassword(password);
		cpd.setMaxIdleTime(maxIdleTime);
		cpd.setMaxPoolSize(maxPoolSize);
		cpd.setMinPoolSize(minPoolSize);
	}

	public static ConnectionPool getConnectionPoolInstance() {
		if (pool == null) {
			pool = new ConnectionPool();
		}
		return pool;
	}

	public synchronized Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			conn = cpd.getConnection();

		} catch (Exception ex) {
			throw new SQLException(ex.getMessage());
		}
		return conn;
	}
}