package my_util;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.*;

public class Validation {
	// Fields
	private Entities course;
	private DBO dbo = new DBO();
	/**
	 * Checks the email and password credentials to the database information
	 * 
	 * {@return true if email and password are found and match
	 * the tuple in the database, or false otherwise}
	 */
	public boolean login(JTextField emailField, JPasswordField passwordField) {
		ArrayList<Supplier<Object>> fieldValidators = new ArrayList<>();
		fieldValidators.add(() -> string(emailField.getText(), "Email"));
		fieldValidators.add(() -> textPassword(passwordField.getPassword()));

		// Will automatically iterate through the list to search for anything that is null
		ArrayList<Object> results = fieldValidators.stream()
				.map(Supplier::get)
				.collect(Collectors.toCollection(ArrayList::new));

		// Searches results to see if anything retains null, indicating a failed field
		if (results.stream().anyMatch(Objects::isNull)) {
			return false;

		} else {
			String email = (String) results.get(0);
			String plaintextPassword = (String) results.get(1);

			return dbo.validatePassword(email, plaintextPassword);
		}
	}
	
	/**
	 * Validates String input from text field
	 *
	 * @return Validated String or empty string if invalid
	 */
	public String string(String input, String fieldName) {
		/**
		 * Complex conditional, basically checking if input is blank OR the result of an
		 * AND comparison between testing an empty, trimmed string and isUpdate is false
		 */
		if (!input.isBlank()) {
			return input.trim();
		} else {
			return null;
		}
	}
	
	/**
	 * Validates Array Character input from Password Field
	 * 
	 * @return String password or null if invalid
	 */
	public String textPassword(char[] charPassword) {
		String stringPassword = new String(charPassword);
		return string(stringPassword, "Password");
	}
}
