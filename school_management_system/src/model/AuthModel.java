package model;

import my_util.*;

public class AuthModel {
	public Boolean validateEmail(String email) {
		String query = "SELECT email FROM tb_user WHERE email=?";
		
		return new Database().executeQuery(
				query,
				null,
				results -> {
					if (results.next()) {
						String storedEmail = results.getString("email");
						String inputEmail = email;
						return storedEmail.equals(inputEmail);
					}
					return false;
				}
				);
	}
	
	public Boolean validatePassword(String email, String password) {
		String query = "SELECT password FROM tb_user WHERE email =?";

		return new Database().executeQuery(
				query,
				parameters -> parameters.setString(1, email),
				results -> {
					if (results.next()) {
						String storedHash = results.getString("password");
						String inputHash = Security.hashPassword(password);
						return storedHash.equals(inputHash);
					}
					return false;
				}
				);
	}
	
	public String retrieveRole(String email, String password) {
		String query = "SELECT role_type FROM tb_user WHERE email=? AND password=?;";
		
		return new Database().executeQuery(
				query,
				null,
				results -> {
					if (results.next()) {
						String role = results.getString("role_type");
						return role;
					}
					return "admin";
		});
	}
}
