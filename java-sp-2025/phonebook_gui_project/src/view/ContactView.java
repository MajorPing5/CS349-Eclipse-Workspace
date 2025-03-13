package view;

import model.*;

import javax.swing.*;
import java.awt.*;

public class ContactView extends JFrame{
	private JButton btnAdd, btnClear, btnUpdate, btnRemove;
	private JList<ContactModel> lstContactList;
	private DefaultListModel<ContactModel> contactListModel;
	
	private JPanel formPanel, listPanel;

	private JTextField txtFirstName, txtLastName, txtPhoneNumber;
	
	public ContactView() {
		
		setTitle("Phonebook");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		buildFormPanel();
		
		buildListPanel();
		
		// add form panel to the window
		add(formPanel, BorderLayout.NORTH);
		
		// add list panel to the window
		add(listPanel, BorderLayout.CENTER);
		//add(new JScrollPane(lstContactList));
		
		// add the remove button to the south
		btnRemove = new JButton("Delete");
		add(btnRemove, BorderLayout.SOUTH);
	}
	
	private void buildFormPanel() {
		formPanel = new JPanel(new GridLayout(4,2));
		formPanel.add(new JLabel("First name"));
		txtFirstName = new JTextField();
		formPanel.add(txtFirstName);
		
		formPanel.add(new JLabel("Last name"));
		txtLastName = new JTextField();
		formPanel.add(txtLastName);
		
		formPanel.add(new JLabel("Phone number"));
		txtPhoneNumber = new JTextField();
		formPanel.add(txtPhoneNumber);
		
		btnAdd = new JButton("Add");
		formPanel.add(btnAdd);
		
		btnClear = new JButton("Clear");
		formPanel.add(btnClear);		
	}
	
	private void buildListPanel() {
		contactListModel = new DefaultListModel<ContactModel>();
		lstContactList = new JList<ContactModel>(contactListModel);
		
		listPanel = new JPanel();
		listPanel.add(lstContactList);
	}

	/**
	 * @return the txtFirstName value
	 */
	public JTextField getTxtFirstName() {
		return txtFirstName;
	}

	/**
	 * @param text the string value of txtFirstName to set
	 */
	public void setTxtFirstName(String text) {
		this.txtFirstName.setText(text);
	}

	/**
	 * @return the txtLastName
	 */
	public JTextField getTxtLastName() {
		return txtLastName;
	}

	/**
	 * @param text the string value of txtLastName to set
	 */
	public void setTxtLastName(String text) {
		this.txtLastName.setText(text);
	}

	/**
	 * @return the txtPhoneNumber
	 */
	public JTextField getTxtPhoneNumber() {
		return txtPhoneNumber;
	}

	/**
	 * @param text the string value of txtPhoneNumber to set
	 */
	public void setTxtPhoneNumber(String text) {
		this.txtPhoneNumber.setText(text);
	}

	/**
	 * @return the btnAdd
	 */
	public JButton getBtnAdd() {
		return btnAdd;
	}

	/**
	 * @param btnAdd the btnAdd to set
	 */
	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

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
	 * @return the btnUpdate
	 */
	public JButton getBtnUpdate() {
		return btnUpdate;
	}

	/**
	 * @param btnUpdate the btnUpdate to set
	 */
	public void setBtnUpdate(JButton btnUpdate) {
		this.btnUpdate = btnUpdate;
	}

	/**
	 * @return the btnRemove
	 */
	public JButton getBtnRemove() {
		return btnRemove;
	}

	/**
	 * @param btnRemove the btnRemove to set
	 */
	public void setBtnRemove(JButton btnRemove) {
		this.btnRemove = btnRemove;
	}

	/**
	 * @return the lstContactList
	 */
	public JList<ContactModel> getLstContactList() {
		return lstContactList;
	}

	/**
	 * @param lstContactList the lstContactList to set
	 */
	public void setLstContactList(JList<ContactModel> lstContactList) {
		this.lstContactList = lstContactList;
	}

	/**
	 * @return the contactListModel
	 */
	public DefaultListModel<ContactModel> getContactListModel() {
		return contactListModel;
	}

	/**
	 * @param contactListModel the contactListModel to set
	 */
	public void setContactListModel(DefaultListModel<ContactModel> contactListModel) {
		this.contactListModel = contactListModel;
	}

	/**
	 * @return the formPanel
	 */
	public JPanel getFormPanel() {
		return formPanel;
	}

	/**
	 * @param formPanel the formPanel to set
	 */
	public void setFormPanel(JPanel formPanel) {
		this.formPanel = formPanel;
	}

	/**
	 * @return the listPanel
	 */
	public JPanel getListPanel() {
		return listPanel;
	}

	/**
	 * @param listPanel the listPanel to set
	 */
	public void setListPanel(JPanel listPanel) {
		this.listPanel = listPanel;
	}	
}
