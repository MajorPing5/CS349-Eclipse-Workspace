package view;

import javax.swing.*;
import java.awt.*;

public class AuthView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;
	
	public AuthView() {
		setTitle("School System");
		
		setSize(600, 400);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create the menu bar
		JMenuBar menuBar = new JMenuBar();
		
		// Create the File menu option
		JMenu mmFile = new JMenu("File");
		
		// Create the options within the File menu
		JMenuItem mmLogOutItem = new JMenuItem("Log-Out");
		JMenuItem mmQuitItem = new JMenuItem("Quit");
		
		setJMenuBar(menuBar);
		
		menuBar.add(mmFile);
		mmFile.add(mmLogOutItem);
		mmFile.add(mmQuitItem);
		getContentPane().setLayout(new BorderLayout(0, 20));
		
		JPanel welcomePanel = new JPanel();
		getContentPane().add(welcomePanel, BorderLayout.NORTH);
		welcomePanel.setLayout(new BorderLayout(0, 10));
		
		JLabel Greeting = new JLabel("Welcome to the School System!");
		JLabel Directions = new JLabel("Log-In using your Email and Password Credentials");
		JPanel loginFields = new JPanel();
		Box authFields = Box.createVerticalBox();
		JLabel emailPrompt = new JLabel("Email: ");
		JLabel passwordPrompt = new JLabel("Password: ");
		JLabel errorField = new JLabel("ERROR: Email and/or Password is Incorrect!");
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(150, 20));
		textField.setToolTipText("Must include @example.com");
		
		Greeting.setHorizontalAlignment(SwingConstants.CENTER);
		Directions.setHorizontalAlignment(SwingConstants.CENTER);
		emailPrompt.setHorizontalAlignment(SwingConstants.RIGHT);
		Directions.setAlignmentX(0.5f);
		
		welcomePanel.add(Greeting, BorderLayout.NORTH);
		welcomePanel.add(Directions, BorderLayout.SOUTH);
		getContentPane().add(loginFields, BorderLayout.CENTER);
		loginFields.add(authFields);
		authFields.add(emailPrompt);
		
		authFields.add(textField);
		
		authFields.add(passwordPrompt);
		passwordPrompt.setHorizontalAlignment(SwingConstants.CENTER);
		
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(150, 20));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		authFields.add(passwordField);
		
		errorField.setForeground(Color.RED);
		errorField.setOpaque(true);
		errorField.setFont(new Font("Tahoma", Font.BOLD, 11));
		errorField.setVisible(false);
		errorField.setEnabled(false);
		authFields.add(errorField);
		
		JPanel Buttons = new JPanel();
		getContentPane().add(Buttons, BorderLayout.SOUTH);
		
		JButton btnLogin = new JButton("Log-In");
		Buttons.add(btnLogin);
		
		setVisible(true);
	}
}
