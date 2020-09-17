package com.cognixia.jumplus.dollarsbank.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The database connection manager for this app.
 * @author Lori White
 * @version v1 (09/14/2020)
 */
public class ConnectionManagerProperties {
	
	private static Connection connection = null;
	
	/**
	 * Makes a connection to a sql server that is specified in the config.properties file. 
	 */
	private static void makeConnection() {
		
		Properties props = new Properties();
		
		/*
		 * try { props.load(new
		 * FileInputStream("src/main/resources/config.properties"));
		 * 
		 * } catch (IOException e) {
		 * 
		 * try { props.load(new FileInputStream("./config.properties"));
		 * 
		 * } catch (IOException r) {
		 * 
		 * r.printStackTrace(); } }
		 */
		
		/*
		 * String url = props.getProperty("url"); String username =
		 * props.getProperty("username"); String password =
		 * props.getProperty("password");
		 */
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
	}
	/**
	 * Retrieves the current sql server connection.
	 * @return Connection - the current sql server connection
	 */
	public static Connection getConnection() {

		if (connection == null) { // if connection not established, create it before returning it
			makeConnection();
		}

		return connection;
	}
	/**
	 * Tests the integration of this class's getConnection function with the sql server provided in config.properties.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		Connection conn = ConnectionManagerProperties.getConnection();
		System.out.println("Connection made");
		
		try {
			conn.close();
			System.out.println("Closed connection");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
