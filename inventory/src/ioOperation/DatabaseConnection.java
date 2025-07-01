package ioOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String DBURL = "jdbc:mysql:///inventory_management";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";

	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
	}

	@SuppressWarnings("unchecked")
	public <T> T executeQuery(String sqlQuery, ParamSetter paramSetter, ResultProcessor<T> resultSetProcessor) {
		try (
			// help with automatic close of the database connection
			Connection con = getConnection();
			PreparedStatement stm = con.prepareStatement(sqlQuery);
		) {
			// Apply parameters on the PreparedStatement if provided
			if(paramSetter != null) {
				paramSetter.setParams(stm);
			}

			if (resultSetProcessor != null) {
				ResultSet results = stm.executeQuery(); // Execute SELECT query
				return resultSetProcessor.process(results);
			} else { // Otherwise it's an INSERT, UPDATE, or DELETE operation
				int rows = stm.executeUpdate();

				// Return True if the number of rows affected is more than 0
				return (T) Boolean.valueOf(rows > 0);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return (T) Boolean.valueOf(false);
		}
	}

	public interface ParamSetter {
		/**
		 *  Sets parameters on the PreparedStatement
		 *  This will be implemented in the Model Class
		 * @param stm SQL Query to be executed
		 * @throws SQLException
		 */
		void setParams (PreparedStatement stm) throws SQLException;
	}

	public interface ResultProcessor<T> {
		/**
		 *  Method to process a ResultSet into the desired return type
		 * @param result
		 * @return
		 * @throws SQLException
		 */
		T process (ResultSet result) throws SQLException;
	}
}