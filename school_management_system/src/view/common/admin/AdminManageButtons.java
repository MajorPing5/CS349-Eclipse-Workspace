/**
 * 
 */
package view.common.admin;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

/**
 * Panel Explicitly for System Management Scene Direction
 */
public class AdminManageButtons extends JPanel{
	private JButton btnStudents, btnTeachers, btnCourses;
	
	private JPanel centerPanel, adminButtons;
	
	private ArrayList<JButton> buttonProp;
	
	// Getters and Setters
	/**
	 * @return the btnStudents
	 */
	public JButton getBtnStudents() {
		return btnStudents;
	}

	/**
	 * @return the btnTeachers
	 */
	public JButton getBtnTeachers() {
		return btnTeachers;
	}

	/**
	 * @return the btnCourses
	 */
	public JButton getBtnCourses() {
		return btnCourses;
	}

	/**
	 * @return the centerPanel
	 */
	public JPanel getCenterPanel() {
		return centerPanel;
	}
	
	// Constructor 
	public AdminManageButtons() {
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2, 1));
		
		buildLabel();
		buildBtns();
		
		add(centerPanel, BorderLayout.CENTER);
	}
	
	// Custom Methods
	private void buildLabel() {
		String directions = "What system information do you want to modify?";
		centerPanel.add(new JLabel(directions));
	}
	
	private void buildBtns() {
		adminButtons = new JPanel(new GridLayout(1,3,10,10));
		
		btnStudents = new JButton("Students");
		btnTeachers = new JButton("Teachers");
		btnCourses = new JButton("Courses");
		
		buttonProp = new ArrayList<>(Arrays.asList(btnStudents, btnTeachers, btnCourses));
		
		buttonProp.forEach(button -> adminButtons.add(button));
		centerPanel.add(adminButtons);
	}
}
