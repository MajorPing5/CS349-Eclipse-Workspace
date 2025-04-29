package view;

import javax.swing.*;
import java.awt.*;

public class AuthView extends JFrame {
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
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 10));
		
		JLabel Greeting = new JLabel("Welcome to the School System!");
		Greeting.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(Greeting, BorderLayout.NORTH);
		
		JLabel Directions = new JLabel("Log-In using your Email and Password Credentials");
		panel.add(Directions, BorderLayout.SOUTH);
		Directions.setHorizontalAlignment(SwingConstants.CENTER);
		Directions.setAlignmentX(0.5f);
		
		JPanel loginField = new JPanel();
		FlowLayout flowLayout = (FlowLayout) loginField.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(20);
		getContentPane().add(loginField, BorderLayout.CENTER);
		
		Box authFields = Box.createVerticalBox();
		loginField.add(authFields);
		
		JLabel emailPrompt = new JLabel("Email: ");
		authFields.add(emailPrompt);
		emailPrompt.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(150, 20));
		textField.setToolTipText("Must include @example.com");
		authFields.add(textField);
		
		JLabel passwordPrompt = new JLabel("Password: ");
		authFields.add(passwordPrompt);
		passwordPrompt.setHorizontalAlignment(SwingConstants.CENTER);
		
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(150, 20));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		authFields.add(passwordField);
		
		JLabel errorField = new JLabel("ERROR: Email and/or Password is Incorrect!");
		errorField.setForeground(Color.RED);
		errorField.setOpaque(true);
		errorField.setFont(new Font("Tahoma", Font.BOLD, 11));
		errorField.setVisible(false);
		errorField.setEnabled(false);
		authFields.add(errorField);
		
		setVisible(true);
	}
}
