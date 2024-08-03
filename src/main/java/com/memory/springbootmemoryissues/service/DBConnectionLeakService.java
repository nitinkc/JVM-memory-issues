package com.memory.springbootmemoryissues.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;

@Service
@Slf4j
public class DBConnectionLeakService {

	public Connection getConnection() throws SQLException {
		final String DB_URL = "jdbc:postgresql://localhost:5432/mydb?currentSchema=test";
		final String USER = "dbuser";
		final String PASSWORD = "a";
		return DriverManager.getConnection(DB_URL, USER, PASSWORD);
	}

	//Not a Spring managed connection.
	public void leakConnection() {
		// 1. Register the Class (Get Driver name)
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;// 2. Create Connection
		PreparedStatement statement = null;// 3. Create Query
		ResultSet resultSet = null;// 4. Execute statement


		try { //Not using Try with Resources as the connection need to be left open to simulate the DB connection leak.
			connection = getConnection();// 2. Create Connection
			statement = connection.prepareStatement("SELECT * FROM  " + "student" + " limit 10");// 3. Create Query
			resultSet = statement.executeQuery(); // 4. Execute statement
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				//connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//leaks DB connections.
	public void start() {
		while(true) {
			log.info("Leaked connection created");
			leakConnection();
		}
	}
}
