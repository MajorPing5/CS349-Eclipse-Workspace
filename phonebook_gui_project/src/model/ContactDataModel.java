package model;

import java.util.ArrayList;

public class ContactDataModel {
	
	private ArrayList<ContactModel> contacts;
	
	public ContactDataModel() {
		contacts = new ArrayList<>();
	}
	
	/**
	 * Add method to add a new contact to the contact ArrayList
	 */
	public void addContact(ContactModel newContact) {
		contacts.add(newContact);
	}
	
	public void removeContact(ContactModel contactToRemove) {
		contacts.remove(contactToRemove);
	}
	
	public ArrayList<ContactModel> getContactList(){
		return contacts;
	}
}
