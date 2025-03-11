package view;

import model.*;

import javax.swing.*;
import java.awt.*;

public class ContactView extends JFrame{
	private JTextField txtFirstName, txtLastName, txtPhoneNumber;
	private JButton btnAdd, btnClear, btnUpdate, btnRemove;
	private JList<ContactModel> lstContactList;
	private DefaultListModel<ContactModel> contactListModel;
	
	private JPanel formPanel, listPanel;
	
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

	public JTextField getTxtFirstName() {
		return txtFirstName;
	}

	public void setTxtFirstName(JTextField txtFirstName) {
		this.txtFirstName = txtFirstName;
	}

	public JTextField getTxtLastName() {
		return txtLastName;
	}

	public void setTxtLastName(JTextField txtLastName) {
		this.txtLastName = txtLastName;
	}

	public JTextField getTxtPhoneNumber() {
		return txtPhoneNumber;
	}

	public void setTxtPhoneNumber(JTextField txtPhoneNumber) {
		this.txtPhoneNumber = txtPhoneNumber;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JButton getBtnClear() {
		return btnClear;
	}

	public void setBtnClear(JButton btnClear) {
		this.btnClear = btnClear;
	}

	public JButton getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(JButton btnUpdate) {
		this.btnUpdate = btnUpdate;
	}

	public JButton getBtnRemove() {
		return btnRemove;
	}

	public void setBtnRemove(JButton btnRemove) {
		this.btnRemove = btnRemove;
	}

	public JList<ContactModel> getLstContactList() {
		return lstContactList;
	}

	public void setLstContactList(JList<ContactModel> lstContactList) {
		this.lstContactList = lstContactList;
	}

	public DefaultListModel<ContactModel> getContactListModel() {
		return contactListModel;
	}

	public void setContactListModel(DefaultListModel<ContactModel> contactListModel) {
		this.contactListModel = contactListModel;
	}

	public JPanel getFormPanel() {
		return formPanel;
	}

	public void setFormPanel(JPanel formPanel) {
		this.formPanel = formPanel;
	}

	public JPanel getListPanel() {
		return listPanel;
	}

	public void setListPanel(JPanel listPanel) {
		this.listPanel = listPanel;
	}
	
	
}
