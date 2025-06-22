package controller;

import model.*;
import view.*;

import java.awt.event.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

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
				
				/**
				 * Creates a collection of pair entries between text fields and
				 * their trimming function that the user can interact with,
				 * allowing easier referencing of individual or collective
				 * fields when needed
				 */
				Map<String, String> txtField = new HashMap<>();
				txtField.put("firstName", view.getTxtFirstName().getText().trim());
				txtField.put("lastName", view.getTxtLastName().getText().trim());
				txtField.put("phoneNumber", view.getTxtPhoneNumber().getText().trim());
				
				// Designates an easy dynamic list for any and all fields left empty
				List<String> emptyFields = new ArrayList<>();
				
				/**
				 * Easily iterates through all fields to see if
				 * (removing all unnecessary spaces) any field is a real empty field
				 */
				txtField.forEach((field, value) -> {
					if(value.isEmpty()) {
						emptyFields.add(field);
					}
				});
				
				/**
				 * Only two options: No field is empty (then execute insertion),
				 * or ANY/ALL fields are empty and display what field(s) are null
				 */
				if (emptyFields.isEmpty()) {
					String firstName = view.getTxtFirstName().getText();
					String lastName = view.getTxtLastName().getText();
					String phoneNumber = view.getTxtPhoneNumber().getText();
					
					
					// Add a new contact
					ContactModel newContact = new ContactModel(firstName, lastName, phoneNumber);
					new ContactDataModel().addContact(newContact);
					
					// update the view (contact list)
					view.getContactListModel().addElement(newContact);
					
					JOptionPane.showMessageDialog(null, "Button clicked");					
				} else {
					JOptionPane.showMessageDialog(view,
							"Missing: " + String.join(", ", emptyFields),
							"Error", JOptionPane.ERROR_MESSAGE);
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
