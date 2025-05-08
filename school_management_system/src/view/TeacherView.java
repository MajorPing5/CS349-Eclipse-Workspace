package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class TeacherView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Dashboard;
	public TeacherView() {
		setTitle("User Dashboard");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Dashboard = new JPanel();
		Dashboard.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(Dashboard);
		Dashboard.setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		Dashboard.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWelcome = new JLabel("placeholder");
		lblWelcome.setFont(new Font("Times New Roman", Font.BOLD, 16));
		topPanel.add(lblWelcome, BorderLayout.WEST);
		
		JButton btnLogout = new JButton("Logout");
		topPanel.add(btnLogout, BorderLayout.EAST);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		JPanel coursesPanel = createButtonPanel("Courses", "View all information regarding your assigned courses & students", this::handleCourses);
		JPanel inboxPanel	= createButtonPanel("Inbox", "Check your in-system messages from students", this::handleInbox);
		
		splitPane.setLeftComponent(coursesPanel);
		splitPane.setRightComponent(inboxPanel);
		
		Dashboard.add(splitPane, BorderLayout.CENTER);
		SwingUtilities.invokeLater(() -> {
			splitPane.setDividerLocation(0.5);
			splitPane.setResizeWeight(0.5);
		});
	}
	
	private JPanel createButtonPanel(String buttonText, String tooltipText, ActionListener handler) {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JButton button = new JButton(buttonText);
		button.setPreferredSize(new Dimension(100,50));
		button.setFont(new Font("Times New Roman", Font.PLAIN, 18));;
		button.setToolTipText(tooltipText);
		button.addActionListener(handler);
		
		GridBagConstraints gbc_button = new GridBagConstraints();
		panel.add(button, gbc_button);		
		return panel;
	}
	
	private void handleCourses(ActionEvent e) {
		System.out.println("Courses button clicked");
	}
	
	private void handleInbox(ActionEvent e) {
		System.out.println("Inbox button clicked");
	}

}
