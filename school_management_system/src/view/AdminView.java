package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.function.Function;

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
			"Description",
			"Teacher First Name",
			"Teacher Last Name",
			"Max Capacity",
			"Status"
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
	
	public AdminManageButtons sysManageButtons;
	public TableFramework tableFramework;
	public PanelOperations panelOps;
	
	// Getters & Setters
	
	/**
	 * @return the contentPanel
	 */
	public JPanel getContentPanel() {
		return contentPanel;
	}

	/**
	 * @return the coursePanel
	 */
	public JPanel getCoursePanel() {
		return coursePanel;
	}

	/**
	 * @return the teacherPanel
	 */
	public JPanel getTeacherPanel() {
		return teacherPanel;
	}

	/**
	 * @return the studentPanel
	 */
	public JPanel getStudentPanel() {
		return studentPanel;
	}

	/**
	 * @return the allFields
	 */
	public ArrayList<JTextField> getAllFields() {
		return allFields;
	}

	/**
	 * @return the detailFields
	 */
	public ArrayList<JTextField> getDetailFields() {
		return detailFields;
	}

	/**
	 * @return the coursesAttributes
	 */
	public String[] getCoursesAttributes() {
		return coursesAttributes;
	}

	/**
	 * @return the studentAttributes
	 */
	public String[] getStudentAttributes() {
		return studentAttributes;
	}

	/**
	 * @return the teacherAttributes
	 */
	public String[] getTeacherAttributes() {
		return teacherAttributes;
	}

	/**
	 * @return the sysManageButtons
	 */
	public AdminManageButtons getSysManageButtons() {
		return sysManageButtons;
	}

	/**
	 * @return the tableFramework
	 */
	public TableFramework getTableFramework() {
		return tableFramework;
	}

	/**
	 * @return the panelOps
	 */
	public PanelOperations getPanelOps() {
		return panelOps;
	}

	/**
	 * @param contentPanel the contentPanel to set
	 */
	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	/**
	 * @param coursePanel the coursePanel to set
	 */
	public void setCoursePanel(JPanel coursePanel) {
		this.coursePanel = coursePanel;
	}

	/**
	 * @param teacherPanel the teacherPanel to set
	 */
	public void setTeacherPanel(JPanel teacherPanel) {
		this.teacherPanel = teacherPanel;
	}

	/**
	 * @param studentPanel the studentPanel to set
	 */
	public void setStudentPanel(JPanel studentPanel) {
		this.studentPanel = studentPanel;
	}

	/**
	 * @param allFields the allFields to set
	 */
	public void setAllFields(ArrayList<JTextField> allFields) {
		this.allFields = allFields;
	}

	/**
	 * @param detailFields the detailFields to set
	 */
	public void setDetailFields(ArrayList<JTextField> detailFields) {
		this.detailFields = detailFields;
	}

	/**
	 * @param coursesAttributes the coursesAttributes to set
	 */
	public void setCoursesAttributes(String[] coursesAttributes) {
		this.coursesAttributes = coursesAttributes;
	}

	/**
	 * @param studentAttributes the studentAttributes to set
	 */
	public void setStudentAttributes(String[] studentAttributes) {
		this.studentAttributes = studentAttributes;
	}

	/**
	 * @param teacherAttributes the teacherAttributes to set
	 */
	public void setTeacherAttributes(String[] teacherAttributes) {
		this.teacherAttributes = teacherAttributes;
	}

	/**
	 * @param sysManageButtons the sysManageButtons to set
	 */
	public void setSysManageButtons(AdminManageButtons sysManageButtons) {
		this.sysManageButtons = sysManageButtons;
	}

	/**
	 * @param tableFramework the tableFramework to set
	 */
	public void setTableFramework(TableFramework tableFramework) {
		this.tableFramework = tableFramework;
	}

	/**
	 * @param panelOps the panelOps to set
	 */
	public void setPanelOps(PanelOperations panelOps) {
		this.panelOps = panelOps;
	}

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
		add(contentPanel);
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
	
	public void activateAdminPanel(String mode, ArrayList<?> courseList, Function<Object, Object[]> rowMapper) {
		tableFramework.newTable(new ArrayList<>(courseList), rowMapper);

		switch (mode) {
		case "Course":
			sceneSwap(coursePanel);
			break;
		case "Student":
			sceneSwap(studentPanel);
			break;
		case "Teacher":
			sceneSwap(teacherPanel);
			break;
		}
	}
}