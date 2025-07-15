package view.common.admin;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

/**
 * 
 */
public class AdminConfirmButtons extends JFrame {
	/**
	 * Explicitly to remove the warning flag for the InventoryView class
	 */
	private static final long serialVersionUID = 1L;
	
	// Specific Fields
	private JButton btnClear, btnSubmit, btnBack;
	private JLabel lblID, lblFirstName, lblLastName, lblEmail, lblPassword;
	private JTextField txtID, txtFirstName, txtLastName, txtEmail, txtPassword;
	
	// Field groupings by panel
	private JPanel southPanel, confirmPanel;
	
	// Individual, iterable lists for specific field groups
	private ArrayList<JTextField> allFields, detailFields;
	private ArrayList<JButton> confirmButtons;
	private ArrayList<JLabel> labels;
	
	// Getters and Setters
	/**
	 * @return the btnClear
	 */
	public JButton getBtnClear() {
		return btnClear;
	}

	/**
	 * @param btnClear the btnClear to set
	 */
	public void setBtnClear(JButton btnClear) {
		this.btnClear = btnClear;
	}

	/**
	 * @return the btnSubmit
	 */
	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	/**
	 * @param btnSubmit the btnSubmit to set
	 */
	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	/**
	 * @return the btnBack
	 */
	public JButton getBtnBack() {
		return btnBack;
	}

	/**
	 * @param btnBack the btnBack to set
	 */
	public void setBtnBack(JButton btnBack) {
		this.btnBack = btnBack;
	}

	/**
	 * @return the txtID
	 */
	public JTextField getTxtID() {
		return txtID;
	}

	/**
	 * @param txtID the txtID to set
	 */
	public void setTxtID(JTextField txtID) {
		this.txtID = txtID;
	}

	/**
	 * @return the txtFirstName
	 */
	public JTextField getTxtFirstName() {
		return txtFirstName;
	}

	/**
	 * @param txtFirstName the txtFirstName to set
	 */
	public void setTxtFirstName(JTextField txtFirstName) {
		this.txtFirstName = txtFirstName;
	}

	/**
	 * @return the txtLastName
	 */
	public JTextField getTxtLastName() {
		return txtLastName;
	}

	/**
	 * @param txtLastName the txtLastName to set
	 */
	public void setTxtLastName(JTextField txtLastName) {
		this.txtLastName = txtLastName;
	}

	/**
	 * @return the txtEmail
	 */
	public JTextField getTxtEmail() {
		return txtEmail;
	}

	/**
	 * @param txtEmail the txtEmail to set
	 */
	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	/**
	 * @return the txtPassword
	 */
	public JTextField getTxtPassword() {
		return txtPassword;
	}

	/**
	 * @param txtPassword the txtPassword to set
	 */
	public void setTxtPassword(JTextField txtPassword) {
		this.txtPassword = txtPassword;
	}

	/**
	 * @return the southPanel
	 */
	public JPanel getSouthPanel() {
		return southPanel;
	}

	/**
	 * @param southPanel the southPanel to set
	 */
	public void setSouthPanel(JPanel southPanel) {
		this.southPanel = southPanel;
	}

	/**
	 * @return the confirmPanel
	 */
	public JPanel getConfirmPanel() {
		return confirmPanel;
	}

	/**
	 * @param confirmPanel the confirmPanel to set
	 */
	public void setConfirmPanel(JPanel confirmPanel) {
		this.confirmPanel = confirmPanel;
	}

	/**
	 * @return the allFields
	 */
	public ArrayList<JTextField> getAllFields() {
		return allFields;
	}

	/**
	 * @param allFields the allFields to set
	 */
	public void setAllFields(ArrayList<JTextField> allFields) {
		this.allFields = allFields;
	}

	/**
	 * @return the detailFields
	 */
	public ArrayList<JTextField> getDetailFields() {
		return detailFields;
	}

	/**
	 * @param detailFields the detailFields to set
	 */
	public void setDetailFields(ArrayList<JTextField> detailFields) {
		this.detailFields = detailFields;
	}

	/**
	 * @return the confirmButtons
	 */
	public ArrayList<JButton> getConfirmButtons() {
		return confirmButtons;
	}

	/**
	 * @param confirmButtons the confirmButtons to set
	 */
	public void setConfirmButtons(ArrayList<JButton> confirmButtons) {
		this.confirmButtons = confirmButtons;
	}

	// Constructor
	public AdminConfirmButtons() {
		buildConfirmPanel();
		add(southPanel, BorderLayout.SOUTH);
	}
	
	// Custom Methods	
	private void buildConfirmPanel() {
		// Confirmation Panel Initialization
		confirmPanel = new JPanel();
		confirmPanel.setLayout(new BoxLayout(confirmPanel, BoxLayout.Y_AXIS));
		
		// Label Initialization
		lblID = new JLabel("ID: ");
		lblFirstName = new JLabel("First Name: ");
		lblLastName = new JLabel("Last Name: ");
		lblEmail = new JLabel("Email: ");
		lblPassword = new JLabel("Password: ");
		
		// Text Field Initialization
		txtID = new JTextField(3);
		txtFirstName = new JTextField(10);
		txtLastName = new JTextField(10);
		txtEmail = new JTextField(10);
		txtPassword = new JTextField(10);
		
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
		labels = new ArrayList<>(Arrays.asList(lblID, lblFirstName, lblLastName, lblEmail, lblPassword));
		allFields = new ArrayList<>(Arrays.asList(txtID, txtFirstName, txtLastName, txtEmail, txtPassword));
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
}