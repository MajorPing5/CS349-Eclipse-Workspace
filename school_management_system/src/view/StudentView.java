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
	private JMenu mmFile;
	private JMenuItem mmLogOutItem;
	private JMenuItem mmQuitItem;
	
	public StudentView() {
		componentSections = new LinkedHashMap<>();
		initializeView();
	}
	
	private void createView() {
		buildMenu();
		buildFrame();
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
		
		JMenuBar menuBar = new JMenuBar();
		getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mmFile = new JMenu("File");
		JMenuItem mmLogOutItem = new JMenuItem("Log-Out");
		JMenuItem mmQuitItem = new JMenuItem("Quit");
		
		
		menuBar.add(mmFile);
		mmFile.add(mmLogOutItem);
		mmFile.addSeparator();
		mmFile.add(mmQuitItem);		
	}
	
	
	/**
	 * Initialize the contents of the full window.
	 */
	private void initialize() {
		setTitle("Course Enrollment");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildMenu();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}
	
	
	private void buildFrame() {
		
	}
	
	// Segment for direct controller access, for pure clarity
	/**
	 * Direct references Log-Out Button located within the menu bar
	 * @return LogOutButton
	 */
	public JButton getLogOutButton() {
		return (JButton) componentSections.get("menu").get(menuBar.getComponentIndex(mmLogOutItem));
	}
	
	public JButton getQuitButton() {
		return (JButton) componentSections.get("menu").get(menuBar.getComponentIndex(mmQuitItem));
	}
}
