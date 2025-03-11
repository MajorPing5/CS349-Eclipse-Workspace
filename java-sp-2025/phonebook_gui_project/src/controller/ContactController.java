package controller;

import model.*;
import view.*;

import java.awt.event.*;

import javax.swing.JOptionPane;

public class ContactController {
	
	private ContactView view;
	private ContactModel model;
	
	public ContactController(ContactView view, ContactModel model) {
		this.view = view;
		this.model = model;
		
		// "add" button listener
		view.getBtnAdd().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Adds empty field validations
				String firstName = view.getTxtFirstName().getText();
				String lastName = view.getTxtLastName().getText();
				String phoneNumber = view.getTxtPhoneNumber().getText();
				
				// Add a new contact
				ContactModel newContact = new ContactModel( firstName, lastName, phoneNumber);
				new ContactDataModel().addContact(newContact);
				
				// update the view (contact list)
				view.getContactListModel().addElement(newContact);
				
				JOptionPane.showMessageDialog(null, "Button clicked");
			}
		});
		
		// "remove" button listener
		view.getBtnRemove().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = view.getLstContactList().getSelectedIndex();
				
				if(selectedIndex != -1) {
					ContactModel contactToRemove = view.getContactListModel().getElementAt(selectedIndex);
					new ContactDataModel().removeContact(contactToRemove);
					
					view.getContactListModel().remove(selectedIndex);
				}
				else {
					JOptionPane.showMessageDialog(null, "No contact selected");
				}
			}
		});
		/**
		// "clear" button listener, MUST be completed by Wednesday
		view.getBtnClear().addActionListener(new ActionListener() {
			// listener information
		});
		 */
	}
}
