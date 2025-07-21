package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import view.common.*;
import view.common.admin.*;

public class AdminView extends JFrame {
	// Fields
	private static final long serialVersionUID = 1L;
	
	// Window Parameters
	private static final int WINDOW_X = 800;
	private static final int WINDOW_Y = 600;
	
	private JPanel contentPanel, coursePanel, teacherPanel, studentPanel;
	private ArrayList<JTextField> allFields, detailFields;
	
	private String[] coursesAttributes = {
			"Course Code",
			"Course Name",
			"Teacher Name",
			"Description",
			"Max Capacity"
	};
	private String[] studentAttributes = {
			"Student ID",
			"First Name",
			"Last Name",
			"Email",
			"Password"
	};
	private String[] teacherAttributes = {
			"Teacher ID",
			"First Name",
			"Last Name",
			"Email",
			"Password",
			"Department",
	};
	
	private AdminManageButtons sysManageButtons;
	private TableFramework tableFramework;
	private PanelOperations panelOps;
	
	// Getters & Setters
	
	// Constructors
	/**
	 * Create the Admin specific frames.
	 */
	public AdminView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_X, WINDOW_Y);
		setLayout(new BorderLayout());

		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Enables common view classes for use in this view
		sysManageButtons = new AdminManageButtons();
		tableFramework = new TableFramework();
		panelOps = new PanelOperations();		
		
		// Creates all potential 
		buildCoursesPanel();
		buildStudentPanel();
		buildTeacherPanel();
		
		contentPanel.add(sysManageButtons, BorderLayout.CENTER);
		
		// Pack the contents of the window and display it.
		pack();
		setVisible(true);
	}
	
	// Custom Methods
	/**
	 * Creates and Preps the Courses Panel for toggling on and off
	 */
	private void buildCoursesPanel() {
		coursePanel = new JPanel();
		tableFramework.buildInitialTable(coursePanel, "Course Catalog", coursesAttributes);
	}
	
	/**
	 * Creates and Preps the Students Panel for toggling on and off
	 */
	private void buildStudentPanel() {
		studentPanel = new JPanel();
		tableFramework.buildInitialTable(studentPanel, "Student Information", studentAttributes);
	}
	
	/**
	 * Creates and Preps the Teachers Panel for toggling on and off
	 */
	private void buildTeacherPanel() {
		teacherPanel = new JPanel();
		tableFramework.buildInitialTable(teacherPanel,"Teacher Information", teacherAttributes);
	}
	
	private void buildCoursesConfirmPanel() {
		
	}
	
	public void sceneSwap(JPanel target) {
		panelOps.panelSwap(contentPanel, target);
	}
}