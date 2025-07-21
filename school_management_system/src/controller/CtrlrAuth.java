package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import model.AuthModel;
import view.AdminView;
import view.StudentView;
import view.TeacherView;
import view.AuthView;

public class CtrlrAuth {
	
	private AuthView view;
	private AuthModel model;
	
	public CtrlrAuth(AuthView view, AuthModel model) {
		this.view = view;
		this.model = model;
		
		view.getBtnLogin().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (loginValidator()) {					
					String role = model.retrieveRole(
							view.getEmailField().getText(),
							new String(view.getPasswordField().getPassword()));							
					view.dispose();
					
					switch (role) {
						case "admin":
							AdminView adminView = new AdminView();
							break;
						case "student":
							StudentView studentView = new StudentView();
							break;
						case "teacher":
							TeacherView teacherView = new TeacherView();
							break;
					}
				} else {
					view.getErrorField().setVisible(true);
				}
			}
		});
	}
	
	/**
	 * Checks the email and password credentials to the database information
	 * 
	 * {@return true if email and password are found and match
	 * the tuple in the database, or false otherwise}
	 */
	private boolean loginValidator() {
		ArrayList<Supplier<Object>> fieldValidators = new ArrayList<>(Arrays.asList(
				() -> validateString(view.getEmailField().getText(), "Email"),
				() -> validateTextPassword(view.getPasswordField().getPassword())));

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

			return model.validatePassword(email, plaintextPassword);
		}
	}
	
	/**
	 * Validates String input from text field
	 *
	 * @return Validated String or empty string if invalid
	 */
	private String validateString(String input, String fieldName) {
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
	private String validateTextPassword(char[] charPassword) {
		String stringPassword = new String(charPassword);
		return validateString(stringPassword, "Password");
	}
}