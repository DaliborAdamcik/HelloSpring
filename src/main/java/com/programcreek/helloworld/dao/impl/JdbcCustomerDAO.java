package com.programcreek.helloworld.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.programcreek.helloworld.dao.*;
import com.programcreek.helloworld.model.*;

public class JdbcCustomerDAO implements EmployeeDAO
{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private Connection getDSConn() throws SQLException {
		Connection cn = dataSource.getConnection();
		cn.prepareStatement("CREATE TABLE IF NOT EXISTS `employee` ( `EID` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `NAME` TEXT NOT NULL, `SURNAME` TEXT NOT NULL )").executeUpdate();
		return cn;
	}
	
	private int LastInsID(Connection cn) throws SQLException {
		try (ResultSet rs = cn.prepareStatement("SELECT last_insert_rowid()").executeQuery();) {
			rs.next();
			return rs.getInt(1);
		}
	}
	
	@Override
	public Employee addEmployee(Employee e) {
		String sql = "INSERT INTO EMPLOYEE " +
				"(NAME, SURNAME) VALUES (?, ?)";

		
		try(Connection conn = getDSConn();
			PreparedStatement ps = conn.prepareStatement(sql);) {
			
			ps.setString(1, e.getFirstName());
			ps.setString(2, e.getLastName());
			ps.executeUpdate();
			e.setId(LastInsID(conn));
			
			return e;
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
			
		} 
	}
	
	@Override
	public List<Employee> getEmployeeList() {
		String sql = "SELECT * FROM EMPLOYEE";
		
		Connection conn = null;
		
		
		
		try {
			conn = getDSConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			List<Employee> employees = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				employees.add(new Employee(
						rs.getInt("EID"),
						rs.getString("NAME"), 
						rs.getString("SURNAME")
				));
			}
			rs.close();
			ps.close();
			return employees;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

}




