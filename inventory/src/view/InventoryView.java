package view;

import javax.swing.*;
import javax.swing.table.*;

import model.InventoryItem;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class InventoryView extends JFrame {
	// Window Size
	private int WINDOW_X = 800;
	private int WINDOW_Y = 600;
	
	// Specific Fields
	private JButton btnAdd, btnClear, btnUpdate, btnDelete, btnSubmit, btnBack;
	private JTable catalog;
	private DefaultTableModel tableModel;
	private JTextField txtID, txtName, txtQuantity, txtPrice;
	
	// Field groupings by panel
	private final JPanel greetPanel = new JPanel();
	private final JPanel invPanel = new JPanel();
	private final JPanel btnPanel = new JPanel();
	
	// Organization System
	private final List<UISection> uiSections = new ArrayList<>();
	private final List<ComponentGroup> compRegistry = new ArrayList<>();
	
	// TODO JTable or JList for inventory display
	// TODO JTextField for input
	// TODO JButton components for actions
	// TODO Error & Success Dialog Boxes
	
	// Region Assignment Helper Class
	private static class GroupRegion {
		final JPanel group;
		final String region;
		GroupRegion(JPanel group, String region) {
			this.group = group;
			this.region = region;
		}
	}
	
	/**
	 * Class Constructor
	 */
	public InventoryView() {
		configMainPanel();
		initializeFieldGroups();
		setupFieldGroups();
		assembleView();
	}
	
	private void configMainPanel() {
		// Creates an appropriate title
		setTitle("Inventory Management");
		
		// Directly specifies the action necessary for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Creates a top-level BorderLayout manager
		setLayout(new BorderLayout());	
		
		// Defines the window size
		setSize(WINDOW_X, WINDOW_Y);
	}
	
	private void initializeFieldGroups() {
		fieldGroups.addAll(Arrays.asList(
				new GroupRegion(greetPanel, BorderLayout.NORTH),
				new GroupRegion(invPanel, BorderLayout.CENTER),
				new GroupRegion(btnPanel, BorderLayout.SOUTH)
				));
	}
	
	private void setupFieldGroups() {
		buildGreet();
		buildCatalog();
		buildConfirmBtns();
	}
	
	private void assembleView() {
		fieldGroups.forEach(groupRegion -> {
			mainPanel.add(groupRegion.group, groupRegion.region);
		});
		
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	private void buildGreet() {
		JLabel txtGreet = new JLabel("Welcome to the Inventory Management System");
		greetPanel.add(txtGreet);
	}

	private void buildCatalog() {
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] { "ID", "Name", "Quantity", "Price" });
		catalog = new JTable(tableModel);
		add(new JScrollPane(catalog));
	}
	
	private void buildOpBtns() {
		
	}
	
	private void buildConfirmBtns() {
		
	}

	public void tableRefresh(ArrayList<InventoryItem> items) {
		// Clears the existing table of all its data
		tableModel.setRowCount(0);
		
		// Begins writing from the beginning all existing data
		for (InventoryItem item: items) {
			Object[] row = {
					item.getID(),
					item.getName(),
					item.getQuantity(),
					item.getPrice()
			};
			tableModel.addRow(row);
		}
	}

/*
 * String filename = "inventory.txt";
 * 
 *//**
	 * Creates a fresh inventory for the whole program to use. If the file already
	 * exists, it will use this inventory object to read into it. If the file does
	 * NOT exist, it will simply create the inventory and proceed with the rest of
	 * the program
	 */
/*
 * Inventory inventory = retrieveFile(filename);
 * 
 * int choiceNo = 0; while (choiceNo != 5) { choiceNo =
 * readInt("Welcome to the inventory management system!\n\n" +
 * "Please choose any of the options you wish to run:\n" + "1. Add a new item\n"
 * + "2. View your current inventory\n" + "3. Update an existing item\n" +
 * "4. Delete an existing item\n" + "5. Quit\n"); switch(choiceNo) { case 1:
 * addItem(inventory); break; case 2: inventory.displayInventory(); break; case
 * 3: updateInventory(inventory); break; case 4: removeItem(inventory); break;
 * case 5: fileExport(inventory); System.exit(0); default:
 * 
 * } }
 * 
 *//**
	 * Reads an integer from user input with validation
	 * 
	 * @param prompt     String message to prompt user for input
	 * @param allowEmpty Whether to accept empty input (returns 0)
	 * @return Valid integer value (≥ 0)
	 */
/*
 * public static int readInt(String prompt) { while (true) {
 * System.out.println(prompt); String entry = keyboard.nextLine().trim();
 * 
 * // Handle empty input if (entry.isEmpty()) { return 0; }
 * 
 * try { int value = Integer.parseInt(entry); if (value <= 0) {
 * System.out.println("Value cannot be negative. Please enter a number ≥ 0"); }
 * else { return value; } } catch (NumberFormatException e) {
 * System.out.println("Invalid integer format. Please enter a whole number ≥ 0"
 * ); } } }
 * 
 *//**
	 * Reads a double from user input with validation
	 * 
	 * @param prompt String message for user's input
	 * @return Valid float value (≥ 0.0)
	 */
/*
 * public static double readDouble(String prompt) { while (true) {
 * System.out.println(prompt); String entry = keyboard.nextLine().trim();
 * 
 * // Handle empty input if (entry.isEmpty()) { return 0; }
 * 
 * try { double value = Double.parseDouble(entry); if (value <= 0) {
 * System.out.println("Value cannot be negative. Please enter a number ≥ 0"); }
 * else { return value; } } catch (NumberFormatException e) {
 * System.out.println("Invalid number format. Please enter a numeric value ≥ 0"
 * ); } } } 
 */
}