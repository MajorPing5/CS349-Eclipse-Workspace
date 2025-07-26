package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TeacherView extends JFrame {
	// TODO Convert TeacherView into CommonView to permit this being the splash page for teachers and students post-login
	private static final long serialVersionUID = 1L;
	
	private JButton btnCourse, btnInbox;
	
	private ArrayList<JButton> dashButtons;
	
	private JPanel Dashboard;
	
	public TeacherView(String fName, String lName) {
		setTitle("User Dashboard");
		setSize(1440, 1024);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Dashboard = new JPanel();
		Dashboard.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(Dashboard);
		Dashboard.setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		Dashboard.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWelcome = new JLabel("Welcome " + fName + " " + lName +"!");
		lblWelcome.setFont(new Font("Times New Roman", Font.BOLD, 16));
		topPanel.add(lblWelcome, BorderLayout.WEST);
		
		JButton btnLogout = new JButton("Logout");
		topPanel.add(btnLogout, BorderLayout.EAST);
		
		JPanel dashboardButtons = new JPanel(new GridLayout(1,2,10,10));
		
		btnCourse = createButton("Courses", "View all information regarding your assigned courses & students");
		btnInbox = createButton("Inbox", "Check your in-system messages from students");
		
		dashButtons = new ArrayList<>(Arrays.asList(btnCourse, btnInbox));
		
		Dashboard.add(dashboardButtons, BorderLayout.CENTER);
		setVisible(true);
	}
	
	private JButton createButton(String buttonText, String tooltipText) {
		JButton button = new JButton(buttonText);
		button.setPreferredSize(new Dimension(100,50));
		button.setFont(new Font("Times New Roman", Font.PLAIN, 18));;
		button.setToolTipText(tooltipText);
		
		return button;
	}
}
