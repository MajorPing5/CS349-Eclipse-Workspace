package my_util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import my_util.Database.ParamSetter;
import my_util.Database.ResultProcessor;

public class Database {
	//private Connection connect;
		private static final String DBURL = "jdbc:mysql:///school_management_db";
		private static final String USERNAME = "root";
		private static final String PASSWORD = "password";
		
		public static Connection getConnection() throws SQLException{
			return DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
		}
		
		public <T> T executeQuery(String sqlQuery, ParamSetter paramSetter, ResultProcessor<T> resultSetProcessor) {
			
			try(
					// help with automatic close of the database connection
					Connection con = getConnection();
					PreparedStatement stm = con.prepareStatement(sqlQuery);
			)
			{
				// Apply parameters on the PreparedStatement if provided
				if(paramSetter != null) {
					paramSetter.setParams(stm);
				}
				
				if(resultSetProcessor != null) {
					ResultSet results = stm.executeQuery(); // Execute SELECT query
					return resultSetProcessor.process(results);
				}
				// Otherwise it's an INSERT, UPDATE, or DELETE operation
				else {
					int rows = stm.executeUpdate();
					
					// Return True if the number of rows affected is more than 0
					return (T) Boolean.valueOf(rows > 0);
				}
			}
			
			catch(SQLException e) {
				System.out.println(e.getMessage());
				return (T) Boolean.valueOf(false);
			}
		}

		public interface ParamSetter{
			// Method to set parameters on the PreparedStatement
			// This will be implemented in the Model Class
			void setParams(PreparedStatement stm) throws SQLException;
		}
		
		public interface ResultProcessor<T>{
			
			// Method to process a ResultSet into the desired return type
			T process(ResultSet result) throws SQLException;
		}


	}

