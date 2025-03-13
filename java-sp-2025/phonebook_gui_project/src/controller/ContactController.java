package controller;

import model.*;
import view.*;

import java.awt.event.*;
import java.util.Map;
import java.util.HashMap;

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
				
				// Add empty field validations				
				

				try {
					String firstName = view.getTxtFirstName().getText();
					String lastName = view.getTxtLastName().getText();
					String phoneNumber = view.getTxtPhoneNumber().getText();
					

					// Add a new contact
					ContactModel newContact = new ContactModel(firstName, lastName, phoneNumber);
					new ContactDataModel().addContact(newContact);

					// update the view (contact list)
					view.getContactListModel().addElement(newContact);

					JOptionPane.showMessageDialog(null, "Button clicked");

				} catch (NullPointerException err) {

					JOptionPane.showMessageDialog(null, "A field has been left empty");

				}
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

		// "clear" button listener, MUST be completed by Wednesday
		view.getBtnClear().addActionListener(new ActionListener() {
			// listener information
			public void actionPerformed(ActionEvent e) {
				/**
				 * Creates a temporary map for all fields to be removed.
				 * Each pair consists of the following info (in order): (field name, operation to run) 
				 */
				Map<String, Runnable> txtClear = new HashMap<>();
				txtClear.put("firstName", () -> view.setTxtFirstName(null));
				txtClear.put("lastName", () -> view.setTxtLastName(null));
				txtClear.put("phoneNumber", () -> view.setTxtPhoneNumber(null));

				// Ensures that ALL modules that need to be cleared can and will be, with easy modularity
				txtClear.values().forEach(Runnable::run);
			}
		});
	}
}
