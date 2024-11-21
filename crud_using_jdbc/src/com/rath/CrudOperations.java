package com.rath;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

public class CrudOperations {

	 HikariDataSource hds =  new HikariDataSource(ConnecionPoolingConfig.getConfig());

	private static final String call = "CALL";
	
	private static final String CREATE_EMP = "INSERT INTO EMPLOYEES (name, email, country, salary) VALUES (?,?,?,?)";

	private static final String SELECT_ALL_EMP = "SELECT * FROM EMPLOYEES";

	private static final String SELECT_EMP = "SELECT * FROM EMPLOYEES WHERE ID = ?";

	private static final String DELETE_EMP = "DELETE FROM EMPLOYEES WHERE ID = ?";

	private static final String UPDATE_EMP = "UPDATE EMPLOYEES SET NAME = ?, EMAIL = ? , COUNTRY = ? , SALARY = ? WHERE ID = ?";
	
	private static final String PROCEDURE = "{CALL sample_procedure(?)}";

	public void createEmp(String name, String email, String country, double salary) {
		try (Connection connection = hds.getConnection();
				PreparedStatement ps = connection.prepareStatement(CREATE_EMP);) {
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, country);
			ps.setDouble(4, salary);
			ps.execute();

		} catch (SQLException e) {
			DBConfig.printSQLException(e);
		}
	}

	public List<String> selectEmp() {
		try (Connection connection = hds.getConnection();
				Statement ps = connection.createStatement();) {
			ResultSet rs = ps.executeQuery(SELECT_ALL_EMP);
			List<String> info = new ArrayList<>();
			while (rs.next()) {
				Integer i = rs.getInt("id");
				String n = rs.getString("name");
				String e = rs.getString("email");
				String c = rs.getString("country");
				Double s = rs.getDouble("salary");
				info.add(i + ", " + n + ", " + e + ", " + c + ", " + s);
			}
			return info;
		} catch (SQLException e) {
			DBConfig.printSQLException(e);
		}
		return null;
	}
	
	public String selectEmp(Integer id) {
		try (Connection connection =hds.getConnection();
				CallableStatement ps = connection.prepareCall(call+" sample_procedure(?)")) {
			ps.setInt(1, id);
//			ps.setString(2, name);
//			ps.setDouble(3, salary);
			 ps.execute();
//			while (rs.next()) {
//				Integer i = rs.getInt("id");
//				String n = rs.getString("name");
//				String e = rs.getString("email");
//				String c = rs.getString("country");
//				Double s = rs.getDouble("salary");
//				return ("selected employee : "+ i + ", " + n + ", " + e + ", " + c + ", " + s);
//			}
		} catch (SQLException e) {
			DBConfig.printSQLException(e);
		}
		return null;
	}
	
	public String deleteEmp(Integer id) {
		try (Connection connection = hds.getConnection();
				PreparedStatement ps = connection.prepareStatement(DELETE_EMP);) {
			ps.setInt(1, id);
			 ps.executeUpdate();
			
				return ("Deleted employee with id :"+id);
		} catch (SQLException e) {
			DBConfig.printSQLException(e);
			return null;
		}
	}
	
	public void updateEmp(int id, String name, String email, String country, double salary) {
		try (Connection connection =hds.getConnection();
				PreparedStatement ps = connection.prepareStatement(UPDATE_EMP);) {
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, country);
			ps.setDouble(4, salary);
			ps.setInt(5, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			DBConfig.printSQLException(e);
		}
	}
}
