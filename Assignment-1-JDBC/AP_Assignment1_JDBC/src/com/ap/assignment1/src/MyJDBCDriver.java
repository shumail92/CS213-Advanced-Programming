package com.ap.assignment1.src;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class MyJDBCDriver implements  Driver{

	static sun.jdbc.odbc.JdbcOdbcDriver c_driver;
	private MyJDBCConnection dbConnector;
	
	public MyJDBCDriver() {
		dbConnector = new MyJDBCConnection();
	}
	
	static {
		sun.jdbc.odbc.JdbcOdbcDriver drv = new sun.jdbc.odbc.JdbcOdbcDriver();
		
		try {
			DriverManager.registerDriver(drv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		dbConnector.dbConn =DriverManager.getConnection(url,info);
		return dbConnector;		
	}

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		// TODO Auto-generated method stub
		return c_driver.acceptsURL(url);
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMajorVersion() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean jdbcCompliant() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
