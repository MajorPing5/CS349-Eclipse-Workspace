package view;

import javax.swing.*;
import javax.swing.table.*;

import model.InventoryItem;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;

public class InventoryView extends JFrame {
	/**
	 * Explicitly to remove the warning flag for the InventoryView class
	 */
	private static final long serialVersionUID = 1L;
	
	// Window Parameters
	private static final int WINDOW_X = 800;
	private static final int WINDOW_Y = 600;
	private static final String NAME = "Inventory Management";
	private static final int DEFAULT = JFrame.EXIT_ON_CLOSE;
	
	// Specific Fields
	private JButton btnAdd, btnClear, btnUpdate, btnDelete, btnSubmit, btnBack;
	private JTable catalog;
	private DefaultTableModel tableModel;
	private JTextField txtID, txtName, txtQuantity, txtPrice;
	private JLabel lblID, lblName, lblQuantity, lblPrice;
	
	// Field groupings by panel
	private JPanel greetPanel = new JPanel();
	private JPanel invPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private JPanel confirmPanel = new JPanel();
	private JPanel opPanel = new JPanel();
	
	// Individual, iterable lists for specific field groups
	private final List<JTextField> ALL_FIELDS = Collections.unmodifiableList(
			new ArrayList<>(Arrays.asList(txtID, txtName, txtQuantity, txtPrice))
			);
	
	private final List<JTextField> DETAIL_FIELDS = Collections.unmodifiableList(
			new ArrayList<>(Arrays.asList(txtName, txtQuantity, txtPrice))
			);
	
	private final List<JButton> OP_BUTTONS = Collections.unmodifiableList(
			new ArrayList<>(Arrays.asList(btnDelete, btnUpdate, btnAdd))
			);
	
	private final List<JButton> CONFIRM_BUTTONS = Collections.unmodifiableList(
			new ArrayList<>(Arrays.asList(btnBack, btnClear, btnSubmit))
			);
	
	private final List<JLabel> LABELS = Collections.unmodifiableList(
			new ArrayList<>(Arrays.asList(lblID, lblName, lblQuantity, lblPrice))
			);
	public enum InventoryState {
		ID_ON,
		ID_OFF
	}
	
	/**
	 * Class Constructor
	 */
	public InventoryView() {
		
		configMainPanel();
		buildGreetPanel();
		buildInitialTable();
		buildBtnOpPanel();
		buildConfirmPanel();
		
		add(greetPanel, BorderLayout.NORTH);
		add(invPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}
	
	private void configMainPanel() {
		// Creates an appropriate title
		setTitle(NAME);
		
		// Defines the window size
		setSize(WINDOW_X, WINDOW_Y);
		
		// Directly specifies the action necessary for the close button
		setDefaultCloseOperation(DEFAULT);
		
		// Creates a top-level BorderLayout manager
		setLayout(new BorderLayout());	
	}
	
	private void buildGreetPanel() {
		greetPanel = new JPanel();
		greetPanel.add(new JLabel("Welcome to the Inventory Management System"));
	}
	
	private void buildInitialTable() {
		invPanel = new JPanel(new BorderLayout());  // Use BorderLayout for full-area coverage
	    invPanel.setBorder(BorderFactory.createTitledBorder("Inventory Items"));
	    
	    // Create table and scroll pane
	    DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name", "Quantity", "Price"}, 0);
	    JTable catalog = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(catalog);
	    
	    // Add table to the panel
	    invPanel.add(scrollPane, BorderLayout.CENTER);
	}
	
	private void buildBtnOpPanel() {
		opPanel = new JPanel(new GridLayout(1, 3, 10, 10));
		OP_BUTTONS.forEach(button -> opPanel.add(button));
		southPanel.add(opPanel);
	}
	
	private void buildConfirmPanel() {
		confirmPanel = new JPanel(new GridLayout(2, 4, 10, 10));
		
		 
		// Row 1
		for (int i = 0; i < ALL_FIELDS.size(); i++) {
			confirmPanel.add(LABELS.get(i));
			confirmPanel.add(ALL_FIELDS.get(i));
		}
		
		//Row 2
		Map<String, Runnable> txtOpBtns = new LinkedHashMap<>();
		txtOpBtns.put("ID", () -> new JButton("Back"));
		txtOpBtns.put("Back", () -> new JButton("Clear"));
		txtOpBtns.put("Submit", () -> new JButton("Submit"));
		
		// Creates the confirmation buttons and immediately inserts them into confirm panel 
		txtOpBtns.values().forEach(Runnable::run);
		CONFIRM_BUTTONS.forEach(obj -> confirmPanel.add(obj));
	}

	private void toggleFieldsEdit(List<JTextField> fields, boolean status) {
		fields.forEach(field -> {
			if (field.isEditable()) {
				field.setEditable(status);
			};
		});
	};

	public void newTable(ArrayList<InventoryItem> items) {
		// Clears the existing table of all its data, if there is any
		if (tableModel.getRowCount() != 0) {
			tableModel.setRowCount(0);
		}
		
		// Begins writing from the beginning all existing data
		for (InventoryItem item : items) {
			Object[] row = {
					item.getID(),
					item.getName(),
					item.getQuantity(),
					item.getPrice()
			};
			tableModel.addRow(row);
		}
	}
	
	public void setState(InventoryState state) {
		switch (state) {
		case ID_ON:
			if (!txtID.isEditable()) { txtID.setEditable(true);	}			
			toggleFieldsEdit(DETAIL_FIELDS, false);
			break;
			
		case ID_OFF:
			if (txtID.isEditable()) { txtID.setEditable(false);	}
			toggleFieldsEdit(DETAIL_FIELDS, true);
			break;
			
		default:
			toggleFieldsEdit(ALL_FIELDS, false);
		}
	}
	
	public void clearEditibleFields() {
		ALL_FIELDS.stream()
		.filter(JTextField::isEditable)
		.forEach(field -> field.setText(""));
	}
	
	/**
	 * Reports a successful entry modification during runtime.
	 * Precon: ALL tested field(s) were proven to be valid;
	 * Postcon: A Success Message is displayed to the user, over the existing window
	 * @returns Success Message
	 */
	public void successEntry() {
		JOptionPane.showMessageDialog(null,
				"Inventory Table has been updated",
				"Success", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Reports the Error discovered during runtime.
	 * Precon: A tested field(s) was proven to be invalid;
	 * Postcon: An error message is displayed to the user, over the existing window	
	 * @param failType The string name of the error type
	 * @param testedFields A list of strings for all tested field(s)
	 * @returns Error Message
	 */
	public void failedEntry(String failType, List<String> testedFields) {
		switch (failType) {
		
			// By far the most-likely and widely applicable error that can take place
		case "Blank": 
			JOptionPane.showMessageDialog(null,
					"Missing: " + String.join(", ", testedFields),
					"Error", JOptionPane.ERROR_MESSAGE);
			break;

			// The following cases are for ID and Quantity fields-specific errors
		case "Not An Integer":
			JOptionPane.showMessageDialog(null,
					"Invalid Integer Entry: " + testedFields,
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
		case "Integer Domain Violation":
			JOptionPane.showMessageDialog(null,
					"Missing: " + String.join(", ", testedFields),
					"Error", JOptionPane.ERROR_MESSAGE);
			break;

			// This case is specific to an ID search error - assuming that the value was a valid int within proper domain
		case "Integer DNE":
			JOptionPane.showMessageDialog(null,
					"Your ID search does not exist. Check if you searched for the right ID value.",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;

			// The following cases are for Price field-specific errors
		case "Not A Float":
			JOptionPane.showMessageDialog(null,
					"Your Price entry MUST be of a numeric value.",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
		case "Float Domain Violation":
			JOptionPane.showMessageDialog(null,
					"Your Price entry can't be < $0.",
					"Error", JOptionPane.ERROR_MESSAGE);
			break;
			
			// Under NO circumstances should this EVER be displayed, but in the rare chance something critical happens...
		default:
			JOptionPane.showMessageDialog(null,
					"Something just went HORRIBLY wrong for you to see this.",
					"Error", JOptionPane.ERROR_MESSAGE);
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