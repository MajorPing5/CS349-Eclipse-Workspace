package view;

import javax.swing.*;
import java.awt.*;

public class AuthView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JLabel greeting, directions, emailPrompt, passwordPrompt, errorField;
	
	private JPanel welcomePanel, loginFields, buttons;
	
	public AuthView() {
		setTitle("School System");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(0, 20));
		
		// EDIT Refactor code below ln 26 for easier reading & clearer execution
		// NOTE Reference ContactController and ContactView.java for further insight on how
		
		JPanel welcomePanel = new JPanel();
		welcomePanel.setLayout(new BorderLayout(0, 10));
		getContentPane().add(welcomePanel, BorderLayout.NORTH);
		
		JLabel greeting = new JLabel("Welcome to the School System!");
		JLabel directions = new JLabel("Log-In using your Email and Password Credentials");
		greeting.setHorizontalAlignment(SwingConstants.CENTER);
		directions.setHorizontalAlignment(SwingConstants.CENTER);
		directions.setAlignmentX(0.5f);
		welcomePanel.add(greeting, BorderLayout.NORTH);
		welcomePanel.add(directions, BorderLayout.SOUTH);

		JPanel loginFields = new JPanel();
		Box authFields = Box.createVerticalBox();
		JLabel emailPrompt = new JLabel("Email: ");
		JLabel passwordPrompt = new JLabel("Password: ");
		
		emailField = new JTextField();
		emailField.setPreferredSize(new Dimension(150, 20));
		emailField.setToolTipText("Must include @example.com");
		
		emailPrompt.setHorizontalAlignment(SwingConstants.RIGHT);
		
		getContentPane().add(loginFields, BorderLayout.CENTER);
		loginFields.add(authFields);
		authFields.add(emailPrompt);
		
		authFields.add(emailField);
		
		authFields.add(passwordPrompt);
		passwordPrompt.setHorizontalAlignment(SwingConstants.CENTER);
		
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(150, 20));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		authFields.add(passwordField);
		
		errorField = new JLabel("ERROR: Email and/or Password is Incorrect!");
		errorField.setForeground(Color.RED);
		errorField.setFont(new Font("Tahoma", Font.BOLD, 11));
		errorField.setVisible(false);
		authFields.add(errorField);
		
		JPanel buttons = new JPanel();
		getContentPane().add(buttons, BorderLayout.SOUTH);
		
		btnLogin = new JButton("Log-In");
		buttons.add(btnLogin);
	}
	
	/**
	 * @return the emailField
	 */
	public JTextField getEmailField() {
		return emailField;
	}

	/**
	 * @param emailField the emailField to set
	 */
	public void setEmailField(JTextField emailField) {
		this.emailField = emailField;
	}

	/**
	 * @return the passwordField
	 */
	public JPasswordField getPasswordField() {
		return passwordField;
	}

	/**
	 * @param passwordField the passwordField to set
	 */
	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	/**
	 * @return the btnLogin
	 */
	public JButton getBtnLogin() {
		return btnLogin;
	}

	/**
	 * @return the errorField
	 */
	public JLabel getErrorField() {
		return errorField;
	}

	/**
	 * @return the entire window
	 */
	public Container getWindow() {
		return getContentPane();
	}
}
