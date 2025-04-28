package view;

import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;

public class AuthView extends JFrame {
	public AuthView() {
		final int WINDOW_WIDTH = 200;
		final int WINDOW_HEIGHT = 200;
		
		setTitle("School System");
		
		setSize(359, 268);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Log-Out");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Quit");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel Greeting = new JLabel("New label");
		Greeting.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(Greeting);
		
		JLabel Directions = new JLabel("New label");
		Directions.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(Directions);
		
		setVisible(true);
	}
}
