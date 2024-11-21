package com.rath;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

public class DBConfig {

	private static final String URL = "jdbc:postgresql://localhost:5432/practice-db?useSSL=false";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Ramadoss6*";

	static Driver driver = new Driver();
	
	public static Connection getConnection() throws SQLException {
		DriverManager.registerDriver(driver);
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
