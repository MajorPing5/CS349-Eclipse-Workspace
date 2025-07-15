package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

public class StudentView extends JFrame {
	private Map<String, List<Component>> componentSections;
	private JMenuBar menuBar;
	private JMenuItem mmLogOutItem;
	private JMenuItem mmQuitItem;
	private JPanel panel;
	
	public StudentView() {
		componentSections = new LinkedHashMap<>();
		
		setTitle("Course Enrollment");
		setLayout(new BorderLayout());
		initializeView();
	}
	
	/**
	 * Initialize the contents of the full window.
	 */	
	public void createView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildMenu();
		buildFrame();
		assembleComponents();
	}

	/**
	 * Pre-Defines all segments of the full view at declaration
	 */
	private void initializeView() {
		componentSections.put("menu",  new ArrayList<>());
		componentSections.put("frame", new ArrayList<>());
	}
	
	/**
	 * Initializes all Menu related components in one segment
	 */
	private void buildMenu() {
		List<Component> menuComponents = componentSections.get("menu");
		
		JMenu mmFile = new JMenu("File");
		JMenuItem mmLogOutItem = new JMenuItem("Log-Out");
		JMenuItem mmQuitItem = new JMenuItem("Quit");
		
		mmFile.add(mmLogOutItem);
		mmFile.addSeparator();
		mmFile.add(mmQuitItem);
		
		menuComponents.add(mmFile);
	}

	private void buildFrame() {
		buildTabbedPaneSection();
	}
	
	private void buildTabbedPaneSection() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);		
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		courseSchedule();
		tabbedPane.addTab("Edit Schedule", panel);
	}
	
	private Object courseSchedule() {
		// TODO Method to properly create course schedule tab is incomplete
		return courseSchedule();
	}
	
	private void assembleComponents() {
		componentSections.get("menu").forEach(comp ->
				add((JMenuBar) comp, BorderLayout.NORTH));
		
		componentSections.get("frame").forEach(comp ->
				add((JTabbedPane) comp, BorderLayout.CENTER));
		
		pack();
		
	}
	
	// Segment for direct controller access, for pure clarity
	/**
	 * Direct references Log-Out Button located within the menu bar
	 * @return LogOutButton
	 */
	public JButton getLogOutButton() {
		return (JButton) componentSections
				.get("menu")
				.get(
						menuBar.getComponentIndex(mmLogOutItem));
	}
	
	public JButton getQuitButton() {
		return (JButton) componentSections
				.get("menu")
				.get(
						menuBar.getComponentIndex(mmQuitItem));
	}
	
	// TODO Create remaining getters for controller access
}
