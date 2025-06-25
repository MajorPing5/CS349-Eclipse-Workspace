package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class InventoryView extends JFrame {
	/**
	 * Explicitly to remove the warning flag for the InventoryView class
	 */
	private static final long serialVersionUID = 1L;
	
	// Window Parameters
	private static final int WINDOW_X = 800;
	private static final int WINDOW_Y = 600;
	private static final String NAME = "Inventory Management";
	
	// Specific Fields
	private JButton btnAdd, btnClear, btnUpdate, btnDelete, btnSubmit, btnBack;
	private JTable catalog;
	private DefaultTableModel tableModel;
	private JLabel lblID, lblName, lblQuantity, lblPrice;
	private JTextField txtID, txtName, txtQuantity, txtPrice;
	
	// Field groupings by panel
	private JPanel greetPanel,invPanel, southPanel, confirmPanel, opPanel;
	
	private boolean isMainPanel;
	
	// Individual, iterable lists for specific field groups
	private ArrayList<JTextField> allFields, detailFields;
	private ArrayList<JButton> opButtons, confirmButtons;
	private ArrayList<JLabel> labels;
	
	public enum InventoryState {
		ID_ON,
		ID_OFF,
		DELETE,
		DISABLE
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
		
		isMainPanel = true;
	}

	// Getters and Setters
	/**
	 * @return the btnAdd
	 */
	public JButton getBtnAdd() {
		return btnAdd;
	}

	/**
	 * @return the btnClear
	 */
	public JButton getBtnClear() {
		return btnClear;
	}

	/**
	 * @return the btnUpdate
	 */
	public JButton getBtnUpdate() {
		return btnUpdate;
	}

	/**
	 * @return the btnDelete
	 */
	public JButton getBtnDelete() {
		return btnDelete;
	}

	/**
	 * @return the btnSubmit
	 */
	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	/**
	 * @return the btnBack
	 */
	public JButton getBtnBack() {
		return btnBack;
	}

	/**
	 * @return the txtID
	 */
	public JTextField getTxtID() {
		return txtID;
	}
	
	/**
	 * @param ID the string value of txtID to set
	 */
	public void setTxtID(String ID) {
		this.txtID.setText(ID);
	}

	/**
	 * @return the txtName
	 */
	public JTextField getTxtName() {
		return txtName;
	}
	
	/**
	 * @param name the string value of txtName to set
	 */
	public void setTxtName(String name) {
		this.txtName.setText(name);
	}

	/**
	 * @return the txtQuantity
	 */
	public JTextField getTxtQuantity() {
		return txtQuantity;
	}
	
	/**
	 * @param quantity the string value of txtQuantity to set
	 */
	public void setTxtQuantity(String quantity) {
		this.txtQuantity.setText(quantity);
	}

	/**
	 * @return the txtPrice
	 */
	public JTextField getTxtPrice() {
		return txtPrice;
	}
	
	/**
	 * @param price the string value of txtPrice to set
	 */
	public void setTxtPrice(String price) {
		this.txtPrice.setText(price);
	}

	/**
	 * @return the ALL_FIELDS
	 */
	public ArrayList<JTextField> getAllFields() {
		return allFields;
	}

	/**
	 * @return the DETAIL_FIELDS
	 */
	public ArrayList<JTextField> getDetailFields() {
	    return detailFields;
	}
	
	public void setCloseHandler(Runnable handler) {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				handler.run();
				dispose();
			}
		});
	}
	
	private void configMainPanel() {
		// Creates an appropriate title
		setTitle(NAME);
		
		// Defines the window size
		setSize(WINDOW_X, WINDOW_Y);
		
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
	    tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Quantity", "Price"}, 0);
	    
	    catalog = new JTable(tableModel);
	    JScrollPane scrollPane = new JScrollPane(catalog);
	    
	    // Add table to the panel
	    invPanel.add(scrollPane, BorderLayout.CENTER);
	}
	
	private void buildBtnOpPanel() {
		opPanel = new JPanel(new GridLayout(1, 3, 10, 10));
		
		// Button Initialization
		btnDelete = new JButton("Delete");
		btnUpdate = new JButton("Update");
		btnAdd = new JButton("Add");
				
		// ArrayList Initialization for these buttons to be group referenced at any time
		opButtons = new ArrayList<>(Arrays.asList(btnDelete, btnUpdate, btnAdd));
		
		opButtons.forEach(button -> opPanel.add(button));
		southPanel = new JPanel();
		southPanel.add(opPanel);
	}
	
	private void buildConfirmPanel() {
		// Confirmation Panel Initialization
		confirmPanel = new JPanel();
		confirmPanel.setLayout(new BoxLayout(confirmPanel, BoxLayout.Y_AXIS));
		
		// Label Initialization
		lblID = new JLabel("ID: ");
		lblName = new JLabel("Name: ");
		lblQuantity = new JLabel("Quantity: ");
		lblPrice = new JLabel("Price: ");
		
		// Text Field Initialization
		txtID = new JTextField(3);
		txtName = new JTextField(10);
		txtQuantity = new JTextField(5);
		txtPrice = new JTextField(5);
		
		// Enter Key Listener for Text Fields
		KeyAdapter enterKeyAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnSubmit.doClick();
				}
			}
		};
		
		// Dedicated ArrayList initialization
		labels = new ArrayList<>(Arrays.asList(lblID, lblName, lblQuantity, lblPrice));
		allFields = new ArrayList<>(Arrays.asList(txtID, txtName, txtQuantity, txtPrice));
		detailFields = new ArrayList<>(allFields);
		detailFields.remove(txtID);
		
		// Attaches button listener across all text fields
		for (JTextField field : allFields) {
			field.addKeyListener(enterKeyAdapter);;
		}
				
		// Text and Fields Panel Declaration & Initialization
		JPanel fieldsPanel = new JPanel();
		fieldsPanel.add(Box.createHorizontalGlue());
		
		// Row 1 - Pairs Label text with appropriate Text field
		for (int i = 0; i < allFields.size(); i++) {
			fieldsPanel.add(labels.get(i));
			fieldsPanel.add(allFields.get(i));
			fieldsPanel.add(Box.createHorizontalGlue());
		}
		
		//Row 2 - Initializes the Confirmation Buttons
		btnBack = new JButton("Back");
		btnClear = new JButton("Clear");
		btnSubmit = new JButton("Submit");		
		
		// Confirmation Buttons Panel Declaration & Initialization
		JPanel btnsPanel = new JPanel();

		// Declares all Confirmation Buttons
		confirmButtons = new ArrayList<>(Arrays.asList(btnBack, btnClear, btnSubmit));
		confirmButtons.forEach(obj -> btnsPanel.add(obj));
		
		confirmPanel.add(fieldsPanel);
		confirmPanel.add(btnsPanel);
		// Does not assign to active panel since this is to be accessed later
	}

	private void toggleFieldsEdit(List<JTextField> fields, boolean status) {
		fields.forEach(field -> {
				field.setEditable(status);
				
				if (status) {
					// Standard Black Text on White Background
					field.setForeground(Color.BLACK);
					field.setBackground(Color.WHITE);
				} else {
					// Lightly grayed out Text Box with darker gray Text
					field.setForeground(Color.GRAY);
					field.setBackground(Color.LIGHT_GRAY);
				}
		});
	};
	
	/**
	 * Generates a new table using a generic data extraction approach
	 * @param items List of items of any type
	 * @param rowMapper Function to convert items to table row data
	 */
	public <T> void newTable(ArrayList<T> items, Function<T, Object[]> rowMapper) {
	    // Clear existing table data
	    if (tableModel.getRowCount() > 0) {
	        tableModel.setRowCount(0);
	    }
	    
	    // Populate table using the mapper function
	    for (T item : items) {
	        tableModel.addRow(rowMapper.apply(item));
	    }
	}
	
	/**
	 * Actively alters the editability of given text fields provided the desired state
	 * @param state The enum status of what to turn on/off (ID_ON, ID_OFF, other)
	 */
	public void setState(InventoryState state) {
		switch (state) {
		case ID_ON:
			if (!txtID.isEditable()) {
				txtID.setForeground(Color.BLACK);
				txtID.setBackground(Color.WHITE);
				txtID.setEditable(true);
			}			
			toggleFieldsEdit(detailFields, false);
			break;
			
		case ID_OFF:
			if (txtID.isEditable()) { txtID.setEditable(false);	}
			toggleFieldsEdit(detailFields, true);
			break;
			
		case DELETE:
			toggleFieldsEdit(allFields, false);
			allFields.forEach(field -> {
				field.setForeground(Color.RED);
			});
			break;
			
		default:
			toggleFieldsEdit(allFields, false);
		}
	}
	
	public void swapSouthPanel() {
		southPanel.removeAll();
		
		// Toggles the boolean for window tracking between the Operations Button vs Confirmation Buttons
		isMainPanel = !isMainPanel;
		
		if (isMainPanel) {
			clearAllFields();
			southPanel.add(opPanel);
		} else {
			southPanel.add(confirmPanel);
		}
		
		southPanel.revalidate();
		southPanel.repaint();
	}
	
	public void clearAllFields() {
		allFields.stream()
		.forEach(field -> {
			if (!field.getText().isBlank())
				field.setText("");
		});
	}
	
	public void clearEditibleFields() {
		allFields.stream()
		.filter(JTextField::isEditable)
		.forEach(field -> {
			if (!field.getText().isBlank())
				field.setText("");
		});
	}
	
	public void clearDetailFields() {
		detailFields.stream()
		.forEach(field -> {
			if (!field.getText().isBlank())
				field.setText("");
		});
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
	public void failedEntry(String failType, ArrayList<String> testedFields) {
		switch (failType) {
		
			// By far the most-likely and widely applicable error that can take place
		case "Empty Field Detected:": 
			JOptionPane.showMessageDialog(null,
					"Empty Field Detected: " + String.join(", ", testedFields),
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
					"Something just went HORRIBLY wrong for you to see this. Important stuff: " + failType +
					" with field(s) " + testedFields,
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}